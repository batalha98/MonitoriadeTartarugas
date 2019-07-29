package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;
import java.util.Date;

public class Hatchling implements Serializable {
    public int idnest;
    public Date dataa;
    public int hatched;
    public int died_in_nest;
    public int alive_in_nest;
    public int undeveloped;
    public int unhatched;
    public int predated_eggs;
    public String description;
}
