package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class LocalizationAndObservation implements Serializable {
    private NestLocalization idnest;
    private NestLocalization nest_marking_date;
    private Date observation_date;

    public NestLocalization getIdnest() {
        return idnest;
    }

    public void setIdnest(NestLocalization idnest) {
        this.idnest = idnest;
    }

    public NestLocalization getNest_marking_date() {
        return nest_marking_date;
    }

    public void setNest_marking_date(NestLocalization nest_marking_date) {
        this.nest_marking_date = nest_marking_date;
    }

    public Date getObservation_date() {
        return observation_date;
    }

    public void setObservation_date(Date observation_date) {
        this.observation_date = observation_date;
    }

    @Override
    public String toString() {
        return "LocalizationAndObservation{" +
                "idnest=" + idnest.getIdnest() +
                ", nest_marking_date=" + nest_marking_date.toString() +
                ", observation_date=" + observation_date.toString() +
                '}';
    }
}
