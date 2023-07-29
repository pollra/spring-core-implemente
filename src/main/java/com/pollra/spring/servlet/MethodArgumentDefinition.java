package com.pollra.spring.servlet;

import lombok.Getter;
import lombok.ToString;

/**
 * @since       2022.09.09
 * @author      pollra
 * @description method argument definition
 **********************************************************************************************************************/
@ToString
public class MethodArgumentDefinition {

    @Getter
    private final String name;

    @Getter
    private final Class<?> type;

    public MethodArgumentDefinition(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }


}
