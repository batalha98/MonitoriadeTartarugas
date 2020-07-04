package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Nest implements Serializable {
    private int idnest;
    private int depth;
    private int eggs_quantity;
    private Float distance_to_tide;
    private String notes;

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

    public Float getDistance_to_tide() {
        return distance_to_tide;
    }

    public void setDistance_to_tide(Float distance_to_tide) {
        this.distance_to_tide = distance_to_tide;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return  "Nest" +"\n"+
                "   Depth: " + depth +"\n"+
                "   Eggs_quantity: " + eggs_quantity +"\n"+
                "   Distance: " + distance_to_tide +"\n"+
                "   Notes: " + notes;
    }
}
