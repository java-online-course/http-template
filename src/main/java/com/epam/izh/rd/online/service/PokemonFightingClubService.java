package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Random;

public class PokemonFightingClubService implements IPokemonFightingClubService {
  final Random random = new Random();

  private static class PokemonFightingClubServiceHolder {
    public static final PokemonFightingClubService POKEMON_FIGHTING_CLUB_SERVICE_INSTANCE =
        new PokemonFightingClubService();
  }

  public static PokemonFightingClubService getInstance() {
    return PokemonFightingClubService.PokemonFightingClubServiceHolder
        .POKEMON_FIGHTING_CLUB_SERVICE_INSTANCE;
  }

  @Override
  public Pokemon doBattle(Pokemon p1, Pokemon p2) {
    Pokemon winner;
    while (p1.getHp() != 0 || p2.getHp() != 0) {
      Pokemon firstAttacker = getRandomPokemon(p1, p2);
      if (firstAttacker == p1) {
        doDamage(p1, p2);
      } else {
        doDamage(p2, p1);
      }
      if ((p1.getHp() == 0 || p2.getHp() == 0)) {
        break;
      }
    }
    winner = p1.getHp() == 0 ? p2 : p1;
    return winner;
  }

  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  @Override
  public void showWinner(Pokemon winner) {
    System.out.println("Победитель: покемон " + winner.getPokemonName());
  }

  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  public void downloadImageWinner(String URLimageWinner) {
    try {
      URL url = new URL(URLimageWinner);
      URLConnection urlConnection = url.openConnection();
      InputStream inputStream = urlConnection.getInputStream();
      String userDir = System.getProperty("user.dir");
      Path outputPath = Paths.get(userDir, "winner.png");
      Files.copy(
          inputStream, Objects.requireNonNull(outputPath), StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Image successful downloaded");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void doDamage(Pokemon from, Pokemon to) {
    short damage = (short) (from.getAttack() - to.getDefense());
    to.setHp((short) (to.getHp() - damage));
    if (to.getHp() < 0) {
      to.setHp((short) 0);
    }
  }

  private Pokemon getRandomPokemon(Pokemon firstPokemon, Pokemon secondPokemon) {
    return random.nextBoolean() ? firstPokemon : secondPokemon;
  }
}
