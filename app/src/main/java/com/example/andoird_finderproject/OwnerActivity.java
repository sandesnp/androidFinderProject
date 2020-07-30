package com.example.andoird_finderproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andoird_finderproject.controllers.controllerOwner;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.owner;

public class OwnerActivity extends AppCompatActivity {

    EditText etOwnerName, etOwnerAddress, etOwnerPhoneNumber, etOwnerEmail;
    Button btnOwnerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        global.StrictMode();

        etOwnerAddress = findViewById(R.id.etowneraddress);
        etOwnerName = findViewById(R.id.etownername);
        etOwnerPhoneNumber = findViewById(R.id.etownerphonenumber);
        etOwnerEmail = findViewById(R.id.etowneremail);
        btnOwnerRegister = findViewById(R.id.btnownerregister);

        btnOwnerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                owner owner= new owner();
                owner.setOwnername(etOwnerName.getText().toString());
                owner.setOwneraddress(etOwnerAddress.getText().toString());
                owner.setOwneremail(etOwnerEmail.getText().toString());
                owner.setOwnerphonenumber(etOwnerPhoneNumber.getText().toString());
                if(new controllerOwner(owner).post()){
                    Toast.makeText(OwnerActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}