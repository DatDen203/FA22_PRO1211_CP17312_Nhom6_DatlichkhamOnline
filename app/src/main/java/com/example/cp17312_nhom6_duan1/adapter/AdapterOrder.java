package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.OrderDoctorActivity;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.OrderViewHolder;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.FileDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.RoomsDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.RoomsDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterOrder extends RecyclerView.Adapter<OrderViewHolder> {
    private ArrayList<OrderDoctorDTO> listOrderDoctor = new ArrayList<>();
    private Context context;

    public AdapterOrder(ArrayList<OrderDoctorDTO> listOrderDoctor, Context context) {
        this.listOrderDoctor = listOrderDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderDoctorDTO orderDoctorDTO = listOrderDoctor.get(position);
        final int vitri = position;

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        DoctorDAO doctorDAO  = new DoctorDAO(context);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(orderDoctorDTO.getDoctor_id());

        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());

        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRooms.setText(roomsDTO.getName());

        holder.tvStartDate.setText(orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText(orderDoctorDTO.getStart_time());
        holder.tvPrice.setText(orderDoctorDTO.getTotal()+"đ");
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDoctorDAO orderDoctorDAO =new OrderDoctorDAO(context);
                int res = orderDoctorDAO.deleteRow(orderDoctorDTO);
                OrderDoctorActivity.listOrderDoctor.remove(vitri);
                notifyDataSetChanged();
            }
        });
        holder.tvNamefile.setText(fileDTO.getFullname());
    }

    @Override
    public int getItemCount() {
        return listOrderDoctor.size();
    }
}
