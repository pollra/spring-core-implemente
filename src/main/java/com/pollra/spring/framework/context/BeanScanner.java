package com.pollra.spring.framework.context;

import java.util.Set;

/**
 * @since       2022.07.14
 * @author      pollra
 * @description bean scanner
 **********************************************************************************************************************/
public interface BeanScanner {
    Set<Class<?>> scan();
}
