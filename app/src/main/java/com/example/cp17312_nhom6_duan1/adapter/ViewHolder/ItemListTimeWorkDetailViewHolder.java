package com.example.cp17312_nhom6_duan1.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;

public class ItemListTimeWorkDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameTimeWorkDetail;
    public ItemListTimeWorkDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameTimeWorkDetail = itemView.findViewById(R.id.tvNameTimeWorkDetail);
    }
}
