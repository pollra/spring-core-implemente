package com.pollra.spring.core.scanner;

import com.pollra.spring.core.annotation.Component;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description bean scanner
 **********************************************************************************************************************/
public class BeanScanner {

    public static Set<Class<?>> scan(Class<? extends Annotation>[] annotations, Class<?> baseClass) {
        final Set<Class<?>> result = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            Set<Class<?>> scan = scan(annotation, baseClass);
            result.addAll(scan);
        }
        return result;
    }

    public static Set<Class<?>> scan(Class<? extends Annotation> annotation, Class<?> baseClass) {
        Reflections reflections = new Reflections(baseClass.getPackageName(), Scanners.TypesAnnotated);
        return reflections.getTypesAnnotatedWith(annotation);
    }
}
