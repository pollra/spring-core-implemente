package com.pollra.spring.servlet.request.extractor;

import com.pollra.spring.servlet.definition.HandlerDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @since       2022.09.04
 * @author      pollra
 * @description request argument extractor
 **********************************************************************************************************************/
public interface RequestArgumentExtractor {

    Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request);
    boolean isProcessed(HandlerDefinition persistDefinition, HttpServletRequest request);
}
