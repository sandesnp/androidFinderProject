package com.example.andoird_finderproject.models;

public class location_address {

    private String display_name;
    private address address;

    public location_address(String display_name, com.example.andoird_finderproject.models.address address) {
        this.display_name = display_name;
        this.address = address;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public address getAddress() {
        return address;
    }
}
