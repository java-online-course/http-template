package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import java.io.IOException;

/**
 * Сервис по загрузке данных покемонов из внешнего сервиса
 */
public interface PokemonFetchingService {

    /**
     * @param name - имя покемона
     * @return сущность Pokemon
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    Pokemon fetchByName(String name) throws IllegalArgumentException;

    /**
     * @param name - имя покемона
     * @return картинка покемона в виде массива байтов
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    byte[] getPokemonImage(String name) throws IllegalArgumentException, IOException;
}
