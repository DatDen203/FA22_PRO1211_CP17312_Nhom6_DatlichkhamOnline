package com.example.cp17312_nhom6_duan1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterCalenderDoctor;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.AllDTO;

import java.util.ArrayList;


public class Fragment_Calender_Doctor extends Fragment {

    private AppCompatSpinner spCalenderDate;
    private RecyclerView rcvCalenderDoctor;

    ArrayList<String> dates = new ArrayList<>();

    DoctorDAO doctorDAO;
    ArrayList<AllDTO> listCalenders;
    ArrayList<AllDTO> listCalendersToday;
    AdapterCalenderDoctor adapterCalenderDoctor;

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
        if(id!=-1){
            listCalenders = doctorDAO.CalendarDoctor(doctorDAO.getIdDoctorByIdUser(id));
            listCalendersToday =doctorDAO.CalendarDoctorByDateNow(doctorDAO.getIdDoctorByIdUser(id));
        }

        dates.add("All");
        dates.add("Today");
        ArrayAdapter<String> adapterSpDates = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dates);
        adapterSpDates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCalenderDate.setAdapter(adapterSpDates);
        spCalenderDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    adapterCalenderDoctor = new AdapterCalenderDoctor(doctorDAO, listCalenders);
                    rcvCalenderDoctor.setAdapter(adapterCalenderDoctor);
                } else if (position == 1) {
                    adapterCalenderDoctor= new AdapterCalenderDoctor(doctorDAO,listCalendersToday);
                    rcvCalenderDoctor.setAdapter(adapterCalenderDoctor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void findViewId(View view) {
        spCalenderDate = view.findViewById(R.id.sp_calender_date);
        rcvCalenderDoctor = view.findViewById(R.id.rcv_calender_doctor);
    }
}