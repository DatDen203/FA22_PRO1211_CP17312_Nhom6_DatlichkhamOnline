package com.example.cp17312_nhom6_duan1.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerAccountDoctor;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerAccountUser;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerCategory;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerDoctor;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerFile;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerRoom;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ManagerService;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_Statistical;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_Statistical_month_doctor;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_ViewPagerTimeWork;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_list_examination_today;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_list_order_to_day;
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
        replaceFragmet(new Fragment_list_order_to_day());
        navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(true);
        toolBar.setTitle("Danh s??ch ?????t l???ch kh??m h??m nay");

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
                toolBar.setTitle("Qu???n l?? b??c s??");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_managerFile:
                replaceFragmet(new Fragment_ManagerFile());
                toolBar.setTitle("Qu???n l?? h??? s?? b???nh nh??n");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_managerTimeWork:
                toolBar.setTitle("Qu???n l?? ca l??m vi???c");
                replaceFragmet(new Fragment_ViewPagerTimeWork());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_managerCategory:
                replaceFragmet(new Fragment_ManagerCategory());
                toolBar.setTitle("Qu???n l?? lo???i d???ch v??? kh??m");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_managerService:
                replaceFragmet(new Fragment_ManagerService());
                toolBar.setTitle("Qu???n l?? chuy??n khoa");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_managerRoom:
                replaceFragmet(new Fragment_ManagerRoom());
                toolBar.setTitle("Qu???n l?? ph??ng kh??m");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_AccountDoctor:
                replaceFragmet(new Fragment_ManagerAccountDoctor());
                toolBar.setTitle("Danh s??ch t??i kho???n b??c s?? ");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.m_AccountUser:
                replaceFragmet( new Fragment_ManagerAccountUser());
                toolBar.setTitle("Danh s??ch t??i kho???n ng?????i d??ng");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.Statistical:
                replaceFragmet(new Fragment_Statistical());
                toolBar.setTitle("Th???ng k??");
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                break;
            case R.id.list_order_today:
                toolBar.setTitle("Danh s??ch ?????t l???ch kh??m h??m nay");
                replaceFragmet(new Fragment_list_order_to_day());
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
                break;
            case R.id.list_examination_today:
                toolBar.setTitle("Danh s??ch l???ch kh??m h??m nay");
                replaceFragmet(new Fragment_list_examination_today());
                navigationAdmin.getMenu().findItem(R.id.list_examination_today).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerFile).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_AccountUser).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.Statistical).setChecked(false);
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