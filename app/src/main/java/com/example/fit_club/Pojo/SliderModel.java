package com.example.fit_club.Pojo;

public class SliderModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlider_image() {
        return slider_image;
    }

    public void setSlider_image(String slider_image) {
        this.slider_image = slider_image;
    }

    String name,slider_image;

    public SliderModel(String name, String slider_image) {
        this.name = name;
        this.slider_image = slider_image;
    }


}
