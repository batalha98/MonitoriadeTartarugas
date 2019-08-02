package com.example.monitoriadetartarugas.domain.entitys;



import java.io.Serializable;

public class Habitat implements Serializable {
    private int idhabitat;
    private String habitat;

    public int getIdhabitat(){
        return idhabitat;
    }

    public String getHabitat(){
        return habitat;
    }

    public void setIdhabitat(int idhabitat){
        this.idhabitat = idhabitat;
    }

    public void setHabitat(String habitat){
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return habitat;
    }
}
