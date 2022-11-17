package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.RoomViewHolder;
import com.example.cp17312_nhom6_duan1.dto.RoomsDTO;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private ArrayList<RoomsDTO> listRooms = new ArrayList<>();
    private Context context;

    public RoomAdapter(ArrayList<RoomsDTO> listRooms, Context context) {
        this.listRooms = listRooms;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_rooms,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomsDTO roomsDTO = listRooms.get(position);
        holder.tvNameRoom.setText(roomsDTO.getName());
        holder.tvLocationRoom.setText(roomsDTO.getLocation());

    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }
}
