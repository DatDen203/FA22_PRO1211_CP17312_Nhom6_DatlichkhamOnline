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

    private AppCompatSpinner spCalenderDate;
    private RecyclerView rcvCalenderDoctor;

    private ArrayList<String> dates = new ArrayList<>();

    private DoctorDAO doctorDAO;
    private ArrayList<OrderDoctorDTO> listAllOrderNoCofirm;
    private ArrayList<OrderDoctorDTO> listAllOrderYesCofirm;
    private ArrayList<OrderDoctorDTO> listOrderNoCofirmByToDay;
    private DoctorDTO doctorDTO;
    private OrderDoctorDAO orderDoctorDAO;
    private String date;

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
            listAllOrderYesCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllYesConfirm(doctorDTO.getId());

            //Lay ngay hien tai
            //Lấy ra ngày hiện tại
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            date = year + "/" + (month + 1) + "/" + day;

            listOrderNoCofirmByToDay = orderDoctorDAO.listOrderDoctorByDateToDayByDoctor(date, doctorDTO.getId());
        }

        dates.add("Today no confirm");
        dates.add("All no confirm");
        dates.add("All yes confirm");
        ArrayAdapter<String> adapterSpDates = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dates);
        adapterSpDates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCalenderDate.setAdapter(adapterSpDates);
        spCalenderDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    showListNoCofrimByTDay();

                } else if (position == 1) {
                    showListAllNoCofrim();

                } else {
                    showListYesConfirm();


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showListYesConfirm() {
        AdapterOrderYesConfirm adapterOrderYesConfirm = new AdapterOrderYesConfirm(listAllOrderYesCofirm,getContext());
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvCalenderDoctor.setLayoutManager(manager3);
        rcvCalenderDoctor.setAdapter(adapterOrderYesConfirm);
    }

    private void showListAllNoCofrim() {
        AdapterOrderNocofirm adapterOrderNocofirm = new AdapterOrderNocofirm(listAllOrderNoCofirm, getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCalenderDoctor.setLayoutManager(manager1);
        rcvCalenderDoctor.setAdapter(adapterOrderNocofirm);
    }

    private void showListNoCofrimByTDay() {
        AdapterOrderNocofirm adapterOrderNocofirm = new AdapterOrderNocofirm(listOrderNoCofirmByToDay, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCalenderDoctor.setLayoutManager(manager);
        rcvCalenderDoctor.setAdapter(adapterOrderNocofirm);
    }

    public void findViewId(View view) {
        spCalenderDate = view.findViewById(R.id.sp_calender_date);
        rcvCalenderDoctor = view.findViewById(R.id.rcv_calender_doctor);
    }

    @Override
    public void onResume() {
        super.onResume();
        listAllOrderNoCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllNoConfirm(doctorDTO.getId());
        listAllOrderYesCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllYesConfirm(doctorDTO.getId());
        listOrderNoCofirmByToDay = orderDoctorDAO.listOrderDoctorByDateToDayByDoctor(date, doctorDTO.getId());
        showListYesConfirm();
        showListAllNoCofrim();
        showListNoCofrimByTDay();
    }

}