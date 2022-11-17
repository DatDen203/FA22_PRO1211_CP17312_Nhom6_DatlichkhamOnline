package com.example.cp17312_nhom6_duan1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.TimeWorkDetailViewHolder;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWorkDetail;
import com.google.android.material.textfield.TextInputLayout;

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
        View view = LayoutInflater.from(context).inflate(R.layout.item_timework_detail, parent, false);
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
        holder.tvUpdateTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listTimeWorkDetail.size();
    }

    public void updateRow(Context context, DTO_TimeWorkDetail obj, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_edit_time_work_detail);

        TextInputLayout edTimeWorkdetail = dialog.findViewById(R.id.edTimeWorkDetail);
        Button btnSave = dialog.findViewById(R.id.btnSaveTimeWorkDetail);
        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(context);
        timeWorkDetailDAO.open();
        ArrayList<DTO_TimeWorkDetail> list1 = timeWorkDetailDAO.selectAll();

        edTimeWorkdetail.getEditText().setText(obj.getTime());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeDetail = edTimeWorkdetail.getEditText().getText().toString();
                int res = daoTimeWorkDetail.updateRow(obj);
                if (res > 0) {
                    listTimeWorkDetail.set(index, obj);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không Sửa Được", Toast.LENGTH_SHORT).show();
                }
                dialog.show();
            }
        });
        dialog.show();
    }
}
