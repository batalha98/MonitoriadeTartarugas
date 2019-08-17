package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Nest implements Serializable {
    private int idnest;
    private int depth;
    private int eggs_quantity;
    private Float distance;
    private Float width;
    private String description;

    public int getIdnest() {
        return idnest;
    }

    public void setIdnest(int idnest) {
        this.idnest = idnest;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getEggs_quantity() {
        return eggs_quantity;
    }

    public void setEggs_quantity(int eggs_quantity) {
        this.eggs_quantity = eggs_quantity;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "Nest" +"\n"+
                "   Depth: " + depth +"\n"+
                "   Eggs_quantity: " + eggs_quantity +"\n"+
                "   Distance: " + distance +"\n"+
                "   Width: " + width +"\n"+
                "   Description: " + description;
    }
}
