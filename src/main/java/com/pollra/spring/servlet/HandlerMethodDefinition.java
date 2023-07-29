package com.pollra.spring.servlet;

import com.pollra.spring.servlet.anno.arguments.PathVariable;
import com.pollra.spring.servlet.classify.HandlerClassifier;
import com.pollra.spring.servlet.definition.HandlerDefinition;
import com.pollra.spring.servlet.exceptions.DispatchServletMethodException;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
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
    private final MethodDefinition methodDefinition;
    private Object instance;

    public HandlerMethodDefinition(Method handledMethod, Object instance, Set<HandlerClassifier> handlerClassifiers) {

        Objects.requireNonNull(instance);

        this.methodDefinition = new MethodDefinition(handledMethod);
        this.instance = instance;

        HandlerClassifier handlerClassifier = handlerClassifiers.stream()
                .filter(initHandlerClassifier -> initHandlerClassifier.isMatched(handledMethod))
                .findFirst()
                .orElseThrow();
        final String url = handlerClassifier.getUrl(handledMethod);
        final HttpMethod httpMethod = handlerClassifier.getHttpMethod(handledMethod);
        this.handlerDefinition = new HandlerDefinition(httpMethod, url);
    }

    public Object invoke(Object... parameters) {
        return this.methodDefinition.invoke(this.instance, parameters);
    }

    public Collection<MethodArgumentDefinition> getArgumentDefinitions() {
        return this.methodDefinition.getMethodArgumentDefinitions();
    }
}
