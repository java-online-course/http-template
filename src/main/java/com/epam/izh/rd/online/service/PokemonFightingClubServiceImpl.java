package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService{

    private final PokemonFetchingService pokemonFetchingService;


    public PokemonFightingClubServiceImpl(PokemonFetchingService pokemonFetchingService) {
        this.pokemonFetchingService = pokemonFetchingService;
    }

    /**
     * Инициирует бой между двумя покемонами, должен использовать метод doDamage
     *
     * @param p1 атакующий покемон
     * @param p2 защищающийся покемон
     * @return победителя
     */
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        Pokemon firstPokemon = null;
        Pokemon secondPokemon = null;
        if (p1.getPokemonId() > p2.getPokemonId()) {
            firstPokemon = p1;
            secondPokemon = p2;
        } else {
            firstPokemon = p2;
            secondPokemon = p1;
        }

        while (firstPokemon.getHp() >= 0 && secondPokemon.getHp() >= 0) {
            doDamage(firstPokemon, secondPokemon);
            doDamage(secondPokemon, firstPokemon);
        }

        return firstPokemon.getHp() <= 0 ? secondPokemon : firstPokemon;
    }

    /**
     * Метод загружает картинку победителя в корень проекта
     *
     * @param winner победитель
     */
    @Override
    public void showWinner(Pokemon winner) throws IOException {
        byte[] arrayWinnersImage = pokemonFetchingService.getPokemonImage(winner.getPokemonName());

        try(ByteArrayInputStream bis = new ByteArrayInputStream(arrayWinnersImage);) {
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "png", new File("winner.png") );
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("something wrong while creating image file");
        }
    }

    /**
     * Метод высчитывает урон покемона from и вычитает его из hp покемона to
     *
     * @param from атакующий покемон
     * @param to   защищающийся покемон
     */
    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        to.setHp((short) (to.getHp() - (from.getAttack() - (from.getAttack() * to.getDefense()/100))));
    }
}
