package com.pollra.spring.context;

import com.pollra.spring.core.bean.BeanDefinition;

/**
 * @since       2022.07.23
 * @author      pollra
 * @description factory bean
 **********************************************************************************************************************/
public interface FactoryBean {

    Object newInstance(BeanDefinition beanDefinition);

}
