package com.example.cp17312_nhom6_duan1.adapter.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;

public class DoctorOrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameDoctor,tvTimeWork,tvDes,tvNameSerivce,tvPrice,tvNameRoom;
    public Button btnOrder;
    public RecyclerView rcv_list_timework_detail;
    public DoctorOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvTimeWork = itemView.findViewById(R.id.tvTimeWork);
        tvDes = itemView.findViewById(R.id.tvDes);
        tvNameSerivce = itemView.findViewById(R.id.tvNameSerivce);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        btnOrder = itemView.findViewById(R.id.btnOrder);
        rcv_list_timework_detail = itemView.findViewById(R.id.rcv_list_timework_detail);
    }
}
