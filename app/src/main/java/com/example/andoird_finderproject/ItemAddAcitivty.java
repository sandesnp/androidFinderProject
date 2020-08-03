package com.example.andoird_finderproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ItemAddAcitivty extends AppCompatActivity {

    private EditText itemName, itemDescription, itemType, itemBrand;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add_acitivty);

        itemName= findViewById(R.id.etitemaddname);
        itemDescription= findViewById(R.id.etitemadddescription);
        itemType= findViewById(R.id.etitemaddtype);
        itemBrand= findViewById(R.id.etitemaddbrand);
        btnAdd= findViewById(R.id.btnitemadd);





    }
}