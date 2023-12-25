package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungchplay.AnimationTranformer.ZoomOutPageTransformer;
import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WareHouseActivity extends AppCompatActivity implements View.OnClickListener{
    private View view;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ImageView WareHouseActivity_back;
    private TextView txt_title;
    private ViewPagerAdapterService pagerAdapterService;
    private static final String[] TAB_TITLES = {"Cố định", "Không cố định", "Thêm"};
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
        viewPager2.setPageTransformer( new ZoomOutPageTransformer());
        WareHouseActivity_back = findViewById(R.id.WareHouseActivity_img_back);
        WareHouseActivity_back.setOnClickListener(this);
        txt_title = findViewById(R.id.WareHouseActivity_txt_title);
        txt_title.setText("warehouses");
        // Kết nối TabLayout với ViewPager2 và đặt tên riêng cho từng tab
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            // Thiết lập tiêu đề cho mỗi tab dựa trên mảng chuỗi tên
            tab.setText(TAB_TITLES[position]);
        }).attach();
        //
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.WareHouseActivity_img_back:
                finish();
            break;
        }
    }
}