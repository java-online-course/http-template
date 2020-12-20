package com.epam.izh.rd.online;

import com.epam.izh.rd.online.configuration.MainConfiguration;
import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=MainConfiguration.class)
public class PokemonFetchingServiceTest {


    @Autowired
    PokemonFetchingServiceImpl pokemonFetchingService;

    @Autowired
    PokemonFightingClubService pokemonFightingClubService;

    WireMockConfiguration config = new WireMockConfiguration();
    WireMockServer wireMockServer = new WireMockServer(config.withRootDirectory("src/test/resources/server_path/").port(8080));

    @Before
    public void setup(){
        wireMockServer.start();
    }

    @After
    public void after() {
        wireMockServer.stop();
    }


    @Test
    public void testConvertJsonToObject() {
        Pokemon pokemon1 = new Pokemon(25L, "pikachu", (short) 35, (short) 55, (short) 40, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png");
        Pokemon pokemon2 = pokemonFetchingService.fetchByName("pikachu.json");
        assertEquals(pokemon1, pokemon2);
    }

    @Test
    public void testBattle() {
        Pokemon pokemon1 = pokemonFetchingService.fetchByName("pikachu.json");
        Pokemon pokemon2 = pokemonFetchingService.fetchByName("ditto.json");
        Pokemon winner = pokemonFightingClubService.doBattle(pokemon1, pokemon2);

        assertEquals(winner, pokemon1);
    }

    @Test
    public void testLoadImg() {
        Pokemon pokemon = new Pokemon(132L, "ditto", (short) 35, (short) 55, (short) 40, "");
        pokemon.setImage(pokemonFetchingService.getPokemonImage("ditto.json"));
        assertNotNull(pokemon.getImage());
    }

}
