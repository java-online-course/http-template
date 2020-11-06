package com.epam.izh.rd.online.uitl;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.сonnection.PokeApiResponse;

import java.io.*;

public class ImagePokemon {

    public void saveWinnerImage(Pokemon winner) throws IOException {
        String pathImage = "src/"+ winner.getPokemonName() + ".png";
        PokemonFetchingService pokemonFetchingService = new PokemonFetchingServiceImpl(new PokeApiResponse());
        byte[] pokemonImage = pokemonFetchingService.getPokemonImage(winner.getPokemonName());
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pathImage))) {
               outputStream.write(pokemonImage);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
