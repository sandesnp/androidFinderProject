package com.example.andoird_finderproject.models;

import java.util.ArrayList;

public class shop {

private String shopownerid, shopname,shoplogo, shoplocation;
private shopcoordinate shopcoordinate;
private ArrayList<item> shopitems;


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

    public com.example.andoird_finderproject.models.shopcoordinate getShopcoordinate() {
        return shopcoordinate;
    }

    public void setShopcoordinate(com.example.andoird_finderproject.models.shopcoordinate shopcoordinate) {
        this.shopcoordinate = shopcoordinate;
    }

    public ArrayList<item> getShopitems() {
        return shopitems;
    }

}

