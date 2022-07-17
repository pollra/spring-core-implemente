package com.pollra.spring.application.service;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description dummy entity
 **********************************************************************************************************************/
@Data
@Builder
@ToString
public class DummyEntity {

    private Long id;

    private String name;

    private String subName;
}
