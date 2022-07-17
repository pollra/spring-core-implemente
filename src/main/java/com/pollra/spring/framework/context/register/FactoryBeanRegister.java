package com.pollra.spring.framework.context.register;

import com.pollra.spring.framework.beans.factory.FactoryBean;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description factory register
 **********************************************************************************************************************/
public interface FactoryBeanRegister {

    FactoryBean get(String factoryBeanName);

    void add(FactoryBean factoryBean);
}
