package com.pollra.spring.dummy;

import lombok.*;

/**
 * @since       2022.07.17
 * @author      pollra
 * @description dummy object
 **********************************************************************************************************************/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DummyObject {

    private Long id;

    private String name;

    private String subName;
}
