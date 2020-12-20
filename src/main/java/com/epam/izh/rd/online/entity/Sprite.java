package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprite {

    @JsonProperty("front_default")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

}
