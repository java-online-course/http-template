package com.epam.izh.rd.online.entity.characteristics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stats {

    private String name;
    private int id;
    private List<Stat> statsList;
    private Sprites sprites;

    @JsonCreator
    public Stats(@JsonProperty("id") int id,
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

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Stat> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<Stat> statsList) {
        this.statsList = statsList;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
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

        private String urlPokemonImage;

        @JsonCreator
        public Sprites(@JsonProperty("front_default") String urlPokemonImage) {
            this.urlPokemonImage = urlPokemonImage;
        }

        public String getUrlPokemonImage() {
            return urlPokemonImage;
        }

        public void setUrlPokemonImage(String urlPokemonImage) {
            this.urlPokemonImage = urlPokemonImage;
        }

        @Override
        public String toString() {
            return "Sprites{" +
                    "urlPokemonImage='" + urlPokemonImage + '\'' +
                    '}';
        }
    }
}
