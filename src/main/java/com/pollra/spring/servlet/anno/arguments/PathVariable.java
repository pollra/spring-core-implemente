package com.pollra.spring.servlet.anno.arguments;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since       2022.09.09
 * @author      pollra
 * @description path variable
 **********************************************************************************************************************/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface PathVariable {

    /**
     * 값의 이름을 지정합니다.
     * 이는 url 에 지정되어있는 값 이름과 동일해야 합니다.
     */
    String value() default "";
}
