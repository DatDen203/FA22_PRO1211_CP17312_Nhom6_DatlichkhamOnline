package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.cp17312_nhom6_duan1.adapter.AdapterDoctorOrder;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;

import java.util.ArrayList;

public class ListDoctorByServiceActivity extends AppCompatActivity {
    private RecyclerView rcv_list_doctor_by_id_service;
    private DoctorDAO doctorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor_by_service);
        rcv_list_doctor_by_id_service = findViewById(R.id.rcv_list_doctor_by_id_service);

        Intent intent = getIntent();
        int idService = intent.getIntExtra("idService",-1);
        doctorDAO = new DoctorDAO(this);
        ArrayList<DoctorDTO> listDtoDoctorByIdService = doctorDAO.getDocotrByIdService(idService);
        AdapterDoctorOrder adapterDoctorOrder = new AdapterDoctorOrder(listDtoDoctorByIdService,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_list_doctor_by_id_service.setLayoutManager(manager);
        rcv_list_doctor_by_id_service.setAdapter(adapterDoctorOrder);

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}