package com.epam.izh.rd.online.factory.mapperFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperFactoryImplTest {

    private ObjectMapperFactory objectMapperFactory;

    @BeforeEach
    public void init() {
        objectMapperFactory = new ObjectMapperFactoryImpl();
    }

    @Test
    void testGetObjectMapper() {
        ObjectMapper objectMapper = objectMapperFactory.getObjectMapper();
        assertNotNull(objectMapper);
    }
}