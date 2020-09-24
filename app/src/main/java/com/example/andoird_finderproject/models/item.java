package com.example.andoird_finderproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class item implements Serializable {

    private String  _id,itemname, itemdescription, itemtype, itembrand, itempicture, shopid, shoplogo,shoplocation;
    private shopcoordinate shopcoordinate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItembrand() {
        return itembrand;
    }

    public void setItembrand(String itembrand) {
        this.itembrand = itembrand;
    }

    public String getItempicture() {
        return itempicture;
    }

    public void setItempicture(String itempicture) {
        this.itempicture = itempicture;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public shopcoordinate getShopcoordinate() {
        return shopcoordinate;
    }

    public void setShopcoordinate(shopcoordinate shopcoordinate) {
        this.shopcoordinate = shopcoordinate;
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
}
