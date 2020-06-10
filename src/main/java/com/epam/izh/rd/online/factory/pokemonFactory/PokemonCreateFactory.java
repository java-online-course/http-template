package com.epam.izh.rd.online.factory.pokemonFactory;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.entity.characteristics.Stats;

public class PokemonCreateFactory {

    public static Pokemon createPokemon(Stats stats) {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(stats.getId());
        pokemon.setPokemonName(stats.getName());
        pokemon.setHp((short) stats.getStatsList().get(1).getBaseStat());
        pokemon.setAttack((short) stats.getStatsList().get(2).getBaseStat());
        pokemon.setDefense((short) stats.getStatsList().get(3).getBaseStat());
        return pokemon;
    }
}
