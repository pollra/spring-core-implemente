package com.pollra.spring.dummy;

import com.pollra.spring.core.annotation.Component;
import com.pollra.spring.servlet.anno.PostMapping;
import com.pollra.spring.servlet.anno.RestController;
import com.pollra.spring.servlet.anno.GetMapping;
import lombok.RequiredArgsConstructor;

/**
 * @since       2022.07.30
 * @author      pollra
 * @description dummy controller
 **********************************************************************************************************************/
@RestController
@Component
@RequiredArgsConstructor
public class DummyController {

    private final DummyService dummyService;

    @GetMapping("/")
    public String doGet() {
        System.out.println("Hello world!");
        return "hello server!";
    }

    @GetMapping("/test")
    public String doTest() {
        return "test api";
    }

    @GetMapping("/test/{testId}")
    public String doTest2() {
        return "test api 2";
    }

    @GetMapping("/dummies/{dummyId}")
    public DummyObject get(Long dummyId) {
        return dummyService.get(dummyId);
    }

    @PostMapping("/dummies")
    public DummyObject add(DummyObject dummyObject) {
        return dummyService.save(dummyObject);
    }
}
