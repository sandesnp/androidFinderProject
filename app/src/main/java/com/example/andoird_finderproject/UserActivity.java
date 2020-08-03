package com.example.andoird_finderproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andoird_finderproject.httpRequests.requestUser;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.user;

public class UserActivity extends AppCompatActivity {

    EditText etOwnerName, etOwnerAddress, etOwnerPhoneNumber, etOwnerEmail;
    Button btnOwnerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        global.StrictMode();

        etOwnerAddress = findViewById(R.id.etuseraddress);
        etOwnerName = findViewById(R.id.etusername);
        etOwnerPhoneNumber = findViewById(R.id.etuserphonenumber);
        etOwnerEmail = findViewById(R.id.etuseremail);
        btnOwnerRegister = findViewById(R.id.btnuserregister);

        btnOwnerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user user = new user();
                user.setUserfullname(etOwnerName.getText().toString());
                user.setUseraddress(etOwnerAddress.getText().toString());
                user.setUseremail(etOwnerEmail.getText().toString());
                user.setUserphonenumber(etOwnerPhoneNumber.getText().toString());
                if(new requestUser(user).post()){
                    Toast.makeText(UserActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}