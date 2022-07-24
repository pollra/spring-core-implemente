package com.pollra.spring.core.bean;

import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description bean definition
 **********************************************************************************************************************/
@ToString
public class DefaultBeanDefinition implements BeanDefinition {

    private final Class<?> originalClass;
    private final Constructor<?> constructor;
    private final Class<?>[] dependencies;
    private final Boolean isLazyInit;

    public DefaultBeanDefinition(Class<?> clazz) {
        this.originalClass = clazz;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        this.constructor = Arrays.stream(declaredConstructors)
                .max((ct1, ct2) -> ct1.getParameterCount() - ct2.getParameterCount())
                .orElseThrow();
        this.dependencies = this.constructor.getParameterTypes();
        this.isLazyInit = this.constructor.isAnnotationPresent(Lazy.class);
    }

    @Override
    public String getBeanName() {
        String simpleName = originalClass.getSimpleName();
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }

    @Override
    public Class<?> getOriginalClass() {
        return this.originalClass;
    }

    @Override
    public Constructor<?> getBeanConstructor() {
        return this.constructor;
    }

    @Override
    public Class<?>[] getDependencies() {
        return this.dependencies;
    }
}
