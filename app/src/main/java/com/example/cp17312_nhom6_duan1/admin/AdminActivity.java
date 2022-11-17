package com.example.cp17312_nhom6_duan1.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.doctor.DoctorActivity;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerAccountDoctor;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerAccountUser;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerCategory;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerDoctor;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerFile;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerRoom;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerService;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ViewPagerTimeWork;
import com.example.cp17312_nhom6_duan1.fragment.FragmetTimeWork;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private FrameLayout fragmentContent;
    private NavigationView navigationAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolBar = findViewById(R.id.toolBar);
        fragmentContent = findViewById(R.id.fragment_content);
        navigationAdmin = findViewById(R.id.nv_view);
        toolBar.setNavigationOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        navigationAdmin.setNavigationItemSelectedListener(this);
        replaceFragmet(new Fragment_ManagerDoctor());
        navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.m_managerDoctor:
                replaceFragmet(new Fragment_ManagerDoctor());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_managerFile:
                replaceFragmet(new Fragment_ManagerFile());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_managerTimeWork:
                replaceFragmet(new Fragment_ViewPagerTimeWork());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_managerCategory:
                replaceFragmet(new Fragment_ManagerCategory());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_managerService:
                replaceFragmet(new Fragment_ManagerService());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_managerRoom:
                replaceFragmet(new Fragment_ManagerRoom());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_AccountDoctor:
                replaceFragmet(new Fragment_ManagerAccountDoctor());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                break;
            case R.id.m_AccountUser:
                replaceFragmet( new Fragment_ManagerAccountUser());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(true);
                break;
            case R.id.m_logOut:
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragmet(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit();
    }
}