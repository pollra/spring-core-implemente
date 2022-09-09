package com.pollra.spring.servlet.request.binder;

import com.pollra.spring.servlet.MethodArgumentDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @since       2022.09.03
 * @author      pollra
 * @description request argument binder
 **********************************************************************************************************************/
public interface RequestArgumentBinder {

    Object[] bindArguments(Collection<MethodArgumentDefinition> argumentDefinitions, Map<String, Object> extractedVariables);
    boolean isProcessed(Map<String, Object> extractedVariables);
}
