package com.example.andoird_finderproject.httpRequests;

import com.example.andoird_finderproject.InterfaceAPI.userAPI;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.user;
import com.example.andoird_finderproject.parentClass.restfulRequest;

import retrofit2.Call;
import retrofit2.Response;

public class requestUser extends restfulRequest {

    private user user;


    public requestUser(user user) {
        this.user = user;
    }

    @Override
    public boolean post() {
        userAPI userAPI = global.getInstance().create(userAPI.class);
        Call<user> ownerCall = userAPI.ownerRegister(user);
        try {
            Response<user> ownerResponse = ownerCall.execute();
            if (ownerResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
