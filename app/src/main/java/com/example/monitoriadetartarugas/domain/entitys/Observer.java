package com.example.monitoriadetartarugas.domain.entitys;

import java.io.Serializable;

public class Observer implements Serializable {
    private int idobserver;
    private String observer;

    public int getIdobserver() {
        return idobserver;
    }

    public void setIdobserver(int idobserver) {
        this.idobserver = idobserver;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    @Override
    public String toString() {
        return "Observer{" +
                "idobserver=" + idobserver +
                ", observer='" + observer + '\'' +
                '}';
    }
}
