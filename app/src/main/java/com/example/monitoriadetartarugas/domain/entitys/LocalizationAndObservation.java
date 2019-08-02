package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class LocalizationAndObservation implements Serializable {
    private NestLocalization idnest;
    private NestLocalization localization_date;
    private Date observation_date;

    public NestLocalization getIdnest() {
        return idnest;
    }

    public void setIdnest(NestLocalization idnest) {
        this.idnest = idnest;
    }

    public NestLocalization getLocalization_date() {
        return localization_date;
    }

    public void setLocalization_date(NestLocalization localization_date) {
        this.localization_date = localization_date;
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
                ", localization_date=" + localization_date.getDataa() +
                ", observation_date=" + observation_date +
                '}';
    }
}
