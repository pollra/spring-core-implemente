package com.pollra.spring;

import com.pollra.spring.core.annotation.Bean;
import com.pollra.spring.core.annotation.Component;
import com.pollra.spring.core.beans.BeanComponentDefinition;
import com.pollra.spring.core.beans.BeanDefinition;
import com.pollra.spring.core.beans.DefaultBeanDefinition;
import com.pollra.spring.dummy.DummyName;
import com.pollra.spring.dummy.DummyObject;
import com.pollra.spring.dummy.DummySimpleService;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @since       2022.06.26
 * @author      pollra
 * @description application
 **********************************************************************************************************************/
public class Application {
    private static Map<String, BeanComponentDefinition> beanDefinitions = new HashMap<>();

    public static void main(String[] args) {

        Map<String, Set<Class<?>>> candidateBeans = new HashMap<>();

        System.out.println("current package path = " + Application.class.getPackage());

        Reflections reflections = new Reflections(Application.class.getPackageName(),
 Scanners.TypesAnnotated);
        Set<Class<?>> findComponentClasses = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> findComponentClass : findComponentClasses) {

            String currentComponentClassTypeName = findComponentClass.getTypeName();
            Component annotation = findComponentClass.getAnnotation(Component.class);
            String currentComponentBeanName = annotation.value();

            String beanName = "";
            if (currentComponentBeanName.length() > 0) {
                beanName = currentComponentBeanName;
            } else {
                beanName = findComponentClass.getTypeName();
            }

            BeanDefinition beanDefinition = new DefaultBeanDefinition();
            beanDefinition.setBeanClassName(currentComponentClassTypeName);
            Class<?> superclass = findComponentClass.getSuperclass();
            beanDefinition.setParentName(superclass.getTypeName());

            BeanComponentDefinition beanComponentDefinition = new BeanComponentDefinition(beanDefinition, beanName);
            Constructor<?>[] constructors = findComponentClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                for (Parameter parameter : constructor.getParameters()) {
                    BeanDefinition beanComponentInnerDependency = new DefaultBeanDefinition();
                    beanComponentInnerDependency.setBeanClassName(parameter.getType().getTypeName());
                    BeanComponentDefinition componentDefinition = new BeanComponentDefinition(beanComponentInnerDependency, parameter.getType().getTypeName());
                    beanComponentDefinition.addInnerBeanDefinition(componentDefinition);
                }
            }

            beanDefinitions.put(beanName, beanComponentDefinition);
        }

        System.out.println("beanDefinitions = " + beanDefinitions);


        DummySimpleService instance = (DummySimpleService ) createInstance(DummySimpleService.class);
        DummyObject object = DummyObject.builder()
                .name(DummyName.builder()
                        .firstName("Seunggeun")
                        .lastName("Lee")
                        .build())
                .age(30)
                .build();
        instance.save(object);

        DummyObject dummyObject = instance.get(1L);
        System.out.println("dummyObject = " + dummyObject);
    }

    public static Object createInstance(Class<?> target) {
        String typeName = target.getTypeName();
        Component annotation = target.getAnnotation(Component.class);
        String beanName = annotation.value();

        BeanComponentDefinition beanComponentDefinition;
        if(beanName.length() > 0 && beanDefinitions.containsKey(beanName)) {
            beanComponentDefinition = beanDefinitions.get(beanName);
        } else if(beanDefinitions.containsKey(typeName)) {
            beanComponentDefinition = beanDefinitions.get(typeName);
        } else {
            throw new RuntimeException("Bean 정보를 찾을 수 없습니다.");
        }

        try {
            return recurstionCreate(beanComponentDefinition);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object recurstionCreate(BeanComponentDefinition beanComponentDefinition) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BeanDefinition beanDefinition = beanComponentDefinition.getBeanDefinition();
        String beanClassName = beanDefinition.getBeanClassName();
        Class<?> targetClass = Class.forName(beanClassName);
        if(beanComponentDefinition.getInnerBeanDefinition().size() == 0) {
            Constructor<?> constructor = targetClass.getConstructor();
            return constructor.newInstance();
        }

        Set<BeanComponentDefinition> innerBeanDefinition = beanComponentDefinition.getInnerBeanDefinition();
        Map<BeanComponentDefinition, Object> instanceMap = new HashMap<>();
        Class<?>[] innerDependencies = new Class[innerBeanDefinition.size()];
        int index = 0;
        for (BeanComponentDefinition componentDefinition : innerBeanDefinition) {
            Object innerDependency = recurstionCreate(componentDefinition);
            instanceMap.put(componentDefinition, innerDependency);
            innerDependencies[index++] = Class.forName(componentDefinition.getBeanDefinition().getBeanClassName());
        }
        Constructor<?> constructor = targetClass.getConstructor(innerDependencies);

        Object[] objects = instanceMap.values().stream().toArray();
        return constructor.newInstance(objects);
    }
}
/*
beanDefinitions =
{=
[
    BeanComponentDefinition(
        beanDefinition=DefaultBeanDefinition(parentName=java.lang.Object, beanClassName=com.pollra.spring.dummy.DummySimpleService),
        beanName=com.pollra.spring.dummy.DummySimpleService,
        innerBeanDefinition=[
            DefaultBeanDefinition(parentName=null, beanClassName=com.pollra.spring.dummy.DummySimpleRepository)
        ]
    ),
    BeanComponentDefinition(
        beanDefinition=DefaultBeanDefinition(parentName=java.lang.Object, beanClassName=com.pollra.spring.dummy.DummySimpleRepository),
        beanName=com.pollra.spring.dummy.DummySimpleRepository,
        innerBeanDefinition=[]
    )
]
}

 */

/*
beanDefinitions = {
    com.pollra.spring.dummy.DummySimpleService=BeanComponentDefinition(
        beanDefinition=DefaultBeanDefinition(parentName=java.lang.Object, beanClassName=com.pollra.spring.dummy.DummySimpleService),
        beanName=com.pollra.spring.dummy.DummySimpleService,
        innerBeanDefinition=[
            DefaultBeanDefinition(parentName=null, beanClassName=com.pollra.spring.dummy.DummySimpleRepository)
        ]
    ),
    com.pollra.spring.dummy.DummySimpleRepository=BeanComponentDefinition(
        beanDefinition=DefaultBeanDefinition(parentName=java.lang.Object, beanClassName=com.pollra.spring.dummy.DummySimpleRepository),
        beanName=com.pollra.spring.dummy.DummySimpleRepository,
        innerBeanDefinition=[]
    )
}

 */