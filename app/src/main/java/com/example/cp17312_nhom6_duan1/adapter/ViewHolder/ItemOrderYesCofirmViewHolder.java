package com.example.cp17312_nhom6_duan1.adapter.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;

public class ItemOrderYesCofirmViewHolder extends RecyclerView.ViewHolder {
    public TextView tvFile,tvDetailFile,tvStartDate,tvStartTime;
    public Button btnStatus;
    public ItemOrderYesCofirmViewHolder(@NonNull View itemView) {
        super(itemView);
        tvFile  =itemView.findViewById(R.id.tvFile);
        tvDetailFile = itemView.findViewById(R.id.tvDetailFile);
        tvStartDate = itemView.findViewById(R.id.tvStartDate);
        tvStartTime = itemView.findViewById(R.id.tvStartTime);
        btnStatus = itemView.findViewById(R.id.btnStatus);
    }
}
