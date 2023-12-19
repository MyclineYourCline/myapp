package com.example.ungdungchplay.ViewPagerManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ungdungchplay.FragmentManager.ActiveFragment;
import com.example.ungdungchplay.FragmentManager.HomeFragment;
import com.example.ungdungchplay.FragmentManager.WareHouseFragment;

public class ViewPagerAdapterHome extends FragmentStatePagerAdapter {
    public static final int PAGER_POSITION = 0;
    public static final int WAREHOUSE_POSITION = 1;
    public static final int ACTIVE_POSITION = 2;
    public ViewPagerAdapterHome(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case PAGER_POSITION: return new HomeFragment();
            case WAREHOUSE_POSITION: return new WareHouseFragment();
            case ACTIVE_POSITION: return new ActiveFragment();
            default:return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
