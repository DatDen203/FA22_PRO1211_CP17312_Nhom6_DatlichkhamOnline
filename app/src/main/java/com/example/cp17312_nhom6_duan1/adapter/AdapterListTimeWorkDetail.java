package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ItemListTimeWorkDetailViewHolder;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDetailDTO;

import java.util.ArrayList;

public class AdapterListTimeWorkDetail extends RecyclerView.Adapter<ItemListTimeWorkDetailViewHolder> {
    private ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = new ArrayList<>();
    private Context context;

    public AdapterListTimeWorkDetail(ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto, Context context) {
        this.listTimeWorkDetailDto = listTimeWorkDetailDto;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListTimeWorkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_timework_detail,parent,false);
        return new ItemListTimeWorkDetailViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemListTimeWorkDetailViewHolder holder, int position) {
        TimeWorkDetailDTO timeWorkDetailDTO = listTimeWorkDetailDto.get(position);
        holder.tvNameTimeWorkDetail.setText(timeWorkDetailDTO.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "long", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return listTimeWorkDetailDto.size();
    }
}
