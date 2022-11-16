package com.example.cp17312_nhom6_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.adapter.BannerAdapter;
import com.example.cp17312_nhom6_duan1.dto.Banner;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView tvHiName;
    ImageView imgOpenNav,imgAvt;
    CircleIndicator circleIndicator;
    ViewPager viewPager;
    BannerAdapter bannerAdapter;
    List<Banner> list;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager.getCurrentItem();
            if (currentPosition == list.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(currentPosition + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvHiName = findViewById(R.id.tv_hi_name);
        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circle_indicator);
        drawerLayout = findViewById(R.id.drawerLayout);
        imgOpenNav = findViewById(R.id.img_open_nav);
        
//        tvHiName.setText("Hi " + getIntent().getStringExtra("fullname") + " !");
        list = getListPhoto();
        bannerAdapter = new BannerAdapter(this, list);
        viewPager.setAdapter(bannerAdapter);

        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable, 3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        imgOpenNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_exit:
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        View headerView = navView.getHeaderView(0);
        tvHiName = headerView.findViewById(R.id.tv_hi_name);
        imgAvt = headerView.findViewById(R.id.imgAvt);
        SharedPreferences preferences = getSharedPreferences("getIdUser",MODE_PRIVATE);
        String imgUser = preferences.getString("imgUser","");
        String tvFullName = preferences.getString("fullname","");
        tvHiName.setText("Hi " +tvFullName+ " !");
        if(!imgUser.isEmpty()){
            Uri uri = Uri.parse(imgUser);
            imgAvt.setImageURI(uri);
        }

        imgAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),UpdateUserActivity.class);
                startActivity(intent);
            }
        });
        

//        findViewById(R.id.item_doctor).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, DoctorActivity.class));
//            }
//        });
    }

    private List<Banner> getListPhoto() {
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner2));
        list.add(new Banner(R.drawable.banner3));
        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}