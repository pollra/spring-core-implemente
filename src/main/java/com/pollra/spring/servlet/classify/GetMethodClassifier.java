package com.pollra.spring.servlet.classify;

import com.pollra.spring.servlet.HttpMethod;
import com.pollra.spring.servlet.anno.mappings.GetMapping;

import java.lang.reflect.Method;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description get method classifier
 **********************************************************************************************************************/
public class GetMethodClassifier implements HandlerClassifier {

    @Override
    public HttpMethod getHttpMethod(Method method) {
        GetMapping annotation = method.getAnnotation(GetMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        GetMapping annotation = method.getAnnotation(GetMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return GetMapping.class;
    }
}
