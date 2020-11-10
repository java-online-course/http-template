package com.epam.izh.rd.online.configuration;

import com.epam.izh.rd.online.factory.ObjectMapperFactory;
import com.epam.izh.rd.online.factory.ObjectMapperFactoryImpl;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Configuration
public class MainConfiguration {

    @Bean
    public ObjectMapperFactory objectMapperFactory() {
        return new ObjectMapperFactoryImpl();
    }

    @Bean
    public PokemonFetchingServiceImpl pokemonFetchingService(ObjectMapperFactory objectMapperFactory) {
        return new PokemonFetchingServiceImpl(objectMapperFactory);
    }

    @Bean
    public PokemonFightingClubService pokemonFightingClubService() {
        return new PokemonFightingClubServiceImpl();
    }

}
