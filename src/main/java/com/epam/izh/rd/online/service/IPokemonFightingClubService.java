package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

/** Сервис по проведению боёв между покемонами */
public interface IPokemonFightingClubService {

  /**
   * Инициирует бой между двумя покемонами, должен использовать метод doDamage
   *
   * @param p1 атакующий покемон
   * @param p2 защищающийся покемон
   * @return победителя
   */
  Pokemon doBattle(Pokemon p1, Pokemon p2);

  /**
   * Метод загружает картинку победителя в корень проекта
   *
   * @param winner победитель
   */
  void showWinner(Pokemon winner);

  /**
   * Метод высчитывает урон покемона from и вычитает его из hp покемона to
   *
   * @param from атакующий покемон
   * @param to защищающийся покемон
   */
  void doDamage(Pokemon from, Pokemon to);
}
