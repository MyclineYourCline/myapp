package com.example.ungdungchplay.ActivityManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ungdungchplay.FragmentManager.HomeFragment.ActiveFragment;
import com.example.ungdungchplay.FragmentManager.HomeFragment.HomeFragment;
import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapterService viewPagerAdapterHome;
    private FrameLayout frameLayoutContainer;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unitUI();
    }
    private void unitUI(){
        frameLayoutContainer = findViewById(R.id.Main_container);
        navigationView = findViewById(R.id.Main_btn_navigation);
        //
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Main_container,new HomeFragment()).commit();
        //
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.Main_container,new HomeFragment()).commit();
                        break;
                    case R.id.menu_nav_warehouse:
                        Intent intent = new Intent(MainActivity.this, WareHouseActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_nav_tableActive:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.Main_container,new ActiveFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }
}