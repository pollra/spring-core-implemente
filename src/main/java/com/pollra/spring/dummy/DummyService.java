package com.pollra.spring.dummy;

import com.pollra.spring.core.annotation.Component;
import lombok.RequiredArgsConstructor;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description dummy service
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class DummyService {

    private final DummyRepository dummyRepository;

    public DummyObject save(DummyObject dummyObject) {
        return dummyRepository.add(dummyObject);
    }

    public DummyObject get(Long dummyObjectId) {
        return dummyRepository.get(dummyObjectId);
    }
}
