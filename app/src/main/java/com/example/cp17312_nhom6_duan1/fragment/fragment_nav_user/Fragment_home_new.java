package com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.ListServiceActivity;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterListDoctor;
import com.example.cp17312_nhom6_duan1.adapter.AdapterListDoctorByService;
import com.example.cp17312_nhom6_duan1.adapter.AdapterListService;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;

import java.util.ArrayList;

public class Fragment_home_new extends Fragment {
    private RecyclerView rcv_list_services,rcv_list_doctors;
    private TextView tvListService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_services = view.findViewById(R.id.rcv_list_services);
        rcv_list_doctors = view.findViewById(R.id.rcv_list_doctors);
        tvListService = view.findViewById(R.id.tvListService);

        ServicesDAO servicesDAO = new ServicesDAO(getContext());
        ArrayList<ServicesDTO> listDtoService = servicesDAO.selectAll();
        AdapterListService adapterListService = new AdapterListService(listDtoService,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcv_list_services.setLayoutManager(manager);
        rcv_list_services.setAdapter(adapterListService);

        DoctorDAO doctorDAO = new DoctorDAO(getContext());
        ArrayList<DoctorDTO> listDtoDoctor = doctorDAO.selectAll();
        AdapterListDoctor adapterListDoctor = new AdapterListDoctor(listDtoDoctor,getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcv_list_doctors.setLayoutManager(manager1);
        rcv_list_doctors.setAdapter(adapterListDoctor);

        tvListService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListServiceActivity.class);
                startActivity(intent);
            }
        });

    }
}
