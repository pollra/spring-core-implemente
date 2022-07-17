package com.pollra.spring.dummy;

import com.pollra.spring.core.annotation.Component;
import lombok.RequiredArgsConstructor;

/**
 * @since       2022.06.26
 * @author      pollra
 * @description dummy component
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class DummySimpleService {

    private final DummySimpleRepository dummySimpleRepository;

    public DummyObject save(DummyObject dummyObject) {
        return dummySimpleRepository.save(dummyObject);
    }

    public DummyObject get(Long id) {
        return dummySimpleRepository.findById(id);
    }
}
