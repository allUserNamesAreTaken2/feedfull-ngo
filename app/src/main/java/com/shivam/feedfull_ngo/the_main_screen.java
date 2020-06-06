package com.shivam.feedfull_ngo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class the_main_screen extends AppCompatActivity {
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_main_screen);

        prefs = getSharedPreferences("com.shivam.feedfull_ngo", MODE_PRIVATE);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        NavController navController= Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}