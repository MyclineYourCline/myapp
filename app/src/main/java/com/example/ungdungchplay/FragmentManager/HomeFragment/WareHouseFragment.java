package com.example.ungdungchplay.FragmentManager.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ungdungchplay.R;
import com.example.ungdungchplay.ViewPagerManager.ViewPagerAdapterService;
import com.google.android.material.tabs.TabLayout;

public class WareHouseFragment extends Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager2;
    private ViewPagerAdapterService pagerAdapterService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ware_house, container, false);
        unitUI();
        return view;
    }
    void unitUI(){
        tabLayout = view.findViewById(R.id.fWare_tab);
        viewPager2 = view.findViewById(R.id.fWare_container);
        pagerAdapterService = new ViewPagerAdapterService(getChildFragmentManager()
                , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager2.setAdapter(pagerAdapterService);
        tabLayout.setupWithViewPager(viewPager2);

    }
}