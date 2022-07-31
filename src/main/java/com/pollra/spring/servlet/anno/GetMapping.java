package com.pollra.spring.servlet.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since       2022.07.29
 * @author      pollra
 * @description get mapping
 **********************************************************************************************************************/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMapping {

    String value() default "/";
}
