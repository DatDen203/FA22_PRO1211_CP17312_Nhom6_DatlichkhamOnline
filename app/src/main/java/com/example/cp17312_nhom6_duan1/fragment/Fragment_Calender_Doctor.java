package com.example.cp17312_nhom6_duan1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterCalenderDoctor;
import com.example.cp17312_nhom6_duan1.adapter.AdapterOrderNocofirm;
import com.example.cp17312_nhom6_duan1.adapter.AdapterOrderYesConfirm;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.AllDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;

import java.util.ArrayList;
import java.util.Calendar;


public class Fragment_Calender_Doctor extends Fragment {

    private RecyclerView rcvCalenderDoctor;
    private DoctorDAO doctorDAO;
    private ArrayList<OrderDoctorDTO> listAllOrderNoCofirm;
    private DoctorDTO doctorDTO;
    private OrderDoctorDAO orderDoctorDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__calender__doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewId(view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int id = sharedPreferences.getInt("idUser", -1);
        doctorDAO = new DoctorDAO(getContext());
        orderDoctorDAO = new OrderDoctorDAO(getContext());
        if (id != -1) {
            doctorDTO = doctorDAO.getDtoDoctorByIdAccount(id);
            listAllOrderNoCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllNoConfirm(doctorDTO.getId());
            showListAllNoCofrim(listAllOrderNoCofirm);
        }
    }

    private void showListAllNoCofrim(ArrayList<OrderDoctorDTO> list) {
        AdapterOrderNocofirm adapterOrderNocofirm = new AdapterOrderNocofirm(list, getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCalenderDoctor.setLayoutManager(manager1);
        rcvCalenderDoctor.setAdapter(adapterOrderNocofirm);
    }

    public void findViewId(View view) {
        rcvCalenderDoctor = view.findViewById(R.id.rcv_calender_doctor);
    }

    @Override
    public void onResume() {
        super.onResume();
        listAllOrderNoCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllNoConfirm(doctorDTO.getId());
        showListAllNoCofrim(listAllOrderNoCofirm);

    }

}