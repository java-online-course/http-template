package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprites {
    @JsonProperty("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

}
