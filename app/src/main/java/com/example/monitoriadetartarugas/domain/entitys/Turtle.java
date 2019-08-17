package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Turtle implements Serializable {
    private int idturtle;
    private Specie idspecie;
    private String description;

    public int getIdturtle() {
        return idturtle;
    }

    public void setIdturtle(int idturtle) {
        this.idturtle = idturtle;
    }

    public Specie getIdspecie() {
        return idspecie;
    }

    public void setIdspecie(Specie idspecie) {
        this.idspecie = idspecie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "   Idturtle: " + idturtle +"\n"+
                "   Description: " + description;
    }
}
