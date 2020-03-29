package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.Stat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PokemonLoaderService implements PokemonFetchingService {
    public static HttpURLConnection connection;

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        JSONParser jsonParser = new JSONParser();
        Object object = null;
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "User");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            object = jsonParser.parse(new BufferedReader(new InputStreamReader(connection.getInputStream())));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) object;
        JSONArray stats = (JSONArray) jsonObject.get("stats");
        Pokemon poke = new Stat(stats).createPokemon();
        poke.setPokemonName((String) jsonObject.get("name"));
        poke.setPokemonId((long) jsonObject.get("id"));
        connection.disconnect();
        return poke;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "User");


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject sprites = (JSONObject) jsonObject.get("sprites");
            URL url = new URL((String) sprites.get("front_default"));
            InputStream in = url.openStream();
            Files.copy(in, Paths.get("winner.jpg"));
            byte[] imgInBytes = Files.readAllBytes(Paths.get("winner.jpg"));
            in.close();
            return imgInBytes;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
