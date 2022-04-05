package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;



public class PokemonFetchingServiceImplTest {

    private static PokemonFetchingService pokemonFetchingService;

    @BeforeAll
    public static void simplePokemonFetchingServiceCreate()
    {
        pokemonFetchingService = new PokemonFetchingServiceImp();
    }


    @ParameterizedTest
    @ValueSource(strings = {"pikachu", "slowpoke"})
    public void fetchByNameTest(String name) {

        Pokemon pokemon = pokemonFetchingService.fetchByName(name);

        assertThat(pokemon.getPokemonName(), equalTo(name));
    }


}


