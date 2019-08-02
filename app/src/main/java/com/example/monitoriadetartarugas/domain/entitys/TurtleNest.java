package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class TurtleNest implements Serializable {
    private Nest idnest;
    private Turtle idturtle;

    public Nest getIdnest() {
        return idnest;
    }

    public void setIdnest(Nest idnest) {
        this.idnest = idnest;
    }

    public Turtle getIdturtle() {
        return idturtle;
    }

    public void setIdturtle(Turtle idturtle) {
        this.idturtle = idturtle;
    }

    @Override
    public String toString() {
        return "TurtleNest{" +
                "idnest=" + idnest.getIdnest() +
                ", idturtle=" + idturtle.getIdturtle() +
                '}';
    }
}
