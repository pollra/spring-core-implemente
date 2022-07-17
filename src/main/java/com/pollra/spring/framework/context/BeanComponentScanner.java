package com.pollra.spring.framework.context;

import com.pollra.spring.framework.beans.annotate.Component;
import lombok.AllArgsConstructor;
import org.reflections.Reflections;

import java.util.Set;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description bean component scanner
 **********************************************************************************************************************/
@AllArgsConstructor
public class BeanComponentScanner implements BeanScanner {

    private final Class<?> standardClass;

    public Set<Class<?>> scan() {
        Reflections reflections = new Reflections(standardClass.getPackageName());
        return reflections.getTypesAnnotatedWith(Component.class);
    }
}
