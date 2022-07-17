package com.pollra.spring.dummy;

import com.pollra.spring.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @since       2022.06.28
 * @author      pollra
 * @description dummy repository
 **********************************************************************************************************************/
@Component
public class DummySimpleRepository {

    private final Map<Long, DummyObject> storage = new HashMap<>();

    public DummyObject save(DummyObject dummyObject) {
        int storageSize = storage.size() + 1;
        dummyObject.setId(( long ) storageSize);
        storage.put(dummyObject.getId(), dummyObject);
        return dummyObject;
    }

    public DummyObject findById(Long id) {
        return storage.get(id);
    }
}
