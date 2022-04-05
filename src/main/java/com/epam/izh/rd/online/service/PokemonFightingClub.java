package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;


public class PokemonFightingClub implements PokemonFightingClubService{

    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        Pokemon current;
        Pokemon attack = p1.getPokemonId() < p2.getPokemonId() ? p1 : p2;

        while (p1.getHp() > 0 && p2.getHp() > 0) {
            current = p1 == attack ? p2 : p1;
            doDamage(attack , current);
            attack = current;
            System.out.println(p1.getHp());
            System.out.println(p2.getHp());
        }

        return p1.getHp() <= 0 ? p2 : p1;
    }

    @Override
    public void showWinner(Pokemon winner) {
     PokemonFetchingServiceImp fetchingServiceImp = new PokemonFetchingServiceImp();
     byte[] image = fetchingServiceImp.getPokemonImage(winner.getPokemonName());
        BufferedImage bf;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image)) {
            bf = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bf, "png", new File("win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short damage = (short) (from.getAttack() - from.getAttack() * to.getDefense() / 100);
       to.setHp((short) (to.getHp() - damage));
    }
}
