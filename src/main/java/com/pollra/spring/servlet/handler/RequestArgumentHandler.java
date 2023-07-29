package com.pollra.spring.servlet.handler;

import com.pollra.spring.servlet.HttpMethod;

/**
 * @since       2022.08.21
 * @author      pollra
 * @description request mapping handler
 **********************************************************************************************************************/
public interface RequestArgumentHandler {

    boolean isProcessable(HttpMethod httpMethod, String requestUrl);
    Object getArgument(String requestUrl, String body);
}
