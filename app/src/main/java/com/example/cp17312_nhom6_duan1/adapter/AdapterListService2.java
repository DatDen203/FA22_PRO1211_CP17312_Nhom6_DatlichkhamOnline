package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.ListDoctorByServiceActivity;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ItemListService2ViewHolder;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ItemListServiceViewHolder;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterListService2 extends RecyclerView.Adapter<ItemListService2ViewHolder> {
    private ArrayList<ServicesDTO> listDtoService = new ArrayList<>();
    private Context context;

    public AdapterListService2(ArrayList<ServicesDTO> listDtoService, Context context) {
        this.listDtoService = listDtoService;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListService2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_service_2,parent,false);
        return new ItemListService2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListService2ViewHolder holder, int position) {
        ServicesDTO servicesDTO = listDtoService.get(position);
        holder.tvNameService.setText(servicesDTO.getServicesName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListDoctorByServiceActivity.class);
                intent.putExtra("idService",servicesDTO.getServicesId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDtoService.size();
    }
}
