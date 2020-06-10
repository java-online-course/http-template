package com.epam.izh.rd.online.сonnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PokeApiResponse {

    public byte[] createImageWinnerPokemon(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        return inputStream.readAllBytes();
    }

    public String responsePokeApiJson(String pokemonName) throws IOException {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokemonName);
        URLConnection urlConnection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            return jsonBuilder.toString();
        }
    }
}
