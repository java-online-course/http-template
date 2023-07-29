package com.epam.izh.rd.online.service;


import com.epam.izh.rd.online.factory.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PokemonFetchingService implements ObjectMapperFactory  {

  private PokemonFetchingService() {
    ObjectMapper objectMapper = getObjectMapper();
  }

  private static class PokemonFetchingServiceHolder {

    public static final PokemonFetchingService POKEMON_FETCHING_SERVICE_HOLDER_INSTANCE =
        new PokemonFetchingService();
  }

  public static PokemonFetchingService getInstance() {
    return PokemonFetchingServiceHolder.POKEMON_FETCHING_SERVICE_HOLDER_INSTANCE;
  }

  @Override
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

}
