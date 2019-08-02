package com.example.monitoriadetartarugas.domain.entitys;



import java.io.Serializable;

public class WindDirection implements Serializable {
    private int idwd;
    private String name;

    public int getIdwd(){
        return idwd;
    }

    public String getName(){
        return name;
    }

    public void setIdwd(int idwd){
        this.idwd = idwd;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
