package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Island implements Serializable {
    private String island;

    public String getIsland() {
        return island;
    }

    public void setIsland(String island) {
        this.island = island;
    }

    @Override
    public String toString() {
        return "Island{" +
                "island='" + island + '\'' +
                '}';
    }
}
