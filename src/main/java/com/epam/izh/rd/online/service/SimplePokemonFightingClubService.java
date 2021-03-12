package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class SimplePokemonFightingClubService implements PokemonFightingClubService {
    /**
     * Инициирует бой между двумя покемонами, должен использовать метод doDamage
     *
     * @param p1 атакующий покемон
     * @param p2 защищающийся покемон
     * @return победителя
     */
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        while (p1.getHp() > 0 && p2.getHp() > 0) {
            doDamage(p1, p2);
            if (p1.getHp() > 0 && p2.getHp() > 0) {
                doDamage(p2, p1);
            }
        }
        if (p1.getHp() > 0) {
            return p1;
        } else {
            return p2;
        }
    }

    /**
     * Метод загружает картинку победителя в корень проекта
     *
     * @param winner победитель
     */
    @Override
    public void showWinner(Pokemon winner) {
        SimplePokemonFetchingService service = new SimplePokemonFetchingService();
        byte[] resByteArray = service.getPokemonImage(winner.getPokemonName());
        try {
            BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(resByteArray));
            ImageIO.write(resultImage, "png", new File("resultImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
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
        to.setHp((short) (to.getHp() - (from.getAttack() - (from.getAttack() * to.getDefense() / 100))));
    }
}
