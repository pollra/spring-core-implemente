package com.pollra.spring.dummy;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description dummy object
 **********************************************************************************************************************/
@Data
@Builder
public class DummyObject {

    private Long id;

    private String name;

    private String subName;
}
