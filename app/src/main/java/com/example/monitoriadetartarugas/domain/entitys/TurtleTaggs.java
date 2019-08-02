package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class TurtleTaggs implements Serializable {
    private Turtle idturtle;
    private Date dataa;
    private int leftring;
    private int rightring;
    private int internal_tag;
    private double ccl_measure;
    private double cwl_measure;

    public Turtle getIdturtle() {
        return idturtle;
    }

    public void setIdturtle(Turtle idturtle) {
        this.idturtle = idturtle;
    }

    public Date getDataa() {
        return dataa;
    }

    public void setDataa(Date dataa) {
        this.dataa = dataa;
    }

    public int getLeftring() {
        return leftring;
    }

    public void setLeftring(int leftring) {
        this.leftring = leftring;
    }

    public int getRightring() {
        return rightring;
    }

    public void setRightring(int rightring) {
        this.rightring = rightring;
    }

    public int getInternal_tag() {
        return internal_tag;
    }

    public void setInternal_tag(int internal_tag) {
        this.internal_tag = internal_tag;
    }

    public double getCcl_measure() {
        return ccl_measure;
    }

    public void setCcl_measure(double ccl_measure) {
        this.ccl_measure = ccl_measure;
    }

    public double getCwl_measure() {
        return cwl_measure;
    }

    public void setCwl_measure(double cwl_measure) {
        this.cwl_measure = cwl_measure;
    }

    @Override
    public String toString() {
        return "TurtleTaggs{" +
                "idturtle=" + idturtle.getIdturtle() +
                ", dataa=" + dataa +
                ", leftring=" + leftring +
                ", rightring=" + rightring +
                ", internal_tag=" + internal_tag +
                ", ccl_measure=" + ccl_measure +
                ", cwl_measure=" + cwl_measure +
                '}';
    }
}
