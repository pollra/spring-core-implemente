package com.pollra.spring.framework.beans.factory;

import com.pollra.spring.framework.beans.definition.BeanDefinition;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description method factory bean
 **********************************************************************************************************************/
public class MethodFactoryBean <R> implements FactoryBean {

    @Override
    public Object getInstance(BeanDefinition beanDefinition) {
        System.out.println("create new instance");
        return null;
    }

    @Override
    public String getFactoryName() {
        return null;
    }
}
