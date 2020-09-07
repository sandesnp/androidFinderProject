package com.example.andoird_finderproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoird_finderproject.InterfaceAPI.shopAPI;
import com.example.andoird_finderproject.httpRequests.requestItem;
import com.example.andoird_finderproject.httpRequests.requestShop;
import com.example.andoird_finderproject.models.item;
import com.example.andoird_finderproject.models.location_address;
import com.example.andoird_finderproject.models.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemAddAcitivty extends AppCompatActivity implements View.OnClickListener {

    private EditText itemName, itemDescription, itemType, itemBrand;
    private Button btnAdd;
    private TextView tv_selectImage, tv_selectShop;
    private ImageView ItemImage_01, ItemImage_02, ItemImage_03;
    private String imagePath, imageName = "default";
    public static Activity itemActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add_acitivty);
        itemActivity=this;
        itemName = findViewById(R.id.etitemaddname);
        itemDescription = findViewById(R.id.etitemadddescription);
        itemType = findViewById(R.id.etitemaddtype);
        itemBrand = findViewById(R.id.etitemaddbrand);
        findViewById(R.id.btnitemadd).setOnClickListener(this);
        ItemImage_01 = findViewById(R.id.iv_item_image01);
        ItemImage_02 = findViewById(R.id.iv_item_image02);
        ItemImage_03 = findViewById(R.id.iv_item_image03);
        tv_selectImage = findViewById(R.id.tv_select_item_image);
        tv_selectShop = findViewById(R.id.tv_select_item_shop);
        tv_selectImage.setOnClickListener(this);
        tv_selectShop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnitemadd:
                validationCheck();
                break;

            case R.id.tv_select_item_image:
                SelectImage();
                break;
            case R.id.tv_select_item_shop:
                dialogSelectShop();
                break;
        }
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
            Uri uri = data.getData();
            ItemImage_01.setImageURI(uri);
            ItemImage_02.setImageURI(uri);
            ItemImage_03.setImageURI(uri);
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

    public void validationCheck() {
        if (TextUtils.isEmpty(itemName.getText())) {
            itemName.setError("Please enter Item Name");
            itemName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(itemDescription.getText())) {
            itemDescription.setError("Please enter Item Name");
            itemDescription.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(itemType.getText())) {
            itemType.setError("Please enter Item Name");
            itemType.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(itemBrand.getText())) {
            itemBrand.setError("Please enter Item Name");
            itemBrand.requestFocus();
            return;
        }
        if (imagePath.equals("default")) {
            tv_selectImage.setError("Please Select Image for Item");
            tv_selectImage.requestFocus();
            return;
        }
        if(ShopID.equals("")){
            Toast.makeText(itemActivity, "Please select a Shop", Toast.LENGTH_SHORT).show();
            return;
        }
        itemRegister();
    }

    public void itemRegister() {
        imageName = new requestItem(null).postImage(imagePath);
        item item = new item();
        item.setItemname(itemName.getText().toString());
        item.setItemdescription(itemDescription.getText().toString());
        item.setItemtype(itemType.getText().toString());
        item.setItembrand(itemBrand.getText().toString());
        item.setItempicture(imageName);
        if(new requestItem(item).update(ShopID))
        {
            //If the update is success
            itemName.setText(null);
            itemDescription.setText(null);
            itemType.setText(null);
            itemBrand.setText(null);
            ItemImage_01.setImageResource(R.drawable.red_background_clipart);
            ItemImage_02.setImageResource(R.drawable.red_background_clipart);
            ItemImage_03.setImageResource(R.drawable.red_background_clipart);
            ShopID="";
        }

    }

    String ShopID="";
    public void dialogSelectShop() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        View mView = inflater.inflate(R.layout.select_shop, null);
        final Spinner spn_selectShop = (Spinner) mView.findViewById(R.id.spn_select_shop);

        final String[] shop_Info = {"", ""}; //setting size
        ArrayList<shop> shops = new requestShop(null).fetchArray_Shop();
        //if the fetched Shop array is not null then
        if (shops != null) {

            final Map<String, String> shopMap = new HashMap<>();
            for (shop shop : shops) {
                shopMap.put(shop.getShopname(), shop.get_id());
            }
            ArrayAdapter arrayAdapterShop = new ArrayAdapter<>(this,
                    android.R.layout.select_dialog_item, new ArrayList(shopMap.keySet()));
            spn_selectShop.setAdapter(arrayAdapterShop);

            spn_selectShop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    shop_Info[0] = adapterView.getItemAtPosition(i).toString();
                    shop_Info[1] = shopMap.get(shop_Info[0]); //Extracting id with respect to selected shop
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(mView)
                // Add action buttons
                .setPositiveButton("Select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Do something here
                        ShopID=shop_Info[1];
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }


}