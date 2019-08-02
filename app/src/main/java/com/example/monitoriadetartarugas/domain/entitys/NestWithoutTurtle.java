package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class NestWithoutTurtle implements Serializable {
    private Nest idnest;
    private Specie idspecie;

    public Nest getIdnest() {
        return idnest;
    }

    public void setIdnest(Nest idnest) {
        this.idnest = idnest;
    }

    public Specie getIdspecie() {
        return idspecie;
    }

    public void setIdspecie(Specie idspecie) {
        this.idspecie = idspecie;
    }

    @Override
    public String toString() {
        return "NestWithoutTurtle{" +
                "idnest=" + idnest.getIdnest() +
                ", idspecie=" + idspecie.getSpecie() +
                '}';
    }
}
