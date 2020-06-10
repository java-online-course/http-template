package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {


    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        boolean queryMove = false;
        Pokemon winnerPokemon;
        System.out.println("Добро пожаловать!");
        System.out.println("Соперник 1: " + p1.getPokemonName() + " Здоровье: " + p1.getHp());
        System.out.println("Соперник 2: " + p2.getPokemonName() + " Здоровье: " + p2.getHp());
        if (p1.getPokemonId() >= p2.getPokemonId()) {
            System.out.println("Первый ходит: " + p1.getPokemonName());
            queryMove = true;
        } else {
            System.out.println("Первый ходит: " + p2.getPokemonName());
        }

        while (true) {
            if (queryMove) {
                doDamage(p1, p2);
                queryMove = false;
                if (p2.getHp() <= 0) {
                    showWinner(p1);
                    winnerPokemon = p1;
                    break;
                }
            } else {
                doDamage(p2, p1);
                queryMove = true;
                if (p1.getHp() <= 0 ) {
                    showWinner(p2);
                    winnerPokemon = p2;
                    break;
                }
            }
        }
        return winnerPokemon;
    }

    @Override
    public void showWinner(Pokemon winner) {
        System.out.println("Победитель: " + winner.getPokemonName());

    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short hundred = 100;
        short attack = from.getAttack();
        short defense = to.getDefense();
        short damage = (short) (attack * defense / hundred);
        to.setHp((short) (to.getHp() - damage));
        System.out.println(from.getPokemonName() + (" Нанес: ") + damage + " "
                + to.getPokemonName() + " здоровье: " + to.getHp());
    }
}
