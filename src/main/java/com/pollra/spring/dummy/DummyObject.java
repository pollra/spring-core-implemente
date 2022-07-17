package com.pollra.spring.dummy;

import com.pollra.spring.core.annotation.Bean;
import com.pollra.spring.core.annotation.Component;
import lombok.*;

/**
 * @since       2022.06.26
 * @author      pollra
 * @description dummy object
 **********************************************************************************************************************/
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DummyObject {

    @Setter
    private Long id;
    private DummyName name;
    private Integer age;
}
