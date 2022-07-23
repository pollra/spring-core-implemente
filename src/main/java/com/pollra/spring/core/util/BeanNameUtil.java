package com.pollra.spring.core.util;

/**
 * @since       2022.07.23
 * @author      pollra
 * @description bean name util
 **********************************************************************************************************************/
public class BeanNameUtil {

    public static String getName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        return getName(simpleName);
    }

    public static String getName(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
