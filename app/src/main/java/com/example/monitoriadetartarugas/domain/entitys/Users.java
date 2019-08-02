package com.example.monitoriadetartarugas.domain.entitys;



public class Users {
    private String email;
    private String password;

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
