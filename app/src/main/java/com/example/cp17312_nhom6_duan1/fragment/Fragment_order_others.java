package com.example.cp17312_nhom6_duan1.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cp17312_nhom6_duan1.ConfirmActivity;
import com.example.cp17312_nhom6_duan1.OrderDoctorActivity;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.FileDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_order_others extends Fragment {
    private EditText edNameFullName, edPhone, edBirthday, edCccd, edCountry, edJob, edAddress, edDes, edEmail;
    private Button btnOrder;
    private RadioButton rdoYes, rdoNo;
    private FileDAO fileDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_i, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edNameFullName = view.findViewById(R.id.edNameFullName);
        edPhone = view.findViewById(R.id.edPhone);
        edBirthday = view.findViewById(R.id.edBirthday);
        edCccd = view.findViewById(R.id.edCccd);
        edCountry = view.findViewById(R.id.edCountry);
        edJob = view.findViewById(R.id.edJob);
        edAddress = view.findViewById(R.id.edAddress);
        edDes = view.findViewById(R.id.edDes);
        btnOrder = view.findViewById(R.id.btnOrder);
        rdoYes = view.findViewById(R.id.rdoYes);
        rdoNo = view.findViewById(R.id.rdoNo);
        edEmail = view.findViewById(R.id.edEmail);
        fileDAO = new FileDAO(getContext());

        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);


        edBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = year + "/" + (month + 1) + "/" + day;
                        edBirthday.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });


        SharedPreferences preferences1 = getActivity().getSharedPreferences("getOrderDoctor", Context.MODE_PRIVATE);
        String startTime = preferences1.getString("startTime", "");
        String startDate = preferences1.getString("startDate", "");
        int idDoctor = preferences1.getInt("idDoctor", -1);

        DoctorDAO doctorDAO = new DoctorDAO(getContext());
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);

        ServicesDAO servicesDAO = new ServicesDAO(getContext());
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        OrderDoctorDAO orderDoctorDao = new OrderDoctorDAO(getContext());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFullname(edNameFullName.getText().toString());
                fileDTO.setUser_id(idUser);
                fileDTO.setBirthday(edBirthday.getText().toString());
                fileDTO.setCccd(edCccd.getText().toString());
                fileDTO.setCountry(edCountry.getText().toString());
                if (rdoYes.isChecked()) {
                    fileDTO.setBhyt("Có");
                } else {
                    fileDTO.setBhyt("Không");
                }
                fileDTO.setJob(edJob.getText().toString());
                fileDTO.setEmail(edEmail.getText().toString());
                fileDTO.setAddress(edAddress.getText().toString());
                fileDTO.setDes(edDes.getText().toString());
                long res = fileDAO.insertRow(fileDTO);
                FileDTO fileDTO1 = fileDAO.getFileDToTop();
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setFile_id(fileDTO1.getId());
                orderDoctorDTO.setDoctor_id(idDoctor);
                orderDoctorDTO.setStart_date(startDate);
                orderDoctorDTO.setStart_time(startTime);
                orderDoctorDTO.setTotal(servicesDTO.getServicesPrice());

                long res1 = orderDoctorDao.insertRow(orderDoctorDTO);
                OrderDoctorDTO orderDoctorDTO1 = orderDoctorDao.getOrderDoctorDtoDesc();
                if (res1 > 0) {
                    OrderDoctorActivity.listOrderDoctor.add(orderDoctorDTO1);
                    Intent intent = new Intent(getContext(), ConfirmActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
