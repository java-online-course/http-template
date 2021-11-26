package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonFetchingServiceTest {

    private static WireMockServer wireMockServer;
    private static PokemonFetchingService pokemonFetchingService;

    @BeforeAll
    public static void setup () {
        pokemonFetchingService = new PokemonFetchingServiceImpl("http://localhost:9090/pokeapi.co/api/v2/pokemon/");
        wireMockServer = new WireMockServer(9090);
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/pokeapi.co/api/v2/pokemon/pikachu"))
                .willReturn(aResponse().withHeader("User-Agent", "User")
                        .withStatus(200)
                        .withBodyFile("pikachu.json")));

        wireMockServer.stubFor(get(urlEqualTo("/pokeapi.co/api/v2/pokemon/slowpoke"))
                .willReturn(aResponse().withHeader("User-Agent", "User")
                        .withStatus(200)
                        .withBodyFile("slowpoke.json")));

        wireMockServer.stubFor(get(urlEqualTo("http://localhost:9090/raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"))
                .willReturn(aResponse().withHeader("User-Agent", "User")
                        .withStatus(200)
                        .withBodyFile("25.png")));

        wireMockServer.stubFor(get(urlEqualTo("http://localhost:9090/raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/79.png"))
                .willReturn(aResponse().withHeader("User-Agent", "User")
                        .withStatus(200)
                        .withBodyFile("79.png")));
    }

    @AfterAll
    public static void close () {
        wireMockServer.stop();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pikachu", "slowpoke"})
    public void TestByFetchByName(String name) throws IOException {

        Pokemon pokemon = pokemonFetchingService.fetchByName(name);

        assertEquals(pokemon.getPokemonName(), name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pikachu", "slowpoke"})
    public void TestByFetchImageByName(String name) throws IOException {

        byte[] byteImage = pokemonFetchingService.getPokemonImage(name);

        assertEquals(byteImage.getClass(), byte[].class);
    }
}
