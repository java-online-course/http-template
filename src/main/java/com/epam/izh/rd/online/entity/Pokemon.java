package com.epam.izh.rd.online.entity;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service Для маппинга
 * значений из массива stats рекомендуется использовать отдельный класс Stat и
 * аннотацию @JsonCreator
 */
public class Pokemon {

  /** Уникальный идентификатор, маппится из поля pokemonId */
  private long pokemonId;

  /** Имя покемона, маппится из поля pokemonName */
  private String pokemonName;

  /** Здоровье покемона, маппится из массива объектов stats со значением name: "hp" */
  private short hp;

  /** Атака покемона, маппится из массива объектов stats со значением name: "attack" */
  private short attack;

  /** Защита покемона, маппится из массива объектов stats со значением name: "defense" */
  private short defense;

  private String imageURLfrontDefault;

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

  public String getImageURLfrontDefault() {
    return imageURLfrontDefault;
  }

  public void setImageURLfrontDefault(String imageURLfrontDefault) {
    this.imageURLfrontDefault = imageURLfrontDefault;
  }
}
