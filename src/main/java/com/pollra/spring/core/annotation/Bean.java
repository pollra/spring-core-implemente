package com.pollra.spring.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since       2022.06.27
 * @author      pollra
 * @description bean
 **********************************************************************************************************************/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {


    /**
     * Properties that allow you to define a bean name
     *
     */
    String value() default "";
}
