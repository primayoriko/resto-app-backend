package com.future.restoapp.unitTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerTest {

    public static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
