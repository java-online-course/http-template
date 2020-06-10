package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.сonnection.PokeApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonFetchingServiceImplTest {

    private PokemonFetchingService pokemonFetchingService;
    private Pokemon pokemon;

    @BeforeEach
    public void init() {
        pokemonFetchingService = new PokemonFetchingServiceImpl(new PokeApiResponse());
        pokemon = new Pokemon((short) 1, "bulbasaur", (short) 49, (short) 49, (short) 65);
    }

    @Test
    void testFetchByName() {
        Pokemon testPokemon = pokemonFetchingService.fetchByName("1");
        assertEquals(pokemon, testPokemon);
    }
}