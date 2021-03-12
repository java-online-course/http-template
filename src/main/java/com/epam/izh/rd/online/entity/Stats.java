package com.epam.izh.rd.online.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {
    @JsonProperty("base_stat")
    private short baseStat;

    public short getBaseStat() {
        return baseStat;
    }

}