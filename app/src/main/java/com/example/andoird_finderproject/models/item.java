package com.example.andoird_finderproject.models;

public class item {

    private String itemname, itemdescription, itemtype, itembrand;

    public item(String itemname, String itemdescription, String itemtype, String itembrand) {
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemtype = itemtype;
        this.itembrand = itembrand;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public String getItemtype() {
        return itemtype;
    }

    public String getItembrand() {
        return itembrand;
    }
}
