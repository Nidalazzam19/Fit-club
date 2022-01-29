package com.example.fit_club.Pojo;

public class Booking {
    String name,email,time,telephone,trainername;

    public Booking(String name, String email, String time, String telephone, String trainername) {
        this.name = name;
        this.email = email;
        this.time = time;
        this.telephone = telephone;
        this.trainername = trainername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTrainername() {
        return trainername;
    }

    public void setTrainername(String trainername) {
        this.trainername = trainername;
    }
}
