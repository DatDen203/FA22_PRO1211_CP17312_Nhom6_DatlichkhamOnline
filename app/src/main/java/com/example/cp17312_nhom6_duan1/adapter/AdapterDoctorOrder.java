package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.cp17312_nhom6_duan1.OrderDoctorActivity;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.DoctorOrderViewHolder;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
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

public class AdapterDoctorOrder extends RecyclerView.Adapter<DoctorOrderViewHolder>{
    private ArrayList<DoctorDTO> listDtoDoctor = new ArrayList<>();
    private Context context;

    public AdapterDoctorOrder(ArrayList<DoctorDTO> listDtoDoctor, Context context) {
        this.listDtoDoctor = listDtoDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor_order,parent,false);
        return new DoctorOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorOrderViewHolder holder, int position) {
        DoctorDTO doctorDTO = listDtoDoctor.get(position);
        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        holder.tvDes.setText(doctorDTO.getDescription());

        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(context);
        TimeWorkDTO timeWorkDTO = timeWorkDAO.getDtoTimeWork(doctorDTO.getTimework_id());
        holder.tvTimeWork.setText(timeWorkDTO.getSession());

        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(context);
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = timeWorkDetailDAO.selectAll();
        AdapterListTimeWorkDetail adapterListTimeWorkDetail = new AdapterListTimeWorkDetail(listTimeWorkDetailDto,context);
        LinearLayoutManager manager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcv_list_timework_detail.setLayoutManager(manager);
        holder.rcv_list_timework_detail.setAdapter(adapterListTimeWorkDetail);

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameSerivce.setText(servicesDTO.getServicesName());
        holder.tvPrice.setText(servicesDTO.getServicesPrice()+"đ");

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRoom.setText(roomsDTO.getName());

    }
    @Override
    public int getItemCount() {
        return listDtoDoctor.size();
    }
}
