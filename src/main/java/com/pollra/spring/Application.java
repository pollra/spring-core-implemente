package com.pollra.spring;

import com.pollra.spring.context.ApplicationContext;
import com.pollra.spring.core.annotation.Component;
import com.pollra.spring.core.scanner.BeanScanner;
import com.pollra.spring.dummy.DummyObject;
import com.pollra.spring.dummy.DummyService;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * @since       2022.06.26
 * @author      pollra
 **********************************************************************************************************************/
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.refresh();
        DummyService dummyService = ( DummyService ) applicationContext.getBean("dummyService");

        System.out.println("dummyService = " + dummyService);
        dummyService.save(DummyObject.builder().name("test").subName("subName").build());

        DummyObject dummyObject = dummyService.get(1L);
        System.out.println("dummyObject = " + dummyObject);

    }
}
