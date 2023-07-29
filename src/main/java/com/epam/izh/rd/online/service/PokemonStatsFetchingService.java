package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class PokemonStatsFetchingService {

  private ObjectMapper objectMapper;

  private PokemonStatsFetchingService() {}

  private static final class PokemonStatsFetchingServiceSingletonHolder {
    public static final PokemonStatsFetchingService POKEMON_STATS_FETCHING_SERVICE_INSTANCE =
        new PokemonStatsFetchingService();
  }

  public static PokemonStatsFetchingService getInstance() {
    return PokemonStatsFetchingServiceSingletonHolder.POKEMON_STATS_FETCHING_SERVICE_INSTANCE;
  }

  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public void setStats(byte[] pokemonAsByteArray, Pokemon pokemon) {
    try (JsonParser jsonParser = objectMapper.createParser(pokemonAsByteArray)) {
      pokemon.setPokemonId(findIDpokemon(jsonParser));
      pokemon.setImageURLfrontDefault(findURLimageFrontDefault(jsonParser));
      while (jsonParser.nextToken() != null) {
        if (jsonParser.currentName() == null || jsonParser.getText() == null) {
          continue;
        }
        short statShort = findBaseStatShort(jsonParser);
        switch (findBaseStatType(jsonParser)) {
          case "hp":
            pokemon.setHp(statShort);
            break;
          case "attack":
            pokemon.setAttack(statShort);
            break;
          case "defense":
            pokemon.setDefense(statShort);
            break;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private short findIDpokemon(JsonParser jsonParser) throws IOException {
    short tempStat = -1;
    while (jsonParser.nextToken() != null) {
      if (jsonParser.currentName() == null || jsonParser.getText() == null) {
        continue;
      }
      if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)
          && (jsonParser.currentName().trim().equalsIgnoreCase("id"))) {
        tempStat = (short) jsonParser.getIntValue();
        break;
      }
    }
    return tempStat;
  }

  private short findBaseStatShort(JsonParser jsonParser) throws IOException {
    short tempStat = -1;
    while (jsonParser.nextToken() != null) {
      if (jsonParser.currentName() == null || jsonParser.getText() == null) {
        continue;
      }
      if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)
          && jsonParser.currentName().trim().equalsIgnoreCase("base_stat")) {
        tempStat = (short) jsonParser.getIntValue();
        break;
      }
    }
    return tempStat;
  }

  private String findBaseStatType(JsonParser jsonParser) throws IOException {
    String statType = "nothing";
    while (jsonParser.nextToken() != null) {
      if (jsonParser.currentName() == null || jsonParser.getText() == null) {
        continue;
      }
      if (jsonParser.hasToken(JsonToken.VALUE_STRING)
          && (jsonParser.getText().trim().equalsIgnoreCase("hp")
              || jsonParser.getText().trim().equalsIgnoreCase("attack")
              || jsonParser.getText().trim().equalsIgnoreCase("defense"))) {
        statType = jsonParser.getText().trim();
        break;
      }
    }
    return statType;
  }

  private String findURLimageFrontDefault(JsonParser jsonParser) throws IOException {
    String imageURLfrontDefault = "nothing";
    while (jsonParser.nextToken() != null) {
      if (jsonParser.currentName() == null || jsonParser.getText() == null) {
        continue;
      }
      if (jsonParser.hasToken(JsonToken.VALUE_STRING)
          && jsonParser.currentName().trim().equalsIgnoreCase("front_default")) {
        imageURLfrontDefault = jsonParser.getText().trim();
        break;
      }
    }
    return imageURLfrontDefault;
  }
}
