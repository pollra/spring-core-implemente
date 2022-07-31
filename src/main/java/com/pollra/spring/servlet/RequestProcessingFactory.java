package com.pollra.spring.servlet;

import com.pollra.spring.core.util.BeanNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @since       2022.07.30
 * @author      pollra
 * @description request processing factory
 **********************************************************************************************************************/
public class RequestProcessingFactory {

    private final Logger logger = LoggerFactory.getLogger(RequestProcessingFactory.class);

    private Map<String, Method> uriProcessingMethods = new HashMap<>();

    public RequestProcessingFactory(Set<Class<?>> controllerClasses) {
        logger.info("controllerClasses.size() = " + controllerClasses.size());
        for (Class<?> controllerClass : controllerClasses) {
            Method[] declaredMethods = controllerClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                HandlerMethodDefinition handlerMethodDefinition = new HandlerMethodDefinition(declaredMethod);
                String processingUriPath = handlerMethodDefinition.getUri();
                this.uriProcessingMethods.put(processingUriPath, declaredMethod);
            }
        }
    }

    public Method getProcessMethod(String requestUri) {
        return uriProcessingMethods.get(requestUri);
    }

    public String getDeclaredBeanName(String requestUri) {
        Method processMethod = getProcessMethod(requestUri);
        String simpleName = processMethod.getDeclaringClass().getSimpleName();
        return BeanNameUtil.getName(simpleName);
    }
}
