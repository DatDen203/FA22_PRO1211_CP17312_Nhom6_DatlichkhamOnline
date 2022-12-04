package com.example.cp17312_nhom6_duan1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cp17312_nhom6_duan1.fragment.FragmentItemFileDetail;
import com.example.cp17312_nhom6_duan1.fragment.fragment_admin.FragmentOrderYesComfrim;
import com.example.cp17312_nhom6_duan1.fragment.fragment_admin.FragmentUpdateDoctorInOrder;

public class AdapterViewPagerItemFileDetail extends FragmentStateAdapter {
    public AdapterViewPagerItemFileDetail(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new FragmentItemFileDetail();
           case 1:
               return new FragmentUpdateDoctorInOrder();
           case 2:
               return new FragmentOrderYesComfrim();
           default:
               return new FragmentItemFileDetail();
           }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
