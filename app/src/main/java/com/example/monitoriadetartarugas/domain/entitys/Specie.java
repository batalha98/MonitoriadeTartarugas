package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Specie implements Serializable {
    private int idspecie;
    private String specie;

    public int getIdspecie(){
        return idspecie;
    }

    public String getSpecie(){
        return specie;
    }

    public void setIdspecie(int idspecie){
        this.idspecie = idspecie;
    }

    public void setSpecie(String specie){
        this.specie = specie;
    }

    @Override
    public String toString() {
        return specie;
    }
}
