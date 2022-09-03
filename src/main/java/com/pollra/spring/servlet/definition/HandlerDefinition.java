package com.pollra.spring.servlet.definition;

import com.pollra.spring.servlet.HttpMethod;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @since       2022.08.07
 * @author      pollra
 * @description handler definition
 **********************************************************************************************************************/
@Slf4j
@ToString
public class HandlerDefinition {

    private HttpMethod httpMethod;
    private DefinitionUrl url;

    public HandlerDefinition(HttpMethod httpMethod, String url) {
        Objects.requireNonNull(httpMethod);
        Objects.requireNonNull(url);
        this.httpMethod = httpMethod;
        pathAndQueryMapping(url);
    }

    public HandlerDefinition(String httpMethodIsString, String url) {
        this(HttpMethod.valueOf(httpMethodIsString), url);
    }

    public void pathAndQueryMapping(String url) {
        String[] pathAndQuery = url.split("\\?");
        this.url = new DefinitionUrl(pathAndQuery[0]);
    }

    public boolean isMatched(HandlerDefinition requestHandlerDefinition) {
        HttpMethod requestHttpMethod = requestHandlerDefinition.httpMethod;
        DefinitionUrl requestUrl = requestHandlerDefinition.url;
        log.info("persist: "+ this);
        log.info("request: "+requestHandlerDefinition);
        return this.url.isMatched(requestUrl)
            && this.httpMethod.equals(requestHttpMethod);
    }
}
