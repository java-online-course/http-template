package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private long pokemonId;

    /**
     * Имя покемона, маппится из поля pokemonName
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokemonId == pokemon.pokemonId &&
                hp == pokemon.hp &&
                attack == pokemon.attack &&
                defense == pokemon.defense &&
                Objects.equals(pokemonName, pokemon.pokemonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, pokemonName, hp, attack, defense);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", pokemonName='" + pokemonName + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
