package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.RoomsDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.RoomsDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_order_others;
import com.example.cp17312_nhom6_duan1.fragment.Fragment_order_you;

import java.util.ArrayList;

public class OrderDoctorActivity extends AppCompatActivity {
    private TextView tvNameDoctor, tvNameService, tvNameRooms, tvStartDate, tvStartTime, tvPrice;
    private RadioButton rdoOrderYou, rdoOrderI;
    private FrameLayout frameLayout;
    public static ArrayList<OrderDoctorDTO> listOrderDoctor = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_doctor);
        init();
        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor", -1);
        String startTime = intent.getStringExtra("startTime");
        String startDate = intent.getStringExtra("startDate");
        tvStartDate.setText(startDate);
        tvStartTime.setText(startTime);

        SharedPreferences sharedPreferences = getSharedPreferences("getOrderDoctor",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("startDate",startDate);
        editor.putString("startTime",startTime);
        editor.putInt("idDoctor",idDoctor);
        editor.commit();


        DoctorDAO doctorDAO = new DoctorDAO(this);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);

        AccountDAO accountDAO = new AccountDAO(this);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(this);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(this);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());

        tvNameRooms.setText(roomsDTO.getName());
        tvStartTime.setText(startTime);
        tvStartDate.setText(startDate);
        tvPrice.setText(servicesDTO.getServicesPrice() + "đ");

        rdoOrderYou.setChecked(true);
        getFragment(new Fragment_order_you());

        rdoOrderYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(new Fragment_order_you());
            }
        });
        rdoOrderI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(new Fragment_order_others());
            }
        });
    }

    public void init() {
        tvNameDoctor = findViewById(R.id.tvNameDoctor);
        tvNameService = findViewById(R.id.tvNameService);
        tvNameRooms = findViewById(R.id.tvNameRooms);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvPrice = findViewById(R.id.tvPrice);
        rdoOrderI = findViewById(R.id.rdoOrderI);
        rdoOrderYou = findViewById(R.id.rdoOrderYou);
        frameLayout = findViewById(R.id.frameLayout);
    }
    public void getFragment(Fragment fg){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fg).commit();
    }
}