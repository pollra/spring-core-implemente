package com.pollra.spring.servlet.classify;

import com.pollra.spring.servlet.HttpMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description http method classifier
 **********************************************************************************************************************/
public interface HandlerClassifier {

    HttpMethod getHttpMethod(Method method);
    String getUrl(Method method);

    Class<?> getMapping();

    default boolean isMatched(Method method) {
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            if(getMapping().isInstance(declaredAnnotation)) {
                return true;
            }
        }
        return false;
    }
}
