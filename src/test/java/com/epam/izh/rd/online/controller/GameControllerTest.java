package com.epam.izh.rd.online.controller;

import com.epam.izh.rd.online.entity.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController gameController;
    private Pokemon pokemonOne;
    private Pokemon pokemonTwo;
    private File testImageWinnerPokemon;

    @BeforeEach
    public void init() {
        pokemonOne = new Pokemon((short) 1, "bulbasaur", (short) 49, (short) 49, (short) 65);
        pokemonTwo = new Pokemon((short) 2, "ivysaur", (short) 62, (short) 63, (short) 80);
        gameController = new GameController();
        testImageWinnerPokemon= new File("src/test/resources/ivysaurTest.png");
    }

    @Test
    void doBattleTest() {
        Pokemon winnerPokemon = gameController.doBattle(pokemonOne, pokemonTwo);
        assertEquals(pokemonTwo, winnerPokemon);
        Pokemon reverseWinnerPokemon = gameController.doBattle(pokemonTwo, pokemonOne);
        assertEquals(pokemonTwo, reverseWinnerPokemon);
    }

    @Test
    void showWinnerTestImage() throws IOException {
        gameController.showWinner(pokemonTwo);
        File folder = new File("D:\\http-template\\src");
        File pokemonImageWinner = null;
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.getName().equals("ivysaur.png")) {
                pokemonImageWinner = file;
                break;
            }
        }

        byte[] imagePokemonToByte = new byte[0];
        byte[] testImagePokemonToByte = new byte[2];
        if (pokemonImageWinner != null) {
            imagePokemonToByte = Files.readAllBytes(Paths.get(pokemonImageWinner.getPath()));
            testImagePokemonToByte = Files.readAllBytes(Paths.get(testImageWinnerPokemon.getPath()));
        }
        assertArrayEquals(imagePokemonToByte, testImagePokemonToByte);
    }
}