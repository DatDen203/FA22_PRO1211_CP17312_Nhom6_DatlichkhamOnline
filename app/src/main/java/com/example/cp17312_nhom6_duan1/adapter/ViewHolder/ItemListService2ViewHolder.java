package com.example.cp17312_nhom6_duan1.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;

public class ItemListService2ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgService;
    public TextView tvNameService;
    public ItemListService2ViewHolder(@NonNull View itemView) {
        super(itemView);
        imgService = itemView.findViewById(R.id.imgService);
        tvNameService = itemView.findViewById(R.id.tvNameService);
    }
}
