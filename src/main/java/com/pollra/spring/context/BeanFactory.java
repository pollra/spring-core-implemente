package com.pollra.spring.context;

/**
 * @since       2022.07.23
 * @author      pollra
 * @description bean factory
 **********************************************************************************************************************/
public interface BeanFactory {

    Object getBean(String name);
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
    <T> T getBean(Class<T> requiredTpe, Object... args) throws BeansException;
    boolean containsBean(String name);
}
