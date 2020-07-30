package com.example.andoird_finderproject.controllers;

import com.example.andoird_finderproject.InterfaceAPI.ownerAPI;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.owner;
import com.example.andoird_finderproject.parentClass.restfulRequest;

import retrofit2.Call;
import retrofit2.Response;

public class controllerOwner extends restfulRequest {

    private owner owner;


    public controllerOwner(owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean post() {
        ownerAPI ownerAPI = global.getInstance().create(ownerAPI.class);
        Call<owner> ownerCall = ownerAPI.ownerRegister(owner);
        try {
            Response<owner> ownerResponse = ownerCall.execute();
            if (ownerResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
