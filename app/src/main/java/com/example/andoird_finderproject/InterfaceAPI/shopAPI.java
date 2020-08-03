package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.item;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.response.responseImage;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface shopAPI {

    @Multipart
    @POST("upload")
    Call<responseImage> uploadImage(@Part MultipartBody.Part img);

    @POST("shop")
    Call<shop> shopRegister(@Header("Authorization") String token, @Body shop shop);

    @PUT("shop/{id}")
    Call<shop> itemRegister(@Path("id") String shopID, @Body item item);

}
