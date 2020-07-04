package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class NestLocalization implements Serializable {
    private Nest idnest;
    private Date nest_marking_date;
    private Beach beach;
    private Habitat idhabitat;
    private Float gpsEast;
    private Float gpsSouth;
    private String notes;

    public Nest getIdnest() {
        return idnest;
    }

    public void setIdnest(Nest idnest) {
        this.idnest = idnest;
    }

    public Date getNest_marking_date() {
        return nest_marking_date;
    }

    public void setNest_marking_date(Date nest_marking_date) {
        this.nest_marking_date = nest_marking_date;
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

    public Float getGpsEast() {
        return gpsEast;
    }

    public void setGpsEast(Float gpsEast) {
        this.gpsEast = gpsEast;
    }

    public Float getGpsSouth() {
        return gpsSouth;
    }

    public void setGpsSouth(Float gpsSouth) {
        this.gpsSouth = gpsSouth;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NestLocalization{" +
                "idnest=" + idnest +
                ", nest_marking_date=" + nest_marking_date +
                ", beach=" + beach +
                ", idhabitat=" + idhabitat +
                ", gpsEast=" + gpsEast +
                ", gpsSouth=" + gpsSouth +
                ", notes='" + notes + '\'' +
                '}';
    }
}
