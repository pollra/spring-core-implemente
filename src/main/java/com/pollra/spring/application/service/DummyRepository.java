package com.pollra.spring.application.service;

import com.pollra.spring.framework.beans.annotate.Component;
import javassist.NotFoundException;

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

    private final Map<Long, Optional<DummyEntity>> storage = new HashMap<>();

    public DummyEntity findById(Long id) {
        return storage.get(id).orElseThrow(RuntimeException::new);
    }

    public void save(DummyEntity dummyEntity) {
        int size = storage.size();
        dummyEntity.setId((long) ++size);
        Optional<DummyEntity> requestData = Optional.of(dummyEntity);
        storage.put(dummyEntity.getId(), requestData);
    }
}
