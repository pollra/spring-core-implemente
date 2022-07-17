package com.pollra.spring.framework.beans.factory;

import com.pollra.spring.framework.beans.definition.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description generic factory bean
 **********************************************************************************************************************/
public class SingleModelFactoryBean <T> extends AbstractFactoryBean<T> {

    @Override
    public T getInstance(BeanDefinition beanDefinition) {
        System.out.println("create new instance");
        String beanClassName = beanDefinition.getBeanClassName();
        if( ! beanDefinition.hasConstructorArgumentValues()) {
            try {
                System.out.println("beanClassName = " + beanClassName);
                Class<?> aClass = Class.forName(beanClassName);
                Constructor<?> constructor = aClass.getConstructor();
                Object o = constructor.newInstance();
                return (T) o;
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


        return null;
    }
}
