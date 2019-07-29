package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Turtle implements Serializable {
    private int idturtle;
    private int idspecie;
    private String description;

    public int getIdturtle(){
        return idturtle;
    }

    public int getIdspecie(){
        return idspecie;
    }

    public String getDescription(){
        return description;
    }

    public void setIdturtle(int idturtle){
        this.idturtle = idturtle;
    }

    public void setIdspecie(int idspecie){
        this.idspecie = idspecie;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
