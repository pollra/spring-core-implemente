package com.pollra.spring.servlet.exceptions;

/**
 * @since       2022.07.30
 * @author      pollra
 * @description dispatch servlet method exception
 **********************************************************************************************************************/
public class DispatchServletMethodException extends RuntimeException {
    public DispatchServletMethodException(String message) {
        super(message);
    }

    public DispatchServletMethodException(Throwable cause) {
        super(cause);
    }
}
