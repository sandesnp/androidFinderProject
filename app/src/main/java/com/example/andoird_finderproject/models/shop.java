package com.example.andoird_finderproject.models;

import java.util.ArrayList;

public class shop {

private String _id, shopname,shoplogo, shoplocation;
private shopcoordinate shopcoordinate;
private ArrayList<item> shopitems;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public shopcoordinate getShopcoordinate() {
        return shopcoordinate;
    }

    public void setShopcoordinate(shopcoordinate shopcoordinate) {
        this.shopcoordinate = shopcoordinate;
    }

    public ArrayList<item> getShopitems() {
        return shopitems;
    }

    public void setShopitems(ArrayList<item> shopitems) {
        this.shopitems = shopitems;
    }
}

