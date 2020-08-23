package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.user;
import com.example.andoird_finderproject.response.responseUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface userAPI {

    @POST("user")
    Call<responseUser> userRegister(@Body user user);

    //to Check if the google email is already registered
    @GET("user/{email}")
    Call<responseUser> emailExist(@Path("email") String email);

}
