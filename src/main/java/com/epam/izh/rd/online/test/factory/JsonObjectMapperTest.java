package com.epam.izh.rd.online.test.factory;

import com.epam.izh.rd.online.factory.ObjectMapperFactoryImpement;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonObjectMapperTest {
    @Test
    void getObjectMapper(){
        assertEquals(new ObjectMapper().getClass(), new ObjectMapperFactoryImpement().getObjectMapper().getClass() );
    }
}
