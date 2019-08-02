package com.example.monitoriadetartarugas.domain.entitys;


import java.io.Serializable;

public class Activities implements Serializable {
    private int idactivity;
    private String activity;

    public int getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(int idactivity) {
        this.idactivity = idactivity;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return activity;
    }
}
