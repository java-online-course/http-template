package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинга значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Pokemon {

    @JsonCreator
    public Pokemon(@JsonProperty("stats") Stat[] stats, @JsonProperty("sprites") Sprite sprite) {
        this.hp = stats[0].getStatValue();
        this.attack = stats[1].getStatValue();
        this.defense = stats[2].getStatValue();
        this.imageUrl = sprite.getImageUrl();
    }

    public Pokemon(long pokemonId, String pokemonName, short hp, short attack, short defense, String imageUrl) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.imageUrl = imageUrl;
    }

    @JsonProperty("front_default")
    private String imageUrl;
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

    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getPokemonId() {
        return pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
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

    public short getDefense() {
        return defense;
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
                Objects.equals(imageUrl, pokemon.imageUrl) &&
                Objects.equals(pokemonName, pokemon.pokemonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, pokemonId, pokemonName, hp, attack, defense);
    }
}
