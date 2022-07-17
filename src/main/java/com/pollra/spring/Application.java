package com.pollra.spring;

import com.pollra.spring.application.service.DummyEntity;
import com.pollra.spring.application.service.DummyService;
import com.pollra.spring.framework.beans.definition.BeanDefinition;
import com.pollra.spring.framework.context.ApplicationContext;
import com.pollra.spring.framework.context.ReadOnlyApplicationContext;

/**
 * @since       2022.06.26
 * @author      pollra
 **********************************************************************************************************************/
public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext applicationContext = new ReadOnlyApplicationContext(Application.class);
        DummyService dummyService = applicationContext.getBean("DummyService", DummyService.class);
        System.out.println("dummyService = " + dummyService);
        DummyService dummyService2 = applicationContext.getBean("DummyService", DummyService.class);
        System.out.println("dummyService2 = " + dummyService2);
        dummyService.add(DummyEntity.builder()
                        .name("name_pollra")
                        .subName("subname_pollra")
                .build());
        DummyEntity dummyEntity = dummyService.get(1L);
        System.out.println("dummyEntity = " + dummyEntity);
    }
}
