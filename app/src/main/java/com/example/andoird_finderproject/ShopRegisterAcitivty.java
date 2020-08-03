package com.example.andoird_finderproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andoird_finderproject.controllers.controllerShop;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.models.shopcoordinate;

public class ShopRegisterAcitivty extends AppCompatActivity implements View.OnClickListener {

    private EditText etShopName, etShopLocation;
    private Button etShopButton;
    private ImageView etShopLogo;
    private Boolean checkImage = false;
    private String imagePath, imageName = "default";
    private String ownerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register_acitivty);

//        Bundle bundle = getIntent().getExtras();
//        ownerID = bundle.getString("ownerID");  //passed from other activity

        etShopLocation = findViewById(R.id.etrgshoplocation);
        etShopName = findViewById(R.id.etrgshopname);
        etShopButton = findViewById(R.id.btnrgshopregister);
        etShopLogo = findViewById(R.id.imgrgshoplogo);

        etShopButton.setOnClickListener(this);
        etShopLogo.setOnClickListener(this);

    }

    private void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            checkImage = true;
            Uri uri = data.getData();
            etShopLogo.setImageURI(uri);
            imagePath = getImagePath(uri);
        }

    }


    private String getImagePath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void Register() {

        shop shop = new shop();
        shop.setShopname(etShopName.getText().toString());
        shop.setShoplocation(etShopLocation.getText().toString());
        shop.setShoplogo(imageName);
        shop.setShopownerid("5f22cd86f6c57c541026e83b");
        shop.setShopcoordinate(new shopcoordinate("123", "321", "I am marker."));

        if (new controllerShop(shop).post()) {
            Toast.makeText(this, "Successfully Registered Shop.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnrgshopregister:
                validationCheck();
                break;
            case R.id.imgrgshoplogo:
                SelectImage();
                break;
        }
    }

    public void validationCheck() {
        if (TextUtils.isEmpty(etShopName.getText())) {
            etShopName.setError("Please enter Shop Name");
            etShopName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(etShopLocation.getText())) {
            etShopLocation.setError("Please enter Shop Location");
            etShopLocation.requestFocus();
            return;
        }

        if (checkImage) {
            imageName = new controllerShop(new shop()).postImage(imagePath);
        }
        Register();
    }
}