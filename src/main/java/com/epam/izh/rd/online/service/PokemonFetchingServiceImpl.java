package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class PokemonFetchingServiceImpl implements PokemonFetchingService{

    private final ObjectMapper mapper = new ObjectMapperFactoryImpl().getObjectMapper();
    private final String url;

    public PokemonFetchingServiceImpl(String url) {
        this.url = url;
    }

    private HttpURLConnection configuredUrlConnection(URL url) throws IOException {
        HttpURLConnection httpUrlConnection = null;
        try {
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.addRequestProperty("User-Agent", "User");
            return httpUrlConnection;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Input error occurs while creating the input stream");
        } finally {
            if(httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
    }


    /**
     * @param name - имя покемона
     * @return сущность Pokemon
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException, IOException {
        try{
            HttpURLConnection httpConnection = configuredUrlConnection(new URL(this.url + name));

            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

            String jsonText = bufferedReader.lines()
                    .collect(Collectors.joining());

            return mapper.readValue(jsonText, Pokemon.class);
        } catch (FileNotFoundException e){
            throw new IllegalArgumentException("pokemon's name is incorrect");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Input error occurs while creating the input stream");
        }
    }

    /**
     * @param name - имя покемона
     * @return картинка покемона в виде массива байтов
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException, IOException {
        URL pokemonImageUrl = fetchByName(name).getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (InputStream is = configuredUrlConnection(pokemonImageUrl).getInputStream()) {

            byte[] byteChunk = new byte[1024];
            int n;

            while ((n = is.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }

            return baos.toByteArray();

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("pokemon's name is incorrect");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("failed while reading bytes from file");
        }
        }
}
