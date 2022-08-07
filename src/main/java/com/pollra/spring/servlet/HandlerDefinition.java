package com.pollra.spring.servlet;

import java.util.Objects;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description handler definition
 **********************************************************************************************************************/
public class HandlerDefinition {

    private HttpMethod httpMethod;

    private String url;

    private String regex;

    public HandlerDefinition(HttpMethod httpMethod, String url) {
        init(httpMethod, url);
    }

    public HandlerDefinition(String httpMethodIsString, String url) {
        HttpMethod httpMethod = HttpMethod.valueOf(httpMethodIsString);
        init(httpMethod, url);
    }

    public void init(HttpMethod httpMethod, String url) {
        Objects.requireNonNull(httpMethod);
        Objects.requireNonNull(url);
        this.httpMethod = httpMethod;
        this.url = url;
        setUpRegex();
    }

    private void setUpRegex() {
        String regexTarget = new String(this.url);
        this.regex = regexTarget.replaceAll("(\\{\\w+})", "\\w+");
    }

    public boolean isMatched(HandlerDefinition requestHandlerDefinition) {
        HttpMethod requestHttpMethod = requestHandlerDefinition.httpMethod;
        String requestUrl = requestHandlerDefinition.url;
        boolean matches = requestUrl.matches(this.regex);
        return requestHttpMethod.equals(this.httpMethod) && matches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof HandlerDefinition)) return false;
        HandlerDefinition that = ( HandlerDefinition ) o;
        boolean matches = (( HandlerDefinition ) o).url.matches((( HandlerDefinition ) o).regex);
        if(! matches) return false;
        return httpMethod == that.httpMethod && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpMethod, url);
    }
}
