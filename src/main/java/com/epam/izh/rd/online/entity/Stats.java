package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    private short baseStat;

    private Stat stat;

    @JsonCreator
    public Stats(@JsonProperty("base_stat") short baseStat, @JsonProperty("stat") Stat stat) {
        this.baseStat = baseStat;
        this.stat = stat;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Stat {
        private String name;

        @JsonCreator
        public Stat(@JsonProperty("name") String name) {
            this.name = name;
        }
    }
}
