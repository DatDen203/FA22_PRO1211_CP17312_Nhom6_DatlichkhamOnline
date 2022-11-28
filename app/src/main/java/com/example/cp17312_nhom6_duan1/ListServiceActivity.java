package com.example.cp17312_nhom6_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cp17312_nhom6_duan1.adapter.AdapterListService;
import com.example.cp17312_nhom6_duan1.adapter.AdapterListService2;
import com.example.cp17312_nhom6_duan1.adapter.ServicesAdapter;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;

import java.util.ArrayList;

public class ListServiceActivity extends AppCompatActivity {
    private RecyclerView rcv_list_services;
    private Toolbar toolbarService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service);
        rcv_list_services = findViewById(R.id.rcv_list_services);
        toolbarService = findViewById(R.id.toolbarService);

        ServicesDAO servicesDAO = new ServicesDAO(this);

        ArrayList<ServicesDTO> listDtoSerivce=  servicesDAO.selectAll();
        AdapterListService2 listService = new AdapterListService2(listDtoSerivce,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_list_services.setLayoutManager(manager);
        rcv_list_services.setAdapter(listService);



    }
}