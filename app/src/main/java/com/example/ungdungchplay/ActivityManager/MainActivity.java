package com.example.ungdungchplay.ActivityManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ungdungchplay.Database.SqlOpenHelper;
import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterHome;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapterHome viewPagerAdapterHome;
    private ViewPager viewPager;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unitUI();
    }
    private void unitUI(){
        viewPager = findViewById(R.id.Main_viewPage);
        navigationView = findViewById(R.id.Main_btn_navigation);
        //
        viewPagerAdapterHome = new ViewPagerAdapterHome(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapterHome);
        //
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.menu_nav_home).setChecked(true);
                    break;
                    case 1: navigationView.getMenu().findItem(R.id.menu_nav_warehouse).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.menu_nav_tableActive).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_home:
                        viewPager.setCurrentItem(ViewPagerAdapterHome.PAGER_POSITION);
                        break;
                    case R.id.menu_nav_warehouse:
                        viewPager.setCurrentItem(ViewPagerAdapterHome.WAREHOUSE_POSITION);
                        break;
                    case R.id.menu_nav_tableActive:
                        viewPager.setCurrentItem(ViewPagerAdapterHome.ACTIVE_POSITION);
                        break;
                }
                return true;
            }
        });
    }
}