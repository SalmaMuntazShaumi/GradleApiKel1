package com.example.gradleapi;

public class TeamModel {
    private String sport,image,desc;

    public TeamModel(String image, String sport, String desc) {
        this.sport = sport;
        this.image = image;
        this.desc = desc;
    }

    public void setSport(String sport) { this.sport = sport; }

    public String getSport() { return sport; }

    public void setImage(String image) { this.image = image; }

    public String getImage() { return image; }

    public void setDesc(String desc) { this.desc = desc; }

    public String getDesc() { return desc; }
}
