package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class Observation implements Serializable {
    private Turtle idturtle;
    private Activities idactivity;
    private WindCategory wc;
    private WindDirection wd;
    private Beach beach;
    private Date dataa;
    private Float beach_height;
    private Date beach_time;

    public Turtle getIdturtle() {
        return idturtle;
    }

    public void setIdturtle(Turtle idturtle) {
        this.idturtle = idturtle;
    }

    public Activities getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(Activities idactivity) {
        this.idactivity = idactivity;
    }

    public WindCategory getWc() {
        return wc;
    }

    public void setWc(WindCategory wc) {
        this.wc = wc;
    }

    public WindDirection getWd() {
        return wd;
    }

    public void setWd(WindDirection wd) {
        this.wd = wd;
    }

    public Beach getBeach() {
        return beach;
    }

    public void setBeach(Beach beach) {
        this.beach = beach;
    }

    public Date getDataa() {
        return dataa;
    }

    public void setDataa(Date dataa) {
        this.dataa = dataa;
    }

    public Float getBeach_height() {
        return beach_height;
    }

    public void setBeach_height(Float beach_height) {
        this.beach_height = beach_height;
    }

    public Date getBeach_time() {
        return beach_time;
    }

    public void setBeach_time(Date beach_time) {
        this.beach_time = beach_time;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "idturtle=" + idturtle +
                ", idactivity=" + idactivity +
                ", wc=" + wc +
                ", wd=" + wd +
                ", beach=" + beach +
                ", dataa=" + dataa +
                ", beach_height=" + beach_height +
                ", beach_time=" + beach_time +
                '}';
    }
}
