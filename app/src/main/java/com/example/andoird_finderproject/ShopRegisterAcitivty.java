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
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoird_finderproject.httpRequests.requestShop;
import com.example.andoird_finderproject.models.shop;
import com.example.andoird_finderproject.models.shopcoordinate;

public class ShopRegisterAcitivty extends AppCompatActivity implements View.OnClickListener {

    private EditText etShopName, etShopLocation;
    private TextView tvCoordinate;
    private Button etShopButton;
    private ImageView etShopLogo;
    private Boolean checkImage = false;
    private String imagePath, imageName = "default";
    private String coordinateLatitude, coordinateLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register_acitivty);

//        Bundle bundle = getIntent().getExtras();
//        ownerID = bundle.getString("ownerID");  //passed from other activity

        etShopLocation = findViewById(R.id.etrgshoplocation);
        etShopName = findViewById(R.id.etrgshopname);
        tvCoordinate = findViewById(R.id.tvrgcoordinate);
        etShopButton = findViewById(R.id.btnrgshopregister);
        etShopLogo = findViewById(R.id.imgrgshoplogo);

        tvCoordinate.setOnClickListener(this);
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
        if(resultCode==22){
            coordinateLongitude=Double.toString(data.getDoubleExtra("longitude",0));
            coordinateLatitude=Double.toString(data.getDoubleExtra("latitude",0));
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
        shop.setShopcoordinate(new shopcoordinate(coordinateLatitude, coordinateLongitude, etShopName.getText().toString()));

        if (new requestShop(shop).post()) {
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
            case R.id.tvrgcoordinate:
                String ShopName = etShopName.getText().toString();
                if (!ShopName.equals("")) {
                    Intent intent = new Intent(this, SelectLocationActivity.class);
                    intent.putExtra("shopName", etShopName.getText().toString());
                    startActivityForResult(intent, 22);
                }
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
            imageName = new requestShop(new shop()).postImage(imagePath);
        }
        if(coordinateLatitude !=null && coordinateLongitude!=null){
            Register();
        }
        else{
            Toast.makeText(this, "Please select Map Coordinates.", Toast.LENGTH_SHORT).show();
        }
    }
}