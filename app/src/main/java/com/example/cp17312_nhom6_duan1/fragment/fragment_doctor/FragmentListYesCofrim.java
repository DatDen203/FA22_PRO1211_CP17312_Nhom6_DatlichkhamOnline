package com.example.cp17312_nhom6_duan1.fragment.fragment_doctor;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterOrderYesConfirm;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;

import java.util.ArrayList;


public class FragmentListYesCofrim extends Fragment {
    private RecyclerView rcvListYesCofrim;
    private ArrayList<OrderDoctorDTO> listAllOrderYesCofirm;
    private DoctorDTO doctorDTO;
    private OrderDoctorDAO orderDoctorDAO;
    DoctorDAO doctorDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list_yes_cofrim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListYesCofrim = view.findViewById(R.id.rcv_listYesCofrim);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int id = sharedPreferences.getInt("idUser", -1);
        doctorDAO = new DoctorDAO(getContext());
        orderDoctorDAO = new OrderDoctorDAO(getContext());
        if (id != -1) {
            doctorDTO = doctorDAO.getDtoDoctorByIdAccount(id);
        }
        showData();

    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void showData(){
        listAllOrderYesCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllYesConfirm(doctorDTO.getId());
        AdapterOrderYesConfirm adapterOrderYesConfirm = new AdapterOrderYesConfirm(listAllOrderYesCofirm, getContext());
        rcvListYesCofrim.setAdapter(adapterOrderYesConfirm);
    }


}