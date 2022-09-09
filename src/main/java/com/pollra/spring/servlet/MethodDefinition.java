package com.pollra.spring.servlet;

import com.pollra.spring.servlet.anno.arguments.PathVariable;
import com.pollra.spring.servlet.classify.HandlerClassifier;
import com.pollra.spring.servlet.exceptions.DispatchServletMethodException;
import lombok.Getter;
import org.eclipse.jetty.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @since       2022.09.09
 * @author      pollra
 * @description method definition
 **********************************************************************************************************************/
public class MethodDefinition {

    private final Method method;
    @Getter
    private final Collection<MethodArgumentDefinition> methodArgumentDefinitions;

    public MethodDefinition(Method method) {

        Objects.requireNonNull(method);

        this.method = method;
        this.methodArgumentDefinitions = new ArrayList<>();
        for (Parameter parameter : method.getParameters()) {
            PathVariable annotation = parameter.getAnnotation(PathVariable.class);
            String variableName = Objects.isNull(annotation)
                                ? parameter.getName()
                                : annotation.value();
            Class<?> type = parameter.getType();
            methodArgumentDefinitions.add(new MethodArgumentDefinition(variableName, type));
        }
    }

    public Object invoke(Object instance, Object... parameters) {
        try {
            return this.method.invoke(instance, parameters);
        } catch (IllegalAccessException e) {
            throw new DispatchServletMethodException(e);
        } catch (InvocationTargetException e) {
            throw new DispatchServletMethodException(e);
        }
    }
}
