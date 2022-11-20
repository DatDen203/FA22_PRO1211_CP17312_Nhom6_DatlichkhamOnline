package com.example.cp17312_nhom6_duan1.adapter.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;

public class DoctorViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgDoctor;
    public TextView tvNameDoctor,tvNameService,tvNameRoom,tvTimeWork,tvDes;
    public Button btnUpdateDoctor;
    public DoctorViewHolder(@NonNull View itemView) {
        super(itemView);
        imgDoctor = itemView.findViewById(R.id.imgDoctor);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        tvTimeWork = itemView.findViewById(R.id.tvTimeWork);
        tvDes = itemView.findViewById(R.id.tvDes);
        btnUpdateDoctor = itemView.findViewById(R.id.btnUpdateDoctor);
    }
}
