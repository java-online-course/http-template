package com.epam.izh.rd.online.entity.characteristics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AllStat {

    private final String name;
    private final int id;
    private final List<Stat> statsList;
    private final Sprites sprites;

    @JsonCreator
    public AllStat(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("stats") List<Stat> statsList,
                   @JsonProperty("sprites") Sprites sprites) {

        this.id = id;
        this.name = name;
        this.statsList = statsList;
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Stat> getStatsList() {
        return statsList;
    }

    public Sprites getSprites() {
        return sprites;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", statsList=" + statsList +
                ", sprites=" + sprites +
                '}';
    }

    public static class Sprites {

        private final String urlPokemonImage;

        @JsonCreator
        public Sprites(@JsonProperty("front_default") String urlPokemonImage) {
            this.urlPokemonImage = urlPokemonImage;
        }

        public String getUrlPokemonImage() {
            return urlPokemonImage;
        }


        @Override
        public String toString() {
            return "Sprites{" +
                    "urlPokemonImage='" + urlPokemonImage + '\'' +
                    '}';
        }
    }
}
