package com.epam.izh.rd.online.entity.characteristics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stats {

    private String name;
    private int id;
    private List<Stat> statsList;

    @JsonCreator
    public Stats(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("stats") List<Stat> statsList) {

        this.id = id;
        this.name = name;
        this.statsList = statsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Stat> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<Stat> statsList) {
        this.statsList = statsList;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", statsList=" + statsList +
                '}';
    }
}
