package com.example.andoird_finderproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

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
}