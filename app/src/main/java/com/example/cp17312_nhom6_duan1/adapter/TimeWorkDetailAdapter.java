package com.example.cp17312_nhom6_duan1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.TimeWorkDetailViewHolder;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDAO;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDTO;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDetailDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class TimeWorkDetailAdapter extends RecyclerView.Adapter<TimeWorkDetailViewHolder> {
    private ArrayList<TimeWorkDetailDTO> listTimeWorkDetail = new ArrayList<>();
    private Context context;
    private TimeWorkDetailDAO timeWorkDetailDAO;
    private  TimeWorkDAO daoTimeWork;

    public TimeWorkDetailAdapter(ArrayList<TimeWorkDetailDTO> listTimeWorkDetail, Context context) {
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
         daoTimeWork = new TimeWorkDAO(context);
        daoTimeWork.open();

         timeWorkDetailDAO = new TimeWorkDetailDAO(context);
        timeWorkDetailDAO.open();

        final int index = position;
        TimeWorkDetailDTO dtoTimeWorkDetail = listTimeWorkDetail.get(position);
        holder.tvTimeWorkDetail.setText(dtoTimeWorkDetail.getTime());

        TimeWorkDTO _timeWorkDTO = daoTimeWork.getDtoTimeWork(dtoTimeWorkDetail.getTimework_id());
        holder.tvTimeWork.setText(_timeWorkDTO.getSession());

        holder.tvUpdateTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRow(context,dtoTimeWorkDetail,index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTimeWorkDetail.size();
    }

    public void updateRow(Context context, TimeWorkDetailDTO timeWorkDetailDTO, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_time_work_detail);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        else{
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        Spinner spTimeWork = dialog.findViewById(R.id.spTimeWork);
        ArrayList<TimeWorkDTO> listTimeWork = daoTimeWork.getAll();
        SpinnerTimeWorkAdapter timeWorkAdapter = new SpinnerTimeWorkAdapter(listTimeWork,context);
        spTimeWork.setAdapter(timeWorkAdapter);

        for(int i=0;i<listTimeWork.size();i++){
            TimeWorkDTO timeWorkDTO = listTimeWork.get(i);
            if(timeWorkDTO.getId() == (timeWorkDetailDTO.getTimework_id())){
                spTimeWork.setSelected(true);
                spTimeWork.setSelection(i);
            }
        }
        ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextInputLayout edTimeWorkDetail = dialog.findViewById(R.id.edTimeWorkDetail);
        edTimeWorkDetail.getEditText().setText(timeWorkDetailDTO.getTime());

        Button btnSaveTimeWorkDetail = dialog.findViewById(R.id.btnSaveTimeWorkDetail);
        //Bắt sự kiện cho nút save room
        btnSaveTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               timeWorkDetailDTO.setTime(edTimeWorkDetail.getEditText().getText().toString());
               TimeWorkDTO timeWorkDTO = (TimeWorkDTO) spTimeWork.getSelectedItem();
               timeWorkDetailDTO.setTimework_id(timeWorkDTO.getId());

               int res = timeWorkDetailDAO.updateRow(timeWorkDetailDTO);
               if(res>0){
                   listTimeWork.set(index,timeWorkDTO);
                   notifyDataSetChanged();
                   Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
               }
               else{
                   Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
               }
            }

        });
        dialog.show();
    }
}
