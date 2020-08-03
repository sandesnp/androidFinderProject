package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.owner;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.response.responseImage;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface shopAPI {

    @Multipart
    @POST("upload")
    Call<responseImage> uploadImage(@Part MultipartBody.Part img);

    @POST("shop")
    Call<shop> shopRegister(@Body shop shop);

}
