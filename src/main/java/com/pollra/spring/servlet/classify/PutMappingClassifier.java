package com.pollra.spring.servlet.classify;

import com.pollra.spring.servlet.HttpMethod;
import com.pollra.spring.servlet.anno.mappings.PutMapping;

import java.lang.reflect.Method;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description put mapping classifier
 **********************************************************************************************************************/
public class PutMappingClassifier implements HandlerClassifier{
    @Override
    public HttpMethod getHttpMethod(Method method) {
        PutMapping annotation = method.getAnnotation(PutMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        PutMapping annotation = method.getAnnotation(PutMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return PutMapping.class;
    }
}
