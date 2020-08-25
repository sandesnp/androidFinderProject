package com.example.andoird_finderproject.httpRequests;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.andoird_finderproject.InterfaceAPI.userAPI;
import com.example.andoird_finderproject.MainActivity;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.user;
import com.example.andoird_finderproject.parentClass.restfulRequest;
import com.example.andoird_finderproject.response.responseUser;

import retrofit2.Call;
import retrofit2.Response;

public class requestUser extends restfulRequest {

    private user user;
    SharedPreferences sharedPreferences = MainActivity.activity.getSharedPreferences("User", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    //Just pass null tp constructor when accessing function that doesn't require it.
    public requestUser(user user) {
        this.user = user;
    }

    @Override
    public boolean post() {
        userAPI userAPI = global.getInstance().create(userAPI.class);
        Call<responseUser> ownerCall = userAPI.userRegister(user);
        try {
            Response<responseUser> userResponse = ownerCall.execute();
            if (userResponse.isSuccessful()) {
                global.token += userResponse.body().getToken();
                String abc = userResponse.body().getToken();
                editor.putString("token", userResponse.body().getToken());
                editor.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean fetchBy(String email) {
        userAPI userAPI = global.getInstance().create(userAPI.class);
        Call<responseUser> userCall = userAPI.emailExist(email);
        try {
            Response<responseUser> userResponse = userCall.execute();
            if (userResponse.isSuccessful() && !userResponse.body().getToken().equals("")) {
                global.token += userResponse.body().getToken();
                String abc = userResponse.body().getToken();
                editor.putString("token", userResponse.body().getToken());
                editor.commit();
                return true; //sends true if email exists
            }
            return false; //sends false if email doesn't exists
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
