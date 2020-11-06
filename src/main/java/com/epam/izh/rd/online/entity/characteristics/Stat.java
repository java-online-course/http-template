package com.epam.izh.rd.online.entity.characteristics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stat {

    private int baseStat;
    private StatName statName;

    @JsonCreator
    public Stat(@JsonProperty("base_stat") int baseStat,
                 @JsonProperty("stat") StatName statName) {
        this.baseStat = baseStat;
        this.statName = statName;
    }

    public int getBaseStat() {
        return baseStat;
    }


    public StatName getStat() {
        return statName;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "baseStat=" + baseStat +
                ", statName=" + statName +
                '}';
    }

    static class StatName {
        private String name;

        @JsonCreator
        public StatName(@JsonProperty("name") String name) {
            this.name = name;
        }
    }
}
