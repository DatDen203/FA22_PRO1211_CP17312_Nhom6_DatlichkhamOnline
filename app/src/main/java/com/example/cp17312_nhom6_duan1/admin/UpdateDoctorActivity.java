package com.example.cp17312_nhom6_duan1.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.SpinneRoomsAdapter;
import com.example.cp17312_nhom6_duan1.adapter.SpinnerServiceAdapter;
import com.example.cp17312_nhom6_duan1.adapter.SpinnerTimeWorkAdapter;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.RoomsDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.RoomsDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class UpdateDoctorActivity extends AppCompatActivity {
    private Spinner spRooms, spServices, spTimeWork;
    private SpinneRoomsAdapter spinneRoomsAdapter;
    private SpinnerServiceAdapter spinnerServiceAdapter;
    private SpinnerTimeWorkAdapter spinnerTimeWorkAdapter;
    private Button btnUpdateSaveDoctor;
    private TextInputLayout edNameUpdateDoctor,edUpdatePhoneDoctor,edUpdateDes,edUpdateBirthdayDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor);
        init();

        RoomsDAO roomsDAO = new RoomsDAO(this);
        ServicesDAO servicesDAO = new ServicesDAO(this);
        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(this);
        AccountDAO accountDAO = new AccountDAO(this);
        DoctorDAO doctorDAO = new DoctorDAO(this);

        ArrayList<RoomsDTO> listDtoRooms = roomsDAO.selectAll();
        spinneRoomsAdapter = new SpinneRoomsAdapter(listDtoRooms, this);
        spRooms.setAdapter(spinneRoomsAdapter);

        ArrayList<ServicesDTO> listDtoService = servicesDAO.selectAll();
        spinnerServiceAdapter = new SpinnerServiceAdapter(listDtoService, this);
        spServices.setAdapter(spinnerServiceAdapter);

        ArrayList<TimeWorkDTO> listDtoTimeWork = timeWorkDAO.getAll();
        spinnerTimeWorkAdapter = new SpinnerTimeWorkAdapter(listDtoTimeWork,this);
        spTimeWork.setAdapter(spinnerTimeWorkAdapter);

        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor",-1);
        Toast.makeText(this, idDoctor+"", Toast.LENGTH_SHORT).show();

        //Gắn dữ
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());

        edNameUpdateDoctor.getEditText().setText(accountDTO.getFullName());
        edUpdateBirthdayDoctor.getEditText().setText(doctorDTO.getBirthday());
        edUpdateDes.getEditText().setText(doctorDTO.getDescription());
        edUpdatePhoneDoctor.getEditText().setText(accountDTO.getPhoneNumber());

        for(int i=0;i<listDtoRooms.size();i++){
            RoomsDTO roomsDTO = listDtoRooms.get(i);
            if(roomsDTO.getId()==doctorDTO.getRoom_id()){
                spRooms.setSelection(i);
                spRooms.setSelected(true);
            }
        }

        for(int i=0;i<listDtoService.size();i++){
            ServicesDTO servicesDTO = listDtoService.get(i);
            if(servicesDTO.getServicesId() == doctorDTO.getService_id()){
                spServices.setSelected(true);
                spServices.setSelection(i);
            }
        }

        for(int i=0;i<listDtoTimeWork.size();i++){
            TimeWorkDTO timeWorkDTO = listDtoTimeWork.get(i);
            if(timeWorkDTO.getId() == doctorDTO.getTimework_id()){
                spTimeWork.setSelection(i);
                spTimeWork.setSelected(true);
            }
        }

        btnUpdateSaveDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountDTO.setFullName(edNameUpdateDoctor.getEditText().getText().toString());
                accountDTO.setPhoneNumber(edUpdatePhoneDoctor.getEditText().getText().toString());

                int res  =accountDAO.updateAccount(accountDTO);

                doctorDTO.setBirthday(edUpdateBirthdayDoctor.getEditText().getText().toString());

                ServicesDTO servicesDTO = (ServicesDTO) spServices.getSelectedItem();
                doctorDTO.setService_id(servicesDTO.getServicesId());

                RoomsDTO roomsDTO = (RoomsDTO) spRooms.getSelectedItem();
                doctorDTO.setRoom_id(roomsDTO.getId());

                doctorDTO.setDescription(edUpdateDes.getEditText().getText().toString());

                TimeWorkDTO timeWorkDTO  = (TimeWorkDTO) spTimeWork.getSelectedItem();
                doctorDTO.setTimework_id(timeWorkDTO.getId());

                int res1 = doctorDAO.updateRow(doctorDTO);
                if(res1>0){
                    Toast.makeText(UpdateDoctorActivity.this, "Cập nhật bác sĩ thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpdateDoctorActivity.this, "Cập nhật bác sĩ không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void init(){
        spRooms = findViewById(R.id.spRooms);
        spServices = findViewById(R.id.spServices);
        spTimeWork = findViewById(R.id.spTimeWork);
        edNameUpdateDoctor = findViewById(R.id.edNameUpdateDoctor);
        edUpdateBirthdayDoctor = findViewById(R.id.edUpdateBirthdayDoctor);
        edUpdateDes = findViewById(R.id.edUpdateDes);
        edUpdatePhoneDoctor = findViewById(R.id.edUpdatePhoneDoctor);
        btnUpdateSaveDoctor = findViewById(R.id.btnUpdateSaveDoctor);
    }
}