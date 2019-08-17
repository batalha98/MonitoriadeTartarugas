package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class NestLocalization implements Serializable {
    private Nest idnest;
    private Date dataa;
    private Beach beach;
    private Habitat idhabitat;
    private Float distance;
    private Float height;
    private String observations;

    public Nest getIdnest() {
        return idnest;
    }

    public void setIdnest(Nest idnest) {
        this.idnest = idnest;
    }

    public Date getDataa() {
        return dataa;
    }

    public void setDataa(Date dataa) {
        this.dataa = dataa;
    }

    public Beach getBeach() {
        return beach;
    }

    public void setBeach(Beach beach) {
        this.beach = beach;
    }

    public Habitat getIdhabitat() {
        return idhabitat;
    }

    public void setIdhabitat(Habitat idhabitat) {
        this.idhabitat = idhabitat;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "NestLocalization{" +
                "idnest=" + idnest.getIdnest() +
                ", dataa=" + dataa +
                ", beach=" + beach.getBeach() +
                ", idhabitat=" + idhabitat.getHabitat() +
                ", distance=" + distance +
                ", height=" + height +
                ", observations='" + observations + '\'' +
                '}';
    }
}
