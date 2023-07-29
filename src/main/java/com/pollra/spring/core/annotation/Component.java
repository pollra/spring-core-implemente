package com.pollra.spring.core.annotation;

import java.lang.annotation.*;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description component
 **********************************************************************************************************************/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    String value() default "";
}
