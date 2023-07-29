package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.epam.izh.rd.online.service.PokemonStatsFetchingService;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Http {
  private final Scanner scanner = new Scanner(System.in);
  private final HttpsURLConnection[] httpsURLConnection = new HttpsURLConnection[1];
  private final Map<String, URI> mapPokemonNameAndURI = new HashMap<>();

  private final Map<String, byte[]> mapPokemonByteArrayByName = new HashMap<>();

  private Http() {}

  private static class HttpHolder {
    public static final Http HTTP_HOLDER;

    static {
      HTTP_HOLDER = new Http();
    }
  }

  public static Http getInstance() {
    return HttpHolder.HTTP_HOLDER;
  }

  private void setHttpsURLConnectionParameters(URI uri) throws IOException {
    httpsURLConnection[0] = (HttpsURLConnection) uri.toURL().openConnection();
    httpsURLConnection[0].setRequestMethod("GET");
    httpsURLConnection[0].setRequestProperty("User-Agent", "Mozilla/5.0");
  }

  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  public static void main(String[] args) throws URISyntaxException {
    Http http = Http.getInstance();
    System.out.println("Choose your first pokemon fighter");
    String firstInputPokemon = http.validatePokemonName(http.inputPokemonFromUser());
    http.readJSONasByteArray(firstInputPokemon);
    System.out.println("Choose your second pokemon fighter");
    String secondInputPokemon = http.validatePokemonName(http.inputPokemonFromUser());
    http.readJSONasByteArray(secondInputPokemon);
    http.httpsURLConnection[0].disconnect();

    PokemonFetchingService pokemonFetchingService = PokemonFetchingService.getInstance();

    PokemonStatsFetchingService pokemonStatsFetchingService =
        PokemonStatsFetchingService.getInstance();

    pokemonStatsFetchingService.setObjectMapper(pokemonFetchingService.getObjectMapper());

    Pokemon firstPokemon = new Pokemon();
    Pokemon secondPokemon = new Pokemon();

    firstPokemon.setPokemonName(firstInputPokemon);
    secondPokemon.setPokemonName(secondInputPokemon);

    pokemonStatsFetchingService.setStats(
        http.getPokemonByteArrayFromMap(firstInputPokemon), firstPokemon);
    pokemonStatsFetchingService.setStats(
        http.getPokemonByteArrayFromMap(secondInputPokemon), secondPokemon);

    PokemonFightingClubService pokemonFightingClubService =
        PokemonFightingClubService.getInstance();
    Pokemon winner = pokemonFightingClubService.doBattle(firstPokemon, secondPokemon);
    pokemonFightingClubService.showWinner(winner);
    pokemonFightingClubService.downloadImageWinner(winner.getImageURLfrontDefault());
  }

  private String inputPokemonFromUser() {
    return scanner.next().trim();
  }

  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  private String validatePokemonName(String pokemonFromUser) throws URISyntaxException {
    while (!isPokemonExist(pokemonFromUser)) {
      System.out.println(
          "Incorrect. Choose exist pokemon https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon");
      pokemonFromUser = inputPokemonFromUser();
    }
    mapPokemonNameAndURI.put(pokemonFromUser, constructPokemonURI(pokemonFromUser));
    return pokemonFromUser;
  }

  private URI constructPokemonURI(String pokemonName) throws URISyntaxException {
    return new URI("https://pokeapi.co/api/v2/pokemon/" + pokemonName);
  }

  private boolean isPokemonExist(String pokemonName) {
    try {
      setHttpsURLConnectionParameters(constructPokemonURI(pokemonName));
      return HttpURLConnection.HTTP_OK == httpsURLConnection[0].getResponseCode();
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private void readJSONasByteArray(String pokemonName) {
    try {
      setPokemonByteArrayInMap(
          pokemonName, transferInputStreamToByteArray(httpsURLConnection[0].getInputStream()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private byte[] transferInputStreamToByteArray(InputStream inputStream) throws IOException {
    Objects.requireNonNull(inputStream);
    ByteArrayOutputStream BAOutputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[8192];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      BAOutputStream.write(buffer, 0, bytesRead);
    }
    return BAOutputStream.toByteArray();
  }

  private void setPokemonByteArrayInMap(String pokemonName, byte[] pokemonByteArray) {
    mapPokemonByteArrayByName.put(pokemonName, pokemonByteArray);
  }

  private byte[] getPokemonByteArrayFromMap(String pokemonName) {
    return mapPokemonByteArrayByName.get(pokemonName);
  }
}
