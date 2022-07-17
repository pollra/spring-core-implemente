package com.pollra.spring.framework.beans.factory;

import com.pollra.spring.framework.beans.definition.BeanDefinition;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description factory bean
 **********************************************************************************************************************/
public interface FactoryBean {

    Object getInstance(BeanDefinition beanDefinition);

    String getFactoryName();
}
