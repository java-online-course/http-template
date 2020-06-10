package com.epam.izh.rd.online;

import com.epam.izh.rd.online.controller.GameController;
import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.сonnection.PokeApiResponse;

import java.util.Scanner;
import java.util.concurrent.Callable;


public class Http {
    public static void main(String[] args) throws Exception {
       Pokemon pokemonOne = new PokemonThread().call();
       Pokemon pokemonTwo = new PokemonThread().call();
       GameController gameController = new GameController();
       Pokemon winnerPokemon = gameController.doBattle(pokemonOne, pokemonTwo);
    }

    public static class PokemonThread implements Callable<Pokemon> {

        @Override
        public Pokemon call() {
            Scanner scanner = new Scanner(System.in);
            return new PokemonFetchingServiceImpl(new PokeApiResponse()).fetchByName(scanner.nextLine());

        }
    }
}
