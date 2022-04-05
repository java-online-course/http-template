package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMappers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class PokemonFetchingServiceImp implements PokemonFetchingService{

    ObjectMappers objectMappers = new ObjectMappers();
    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {

        Pokemon pokemon;
        String uri = "https://pokeapi.co/api/v2/pokemon/" + name;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(uri).openConnection();
            connection.addRequestProperty("User-agent", "");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text = reader.lines().collect(Collectors.joining());
            pokemon = objectMappers.getObjectMapper().readValue(text, Pokemon.class);
        }catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Покемон отсутствует");
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        byte[] imagePokemon = null;
        HttpURLConnection connection = null;
       try {
           URL url = new URL(fetchByName(name).getImageUrl());
           connection =(HttpURLConnection) url.openConnection();
           imagePokemon = new byte[connection.getContentLength()];
           connection.getInputStream().read(imagePokemon);
       }catch (IOException e){
           e.printStackTrace();
       } finally {
           if (connection != null) {
               connection.disconnect();
           }
       }
           return imagePokemon;
    }
}
