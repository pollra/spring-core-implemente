package com.pollra.spring.servlet.handler;

import com.pollra.spring.servlet.HttpMethod;

/**
 * @since       2022.08.21
 * @author      pollra
 * @description get request mapping handler
 **********************************************************************************************************************/
public class GetRequestArgumentHandler implements RequestArgumentHandler {

    @Override
    public boolean isProcessable(HttpMethod httpMethod, String requestUrl) {
        return HttpMethod.GET.equals(httpMethod);
    }

    @Override
    public Object getArgument(String requestUrl, String body) {

        return null;
    }
}
