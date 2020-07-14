package com.example.monitoriadetartarugas.domain.entitys;


import java.io.Serializable;

public class Users implements Serializable {
    private String email, password, fname, surname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString() {
        return "[Email: "+email+", password: "+password+"]";
    }
}
