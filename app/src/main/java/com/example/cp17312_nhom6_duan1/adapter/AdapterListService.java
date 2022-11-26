package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ItemListServiceViewHolder;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterListService extends RecyclerView.Adapter<ItemListServiceViewHolder> {
    private ArrayList<ServicesDTO> listDtoService = new ArrayList<>();
    private Context context;

    public AdapterListService(ArrayList<ServicesDTO> listDtoService, Context context) {
        this.listDtoService = listDtoService;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_service,parent,false);
        return new ItemListServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListServiceViewHolder holder, int position) {
        ServicesDTO servicesDTO = listDtoService.get(position);
        holder.tvNameService.setText(servicesDTO.getServicesName());
    }

    @Override
    public int getItemCount() {
        return listDtoService.size();
    }
}
