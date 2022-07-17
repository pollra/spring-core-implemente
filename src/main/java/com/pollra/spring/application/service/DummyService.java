package com.pollra.spring.application.service;

import com.pollra.spring.framework.beans.annotate.Component;
import lombok.RequiredArgsConstructor;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description dummy service
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class DummyService {

    private final DummyRepository dummyRepository;

    public DummyEntity get(Long id) {
        return dummyRepository.findById(id);
    }

    public void add(DummyEntity dummyEntity) {
        dummyRepository.save(dummyEntity);
    }
}
