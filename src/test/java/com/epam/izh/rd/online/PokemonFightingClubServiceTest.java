package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.entity.Stats;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonFightingClubServiceTest {

    private Pokemon firstPokemon;
    private Pokemon secondPokemon;

    private Pokemon fetchingFirstPokemon;
    private Pokemon fetchingSecondPokemon;

    PokemonFetchingService pokemonFetchingService;
    PokemonFightingClubService pokemonFightingClubService;

    @BeforeEach
    public void setup() throws IOException {
        pokemonFetchingService = new PokemonFetchingServiceImpl("https://pokeapi.co/api/v2/pokemon/");

        this.pokemonFightingClubService = new PokemonFightingClubServiceImpl(pokemonFetchingService);

        firstPokemon = new Pokemon(1, "pikachu",
                Arrays.asList(
                        new Stats((short) 35, new Stats.Stat("hp")),
                        new Stats((short) 55, new Stats.Stat("attack")),
                        new Stats((short) 40, new Stats.Stat("defense"))
                ), new Pokemon.Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png/"));
        secondPokemon = new Pokemon(2, "slowpoke",
                Arrays.asList(
                        new Stats((short) 90, new Stats.Stat("hp")),
                        new Stats((short) 65, new Stats.Stat("attack")),
                        new Stats((short) 65, new Stats.Stat("defense"))
                ), new Pokemon.Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/79.png/"));

        fetchingFirstPokemon = pokemonFetchingService.fetchByName("pikachu");
        fetchingSecondPokemon = pokemonFetchingService.fetchByName("slowpoke");
    }

    @Test
    public void Test_DoBattle() {
        assertThat(secondPokemon, CoreMatchers.is(this.pokemonFightingClubService.doBattle(firstPokemon,secondPokemon)));
        assertThat(secondPokemon, CoreMatchers.is(this.pokemonFightingClubService.doBattle(secondPokemon,firstPokemon)));
        assertThat(fetchingSecondPokemon, CoreMatchers.is(this.pokemonFightingClubService.doBattle(fetchingFirstPokemon,fetchingSecondPokemon)));
        assertThat(fetchingSecondPokemon, CoreMatchers.is(this.pokemonFightingClubService.doBattle(fetchingSecondPokemon,fetchingFirstPokemon)));
    }

    @Test
    public void Test_DoDamage() {
        pokemonFightingClubService.doDamage(firstPokemon, secondPokemon);

        assertEquals(70, secondPokemon.getHp());

        pokemonFightingClubService.doDamage(secondPokemon, firstPokemon);
        assertEquals(-4, firstPokemon.getHp());
    }

    @Test
    public void Test_ShowWinner() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream streamPikaImg = classloader.getResourceAsStream("pika.png");
        InputStream streamSlowImg = classloader.getResourceAsStream("slow.png");
        byte[] newWinnerImage = null;
        File fileWinnerImage = null;

        byte[] bytesPikaImg = IOUtils.toByteArray(streamPikaImg);
        byte[] bytesSlowImg = IOUtils.toByteArray(streamSlowImg);

        pokemonFightingClubService.showWinner(firstPokemon);

        fileWinnerImage = new File("winner.png");
        newWinnerImage = Files.readAllBytes(fileWinnerImage.toPath());
        assertArrayEquals(bytesPikaImg, newWinnerImage);
        fileWinnerImage.delete();

        pokemonFightingClubService.showWinner(secondPokemon);
        fileWinnerImage = new File("winner.png");
        newWinnerImage = Files.readAllBytes(fileWinnerImage.toPath());
        assertArrayEquals(bytesSlowImg, newWinnerImage);
        fileWinnerImage.delete();
    }
}