package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ungdungchplay.Database.SqlOpenHelper;
import com.example.ungdungchplay.InterfaceManager.ActivityInterface.ScreenInterFace;
import com.example.ungdungchplay.Presenter.ActivityPresenter.ScreenPresenter;
import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterScreen;

import me.relex.circleindicator.CircleIndicator;

public class ScreenActivity extends AppCompatActivity implements ScreenInterFace, View.OnClickListener{
    private ViewPager viewPager;
    private ScreenPresenter screenPresenter;
    private ViewPagerAdapterScreen adapterScreen;
    private CircleIndicator indicator;
    private TextView txt_next, txt_skip;
    private ImageView img_next; private RelativeLayout layout_bottom;
    public static final int pagerIndex1 =0;
    public static final int pagerIndex2 =1;
    public static final int pagerIndex3 =2;
    public static final String SHARE_NAME = "ResTof";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        unitView();
        adapterScreen = new ViewPagerAdapterScreen(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterScreen);
        indicator.setViewPager(viewPager);

    }
    void unitView (){
        screenPresenter = new ScreenPresenter(this);
        viewPager = findViewById(R.id.Screen_viewPager);
        indicator = findViewById(R.id.Screen_indicator);
        txt_next = findViewById(R.id.Screen_txt_next);
        txt_skip = findViewById(R.id.Screen_txtSkip);
        img_next = findViewById(R.id.Screen_imgNext);
        layout_bottom = findViewById(R.id.layout_bot);
        //
        txt_next.setOnClickListener(this);
        txt_skip.setOnClickListener(this);
        //
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        screenPresenter.checkActive(sharedPreferences.getBoolean("active", false));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == pagerIndex3){
                    txt_skip.setVisibility(View.GONE);
                    layout_bottom.setVisibility(View.GONE);
                }
                else{
                    txt_skip.setVisibility(View.VISIBLE);
                    layout_bottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
         SqlOpenHelper helper = new SqlOpenHelper(this);
         helper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Screen_txt_next:t:
                screenPresenter.next();
                break;
            case R.id.Screen_txtSkip:
                screenPresenter.skip();
                break;
            default:
        }
    }
    @Override
    public void skip() {
               viewPager.setCurrentItem(pagerIndex3);
    }

    @Override
    public void next() {
        if (viewPager.getCurrentItem() == pagerIndex3){
            return;
        }
        else{
            viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
        }
    }
    @Override
    public void activeSuccess() {
        d("ca" + "chung", "activeSuccess: ");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void activeErr() {
        d("ca" + "chung", "activeErr: ");
    }

}