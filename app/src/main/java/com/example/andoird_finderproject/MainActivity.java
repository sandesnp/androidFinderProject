package com.example.andoird_finderproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.andoird_finderproject.fragments.Fragment_Home;
import com.example.andoird_finderproject.fragments.Fragment_Popular;
import com.example.andoird_finderproject.fragments.Fragment_Profile;
import com.example.andoird_finderproject.global.global;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        global.StrictMode();
        activity = this;
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString("token", "").equals("")) {
            //if token not empty
            global.token = "Bearer " + sharedPreferences.getString("token", "");
        }

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        grantPermission();
        loadFragment(new Fragment_Home(), 1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                loadFragment(new Fragment_Home(), 1);
                break;
            case R.id.navigation_popular:
                loadFragment(new Fragment_Popular(), 2);
                break;
            case R.id.navigation_profile:
                loadFragment(new Fragment_Profile(), 3);
                break;
        }
        return true;
    }

    private int position = 0;

    private void loadFragment(Fragment fragment, int position) {

        if (this.position != position) {
            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            if (this.position < position) {
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right, R.anim.enter_from_right, R.anim.exit_from_right);
            } else {
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_left, R.anim.enter_from_right, R.anim.exit_from_right);
            }
            this.position = position;
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (position != 1) {
            loadFragment(new Fragment_Home(), 1);
            return;
        }
        super.onBackPressed();
    }

    private int STORAGE_PERMISSION_CODE = 1;
    String[] PERMISSIONS = new String[2];
    int Fine_Location, External_Storage;

    public void grantPermission() {
        Fine_Location = ContextCompat.checkSelfPermission(MainActivity.activity,
                Manifest.permission.ACCESS_FINE_LOCATION);
        External_Storage = ContextCompat.checkSelfPermission(MainActivity.activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (Fine_Location == PackageManager.PERMISSION_GRANTED && External_Storage == PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (Fine_Location != PackageManager.PERMISSION_GRANTED) {

            PERMISSIONS[0] = Manifest.permission.ACCESS_FINE_LOCATION;
        }
        if (External_Storage != PackageManager.PERMISSION_GRANTED) {

            PERMISSIONS[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        }
        requestStoragePermission();
    }

    private void requestStoragePermission() {
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED) {
                finishAffinity();
            }
        }
    }
}