package com.example.monitoriadetartarugas.domain.entitys;


import java.io.Serializable;

public class Activities implements Serializable {
    private String activity;

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
