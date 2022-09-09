package com.pollra.spring.servlet.request;

/**
 * @since       2022.09.09
 * @author      pollra
 * @description request argument exractor exception
 **********************************************************************************************************************/
public class RequestArgumentExractorException extends RuntimeException{

    public RequestArgumentExractorException(String message) {
        super(message);
    }
}
