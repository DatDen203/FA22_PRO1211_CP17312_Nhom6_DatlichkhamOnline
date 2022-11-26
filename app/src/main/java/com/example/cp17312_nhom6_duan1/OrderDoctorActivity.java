package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.DateIntervalInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.adapter.AdapterListTimeWorkDetail;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.RoomsDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDAO;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.RoomsDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDTO;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDetailDTO;

import java.util.ArrayList;

public class OrderDoctorActivity extends AppCompatActivity {
    private TextView tvNameDoctor,tvDes,tvTimeWork,tvNameSerivce,tvPrice,tvNameRoom;
    private RecyclerView rcv_list_timework_detail;
    private Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_doctor);
        init();
        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor",-1);

        DoctorDAO doctorDAO = new DoctorDAO(this);


        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);
        AccountDAO accountDAO = new AccountDAO(this);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        tvNameDoctor.setText(accountDTO.getFullName());

        tvDes.setText(doctorDTO.getDescription());

        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(this);
        TimeWorkDTO timeWorkDTO = timeWorkDAO.getDtoTimeWork(doctorDTO.getTimework_id());
        tvTimeWork.setText(timeWorkDTO.getSession());

        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(this);
        timeWorkDetailDAO.open();
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = timeWorkDetailDAO.selectAll();
        AdapterListTimeWorkDetail adapterListTimeWorkDetail = new AdapterListTimeWorkDetail(listTimeWorkDetailDto,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rcv_list_timework_detail.setLayoutManager(manager);
        rcv_list_timework_detail.setAdapter(adapterListTimeWorkDetail);

        ServicesDAO servicesDAO = new ServicesDAO(this);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        tvNameSerivce.setText(servicesDTO.getServicesName());
        tvPrice.setText(servicesDTO.getServicesPrice()+"đ");

        RoomsDAO roomsDAO = new RoomsDAO(this);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        tvNameRoom.setText(roomsDTO.getName());

    }
    public void init(){
        tvNameDoctor = findViewById(R.id.tvNameDoctor);
        tvDes = findViewById(R.id.tvDes);
        tvTimeWork = findViewById(R.id.tvTimeWork);
        tvNameSerivce = findViewById(R.id.tvNameSerivce);
        tvPrice = findViewById(R.id.tvPrice);
        tvNameRoom = findViewById(R.id.tvNameRoom);
        rcv_list_timework_detail = findViewById(R.id.rcv_list_timework_detail);
        btnOrder = findViewById(R.id.btnOrder);
    }
}