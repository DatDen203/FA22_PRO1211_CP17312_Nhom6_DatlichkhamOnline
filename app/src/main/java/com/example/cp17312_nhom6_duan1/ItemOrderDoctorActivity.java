package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Calendar;
import java.util.List;

public class ItemOrderDoctorActivity extends AppCompatActivity {
    private TextView tvNameDoctor, tvDes, tvTimeWork, tvNameSerivce, tvPrice, tvNameRoom,tvOrderDate,tvClickOrder;
    private EditText edDateOrder;
    private ImageView imgIconOrderDate;
    private RecyclerView rcv_list_timework_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_order_doctor);
        init();
        imgIconOrderDate.setVisibility(View.GONE);
        tvOrderDate.setVisibility(View.GONE);
        tvClickOrder.setVisibility(View.GONE);
        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(this);
        timeWorkDetailDAO.open();
        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor", -1);

        SharedPreferences sharedPreferences = getSharedPreferences("getIdOrderDoctor", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idDoctor", idDoctor);

        DoctorDAO doctorDAO = new DoctorDAO(this);

        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);
        AccountDAO accountDAO = new AccountDAO(this);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        tvNameDoctor.setText(accountDTO.getFullName());

        tvDes.setText(doctorDTO.getDescription());

        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(this);
        TimeWorkDTO timeWorkDTO = timeWorkDAO.getDtoTimeWork(doctorDTO.getTimework_id());
        tvTimeWork.setText(timeWorkDTO.getSession());
        edDateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(ItemOrderDoctorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = year + "/" + (month + 1) + "/" + day;
                        edDateOrder.setText(date);
                        editor.putString("startDate", date);
                        editor.commit();
                        ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = timeWorkDetailDAO.selectTimeWorkDetailByTimeWorkId(timeWorkDTO.getId());
                        AdapterListTimeWorkDetail adapterListTimeWorkDetail = new AdapterListTimeWorkDetail(listTimeWorkDetailDto, ItemOrderDoctorActivity.this);
                        LinearLayoutManager manager = new LinearLayoutManager(ItemOrderDoctorActivity.this, RecyclerView.HORIZONTAL, false);
                        rcv_list_timework_detail.setLayoutManager(manager);
                        rcv_list_timework_detail.setAdapter(adapterListTimeWorkDetail);
                        imgIconOrderDate.setVisibility(View.VISIBLE);
                        tvOrderDate.setVisibility(View.VISIBLE);
                        tvClickOrder.setVisibility(View.VISIBLE);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
        ServicesDAO servicesDAO = new ServicesDAO(this);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        tvNameSerivce.setText(servicesDTO.getServicesName());
        tvPrice.setText(servicesDTO.getServicesPrice() + "đ");


        RoomsDAO roomsDAO = new RoomsDAO(this);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        tvNameRoom.setText(roomsDTO.getName());


    }

    public void init() {
        tvNameDoctor = findViewById(R.id.tvNameDoctor);
        tvDes = findViewById(R.id.tvDes);
        tvTimeWork = findViewById(R.id.tvTimeWork);
        tvNameSerivce = findViewById(R.id.tvNameSerivce);
        tvPrice = findViewById(R.id.tvPrice);
        tvNameRoom = findViewById(R.id.tvNameRoom);
        rcv_list_timework_detail = findViewById(R.id.rcv_list_timework_detail);
        edDateOrder = findViewById(R.id.edDateOrder);
        imgIconOrderDate = findViewById(R.id.imgIconOrderDate);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvClickOrder = findViewById(R.id.tvClickOrder);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}