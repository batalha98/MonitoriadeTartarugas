package com.example.monitoriadetartarugas.domain.entitys;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Beach implements Serializable {
    private String beach;
    private String island;

    public String getBeach(){
        return beach;
    }

    public String getIsland(){
        return island;
    }

    public void setBeach(String beach){
        this.beach = beach;
    }

    public void setIsland(String island){
        this.island = island;
    }

    @Override
    public String toString() {
        return "["+"Beach: "+beach+" ;Island: "+island+"]";
    }
}
