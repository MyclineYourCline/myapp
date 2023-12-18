package com.example.ungdungchplay.ViewPagerManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ungdungchplay.FragmentManager.ScreenFragment1;
import com.example.ungdungchplay.FragmentManager.ScreenFragment2;
import com.example.ungdungchplay.FragmentManager.ScreenFragment3;

public class ViewPagerAdapterScreen extends FragmentStatePagerAdapter {
    public ViewPagerAdapterScreen(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return  new ScreenFragment1();
            case 1: return  new ScreenFragment2();
            case 2: return  new ScreenFragment3();
            default: return  new ScreenFragment1();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
