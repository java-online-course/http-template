package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImp;
import com.epam.izh.rd.online.service.PokemonFightingClub;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PokemonFightingClubTest {
    static Pokemon p1;
    static Pokemon p2;
    static PokemonFetchingService pokemonFetchingService;
    static PokemonFightingClubService pokemonFightingClubService;

    private static WireMockServer wireMockServer;

    @BeforeEach
    public void fetchingPokemon(){
        p1 = pokemonFetchingService.fetchByName("pikachu");
        p2 = pokemonFetchingService.fetchByName("slowpoke");
    }


    @BeforeAll
    public static void setup () {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        setupStub();

        pokemonFetchingService = new PokemonFetchingServiceImp();
        pokemonFightingClubService = new PokemonFightingClub();
    }

    public static void setupStub() {

        wireMockServer.stubFor(get(urlMatching("/pokeapi.co/api/v2/pokemon/.*")).atPriority(5)
                .willReturn(aResponse().withStatus(401)));

        stubFor(get(urlEqualTo("/api/v2/pokemon/pikachu"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("pikachu.json")
                )
        );
        stubFor(get(urlEqualTo("/api/v2/pokemon/pikachus")).willReturn(aResponse().withHeader("User-Agent", "")
                        .withBody("Not Found")
                        .withStatus(404)
                )
        );

        stubFor(get(urlEqualTo("/sprites/pokemon/25.png")).willReturn(aResponse().withHeader("Content-Type", "image/png")
                        .withBodyFile("25.png")
                )
        );

    }

    @Test
    public void battleTest(){
        assertThat(p2, CoreMatchers.is(pokemonFightingClubService.doBattle(p1,p2)));
    }

    @Test
    public void doDamageTest(){

        pokemonFightingClubService.doDamage(p1, p2);
        assertEquals(35, p1.getHp());
    }

}
