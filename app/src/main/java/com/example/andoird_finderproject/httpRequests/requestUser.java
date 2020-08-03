package com.example.andoird_finderproject.httpRequests;

import com.example.andoird_finderproject.InterfaceAPI.userAPI;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.user;
import com.example.andoird_finderproject.parentClass.restfulRequest;
import com.example.andoird_finderproject.response.responseUser;

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
        Call<responseUser> ownerCall = userAPI.ownerRegister(user);
        try {
            Response<responseUser> userResponse = ownerCall.execute();
            if (userResponse.isSuccessful()) {
                global.token+=userResponse.body().getToken();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
