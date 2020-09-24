package com.example.andoird_finderproject.models;

public class shopcoordinate {

    private String latitude, longitude, marker;

    public shopcoordinate(String latitude, String longitude, String marker) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.marker = marker;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMarker() {
        return marker;
    }

}
