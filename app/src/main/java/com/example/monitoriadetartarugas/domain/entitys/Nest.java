package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Nest implements Serializable {
    private int idnest;
    private int depth;
    private int eggs_quantity;
    private float distance;
    private float width;
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
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
        return "Nest{" +
                "idnest=" + idnest +
                ", depth=" + depth +
                ", eggs_quantity=" + eggs_quantity +
                ", distance=" + distance +
                ", width=" + width +
                ", description='" + description + '\'' +
                '}';
    }
}
