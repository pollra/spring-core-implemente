package com.pollra.spring.framework.beans.factory;

import com.pollra.spring.framework.beans.definition.BeanDefinition;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description nested factory bean
 **********************************************************************************************************************/
public class NestedFactoryBean extends AbstractFactoryBean{
    @Override
    public Object getInstance(BeanDefinition beanDefinition) {
        return super.getInstance(beanDefinition);
    }

    @Override
    public String getFactoryName() {
        return super.getFactoryName();
    }
}
