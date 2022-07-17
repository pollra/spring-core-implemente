package com.pollra.spring.framework.context.register;

import com.pollra.spring.framework.beans.definition.BeanDefinition;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description bean register
 **********************************************************************************************************************/
public interface BeanDefinitionRegister {

    void add(String beanName, BeanDefinition beanDefinition);

    BeanDefinition get(String beanName);

    BeanDefinition get(Class<?> type);

    boolean isContains(String beanName);

    boolean isContains(Class<?> type);
}
