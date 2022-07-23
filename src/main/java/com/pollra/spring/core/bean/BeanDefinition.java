package com.pollra.spring.core.bean;

import java.lang.reflect.Constructor;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description bean definition
 **********************************************************************************************************************/
public interface BeanDefinition {

    String getBeanName();
    Class<?> getOriginalClass();
    Constructor<?> getBeanConstructor();
    Class<?>[] getDependencies();
}
