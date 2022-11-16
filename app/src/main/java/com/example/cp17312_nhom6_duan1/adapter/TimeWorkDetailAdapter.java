package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.TimeWorkDetailViewHolder;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWorkDetail;

import java.util.ArrayList;

public class TimeWorkDetailAdapter extends RecyclerView.Adapter<TimeWorkDetailViewHolder> {
    private ArrayList<DTO_TimeWorkDetail> listTimeWorkDetail = new ArrayList<>();
    private Context context;
    private TimeWorkDetailDAO daoTimeWorkDetail;

    public TimeWorkDetailAdapter(ArrayList<DTO_TimeWorkDetail> listTimeWorkDetail, Context context) {
        this.listTimeWorkDetail = listTimeWorkDetail;
        this.context = context;
    }

    @NonNull
    @Override
    public TimeWorkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_timework_detail,parent,false);
        return new TimeWorkDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeWorkDetailViewHolder holder, int position) {
        TimeWorkDetailDAO daoTimeWork = new TimeWorkDetailDAO(context);
        daoTimeWork.open();
        DTO_TimeWorkDetail dtoTimeWorkDetail = listTimeWorkDetail.get(position);
        holder.tvTimeWorkDetail.setText(dtoTimeWorkDetail.getTime());
//        DTO_TimeWorkDetail dtoTimeWork = daoTimeWork.getDtoTimeWork(dtoTimeWorkDetail.getTimework_id());
//        holder.tvTimeWork.setText(dtoTimeWork.getSession());
    }

    @Override
    public int getItemCount() {
        return listTimeWorkDetail.size();
    }
}
