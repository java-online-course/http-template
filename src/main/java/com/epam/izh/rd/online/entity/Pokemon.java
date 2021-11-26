package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинга значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */
@Data
@NoArgsConstructor
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


    private URL image;

    @JsonCreator
    public Pokemon(
            @JsonProperty("id") long pokemonId,
            @JsonProperty("name") String pokemonName,
            @JsonProperty("stats") List<Stats> stats,
            @JsonProperty("sprites") Sprite sprite) {

        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        Map<String, Short> mapStatByBaseName = getMapStatsByBaseStat(stats);
        this.hp = mapStatByBaseName.get("hp");
        this.attack = mapStatByBaseName.get("attack");
        this.defense = mapStatByBaseName.get("defense");
        this.image = sprite.getImageUrl();
    }


    private Map<String, Short> getMapStatsByBaseStat(List<Stats> stats) {
        Map<String, Short> mapStats = new HashMap<>();

        for (Stats stat : stats) {
            mapStats.put(stat.getStat().getName(), stat.getBaseStat());
        }
        return mapStats;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprite {

        private URL imageUrl;

        @SneakyThrows
        @JsonCreator
        public Sprite(@JsonProperty("front_default") String uri) {
            this.imageUrl = new URL(uri);
        }
    }
}
