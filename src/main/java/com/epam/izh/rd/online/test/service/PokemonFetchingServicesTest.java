package com.epam.izh.rd.online.test.service;

import com.epam.izh.rd.online.service.PokemonFethingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonFetchingServicesTest {
    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        stubFor(get(urlEqualTo("/pokemon/pikachu/"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    void fetchByName() {
        assertEquals("pikachu", new PokemonFethingServiceImpl().fetchByName("pikachu").getPokemonName());
    }

    @Test
    void getJsonText() throws IOException {
        assertNotNull(new PokemonFethingServiceImpl().fetchByName("pikachu"));
    }

    @Test
    void getJsonTextException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new PokemonFethingServiceImpl().fetchByName("max");
        });
        assertNotNull(throwable.getMessage());
    }

//    @Test
//    public void testStatusCodePositive() {
//        given().
//                when().
//                get("http://localhost:8080/pokemon/pikachu/").
//                then().
//                assertThat().statusCode(200);
//        tearDown();
//    }

    @Test
    void getPokemonImage() {
        byte[] bytes = new byte[0];
        bytes = new PokemonFethingServiceImpl().getPokemonImage("slowpoke");
        assertNotNull(bytes);
    }


    public static void tearDown(){
        wireMockServer.stop();
    }
}
