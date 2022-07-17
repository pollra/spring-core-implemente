package com.pollra.spring.framework.beans.definition;

import com.pollra.spring.framework.beans.factory.FactoryBean;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description bean definition
 **********************************************************************************************************************/
public interface BeanDefinition {

    void setBeanClassName(@Nullable String beanClassName);

    @Nullable
    String getBeanClassName();

    void setPrimary(boolean primary);

    boolean isPrimary();

    void setFactoryBeanName(@Nullable String factoryBeanName);

    @Nullable
    String getFactoryBeanName();

    Constructor getConstructor();

    BeanDefinition[] getConstructorArgumentValues();

    default boolean hasConstructorArgumentValues() {
        return getConstructorArgumentValues().length > 0;
    }
}
