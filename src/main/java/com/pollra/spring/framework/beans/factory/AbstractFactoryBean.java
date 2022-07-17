package com.pollra.spring.framework.beans.factory;

import com.pollra.spring.framework.beans.definition.BeanDefinition;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description abstract factory bean
 **********************************************************************************************************************/
public abstract class AbstractFactoryBean<T> implements FactoryBean {

    public T getInstance(BeanDefinition beanDefinition){
        throw new BeansException("not implement exception");
    }

    public String getFactoryName() {
        return this.getClass().getSimpleName();
    }
}
