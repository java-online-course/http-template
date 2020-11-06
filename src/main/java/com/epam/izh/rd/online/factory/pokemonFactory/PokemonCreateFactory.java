package com.epam.izh.rd.online.factory.pokemonFactory;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.entity.characteristics.AllStat;

public class PokemonCreateFactory {

    public static Pokemon createPokemon(AllStat allStat) {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(allStat.getId());
        pokemon.setPokemonName(allStat.getName());
        pokemon.setHp((short) allStat.getStatsList().get(1).getBaseStat());
        pokemon.setAttack((short) allStat.getStatsList().get(2).getBaseStat());
        pokemon.setDefense((short) allStat.getStatsList().get(3).getBaseStat());
        return pokemon;
    }
}
