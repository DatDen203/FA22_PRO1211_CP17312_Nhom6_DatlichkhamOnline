package com.example.cp17312_nhom6_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.cp17312_nhom6_duan1.adapter.BannerAdapter;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.Banner;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;
import com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user.Fragment_bookingHistory;
import com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user.Fragment_file;
import com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user.Fragment_home;
import com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user.Fragment_home_new;
import com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user.Fragment_info;
import com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user.Fragment_listDoctor;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity{
//    private RelativeLayout itemService1;
//    private RelativeLayout itemDoctor;
//    private RelativeLayout itemService2;
//    private RelativeLayout itemService3;
//    private RelativeLayout itemService4;
    DrawerLayout drawerLayout;
    TextView tvHiName;
    ImageView imgOpenNav, imgAvt;
//    CircleIndicator circleIndicator;
//    ViewPager viewPager;
//    BannerAdapter bannerAdapter;
//    List<Banner> list;
//    ServicesDAO servicesDAO;
//    ArrayList<ServicesDTO> listService;
//    Handler handler = new Handler(Looper.getMainLooper());
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            int currentPosition = viewPager.getCurrentItem();
//            if (currentPosition == list.size() - 1) {
//                viewPager.setCurrentItem(0);
//            } else {
//                viewPager.setCurrentItem(currentPosition + 1);
//            }
//        }
//    };
int back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        back = intent.getIntExtra("back",-1);
//        servicesDAO = new ServicesDAO(getBaseContext());

//        viewPager = findViewById(R.id.view_pager);
//        circleIndicator = findViewById(R.id.circle_indicator);
        drawerLayout = findViewById(R.id.drawerLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        imgOpenNav = toolbar.findViewById(R.id.img_open_nav);

//        tvHiName.setText("Hi " + getIntent().getStringExtra("fullname") + " !");
//        list = getListPhoto();
//        bannerAdapter = new BannerAdapter(this, list);
//        viewPager.setAdapter(bannerAdapter);

//        circleIndicator.setViewPager(viewPager);
//        handler.postDelayed(runnable, 3000);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                handler.removeCallbacks(runnable);
//                handler.postDelayed(runnable, 3000);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        imgOpenNav.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        NavigationView navView = findViewById(R.id.nav_view);
        replaceFragment(new Fragment_home_new());
        navView.getMenu().findItem(R.id.nav_home_user).setChecked(true);
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_home_user:
                    replaceFragment(new Fragment_home_new());
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(true);
                    navView.getMenu().findItem(R.id.nav_listDoctor).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_info).setChecked(false);
                    break;
                case R.id.nav_listDoctor:
                    replaceFragment(new Fragment_listDoctor());
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_listDoctor).setChecked(true);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_info).setChecked(false);
                    break;
                case R.id.nav_file:
                    replaceFragment(new Fragment_file());
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_listDoctor).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(true);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_info).setChecked(false);
                    break;
                case R.id.nav_booking_history:
                    replaceFragment(new Fragment_bookingHistory());
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_listDoctor).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(true);
                    navView.getMenu().findItem(R.id.nav_info).setChecked(false);
                    break;
                case R.id.nav_info:
                    replaceFragment(new Fragment_info());
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_listDoctor).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_info).setChecked(true);
                    break;
                case R.id.nav_exit:
                    finish();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        View headerView = navView.getHeaderView(0);
        tvHiName = headerView.findViewById(R.id.tv_hi_name);
        imgAvt = headerView.findViewById(R.id.imgAvt);
        SharedPreferences preferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
        String imgUser = preferences.getString("imgUser", "");
        String tvFullName = preferences.getString("fullname", "");
        tvHiName.setText("Hi " + tvFullName + " !");
        if (!imgUser.isEmpty()) {
            Uri uri = Uri.parse(imgUser);
            imgAvt.setImageURI(uri);
        }

        imgAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), UpdateUserActivity.class);
                startActivity(intent);
            }
        });
//        listService = servicesDAO.selectAll();
//        itemService1 = findViewById(R.id.item_service1);
//        itemDoctor = findViewById(R.id.item_doctor);
//        itemService2 = findViewById(R.id.item_service2);
//        itemService3 = findViewById(R.id.item_service3);
//        itemService4 = findViewById(R.id.item_service4);
//        itemService1.setOnClickListener(view -> {
//            Intent intent = new Intent(this, DoctorByServiceActivity.class);
//            intent.putExtra("serviceid", listService.get(0).getServicesId());
//            intent.putExtra("serviceName", listService.get(0).getServicesName());
//            startActivity(intent);
//        });
//        itemService2.setOnClickListener(view -> {
//            Intent intent = new Intent(this, DoctorByServiceActivity.class);
//            intent.putExtra("serviceid", listService.get(1).getServicesId());
//            intent.putExtra("serviceName", listService.get(1).getServicesName());
//            startActivity(intent);
//        });
//        itemService3.setOnClickListener(view -> {
//            Intent intent = new Intent(this, DoctorByServiceActivity.class);
//            intent.putExtra("serviceid", listService.get(2).getServicesId());
//            intent.putExtra("serviceName", listService.get(3).getServicesName());
//            startActivity(intent);
//        });
//        itemService4.setOnClickListener(view -> {
//            Intent intent = new Intent(this, DoctorByServiceActivity.class);
//            intent.putExtra("serviceid", listService.get(3).getServicesId());
//            intent.putExtra("serviceName", listService.get(3).getServicesName());
//            startActivity(intent);
//        });
//        itemDoctor.setOnClickListener(view -> {
//            Intent intent = new Intent(this, DoctorByServiceActivity.class);
//            intent.putExtra("serviceid", -1);
//            intent.putExtra("serviceName", "List of Doctors");
//            startActivity(intent);
//        });

    }

    private List<Banner> getListPhoto() {
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner2));
        list.add(new Banner(R.drawable.banner3));
        return list;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framer, fragment).commit();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        handler.removeCallbacks(runnable);
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        handler.postDelayed(runnable, 3000);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        if(back!=1){
            finish();
        }
        super.onPause();
    }
}