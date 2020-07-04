package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class ObservationAndObserver implements Serializable {
    private LocalizationAndObservation idnest;
    private LocalizationAndObservation nest_marking_date;
    private LocalizationAndObservation observation_date;
    private Observer idobserver;

    public LocalizationAndObservation getIdnest() {
        return idnest;
    }

    public void setIdnest(LocalizationAndObservation idnest) {
        this.idnest = idnest;
    }

    public LocalizationAndObservation getnest_marking_date() {
        return nest_marking_date;
    }

    public void setnest_marking_date(LocalizationAndObservation nest_marking_date) {
        this.nest_marking_date = nest_marking_date;
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
                ", nest_marking_date=" + nest_marking_date.getNest_marking_date() +
                ", observation_date=" + observation_date.getObservation_date() +
                ", idobserver=" + idobserver.getObserver() +
                '}';
    }
}
