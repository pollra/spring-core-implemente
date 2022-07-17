package com.pollra.spring.framework.beans.definition;

import com.pollra.spring.framework.beans.factory.BeansException;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @since       2022.07.12
 * @author      pollra
 * @description generic bean definition
 **********************************************************************************************************************/
public class GenericBeanDefinition implements BeanDefinition {

    private String beanClassName;
    private boolean isPrimary;
    private String factoryBeanName;
    private BeanDefinition[] constructorArgumentValues;
    private Constructor constructor;

    private static final String PRIMARY_TYPE_FIRST = "java.";
    private static final String SINGLE_MODEL_FACTORY_BEAN = "SingleModelFactoryBean";

    public GenericBeanDefinition(Class<?> clazz) throws ClassNotFoundException {
        this.beanClassName = clazz.getName();
        String typeName = clazz.getTypeName();
        this.factoryBeanName = SINGLE_MODEL_FACTORY_BEAN;
        this.isPrimary = 0 == typeName.indexOf(PRIMARY_TYPE_FIRST);
        Constructor<?>[] constructors = clazz.getConstructors();
        int maxParameterSize = 0;
        Constructor maxSizeConstructor = null;
        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();
            if(maxParameterSize <= parameters.length) {
                maxParameterSize = parameters.length;
                maxSizeConstructor = constructor;
            }
        }
        this.constructor = maxSizeConstructor;
        Parameter[] parameters = maxSizeConstructor.getParameters();
        BeanDefinition[] innerBeanDefinitions = new BeanDefinition[maxParameterSize];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            innerBeanDefinitions[i] = new GenericBeanDefinition(type);
        }
        this.constructorArgumentValues = innerBeanDefinitions;
        System.out.println("constructorArgumentValues = " + constructorArgumentValues);
    }

    @Override
    public Constructor getConstructor() {
        return null;
    }

    @Override
    public void setBeanClassName(@Nullable String beanClassName) {
        if(beanClassName == null || beanClassName.isEmpty()) {
            throw new BeansException("bean name is do not blank");
        }
        this.beanClassName = beanClassName;
    }

    @Nullable
    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    @Override
    public void setPrimary(boolean primary) {
        this.isPrimary = primary;
    }

    @Override
    public boolean isPrimary() {
        return isPrimary;
    }

    @Override
    public void setFactoryBeanName(@Nullable String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    @Nullable
    @Override
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    @Override
    public BeanDefinition[] getConstructorArgumentValues() {
        return this.constructorArgumentValues;
    }
}
