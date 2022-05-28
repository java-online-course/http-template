package com.epam.izh.rd.online.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactoryImpement implements ObjectMapperFactory {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperFactoryImpement() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
