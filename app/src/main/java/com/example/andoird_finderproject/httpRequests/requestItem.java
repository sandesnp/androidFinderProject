package com.example.andoird_finderproject.httpRequests;

import com.example.andoird_finderproject.models.item;
import com.example.andoird_finderproject.parentClass.restfulRequest;

public class requestItem extends restfulRequest {

    private item item;

    public requestItem(item item) {
        this.item = item;
    }

    @Override
    public boolean update() {


        return false;
    }
}
