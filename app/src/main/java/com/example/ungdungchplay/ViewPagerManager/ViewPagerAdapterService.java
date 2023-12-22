package com.example.ungdungchplay.ViewPagerManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ungdungchplay.FragmentManager.ServiceFragment.AddServices;
import com.example.ungdungchplay.FragmentManager.ServiceFragment.FixedService;
import com.example.ungdungchplay.FragmentManager.ServiceFragment.NotFixedService;

public class ViewPagerAdapterService extends FragmentStatePagerAdapter {
    public static final int ADD_POSITION = 0;
    public static final int FIXED_POSITION = 1;
    public static final int NOT_FIXED_POSITION = 2;
    public ViewPagerAdapterService(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case ADD_POSITION: return new FixedService();
            case FIXED_POSITION: return new NotFixedService();
            case NOT_FIXED_POSITION: return new AddServices();
            default:return new AddServices();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0: title = "Dịch vụ cố định";
            break;
            case 1: title = "Dịch vụ khác";
                break;
            case 2: title = "Thêm dịch vụ";
                break;
        }
        return title;
    }
}
