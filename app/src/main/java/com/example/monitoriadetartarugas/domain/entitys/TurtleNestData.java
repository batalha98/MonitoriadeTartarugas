package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class TurtleNestData implements Serializable {

    int idturtle, idnest,hatched,died_in_nest,alive_in_nest,undeveloped,unhatched,predated_eggs,
            leftring,rightring,internal_tag, depth, eggs_quantity;
    Date hatch_dataa, nest_marking_date,tagg_dataa,obs_dataa;
    String nest_beach,habitat,obs_beach,activity,wc,wd;

    double ccl_measure,cwl_measure;
    float gps_east,gps_south,dune_height, distance_to_tide;

    public TurtleNestData() {
    }

    public int getIdturtle() {
        return idturtle;
    }

    public void setIdturtle(int idturtle) {
        this.idturtle = idturtle;
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

    public Date getHatch_dataa() {
        return hatch_dataa;
    }

    public void setHatch_dataa(Date hatch_dataa) {
        this.hatch_dataa = hatch_dataa;
    }

    public Date getNest_marking_date() {
        return nest_marking_date;
    }

    public void setNest_marking_date(Date nest_marking_date) {
        this.nest_marking_date = nest_marking_date;
    }

    public Date getTagg_dataa() {
        return tagg_dataa;
    }

    public void setTagg_dataa(Date tagg_dataa) {
        this.tagg_dataa = tagg_dataa;
    }

    public Date getObs_dataa() {
        return obs_dataa;
    }

    public void setObs_dataa(Date obs_dataa) {
        this.obs_dataa = obs_dataa;
    }

    public String getNest_beach() {
        return nest_beach;
    }

    public void setNest_beach(String nest_beach) {
        this.nest_beach = nest_beach;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getObs_beach() {
        return obs_beach;
    }

    public void setObs_beach(String obs_beach) {
        this.obs_beach = obs_beach;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getWc() {
        return wc;
    }

    public void setWc(String wc) {
        this.wc = wc;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
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

    public float getDune_height() {
        return dune_height;
    }

    public void setDune_height(float dune_height) {
        this.dune_height = dune_height;
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

    public float getDistance_to_tide() {
        return distance_to_tide;
    }

    public void setDistance_to_tide(float distance_to_tide) {
        this.distance_to_tide = distance_to_tide;
    }
}