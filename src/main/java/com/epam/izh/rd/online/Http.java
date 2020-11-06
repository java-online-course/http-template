package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.FightingClubService;
import com.epam.izh.rd.online.service.PokemonLoaderService;

public class Http {
    public static void main(String[] args) {

        PokemonLoaderService pokemonLoaderService = new PokemonLoaderService();
        FightingClubService fightingClubService = new FightingClubService(pokemonLoaderService);
        Pokemon pikachu = pokemonLoaderService.fetchByName("pikachu");
        Pokemon slowpoke = pokemonLoaderService.fetchByName("slowpoke");
        fightingClubService.showWinner(fightingClubService.doBattle(pikachu,slowpoke));
    }
}
