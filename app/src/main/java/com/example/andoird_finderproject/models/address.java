package com.example.andoird_finderproject.models;

public class address {
    private String place_id, suburb, county, country, village;

    public address(String place_id, String suburb, String county, String country, String village) {
        this.place_id = place_id;
        this.suburb = suburb;
        this.county = county;
        this.country = country;
        this.village = village;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public String getVillage() {
        return village;
    }
}
