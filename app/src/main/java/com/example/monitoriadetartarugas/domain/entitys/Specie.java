package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Specie implements Serializable {
    public int idspecie;
    public String specie;

    @Override
    public String toString(){
        return specie;
    }
}
