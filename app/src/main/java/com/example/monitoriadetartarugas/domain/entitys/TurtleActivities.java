package com.example.monitoriadetartarugas.domain.entitys;

import android.app.Activity;

import java.io.Serializable;
import java.util.Date;

public class TurtleActivities implements Serializable {
    private Observation idturtle;
    private Observation beach;
    private Activities activity;

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

    public Activities getActivity() {
        return activity;
    }

    public void setActivity(Activities activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "TurtleActivities{" +
                "idturtle=" + idturtle.getIdturtle() +
                ", beach=" + beach.getBeach().getBeach() +
                ", activity=" + activity.getActivity() +
                '}';
    }
}
