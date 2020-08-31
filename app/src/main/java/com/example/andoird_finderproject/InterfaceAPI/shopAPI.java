package com.example.andoird_finderproject.InterfaceAPI;

import com.example.andoird_finderproject.models.item;
import com.example.andoird_finderproject.models.location_address;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.response.responseImage;
import com.example.andoird_finderproject.response.responseItem;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface shopAPI {

    @Multipart
    @POST("upload")
    Call<responseImage> uploadImage(@Part MultipartBody.Part img);

    @POST("shop")
    Call<shop> shopRegister(@Header("Authorization") String token, @Body shop shop);


    @GET("v1/reverse.php?key=6ee6609c633bc3&format=json")
    Call<location_address> getAddress(@Query("lat") String lat, @Query("lon") String lon);

    @GET("shop")
    Call<ArrayList<shop>> getShop(@Header("Authorization") String token);

    @POST("shop/{id}/item")
    Call<responseItem> itemRegister(@Path("id") String id, @Body item item, @Header("Authorization") String token);

    @GET("shop/item")
    Call<List<item>> getItem();

}
