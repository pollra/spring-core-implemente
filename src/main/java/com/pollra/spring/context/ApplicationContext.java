package com.pollra.spring.context;

import com.pollra.spring.Application;
import com.pollra.spring.core.annotation.Component;
import com.pollra.spring.core.bean.BeanDefinition;
import com.pollra.spring.core.bean.DefaultBeanDefinition;
import com.pollra.spring.core.scanner.BeanScanner;
import com.pollra.spring.core.util.BeanNameUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @since       2022.07.23
 * @author      pollra
 * @description application context
 **********************************************************************************************************************/
public class ApplicationContext implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions;
    private Map<String, Object> beanRegister;

    public ApplicationContext() {
        this.beanDefinitions = new HashMap<>();
        this.beanRegister = new HashMap<>();
    }

    public void refresh() {
        beanDefinitions.clear();
        beanRegister.clear();
        Class<? extends Annotation>[] annotations = new Class[]{Component.class};
        Set<Class<?>> scan = BeanScanner.scan(annotations, Application.class);
        System.out.println("scan = " + scan);

        for (Class<?> candidateBean : scan) {
            BeanDefinition beanDefinition = new DefaultBeanDefinition(candidateBean);
            beanDefinitions.put(beanDefinition.getBeanName(), beanDefinition);
        }
    }

    @Override
    public Object getBean(String name) {
        String beanName = BeanNameUtil.getName(name);
        if(beanRegister.containsKey(beanName)) {
            return beanRegister.get(beanName);
        } else {
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if(Objects.isNull(beanDefinition)) {
                throw new BeansException("bean definition is not found");
            }
            Class<?>[] requiredDependencies = beanDefinition.getDependencies();
            Object[] constructorArguments = new Object[requiredDependencies.length];
            for (int i = 0; i < requiredDependencies.length; i++) {
                String argumentBeanName = BeanNameUtil.getName(requiredDependencies[i]);
                constructorArguments[i] = getBean(argumentBeanName);
            }
            return getBean(beanDefinition.getOriginalClass(), constructorArguments);
        }
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        String beanName = BeanNameUtil.getName(name);
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if(Objects.isNull(beanDefinition)) {
            throw new BeansException("bean definition is not found");
        }
        Class<?>[] dependencies = beanDefinition.getDependencies();
        Object[] instanceOfDependency = new Object[dependencies.length];
        for (int i = 0; i < dependencies.length; i++) {
            String simpleName = dependencies[i].getSimpleName();
            String dependencyBeanName = BeanNameUtil.getName(simpleName);
            instanceOfDependency[i] = getBean(dependencyBeanName);
        }
        return getBean(requiredType, instanceOfDependency);
    }

    @Override
    public <T> T getBean(Class<T> requiredTpe, Object... args) throws BeansException {
        String beanName = BeanNameUtil.getName(requiredTpe.getSimpleName());
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if(Objects.isNull(beanDefinition)) {
            throw new BeansException("bean definition is not found");
        }
        Constructor<?> beanConstructor = beanDefinition.getBeanConstructor();
        try {
            return (T) beanConstructor.newInstance(args);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsBean(String name) {
        String beanName = BeanNameUtil.getName(name);
        Object instance = beanRegister.get(beanName);
        return instance != null;
    }
}
