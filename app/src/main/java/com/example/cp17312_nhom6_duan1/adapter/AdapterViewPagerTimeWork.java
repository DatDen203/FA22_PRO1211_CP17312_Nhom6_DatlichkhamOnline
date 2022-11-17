package com.example.cp17312_nhom6_duan1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cp17312_nhom6_duan1.fragment.FragmentTimeWorkDetail;
import com.example.cp17312_nhom6_duan1.fragment.FragmetTimeWork;

public class AdapterViewPagerTimeWork extends FragmentStateAdapter {


    public AdapterViewPagerTimeWork(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmetTimeWork();
            case 1:
                return  new FragmentTimeWorkDetail();
            default:
                return new FragmetTimeWork();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
