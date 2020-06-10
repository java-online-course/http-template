package com.epam.izh.rd.online.uitl;

import com.epam.izh.rd.online.factory.mapperFactory.ObjectMapperFactory;
import com.epam.izh.rd.online.factory.mapperFactory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;




public class Converter<T> {

    public T convertFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapperFactory mapperFactory = new ObjectMapperFactoryImpl();
        ObjectMapper objectMapper = mapperFactory.getObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, clazz);
    }
}
