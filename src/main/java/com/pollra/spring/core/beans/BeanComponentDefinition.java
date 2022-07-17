package com.pollra.spring.core.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @since       2022.06.28
 * @author      pollra
 * @description component bean definition
 **********************************************************************************************************************/
@ToString
public class BeanComponentDefinition {

    @Getter
    private final BeanDefinition beanDefinition;

    @Getter
    private final String beanName;

    @Getter
    private Set<BeanComponentDefinition> innerBeanDefinition;

    public BeanComponentDefinition(BeanDefinition beanDefinition, String beanName) {
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
        innerBeanDefinition = new HashSet<>();
    }

    public void addInnerBeanDefinition(BeanComponentDefinition beanDefinition) {
        innerBeanDefinition.add(beanDefinition);
    }
}
