package com.pollra.spring;

import com.pollra.spring.context.ApplicationContext;
import com.pollra.spring.dummy.DummyObject;
import com.pollra.spring.dummy.DummyService;
import com.pollra.spring.servlet.FrontController;
import org.eclipse.jetty.server.Server;

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

        FrontController frontController = new FrontController(applicationContext);

        Server server = new Server(8080);
        try {
            server.setHandler(frontController);

            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
