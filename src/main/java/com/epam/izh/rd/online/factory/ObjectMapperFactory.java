package com.epam.izh.rd.online.factory;

import com.fasterxml.jackson.databind.ObjectMapper;

@FunctionalInterface
public interface ObjectMapperFactory {

  ObjectMapper getObjectMapper();
}
