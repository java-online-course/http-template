package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.SimpleObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimplePokemonFetchingService implements PokemonFetchingService {
    /**
     * @param name - имя покемона
     * @return сущность Pokemon
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon = new Pokemon();
        String urlPath = "https://pokeapi.co/api/v2/pokemon/" + name;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "User-Agent");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            SimpleObjectMapper simpleObjectMapper = new SimpleObjectMapper();
            pokemon = simpleObjectMapper.getObjectMapper().readValue(bufferedReader, Pokemon.class);
            connection.disconnect();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Возможно вы ввели неверное имя покемона!");
        }
        return pokemon;
    }

    /**
     * @param name - имя покемона
     * @return картинка покемона в виде массива байтов
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        Pokemon pokemon = fetchByName(name);
        try {
            URL url = new URL(pokemon.getSprite().getFrontDefault());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "User-Agent");
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            BufferedImage image = ImageIO.read(ImageIO.createImageInputStream(connection.getInputStream()));
            ImageIO.write(image, "png", baos);
            baos.flush();
            String base64String = Base64.encode(baos.toByteArray());
            baos.close();
            byte[] resByteArray = Base64.decode(base64String);
            connection.disconnect();
            return resByteArray;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Возможно вы ввели неверное имя покемона!");
        }
    }
}
