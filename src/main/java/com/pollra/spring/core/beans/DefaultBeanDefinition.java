package com.pollra.spring.core.beans;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @since       2022.06.28
 * @author      pollra
 * @description default bean definition
 **********************************************************************************************************************/
@ToString
@EqualsAndHashCode
public class DefaultBeanDefinition implements BeanDefinition {

    @Setter
    @Getter
    private String parentName;

    @Setter
    @Getter
    private String beanClassName;
}
