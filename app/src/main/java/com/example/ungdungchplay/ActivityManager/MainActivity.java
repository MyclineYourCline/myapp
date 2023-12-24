package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ungdungchplay.FragmentManager.HomeFragment.AccountFragment;
import com.example.ungdungchplay.FragmentManager.HomeFragment.ActiveFragment;
import com.example.ungdungchplay.FragmentManager.HomeFragment.HomeFragment;
import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unitUI();
    }
    private void unitUI(){
        navigationView = findViewById(R.id.Main_btn_navigation);
        //
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Main_container, new HomeFragment());
        fragmentTransaction.commit();
        //
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_warehouse:
                            Intent intent = new Intent(MainActivity.this, WareHouseActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.menu_nav_tableActive:
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.Main_container, new ActiveFragment());
                            fragmentTransaction.commit();
                            break;
                        case R.id.menu_nav_account:
                            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction1.replace(R.id.Main_container, new AccountFragment());
                            fragmentTransaction1.commit();
                            break;
                    case R.id.menu_nav_home:
                    default:
                            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction2.replace(R.id.Main_container, new HomeFragment());
                            fragmentTransaction2.commit();
                            break;

                }
                return true;
            }
        });
    }
}