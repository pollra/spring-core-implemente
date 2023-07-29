package com.pollra.spring.servlet.classify;

import com.pollra.spring.servlet.HttpMethod;
import com.pollra.spring.servlet.anno.mappings.PostMapping;

import java.lang.reflect.Method;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description post method classifier
 **********************************************************************************************************************/
public class PostMethodClassifier implements HandlerClassifier {
    @Override
    public HttpMethod getHttpMethod(Method method) {
        PostMapping annotation = method.getAnnotation(PostMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        PostMapping annotation = method.getAnnotation(PostMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return PostMapping.class;
    }
}
