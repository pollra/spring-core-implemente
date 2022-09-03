package com.pollra.spring.servlet.handler;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("url 파서 테스트")
class UrlArgumentParserTest {

    private UrlArgumentParser urlArgumentParser = new UrlArgumentParser();

    @Nested
    @DisplayName("성공 테스트")
    public class Ok {

        @Test
        public void success() {

            UrlArgumentParser.getArguments("/users/1/");
        }
    }
}