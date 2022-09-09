package com.pollra.spring.servlet.request.extractor;

import com.pollra.spring.servlet.definition.DefinitionUrl;
import com.pollra.spring.servlet.definition.HandlerDefinition;
import com.pollra.spring.servlet.request.extractor.RequestArgumentExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @since       2022.09.04
 * @author      pollra
 * @description get request argument extractor
 **********************************************************************************************************************/
public class PathVariableRequestArgumentExtractor implements RequestArgumentExtractor {

    @Override
    public Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request) {
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        HandlerDefinition requestDefinition = new HandlerDefinition(requestMethod, requestURI);
        return persistDefinition.getPathVariable(requestDefinition);
    }

    @Override
    public boolean isProcessed(HandlerDefinition persistDefinition, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        DefinitionUrl requestDefinitionUrl = new DefinitionUrl(requestURI);
        return requestDefinitionUrl.urlHasPathVariable();
    }
}
