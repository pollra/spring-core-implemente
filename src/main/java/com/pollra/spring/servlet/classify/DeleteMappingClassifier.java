package com.pollra.spring.servlet.classify;

import com.pollra.spring.servlet.HttpMethod;
import com.pollra.spring.servlet.anno.DeleteMapping;

import java.lang.reflect.Method;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description delete mapping classifier
 **********************************************************************************************************************/
public class DeleteMappingClassifier implements HandlerClassifier{
    @Override
    public HttpMethod getHttpMethod(Method method) {
        DeleteMapping annotation = method.getAnnotation(DeleteMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        DeleteMapping annotation = method.getAnnotation(DeleteMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return DeleteMapping.class;
    }
}
