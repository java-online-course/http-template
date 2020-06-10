package com.epam.izh.rd.online.controller;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;

public class GameController {

    private final PokemonFightingClubService fightingClubService;

    public GameController() {
        this.fightingClubService = new PokemonFightingClubServiceImpl();
    }

    public Pokemon doBattle(Pokemon pokemonOne, Pokemon PokemonTwo) {
        return fightingClubService.doBattle(pokemonOne, PokemonTwo);
    }

}
