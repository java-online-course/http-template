package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImp;
import com.epam.izh.rd.online.service.PokemonFightingClub;

public class Http {
    public static void main(String[] args) {
        PokemonFightingClub fightingClub = new PokemonFightingClub();
        Pokemon winner = fightingClub.doBattle(new PokemonFetchingServiceImp().fetchByName("pikachu"), new PokemonFetchingServiceImp().fetchByName("slowpoke"));
        fightingClub.showWinner(winner);
        System.out.println("победитель " + winner.getPokemonName());
    }
}
