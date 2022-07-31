package com.pollra.spring.servlet;

import com.pollra.spring.servlet.anno.*;
import com.pollra.spring.servlet.exceptions.DispatchServletMethodException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @since       2022.07.29
 * @author      pollra
 * @description handler method definition
 **********************************************************************************************************************/
public class HandlerMethodDefinition {

    private String uri;
    private Method method;

    public HandlerMethodDefinition(Method target) {
        Class<?>[] parameterTypes = target.getParameterTypes();
        this.method = target;
        setUri(target);
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

    public String getUri() {
        return this.uri;
    }

    private void setUri(Method target) {
        RequestMapping requestMapping = target.getAnnotation(RequestMapping.class);
        if(Objects.nonNull(requestMapping)) {
            this.uri = requestMapping.value();
            return;
        }
        PostMapping postMapping = target.getAnnotation(PostMapping.class);
        if(Objects.nonNull(postMapping)) {
            this.uri = postMapping.value();
            return;
        }
        GetMapping getMapping = target.getAnnotation(GetMapping.class);
        if(Objects.nonNull(getMapping)) {
            this.uri = getMapping.value();
            return;
        }
        PutMapping putMapping = target.getAnnotation(PutMapping.class);
        if(Objects.nonNull(putMapping)) {
            this.uri = putMapping.value();
            return;
        }
        DeleteMapping deleteMapping = target.getAnnotation(DeleteMapping.class);
        if(Objects.nonNull(deleteMapping)) {
            this.uri = deleteMapping.value();
        }
    }
}
