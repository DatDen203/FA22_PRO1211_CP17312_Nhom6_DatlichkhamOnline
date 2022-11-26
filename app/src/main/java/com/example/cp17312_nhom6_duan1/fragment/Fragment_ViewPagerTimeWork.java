package com.example.cp17312_nhom6_duan1.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterViewPagerTimeWork;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_ViewPagerTimeWork extends Fragment {
    private ViewPager2 vpTimeWork;
    AdapterViewPagerTimeWork adapterViewPagerTimeWork;
    private TabLayout tabTimeWork;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_time_work, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vpTimeWork = view.findViewById(R.id.vp_time_work);
        adapterViewPagerTimeWork = new AdapterViewPagerTimeWork(this);
        vpTimeWork.setAdapter(adapterViewPagerTimeWork);
        tabTimeWork = view.findViewById(R.id.tab_time_work);
        TabLayoutMediator mediator = new TabLayoutMediator(tabTimeWork, vpTimeWork, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Time Work");
                }else if(position==1){
                    tab.setText("Time Work Detail");
                }
            }
        });
        mediator.attach();
    }


}
