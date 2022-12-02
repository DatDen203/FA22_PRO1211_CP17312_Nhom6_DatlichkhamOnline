package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.cp17312_nhom6_duan1.adapter.AdapterHistoryOrder;
import com.example.cp17312_nhom6_duan1.adapter.AdapterOrder;
import com.example.cp17312_nhom6_duan1.dao.OrderDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;

import java.util.ArrayList;

public class ListOrderActivity extends AppCompatActivity {
    private RecyclerView rcv_list_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        rcv_list_order = findViewById(R.id.rcv_list_order);

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(this);
        SharedPreferences sharedPreferences = getSharedPreferences("getIdUser",MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser",-1);
        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getListOrderDetailDtoByNoConfirm(idUser);

        AdapterHistoryOrder adapterOrder = new AdapterHistoryOrder(listOrderDetail,this);
        LinearLayoutManager manager  =new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_list_order.setLayoutManager(manager);
        rcv_list_order.setAdapter(adapterOrder);

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}