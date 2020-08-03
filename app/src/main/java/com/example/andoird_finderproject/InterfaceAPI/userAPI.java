package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.user;
import com.example.andoird_finderproject.response.responseUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface userAPI {

    @POST("user")
    Call<responseUser> ownerRegister(@Body user user);



}
