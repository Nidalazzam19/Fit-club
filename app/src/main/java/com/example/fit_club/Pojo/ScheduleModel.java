package com.example.fit_club.Pojo;

public class ScheduleModel {

    String activity,day,time,place;

    public ScheduleModel(String activity, String day, String time, String place) {
        this.activity = activity;
        this.day = day;
        this.time = time;
        this.place = place;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



}
