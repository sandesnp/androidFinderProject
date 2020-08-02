package com.example.andoird_finderproject.models;

public class shop {

private String shopownerid, shopname,shoplogo, shoplocation;
private shopcoordinate shopcoordinate;


    public String getShopownerid() {
        return shopownerid;
    }

    public void setShopownerid(String shopownerid) {
        this.shopownerid = shopownerid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShoplogo() {
        return shoplogo;
    }

    public void setShoplogo(String shoplogo) {
        this.shoplogo = shoplogo;
    }

    public String getShoplocation() {
        return shoplocation;
    }

    public void setShoplocation(String shoplocation) {
        this.shoplocation = shoplocation;
    }

    public com.example.andoird_finderproject.models.shopcoordinate getLatilongi() {
        return shopcoordinate;
    }

    public void setLatilongi(com.example.andoird_finderproject.models.shopcoordinate latilongi) {
        this.shopcoordinate = latilongi;
    }
}

