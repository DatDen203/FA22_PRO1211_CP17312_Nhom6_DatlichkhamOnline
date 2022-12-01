package com.example.cp17312_nhom6_duan1.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterStatisticalDoctor;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.AllDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_Statistical_month_doctor extends Fragment {
    private RecyclerView rcvStatisticalDoctorForMonth;
    private TextView tvSumPrice;
    OrderDetailDAO orderDetailDAO;
    ArrayList<AllDTO> listPriceMonth;
    DoctorDAO doctorDAO;
    String startDate;
    String endDate;
    DoctorDTO doctorDTO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistical_month_by_doctor,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvStatisticalDoctorForMonth = view.findViewById(R.id.rcv_StatisticalDoctorForMonth);
        tvSumPrice = view.findViewById(R.id.tv_sumPrice);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUSer = sharedPreferences.getInt("idUser",-1);
        doctorDAO = new DoctorDAO(getContext());
        doctorDTO = doctorDAO.getDtoDoctorByIdAccount(idUSer);
        orderDetailDAO = new OrderDetailDAO(getContext());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(day<10){
            endDate = year + "/" + (month + 1) + "/" +"0"+ day;
        }else{
            endDate = year + "/" + (month + 1) + "/" + day;
        }
        startDate = year + "/" + (month+1) + "/" + "01";
        Log.e("TAG", "onViewCreated: "+startDate);
        Log.e("TAG", "onViewCreated: "+endDate);
        listPriceMonth = orderDetailDAO.getListPriceByMonthDoctor(startDate, endDate,doctorDTO.getId());
        Log.e("TAG", "onViewCreated: list.size="+listPriceMonth.size());
        AdapterStatisticalDoctor adapterStatisticalDoctor = new AdapterStatisticalDoctor(listPriceMonth,"month");
        rcvStatisticalDoctorForMonth.setAdapter(adapterStatisticalDoctor);
        tvSumPrice.setText(orderDetailDAO.getSumPriceByMonthDoctor(startDate, endDate, doctorDTO.getId())+" đ");
    }

    @Override
    public void onResume() {
        super.onResume();
        listPriceMonth = orderDetailDAO.getListPriceByMonthDoctor(startDate, endDate,doctorDTO.getId());
        AdapterStatisticalDoctor adapterStatisticalDoctor = new AdapterStatisticalDoctor(listPriceMonth,"month");
        rcvStatisticalDoctorForMonth.setAdapter(adapterStatisticalDoctor);
        tvSumPrice.setText(orderDetailDAO.getSumPriceByMonthDoctor(startDate, endDate, doctorDTO.getId())+" đ");
    }
}
