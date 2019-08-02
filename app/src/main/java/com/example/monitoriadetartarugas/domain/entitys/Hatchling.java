package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class Hatchling implements Serializable {
    private Nest idnest;
    private Date dataa;
    private int hatched;
    private int died_in_nest;
    private int alive_in_nest;
    private int undeveloped;
    private int unhatched;
    private int predated_eggs;
    private String description;

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

    public int getHatched() {
        return hatched;
    }

    public void setHatched(int hatched) {
        this.hatched = hatched;
    }

    public int getDied_in_nest() {
        return died_in_nest;
    }

    public void setDied_in_nest(int died_in_nest) {
        this.died_in_nest = died_in_nest;
    }

    public int getAlive_in_nest() {
        return alive_in_nest;
    }

    public void setAlive_in_nest(int alive_in_nest) {
        this.alive_in_nest = alive_in_nest;
    }

    public int getUndeveloped() {
        return undeveloped;
    }

    public void setUndeveloped(int undeveloped) {
        this.undeveloped = undeveloped;
    }

    public int getUnhatched() {
        return unhatched;
    }

    public void setUnhatched(int unhatched) {
        this.unhatched = unhatched;
    }

    public int getPredated_eggs() {
        return predated_eggs;
    }

    public void setPredated_eggs(int predated_eggs) {
        this.predated_eggs = predated_eggs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Hatchling{" +
                "idnest=" + idnest.getIdnest() +
                ", dataa=" + dataa +
                ", hatched=" + hatched +
                ", died_in_nest=" + died_in_nest +
                ", alive_in_nest=" + alive_in_nest +
                ", undeveloped=" + undeveloped +
                ", unhatched=" + unhatched +
                ", predated_eggs=" + predated_eggs +
                ", description='" + description + '\'' +
                '}';
    }
}
