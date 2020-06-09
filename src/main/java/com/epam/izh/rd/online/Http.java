package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.characteristics.Stats;
import com.epam.izh.rd.online.uitl.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Http {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
        URLConnection urlConnection = url.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Converter<Stats> converter = new Converter<>();
        Stats stats = converter.convertFromJson(stringBuilder.toString(), Stats.class);
        System.out.println("____________________________");
        System.out.println(stats.toString());
    }
}
