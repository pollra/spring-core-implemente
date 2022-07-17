package com.pollra.spring.framework.context;

import com.pollra.spring.framework.beans.annotate.Component;
import com.pollra.spring.framework.beans.definition.BeanDefinition;
import com.pollra.spring.framework.beans.definition.GenericBeanDefinition;
import com.pollra.spring.framework.beans.factory.BeansException;
import com.pollra.spring.framework.beans.factory.FactoryBean;
import com.pollra.spring.framework.context.register.BeanDefinitionRegister;
import com.pollra.spring.framework.context.register.FactoryBeanRegister;
import com.pollra.spring.framework.context.register.InMemoryBeanDefinitionRegister;
import com.pollra.spring.framework.context.register.InMemoryFactoryBeanRegister;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description read only application context
 **********************************************************************************************************************/
public class ReadOnlyApplicationContext implements ApplicationContext {

    private final BeanDefinitionRegister beanDefinitionRegister;
    private final FactoryBeanRegister factoryRegister;

    private final Map<String, Object> beanRegister;

    public ReadOnlyApplicationContext(Class<?> targetClass) throws ClassNotFoundException {
        beanDefinitionRegister = new InMemoryBeanDefinitionRegister();
        factoryRegister = new InMemoryFactoryBeanRegister();
        beanRegister = new HashMap<>();

        BeanComponentScanner beanComponentScanner = new BeanComponentScanner(targetClass);
        Set<Class<?>> scan = beanComponentScanner.scan();

        for (Class<?> aClass : scan) {
            Component component = aClass.getAnnotation(Component.class);
            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition(aClass);
            if(component.value().isEmpty()) {
                String beanClassName = genericBeanDefinition.getBeanClassName();
                beanDefinitionRegister.add(beanClassName, genericBeanDefinition);
            } else {
                beanDefinitionRegister.add(component.value(), genericBeanDefinition);
            }
        }
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return beanRegister.get(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        Object object = beanRegister.get(name);
        if(Objects.nonNull(object)) {
            return requiredType.cast(object);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name, requiredType);
        String factoryBeanName = beanDefinition.getFactoryBeanName();
        if( ! beanDefinition.hasConstructorArgumentValues()) {
            FactoryBean factoryBean = factoryRegister.get(factoryBeanName);
            T classInstance = requiredType.cast(factoryBean.getInstance(beanDefinition));
            beanRegister.put(name, classInstance);
            return classInstance;
        }

        BeanDefinition[] innerBeanDefinitions = beanDefinition.getConstructorArgumentValues();
        Object[] constructorArguments = new Object[innerBeanDefinitions.length];
        int index = 0;
        for (BeanDefinition innerBeanDefinition : innerBeanDefinitions) {
            String beanClassName = innerBeanDefinition.getBeanClassName();
            try {
                Class<?> aClass = Class.forName(beanClassName);
                Object persistInstance = getBean(aClass.getSimpleName(), aClass);
                constructorArguments[index++] = persistInstance;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return getBean(name, requiredType, constructorArguments);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType, Object... objects) {
        BeanDefinition beanDefinition = getBeanDefinition(name, requiredType);
        Constructor constructor = beanDefinition.getConstructor();
        Object newInstance = null;
        try {
            newInstance = constructor.newInstance(objects);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return (T) newInstance;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        String simpleName = requiredType.getSimpleName();
        return getBean(simpleName, requiredType);
    }

    @Override
    public boolean containsBean(String name) {
        return beanRegister.containsKey(name);
    }

    private BeanDefinition getBeanDefinition(String beanName, Class requiredType) {
        BeanDefinition beanDefinition = beanDefinitionRegister.get(beanName);
        if(Objects.isNull(beanDefinition)) {
            String simpleName = requiredType.getSimpleName();
            beanDefinition = beanDefinitionRegister.get(simpleName);
        }
        if(Objects.isNull(beanDefinition)) {
            throw new BeansException();
        }
        return beanDefinition;
    }
}
