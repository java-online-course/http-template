package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.entity.characteristics.AllStat;
import com.epam.izh.rd.online.factory.pokemonFactory.PokemonCreateFactory;
import com.epam.izh.rd.online.uitl.Converter;
import com.epam.izh.rd.online.сonnection.PokeApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {

    private final Logger logger = LoggerFactory.getLogger(PokemonFetchingServiceImpl.class);

    private final PokeApiResponse pokeApiResponse;

    public PokemonFetchingServiceImpl(PokeApiResponse pokeApiResponse) {
        this.pokeApiResponse = pokeApiResponse;
    }

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon;
        try {
            AllStat pokemonAllStat = getStats(name);
            pokemon = PokemonCreateFactory.createPokemon(pokemonAllStat);

        } catch (IOException exception) {
            logger.info("Invalid pokemon name - " + exception);
            throw new IllegalArgumentException(exception);
        }
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException  {
        byte[] imageWinnerPokemon;
        try {
            String urlPokemonImage = getStats(name).getSprites().getUrlPokemonImage();
            imageWinnerPokemon = pokeApiResponse.createImageWinnerPokemon(urlPokemonImage);

        } catch (IOException exception) {
            logger.info("Failed method getPokemonImage() " + exception);
            throw new IllegalArgumentException(exception);
        }
        return imageWinnerPokemon;
    }

    private AllStat getStats(String name) throws IOException {
        String jsonPokemon = pokeApiResponse.responsePokeApiJson(name);
        Converter<AllStat> converter = new Converter<>();
        return converter.convertFromJson(jsonPokemon, AllStat.class);
    }
}
