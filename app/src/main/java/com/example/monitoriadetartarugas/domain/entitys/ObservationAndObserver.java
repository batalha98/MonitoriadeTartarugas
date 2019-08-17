package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class ObservationAndObserver implements Serializable {
    private LocalizationAndObservation idnest;
    private LocalizationAndObservation localization_date;
    private LocalizationAndObservation observation_date;
    private Observer idobserver;

    public LocalizationAndObservation getIdnest() {
        return idnest;
    }

    public void setIdnest(LocalizationAndObservation idnest) {
        this.idnest = idnest;
    }

    public LocalizationAndObservation getLocalization_date() {
        return localization_date;
    }

    public void setLocalization_date(LocalizationAndObservation localization_date) {
        this.localization_date = localization_date;
    }

    public LocalizationAndObservation getObservation_date() {
        return observation_date;
    }

    public void setObservation_date(LocalizationAndObservation observation_date) {
        this.observation_date = observation_date;
    }

    public Observer getIdobserver() {
        return idobserver;
    }

    public void setIdobserver(Observer idobserver) {
        this.idobserver = idobserver;
    }

    @Override
    public String toString() {
        return "ObservationAndObserver{" +
                "idnest=" + idnest.getIdnest() +
                ", localization_date=" + localization_date.getLocalization_date() +
                ", observation_date=" + observation_date.getObservation_date() +
                ", idobserver=" + idobserver.getObserver() +
                '}';
    }
}
