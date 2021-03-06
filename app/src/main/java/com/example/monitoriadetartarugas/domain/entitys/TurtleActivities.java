package com.example.monitoriadetartarugas.domain.entitys;

import android.app.Activity;

import java.io.Serializable;
import java.util.Date;

public class TurtleActivities implements Serializable {
    private Observation idturtle;
    private Observation beach;
    private Observation dataa;
    private Activities idactivity;

    public Observation getIdturtle() {
        return idturtle;
    }

    public void setIdturtle(Observation idturtle) {
        this.idturtle = idturtle;
    }

    public Observation getBeach() {
        return beach;
    }

    public void setBeach(Observation beach) {
        this.beach = beach;
    }

    public Observation getDataa() {
        return dataa;
    }

    public void setDataa(Observation dataa) {
        this.dataa = dataa;
    }

    public Activities getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(Activities idactivity) {
        this.idactivity = idactivity;
    }

    @Override
    public String toString() {
        return "TurtleActivities{" +
                "idturtle=" + idturtle.getIdturtle() +
                ", beach=" + beach.getBeach().getBeach() +
                ", dataa=" + dataa.getDataa() +
                ", idactivity=" + idactivity.getActivity() +
                '}';
    }
}
