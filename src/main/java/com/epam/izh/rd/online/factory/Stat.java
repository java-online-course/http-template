package com.epam.izh.rd.online.factory;

import com.epam.izh.rd.online.entity.Pokemon;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Stat {
    private long pokemonId;
    private short hp;
    private short attack;
    private short defense;

    @JsonCreator
    public Stat(JSONArray jsonArray) {

        for (Object slide : jsonArray) {

            JSONObject statsElement = (JSONObject) slide;
            long statValue = (long) statsElement.get("base_stat");
            JSONObject statName = (JSONObject) statsElement.get("stat");
            switch (statName.get("name").toString()) {
                case "pokemonId":
                    pokemonId = statValue;
                    break;
                case "hp":
                    hp = (short) statValue;
                    break;
                case "attack":
                    attack = (short) statValue;
                    break;
                case "defense":
                    defense = (short) statValue;
                    break;
            }

        }


    }

    public Pokemon createPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setHp(hp);
        pokemon.setAttack(attack);
        pokemon.setDefense(defense);
        pokemon.setPokemonId(pokemonId);
        return pokemon;
    }
}
