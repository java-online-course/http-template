package com.epam.izh.rd.online.test.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonFightingClubTest {
    final Pokemon PIKACHU = new Pokemon(25, "pikachu", Short.parseShort(String.valueOf(35)), Short.parseShort(String.valueOf(55)), Short.parseShort(String.valueOf(40)));
    final Pokemon SLOWPOKE = new Pokemon(79, "slowpoke", Short.parseShort(String.valueOf(90)), Short.parseShort(String.valueOf(65)), Short.parseShort(String.valueOf(65)));

    final PokemonFightingClubService FIGHTING_CLUB = new PokemonFightingClubService() {
        @Override
        public Pokemon doBattle(Pokemon p1, Pokemon p2) {
            return null;
        }

        @Override
        public void showWinner(Pokemon winner) {

        }

        @Override
        public void doDamage(Pokemon from, Pokemon to) {

        }
    };


    @Test
    void doBattle() {
        assertEquals(SLOWPOKE,FIGHTING_CLUB.doBattle(PIKACHU,SLOWPOKE));
        new File("/Users/maksim/IdeaProjects/http-template/slowpoke.png").delete();

    }

    @Test
    void showWinner() {
        File file = new File("/Users/maksim/IdeaProjects/http-template/slowpoke.png");
        FIGHTING_CLUB.showWinner(SLOWPOKE);
        assertTrue(file.delete());

    }

    @Test
    void doDamage() {
        FIGHTING_CLUB.doDamage(SLOWPOKE,PIKACHU);
        assertEquals(-4,PIKACHU.getHp());
    }
}
