package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

public class FightingClubService implements PokemonFightingClubService {
    private PokemonLoaderService pokemonLoaderService;

    public FightingClubService(PokemonLoaderService pokemonLoaderService) {
        this.pokemonLoaderService = pokemonLoaderService;
    }

    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        boolean isAttackOfFirstPokemon;
        isAttackOfFirstPokemon = p1.getPokemonId() > p2.getPokemonId();
        while (p1.getHp() > 0 && p2.getHp() > 0) {
            if (isAttackOfFirstPokemon) {
                doDamage(p1, p2);
                isAttackOfFirstPokemon = false;
            } else {
                doDamage(p2, p1);
                isAttackOfFirstPokemon = true;
            }
        }
        return p1.getHp() > 0 ? p1 : p2;
    }

    @Override
    public void showWinner(Pokemon winner) {
        pokemonLoaderService.getPokemonImage(winner.getPokemonName());
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        to.setHp((short) (to.getHp()-(from.getAttack() * to.getDefense() / 100)));
    }
}
