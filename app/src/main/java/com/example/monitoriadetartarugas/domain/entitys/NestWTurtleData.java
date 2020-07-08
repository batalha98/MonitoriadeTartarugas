package com.example.monitoriadetartarugas.domain.entitys;

import java.util.Date;

public class NestWTurtleData {
    int idnest,hatched,died_in_nest,alive_in_nest,undeveloped,
            unhatched,predated_eggs, eggs_quantity, depth;
    Date hatch_dataa, nest_loc_date;
    String beach,habitat,specie,nest_notes, hatch_notes;
    float gps_east,gps_south,distance_to_tide;

    public NestWTurtleData() {
    }

    public int getIdnest() {
        return idnest;
    }

    public void setIdnest(int idnest) {
        this.idnest = idnest;
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

    public int getEggs_quantity() {
        return eggs_quantity;
    }

    public void setEggs_quantity(int eggs_quantity) {
        this.eggs_quantity = eggs_quantity;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Date getHatch_dataa() {
        return hatch_dataa;
    }

    public void setHatch_dataa(Date hatch_dataa) {
        this.hatch_dataa = hatch_dataa;
    }

    public Date getNest_loc_date() {
        return nest_loc_date;
    }

    public void setNest_loc_date(Date nest_loc_date) {
        this.nest_loc_date = nest_loc_date;
    }

    public String getBeach() {
        return beach;
    }

    public void setBeach(String beach) {
        this.beach = beach;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getNest_notes() {
        return nest_notes;
    }

    public void setNest_notes(String nest_notes) {
        this.nest_notes = nest_notes;
    }

    public String getHatch_notes() {
        return hatch_notes;
    }

    public void setHatch_notes(String hatch_notes) {
        this.hatch_notes = hatch_notes;
    }

    public float getGps_east() {
        return gps_east;
    }

    public void setGps_east(float gps_east) {
        this.gps_east = gps_east;
    }

    public float getGps_south() {
        return gps_south;
    }

    public void setGps_south(float gps_south) {
        this.gps_south = gps_south;
    }

    public float getDistance_to_tide() {
        return distance_to_tide;
    }

    public void setDistance_to_tide(float distance_to_tide) {
        this.distance_to_tide = distance_to_tide;
    }
}
