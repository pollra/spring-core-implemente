package com.pollra.spring.core.beans;

import javassist.expr.ConstructorCall;
import lombok.EqualsAndHashCode;

import javax.annotation.Nullable;
import javax.management.ConstructorParameters;

/**
 * @since       2022.06.26
 * @author      pollra
 * @description bean definition
 **********************************************************************************************************************/
public interface BeanDefinition {

    void setParentName(@Nullable String parentName);

    @Nullable
    String getParentName();

    void setBeanClassName(@Nullable String beanClassName);

    @Nullable
    String getBeanClassName();
}
