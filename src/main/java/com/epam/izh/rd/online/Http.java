package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;

import java.io.IOException;

public class Http {
    public static void main(String[] args) throws IOException {
        PokemonFetchingServiceImpl servicePokemon = new PokemonFetchingServiceImpl("https://pokeapi.co/api/v2/pokemon/");
        Pokemon pokemonPikachu = servicePokemon.fetchByName("pikachu");
        Pokemon pokemonSlowpoke = servicePokemon.fetchByName("slowpoke");

        byte[] bytesPikachu = servicePokemon.getPokemonImage("pikachu");

        PokemonFightingClubService pokemonFightingClubService = new PokemonFightingClubServiceImpl(servicePokemon);

        Pokemon winner = pokemonFightingClubService.doBattle(pokemonSlowpoke, pokemonPikachu);
        pokemonFightingClubService.showWinner(winner);
        System.out.println(winner);
    }
}
