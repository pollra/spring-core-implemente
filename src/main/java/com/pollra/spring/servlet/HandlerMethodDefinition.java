package com.pollra.spring.servlet;

import com.pollra.spring.servlet.classify.HandlerClassifier;
import com.pollra.spring.servlet.definition.HandlerDefinition;
import com.pollra.spring.servlet.exceptions.DispatchServletMethodException;
import com.pollra.spring.servlet.request.RequestParameters;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

/**
 * @since       2022.07.29
 * @author      pollra
 * @description handler method definition
 **********************************************************************************************************************/
public class HandlerMethodDefinition {

    @Getter
    private HandlerDefinition handlerDefinition;
    private final Method handledMethod;
    private Object instance;

    public HandlerMethodDefinition(Method handledMethod, Object instance, Set<HandlerClassifier> handlerClassifiers) {

        Objects.requireNonNull(handledMethod);
        Objects.requireNonNull(instance);

        this.handledMethod = handledMethod;
        this.instance = instance;

        HandlerClassifier handlerReader = handlerClassifiers.stream()
                .filter(handlerClassifier -> handlerClassifier.isMatched(handledMethod))
                .findFirst()
                .orElseThrow();
        final String url = handlerReader.getUrl(handledMethod);
        final HttpMethod httpMethod = handlerReader.getHttpMethod(handledMethod);
        this.handlerDefinition = new HandlerDefinition(httpMethod, url);
    }

    public Object invoke(Object... parameters) {
        try {
            return this.handledMethod.invoke(this.instance, parameters);
        } catch (IllegalAccessException e) {
            throw new DispatchServletMethodException(e);
        } catch (InvocationTargetException e) {
            throw new DispatchServletMethodException(e);
        }
    }

    public Class<?>[] getParameters() {
        return this.handledMethod.getParameterTypes();
    }
}
