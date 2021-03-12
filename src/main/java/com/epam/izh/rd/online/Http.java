package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimplePokemonFetchingService;
import com.epam.izh.rd.online.service.SimplePokemonFightingClubService;

public class Http {
    public static void main(String[] args) {
        SimplePokemonFetchingService service = new SimplePokemonFetchingService();
        SimplePokemonFightingClubService fightingClub = new SimplePokemonFightingClubService();
        fightingClub.showWinner(fightingClub.doBattle(service.fetchByName("slowpoke"), service.fetchByName("pikachu")));


    }
}
