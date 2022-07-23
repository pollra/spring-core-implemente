package com.pollra.spring.dummy;

import com.pollra.spring.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description dummy repository
 **********************************************************************************************************************/
@Component
public class DummyRepository {

    private final Map<Long, Optional<DummyObject>> storage = new HashMap<>();

    public DummyObject get(Long id) {
        return storage.get(id).orElseThrow(RuntimeException::new);
    }

    public DummyObject add(DummyObject dummyObject) {
        long size = storage.size();
        dummyObject.setId(++size);
        storage.put(size, Optional.of(dummyObject));
        return dummyObject;
    }
}
