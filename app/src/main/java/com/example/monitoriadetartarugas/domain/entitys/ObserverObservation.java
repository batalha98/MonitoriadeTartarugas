package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class ObserverObservation implements Serializable {
    private Observation idturtle;
    private Observation beach;
    private Observation dataa;
    private Observer idobserver;

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

    public Observer getIdobserver() {
        return idobserver;
    }

    public void setIdobserver(Observer idobserver) {
        this.idobserver = idobserver;
    }

    @Override
    public String toString() {
        return "ObserverObservation{" +
                "idturtle=" + idturtle.getIdturtle() +
                ", beach=" + beach.getBeach().getBeach() +
                ", dataa=" + dataa.getDataa() +
                ", idobserver=" + idobserver.getObserver() +
                '}';
    }
}
