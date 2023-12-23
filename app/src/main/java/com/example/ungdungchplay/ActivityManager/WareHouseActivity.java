package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WareHouseActivity extends AppCompatActivity implements View.OnClickListener{
    private View view;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ImageView WareHouseActivity_back;
    private ViewPagerAdapterService pagerAdapterService;
    private static final String[] TAB_TITLES = {"Dịch vụ cố định", "Dịch vụ Khác", "Thêm dịch vụ"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware_house);
        unitUI();
    }
    void unitUI(){
        tabLayout = findViewById(R.id.WareHouseActivity_tab);
        viewPager2 = findViewById(R.id.WareHouseActivity_container);
        pagerAdapterService = new ViewPagerAdapterService(this);
        viewPager2.setAdapter(pagerAdapterService);
        viewPager2.setOffscreenPageLimit(2);
        WareHouseActivity_back = findViewById(R.id.WareHouseActivity_back);
        WareHouseActivity_back.setOnClickListener(this);
        // Kết nối TabLayout với ViewPager2 và đặt tên riêng cho từng tab
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            // Thiết lập tiêu đề cho mỗi tab dựa trên mảng chuỗi tên
            tab.setText(TAB_TITLES[position]);
        }).attach();
        //
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                d("ca" + "chung", "onPageScrolled: "+positionOffsetPixels);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.WareHouseActivity_back:
                finish();
            break;
        }
    }
}