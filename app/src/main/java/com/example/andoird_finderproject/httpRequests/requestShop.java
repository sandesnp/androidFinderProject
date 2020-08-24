package com.example.andoird_finderproject.httpRequests;

import com.example.andoird_finderproject.InterfaceAPI.shopAPI;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.parentClass.restfulRequest;
import com.example.andoird_finderproject.response.responseImage;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class requestShop extends restfulRequest {

    private shop shop;

    public requestShop(shop shop) {
        this.shop = shop;
    }

    @Override
    public String postImage(String imagePath) {

        String imageName="";
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        shopAPI shopAPI= global.getInstance().create(shopAPI.class);
        Call<responseImage> shopCall= shopAPI.uploadImage(body);


        try {
            Response<responseImage> imageResponse= shopCall.execute();
            imageName= imageResponse.body().getFilename();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageName;
    }

    @Override
    public boolean post() {

        shopAPI shopAPI= global.getInstance().create(shopAPI.class);
        Call<shop> shopCall= shopAPI.shopRegister(global.token, shop);
        global.StrictMode();
        try {
            Response<shop> shopResponse= shopCall.execute();
            if(shopResponse.isSuccessful()){
             return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
