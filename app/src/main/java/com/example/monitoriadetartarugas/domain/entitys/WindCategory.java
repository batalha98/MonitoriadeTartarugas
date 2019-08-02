package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class WindCategory implements Serializable {
    private int idwc;
    private String name;

    public int getIdwc(){
        return idwc;
    }

    public String getName(){
        return name;
    }

    public void setIdwc(int idwc){
        this.idwc = idwc;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
