package com.epam.izh.rd.online;

import com.epam.izh.rd.online.configuration.MainConfiguration;
import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Http {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);

        PokemonFetchingService pokemonFetchingService = ctx.getBean(PokemonFetchingService.class);
        PokemonFightingClubService pokemonFightingClubService = ctx.getBean(PokemonFightingClubService.class);

        Pokemon pokemon1 = pokemonFetchingService.fetchByName("pikachu");
        Pokemon pokemon2 = pokemonFetchingService.fetchByName("slowpoke");
        System.out.println("Сражаются " + pokemon1.getPokemonName() + " и " + pokemon2.getPokemonName());

        Pokemon winner = pokemonFightingClubService.doBattle(pokemon1, pokemon2);
        System.out.println("Победил " + winner.getPokemonName());

        winner.setImage(pokemonFetchingService.getPokemonImage(winner.getPokemonName()));
        pokemonFightingClubService.showWinner(winner);


    }
}
