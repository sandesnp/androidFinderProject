package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.owner;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ownerAPI {

    @POST("owner")
    Call<owner> ownerRegister(@Body owner owner);



}
