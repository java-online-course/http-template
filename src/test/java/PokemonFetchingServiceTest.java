import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonLoaderService;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PokemonFetchingServiceTest {
    private PokemonLoaderService pokemonLoaderService;
    @ClassRule
    public static final WireMockClassRule wireMockRule = new WireMockClassRule(8080);

    @Before
    public void setUp() {
        pokemonLoaderService = new PokemonLoaderService();
    }

    @Before
    @After
    public void closeUp() {
        File winner = new File("winner.jpg");
        winner.delete();
    }

    @Test
    public void fetchByNameTest() {
        String jsonBody = "{\n" +
                "  \"abilities\": [\n" +
                "    {\n" +
                "      \"ability\": {\n" +
                "        \"name\": \"lightning-rod\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/ability/31/\"\n" +
                "      },\n" +
                "      \"is_hidden\": true,\n" +
                "      \"slot\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"ability\": {\n" +
                "        \"name\": \"static\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/ability/9/\"\n" +
                "      },\n" +
                "      \"is_hidden\": false,\n" +
                "      \"slot\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"forms\": [\n" +
                "    {\n" +
                "      \"name\": \"pikachu\",\n" +
                "      \"url\": \"https://pokeapi.co/api/v2/pokemon-form/25/\"\n" +
                "    }\n" +
                "  ],\n" +
                "\n" +
                "  \"height\": 4,\n" +
                "  \"id\": 25,\n" +
                "  \"name\": \"pikachu\",\n" +
                " \n" +
                "  \n" +
                "  \"sprites\": {\n" +
                "    \"back_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png\",\n" +
                "    \"back_female\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/female/25.png\",\n" +
                "    \"back_shiny_female\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/female/25.png\",\n" +
                "    \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png\",\n" +
                "    \"front_shiny_female\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/female/25.png\"\n" +
                "  },\n" +
                "  \"stats\": [\n" +
                "    {\n" +
                "      \"base_stat\": 90,\n" +
                "      \"effort\": 2,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"speed\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/6/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 50,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"special-defense\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/5/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 50,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"special-attack\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/4/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 40,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"defense\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/3/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 55,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"attack\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/2/\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"base_stat\": 35,\n" +
                "      \"effort\": 0,\n" +
                "      \"stat\": {\n" +
                "        \"name\": \"hp\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/stat/1/\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"types\": [\n" +
                "    {\n" +
                "      \"slot\": 1,\n" +
                "      \"type\": {\n" +
                "        \"name\": \"electric\",\n" +
                "        \"url\": \"https://pokeapi.co/api/v2/type/13/\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "\n" +
                "}\n";
        wireMockRule.stubFor(get(urlMatching(".*/pokemon/.*"))
                .willReturn(aResponse().withBody(jsonBody)));


        Pokemon pikachu = pokemonLoaderService.fetchByName("pikachu");
        assertEquals(pikachu.getHp(), 35);
        assertEquals(pikachu.getPokemonId(), 25);


    }

    @Test
    public void getPokemonImageTest() {
        byte[] slowpokeBytes = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 96, 0, 0, 0, 96, 4, 3, 0, 0, 0, 16, -74, 106, 11, 0, 0, 0, 42, 80, 76, 84, 69, 0, 0, 0, 16, 16, 16, 106, 98, 98, 123, 32, 49, -117, 90, 24, -84, 65, 82, -43, -51, -51, -34, 98, 123, -34, -92, 98, -18, -59, -117, -1, -125, -108, -1, -92, -92, -1, -26, -76, -1, -1, -1, -114, 0, 112, 24, 0, 0, 0, 1, 116, 82, 78, 83, 0, 64, -26, -40, 102, 0, 0, 2, 37, 73, 68, 65, 84, 88, -61, -19, -106, 63, 107, -37, 64, 24, -58, 37, -89, -63, -85, -113, -72, 117, 83, -56, -30, 33, -122, -126, -96, -62, 20, 65, 54, 83, 67, 93, -45, -126, 23, -111, 33, 91, 6, 7, 90, 10, -67, -54, -31, 5, 21, 15, 5, 15, -22, -18, -63, -126, 44, 25, -116, -32, -3, 8, -15, -38, 37, -96, 37, -117, 50, -124, -5, 46, 121, 79, -70, -76, -37, -99, -44, -79, -36, 51, 8, 15, -49, 79, -17, -33, 59, -53, 113, -84, -84, -84, -84, -84, -2, 81, -83, -2, 0, -62, 6, -2, -66, 39, 30, 16, -21, 35, 125, 33, -60, 13, -110, 46, 103, -11, -14, -7, -6, 4, 96, 86, -117, 56, -98, 11, -15, 5, -79, 54, -47, -102, -97, 51, -58, 98, 69, 108, 107, 0, -84, 67, 79, -10, 92, 17, -77, -70, -107, -69, 42, -56, -74, 126, 115, 89, -61, 16, -114, -13, -86, 41, -32, -58, 13, 115, 114, 122, -115, 1, 32, 0, 26, 0, 46, 0, 102, 48, 107, 4, -112, 26, 0, 123, 53, -127, -74, -76, -55, 31, 65, 9, 116, 76, 126, -8, -79, 35, -63, 55, 89, -76, 30, 104, -121, 111, -92, 31, 119, -31, 112, 119, 67, -117, -54, -64, 0, -60, 89, -28, 64, 56, -60, 45, -30, 91, 57, -128, 83, 3, -48, -90, 22, 2, -67, -38, 15, 16, -93, -14, -64, 25, -128, 125, 36, 63, 1, 24, -60, 67, 56, -111, -89, -89, 12, -63, 117, 77, -92, 98, -91, 49, -126, 64, 2, 24, -103, -128, -77, 50, 0, 102, 28, -96, -36, 84, 8, -76, -128, -29, -57, -46, 15, -16, 7, -56, 34, -3, 24, -114, -18, -91, 73, 20, 0, 61, 94, 93, 1, -48, -45, 2, 75, 65, 7, 96, 41, -60, 5, -16, -22, -20, 0, -27, -92, -55, 104, -1, 78, 36, -29, -23, -14, 78, -36, 67, -124, 10, -120, -76, -128, -40, -28, -7, -6, -109, 32, -96, -20, -86, 28, 11, -41, 101, -76, -105, -26, -92, -75, 76, -87, 26, -125, 108, -86, -81, 89, -70, 103, -46, -97, -33, 78, -88, 104, -88, -10, -12, 84, -65, -86, 31, -13, 74, 35, 80, -70, -116, 51, 29, 64, 1, 62, -4, -66, -94, 16, -55, 19, 65, 59, 5, -111, 6, -72, -70, 77, -109, 95, -28, 79, 86, 61, 5, 80, 4, 13, -32, -90, -23, -118, 117, 39, 9, -87, 2, 12, -101, -28, -98, -68, 92, 49, -10, -98, 119, -109, -48, 103, 7, -78, 94, -82, 5, 90, -81, -15, -6, 104, -68, 2, 40, -34, -47, -72, -31, 0, 76, -128, 87, 96, -74, -104, -56, 92, -68, -49, 106, 4, -33, 7, -102, -43, 115, 69, -127, -80, 72, -90, 100, -11, -124, 2, -68, 66, 11, -48, -21, -8, 122, 74, -39, -68, 16, 114, -119, 72, -57, -13, 11, 77, -51, -116, -78, -31, 105, 55, -58, -59, 104, -96, -58, 64, -105, 6, -17, -24, -2, 113, 24, 59, -36, -116, 71, 93, -43, 84, -97, 57, -116, -103, -18, 48, -9, 48, -35, 36, 63, 101, 48, 48, 122, -1, -122, -23, -108, 79, -5, -7, 100, 101, 101, -11, -65, -22, 17, -10, -59, 99, -55, 93, -55, -72, 75, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};

        assertArrayEquals(pokemonLoaderService.getPokemonImage("slowpoke"), slowpokeBytes);
    }
}
