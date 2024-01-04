package com.example.ungdungchplay.ActivityManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.ungdungchplay.AnimationTranformer.ZoomOutPageTransformer;
import com.example.ungdungchplay.ModelManager.Table;
import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewpagerAdapterOder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OderActivity extends AppCompatActivity implements View.OnClickListener {
    private Table table;
    private Intent intent;
    private Bundle bundle;
    private ViewpagerAdapterOder viewpagerAdapterOder;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ImageView img_back;
    private TextView txt_title;

    public Table getTable() {
        return table;
    }
    private static final String[] TAB_TITLES = {"Oder", "Detail"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        getDataForTableActivity();
        unitUI();
    }
    private void getDataForTableActivity (){
        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null){
            table = (Table) bundle.getSerializable("Data");
        }
    }
    @SuppressLint("WrongViewCast")
    private void unitUI (){
        viewpagerAdapterOder = new ViewpagerAdapterOder(this);
        viewPager2 = findViewById(R.id.OderActivity_frame_container);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        tabLayout = findViewById(R.id.OderActivity_tabLayout);
        viewPager2.setAdapter(viewpagerAdapterOder);
        img_back = findViewById(R.id.OderActivity_img_back);
        txt_title = findViewById(R.id.OderActivity_txt_title);
        txt_title.setText("Oder Table: "+table.getName());
        img_back.setOnClickListener(this);

        //
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            // Thiết lập tiêu đề cho mỗi tab dựa trên mảng chuỗi tên
            tab.setText(TAB_TITLES[position]);
        }).attach();
        //
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.OderActivity_img_back: finish();
            break;
        }
    }
}