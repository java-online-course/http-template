package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.entity.characteristics.Stats;
import com.epam.izh.rd.online.factory.pokemonFactory.PokemonCreateFactory;
import com.epam.izh.rd.online.uitl.Converter;
import com.epam.izh.rd.online.сonnection.PokeApiResponse;


import java.io.IOException;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {

    private final PokeApiResponse pokeApiResponse;

    public PokemonFetchingServiceImpl(PokeApiResponse pokeApiResponse) {
        this.pokeApiResponse = pokeApiResponse;
    }

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon = null;
        try {
            Stats pokemonStats = getStats(name);
            pokemon = PokemonCreateFactory.createPokemon(pokemonStats);

        } catch (IOException exception) {
            System.out.println("Invalid pokemon name - " + exception);
        }
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        byte[] imageWinnerPokemon = new byte[0];
        try {
            String urlPokemonImage = getStats(name).getSprites().getUrlPokemonImage();
            imageWinnerPokemon = pokeApiResponse.createImageWinnerPokemon(urlPokemonImage);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return imageWinnerPokemon;
    }

    private Stats getStats(String name) throws IOException {
        String jsonPokemon = pokeApiResponse.responsePokeApiJson(name);
        Converter<Stats> converter = new Converter<>();
        return converter.convertFromJson(jsonPokemon, Stats.class);
    }
}
