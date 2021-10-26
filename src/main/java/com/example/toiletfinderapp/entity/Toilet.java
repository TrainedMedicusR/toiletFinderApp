package com.example.toiletfinderapp.entity;

public class Toilet {
    private Integer toiletId;

    private String name;

    private Double longitude;

    private Double latitude;

    private Boolean isDamage;

    public Integer getToiletId() {
        return toiletId;
    }

    public void setToiletId(Integer toiletId) {
        this.toiletId = toiletId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getIsDamage() {
        return isDamage;
    }

    public void setIsDamage(Boolean isDamage) {
        this.isDamage = isDamage;
    }
}