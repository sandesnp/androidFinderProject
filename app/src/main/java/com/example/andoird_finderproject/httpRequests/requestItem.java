package com.example.andoird_finderproject.httpRequests;

import android.widget.Toast;

import com.example.andoird_finderproject.InterfaceAPI.shopAPI;
import com.example.andoird_finderproject.ItemAddAcitivty;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.item;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.parentClass.restfulRequest;
import com.example.andoird_finderproject.response.responseImage;
import com.example.andoird_finderproject.response.responseItem;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class requestItem extends restfulRequest {

    private item item;

    public requestItem(item item) {
        this.item = item;
    }


    public List<item> fetchItems() {
        shopAPI shopAPI = global.getInstance().create(shopAPI.class);
        Call<List<item>> itemCall = shopAPI.getItem();
        try {
            Response<List<item>> itemResponse = itemCall.execute();
            if (itemResponse.isSuccessful()) {
                return itemResponse.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean update(String ID) {
        shopAPI shopAPI = global.getInstance().create(shopAPI.class);
        Call<responseItem> shopCall = shopAPI.itemRegister(ID, item, global.token);
        global.StrictMode();
        try {
            Response<responseItem> shopResponse = shopCall.execute();
            if (shopResponse.isSuccessful()) {
                Toast.makeText(ItemAddAcitivty.itemActivity, shopResponse.body().getStatus(), Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String postImage(String imagePath) {
        String imageName = "";
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        shopAPI shopAPI = global.getInstance().create(shopAPI.class);
        Call<responseImage> shopCall = shopAPI.uploadImage(body);
        try {
            Response<responseImage> imageResponse = shopCall.execute();
            imageName = imageResponse.body().getFilename();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageName;
    }
}
