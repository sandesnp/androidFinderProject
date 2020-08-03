package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface userAPI {

    @POST("user")
    Call<user> ownerRegister(@Body user user);



}
