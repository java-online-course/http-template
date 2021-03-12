package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.SimplePokemonFetchingService;
import com.epam.izh.rd.online.service.SimplePokemonFightingClubService;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class ServicesTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);
    SimplePokemonFetchingService service = new SimplePokemonFetchingService();
    SimplePokemonFightingClubService fightingService = new SimplePokemonFightingClubService();

    @Test
    public void testSimplePokemonFetchByName(){
        String json = "{\"id\": 132," +
            "\"name\": \"ditto\"," +
                " \"stats\": [\n" +
                "    {\n" +
                "      \"base_stat\": 48,\n" +
                "      \"effort\": 1,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"hp\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/1/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 48,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"attack\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/2/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 48,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"defense\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/3/\"\n" +
                "      }\n" +
                "    }" +
                "}}";
        wireMockRule.stubFor(get(urlMatching("api/v2/pokemon/"))
                .willReturn(aResponse().withBody(json)));
        String notice = "Ваш покемон не создан или некорректен!";
        Pokemon pokemon = service.fetchByName("ditto");
        assertEquals( pokemon.getPokemonId(), 132, notice);
        assertEquals(pokemon.getPokemonName(), "ditto", notice);
        assertEquals(pokemon.getHp(), 48, notice);
        assertEquals(pokemon.getAttack(), 48, notice);
        assertEquals(pokemon.getDefense(), 48, notice);
    }
    @Test
    public void testGetPokemonImage(){
        byte[] arrayBytes = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 96, 0, 0, 0, 96, 4, 3, 0, 0, 0, 16, -74, 106, 11, 0, 0, 0, 48, 80, 76, 84, 69, 0, 0, 0, 0, 0, 0, 90, 24, -108, -100, 90, -76, -76, 106, -51, -59, 123, -26, -26, -92, -10, -1, -51, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -114, -28, -53, 28, 0, 0, 0, 1, 116, 82, 78, 83, 0, 64, -26, -40, 102, 0, 0, 1, 63, 73, 68, 65, 84, 120, -38, -19, -107, -67, 78, -61, 48, 16, -57, -49, 73, 37, -38, 9, 39, 98, 98, -88, -14, -79, 50, -76, 82, -59, -62, -64, -52, 11, -16, -76, 32, -15, 0, 60, -125, -29, 110, -111, -118, 114, 81, 7, -16, 0, 49, -7, 16, 33, 33, 78, 109, 36, 6, -122, -5, 43, 31, -10, 37, 63, -3, -17, -20, -117, 2, 64, 34, -111, 72, 36, -46, 31, -56, 55, 71, 35, 80, -65, 2, -82, -17, -76, -116, -103, 114, 6, -110, -101, 55, -71, -70, -113, -124, 114, 2, -4, -19, -54, 63, 87, 120, 21, -88, 51, 105, 0, 22, -109, -56, -18, 54, 3, -48, -95, -58, -46, -104, -83, 55, 49, -40, 52, 87, -58, 1, -31, 24, -71, 0, 26, -79, 31, -121, 78, -128, -82, 83, 121, 106, -121, 113, -102, -72, 20, 125, -88, 19, -87, -113, 119, 15, 24, 92, -66, -106, 86, -121, 70, 85, -65, 26, 91, 123, 74, -16, 82, 7, -97, -27, 108, 107, 24, 29, 96, -9, -15, -16, 56, -61, 44, -52, -31, 52, -19, -18, 123, 7, 7, 54, -100, 8, 7, 32, 29, -116, -117, -60, -79, -122, 47, -107, 14, 69, -77, -51, 96, -62, -21, 22, -79, 1, 23, -61, 9, 7, 43, -32, 69, -29, -108, 66, -85, 67, 53, 6, 38, 57, -3, 4, 98, -56, -58, 112, 108, -39, -72, -110, 107, 1, -84, 125, 75, -114, -35, 78, 45, -85, 110, -50, 106, -67, 118, 105, -115, -94, 45, 58, -117, 1, -113, 70, -125, -103, 94, -46, -33, 45, -127, -74, 15, 72, 5, -107, 100, -53, 14, -53, -40, 82, -28, 86, 64, 33, -122, 29, 80, -28, 60, -57, -109, -67, -39, -81, 68, -48, 84, 34, -48, -12, -52, 8, 116, -37, -123, -12, -25, 33, -111, 72, 36, -46, 63, -46, 39, 36, 108, 82, -61, -21, 96, 108, 38, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};
        assertArrayEquals(service.getPokemonImage("ditto"),arrayBytes, "Метод реализован не верно");

    }
    @Test
    public void testShowWinner(){
        Pokemon poke1 = service.fetchByName("slowpoke");
        Pokemon poke2 = service.fetchByName("ditto");
        assertEquals(fightingService.doBattle(poke1,poke2),poke1,"Метод реализован не верно");
    }
    @Test
    public void showWinner(){
        fightingService.showWinner(service.fetchByName("slowpoke"));
        assertTrue(new File("resultImage.png").exists(),"Изображение не сохранилось");
    }
}
