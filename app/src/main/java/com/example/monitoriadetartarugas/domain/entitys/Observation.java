package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Observation implements Serializable {
    private Turtle idturtle;
    private Activities idactivity;
    private WindCategory wc;
    private WindDirection wd;
    private Beach beach;
    private Date dataa;
    private Float dune_height;

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

    public Float getDune_height() {
        return dune_height;
    }

    public void setDune_height(Float dune_height) {
        this.dune_height = dune_height;
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
                ", beach_height=" + dune_height +
                '}';
    }
}
