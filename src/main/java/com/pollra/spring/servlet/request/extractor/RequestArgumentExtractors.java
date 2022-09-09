package com.pollra.spring.servlet.request.extractor;

import com.pollra.spring.servlet.definition.HandlerDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @since       2022.09.09
 * @author      pollra
 * @description request argument extractors
 **********************************************************************************************************************/
public class RequestArgumentExtractors {

    private final Collection<RequestArgumentExtractor> requestArgumentExtractors;

    public RequestArgumentExtractors() {

        this.requestArgumentExtractors = new ArrayList<>();
        this.requestArgumentExtractors.add(new UrlQueryVariableRequestArgumentExtractor());
        this.requestArgumentExtractors.add(new PathVariableRequestArgumentExtractor());
    }

    public Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request) {
        List<Map<String, Object>> extractResults = requestArgumentExtractors.stream()
                .filter(requestArgumentExtractor -> requestArgumentExtractor.isProcessed(persistDefinition, request))
                .map(requestArgumentExtractor -> requestArgumentExtractor.extract(persistDefinition, request))
                .collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        for (Map<String, Object> extractResult : extractResults) {
            result.putAll(extractResult);
        }
        return result;
    }
}
