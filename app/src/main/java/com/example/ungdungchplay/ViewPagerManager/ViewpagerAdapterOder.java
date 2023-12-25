package com.example.ungdungchplay.ViewPagerManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ungdungchplay.FragmentManager.OderFragment.OderDetailFragment;
import com.example.ungdungchplay.FragmentManager.OderFragment.OderFragment;

public class ViewpagerAdapterOder extends FragmentStateAdapter {
    public ViewpagerAdapterOder(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new OderFragment();
            case 1: return  new OderDetailFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
