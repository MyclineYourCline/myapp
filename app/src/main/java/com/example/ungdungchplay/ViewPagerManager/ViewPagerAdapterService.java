package com.example.ungdungchplay.ViewPagerManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ungdungchplay.FragmentManager.ServiceFragment.AddServices;
import com.example.ungdungchplay.FragmentManager.ServiceFragment.FixedService;
import com.example.ungdungchplay.FragmentManager.ServiceFragment.NotFixedService;

public class ViewPagerAdapterService extends FragmentStateAdapter {


    public ViewPagerAdapterService(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new FixedService();
           case 1:
               return new NotFixedService();
           case 2:
               return new AddServices();
       }
       return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
