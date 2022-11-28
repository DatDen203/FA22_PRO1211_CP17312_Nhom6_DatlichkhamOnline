package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.adapter.AdapterOrder;
import com.example.cp17312_nhom6_duan1.dao.OrderDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDetailDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.OrderDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {
    private RecyclerView rcv_list_order;
    private OrderDoctorDAO orderDoctorDAO;
    private TextView tvSumPrice;
    private float sumPrice = 0;
    private Button btnOrder, btnAddOrder;
    private ArrayList<OrderDoctorDTO> list = OrderDoctorActivity.listOrderDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        rcv_list_order = findViewById(R.id.rcv_list_order);
        tvSumPrice = findViewById(R.id.tvSumPrice);
        btnOrder = findViewById(R.id.btnOrder);
        btnAddOrder = findViewById(R.id.btnAddOrder);

        orderDoctorDAO = new OrderDoctorDAO(this);

        AdapterOrder adapterOrder = new AdapterOrder(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_list_order.setLayoutManager(manager);
        rcv_list_order.setAdapter(adapterOrder);

        for (int i = 0; i < list.size(); i++) {
            sumPrice += list.get(i).getTotal();
        }
        tvSumPrice.setText("Thành tiền : " + sumPrice + "đ");

        OrderDAO orderDAO = new OrderDAO(this);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(this);

        //Lấy ra ngày hiện tại
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String date = year + "/" + (month + 1) + "/" + day;

        //Lấy ra giờ hiện tại
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        String time = hour + ":" + minute;

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    OrderDoctorDTO orderDoctorDTO = list.get(i);
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setFile_id(orderDoctorDTO.getFile_id());
                    orderDTO.setOrder_date(date);
                    orderDTO.setOrder_time(time);
                    long res = orderDAO.insertRow(orderDTO);
                }
                ArrayList<OrderDTO> listOrderDto = orderDAO.selectDesc();
                for(int i = 0;i<list.size();i++){
                    OrderDTO orderDTO = listOrderDto.get(i);
                    OrderDoctorDTO orderDoctorDTO = OrderDoctorActivity.listOrderDoctor.get(i);

                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                    orderDetailDTO.setOrder_id(orderDTO.getId());
                    orderDetailDTO.setOrderDoctor_id(orderDoctorDTO.getId());

                    long res = orderDetailDAO.innsertRow(orderDetailDTO);
                }
                Toast.makeText(ConfirmActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                OrderDoctorActivity.listOrderDoctor.clear();
            }
        });

        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        orderDoctorDAO = new OrderDoctorDAO(this);
        AdapterOrder adapterOrder = new AdapterOrder(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_list_order.setLayoutManager(manager);
        rcv_list_order.setAdapter(adapterOrder);
        for (int i = 0; i < list.size(); i++) {
            sumPrice += list.get(i).getTotal();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OrderDoctorActivity.listOrderDoctor.clear();
    }
}