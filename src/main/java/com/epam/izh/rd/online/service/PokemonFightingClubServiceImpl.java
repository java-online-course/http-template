package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        boolean isAttackP1 = true;
        if (p1.getPokemonId() > p2.getPokemonId()) {
            isAttackP1 = false;
        }

        while (p1.getHp() > 0 && p2.getHp() > 0) {
            if (isAttackP1) {
                doDamage(p1, p2);
            } else {
                doDamage(p2, p1);
            }
            isAttackP1 = !isAttackP1;
        }

        if (p1.getHp() > 0) {
            return p1;
        } else {
            return p2;
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
        try {
            File file = new File(System.getProperty("user.dir") + "\\winner.png");
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] data = winner.getImage();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error save image");
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        int damage = from.getAttack() - (from.getAttack() * to.getDefense() / 100);
        int newHp = to.getHp() - damage;
        to.setHp((short) newHp);
    }
}
