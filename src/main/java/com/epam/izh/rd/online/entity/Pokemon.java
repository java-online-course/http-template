package com.epam.izh.rd.online.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинга значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    /**
     * Уникальный идентификатор, маппится из поля pokemonId
     */
    @JsonProperty("id")
    private long pokemonId;
    /**
     * Имя покемона, маппится из поля pokemonName
     */
    @JsonProperty("name")
    private String pokemonName;
    /**
     * Здоровье покемона, маппится из массива объектов stats со значением name: "hp"
     */
    private short hp;
    /**
     * Атака покемона, маппится из массива объектов stats со значением name: "attack"
     */
    private short attack;
    /**
     * Защита покемона, маппится из массива объектов stats со значением name: "defense"
     */
    private short defense;
    private Sprites sprite;

    public Pokemon() {
    }

    @JsonCreator
    public Pokemon(@JsonProperty("stats") List<Stats> stats, @JsonProperty("sprites") Sprites sprite) {
        this.hp = stats.get(0).getBaseStat();
        this.attack = stats.get(1).getBaseStat();
        this.defense = stats.get(2).getBaseStat();
        this.sprite = sprite;
    }

    public long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public short getHp() {
        return hp;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public short getAttack() {
        return attack;
    }

    public void setAttack(short attack) {
        this.attack = attack;
    }

    public short getDefense() {
        return defense;
    }

    public void setDefense(short defense) {
        this.defense = defense;
    }

    public Sprites getSprite() {
        return sprite;
    }

    public void setSprite(Sprites sprite) {
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "sprites=" + sprite +
                ", pokemonId=" + pokemonId +
                ", pokemonName='" + pokemonName + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokemonId == pokemon.pokemonId && hp == pokemon.hp && attack == pokemon.attack && defense == pokemon.defense && Objects.equals(pokemonName, pokemon.pokemonName) && Objects.equals(sprite, pokemon.sprite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, pokemonName, hp, attack, defense, sprite);
    }
}
