package com.example.cp17312_nhom6_duan1.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cp17312_nhom6_duan1.fragment.Fragment_Statistical_month;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_Statistical_month_doctor;

public class AdapterViewPagerStatistical extends FragmentPagerAdapter {

    public AdapterViewPagerStatistical(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment_Statistical_month();
            case 1:
                return new Fragment_Statistical_month_doctor();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Thống kê tháng";
            case 1:
                return "Doctor";

        }
        return super.getPageTitle(position);
    }
}
