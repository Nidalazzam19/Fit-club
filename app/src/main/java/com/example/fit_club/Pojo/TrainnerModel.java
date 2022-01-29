package com.example.fit_club.Pojo;

public class TrainnerModel {

    String email;
    String name;
    String tele;
    public TrainnerModel(String email, String name, String tele) {
        this.email = email;
        this.name = name;
        this.tele = tele;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }







}
