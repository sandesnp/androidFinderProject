package com.example.andoird_finderproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andoird_finderproject.httpRequests.requestUser;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.user;

public class UserActivity extends AppCompatActivity {

    EditText etUserName, etUserAddress, etUserPhoneNumber, etUserEmail, etUserPassword;
    Button btnUserRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        global.StrictMode();

        etUserAddress = findViewById(R.id.etuseraddress);
        etUserName = findViewById(R.id.etusername);
        etUserPhoneNumber = findViewById(R.id.etuserphonenumber);
        etUserEmail = findViewById(R.id.etuseremail);
        etUserPassword = findViewById(R.id.etuserpassword);
        btnUserRegister = findViewById(R.id.btnuserregister);

        btnUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user user = new user();
                user.setUserfullname(etUserName.getText().toString());
                user.setUseraddress(etUserAddress.getText().toString());
                user.setUseremail(etUserEmail.getText().toString());
                user.setUserphonenumber(etUserPhoneNumber.getText().toString());
                user.setPassword(etUserPassword.getText().toString());
                if (new requestUser(user).post()) {
                    Toast.makeText(UserActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                }
            }
        });
    }
}