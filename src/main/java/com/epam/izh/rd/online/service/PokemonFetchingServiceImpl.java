package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapperFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@PropertySource("classpath:application.properties")
@Component
public class PokemonFetchingServiceImpl implements PokemonFetchingService {

    final
    ObjectMapperFactory objectMapperFactory;

    private String apiServer;

    public PokemonFetchingServiceImpl(ObjectMapperFactory objectMapperFactory) {
        this.objectMapperFactory = objectMapperFactory;
    }

    @Value("${server-api}")
    public void setApiServer(String apiServer) {
        this.apiServer = apiServer;
    }

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        try {
            HttpResponse answer = getAnswer(apiServer + name);
            String json = new BasicResponseHandler().handleResponse(answer);
            return objectMapperFactory.getObjectMapper().readValue(json, Pokemon.class);
        } catch (IOException ex) {
            throw new IllegalArgumentException("No connection to the server");
        }
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        Pokemon pokemon = fetchByName(name);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage img = ImageIO.read(new URL(pokemon.getImageUrl()));
            ImageIO.write(img, "png", baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error download image");
        }
    }

    private HttpResponse getAnswer(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request);
    }

}
