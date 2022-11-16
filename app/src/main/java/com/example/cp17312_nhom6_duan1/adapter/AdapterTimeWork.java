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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDAO;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWork;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterTimeWork extends RecyclerView.Adapter<AdapterTimeWork.ViewHoderItemTimeWork> {
    TimeWorkDAO timeWorkDAO;
    ArrayList<DTO_TimeWork> listTimeWork;
    Context context;

    public AdapterTimeWork(TimeWorkDAO timeWorkDAO, ArrayList<DTO_TimeWork> listTimeWork) {
        this.timeWorkDAO = timeWorkDAO;
        this.listTimeWork = listTimeWork;
    }

    @NonNull
    @Override
    public ViewHoderItemTimeWork onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_time_work, parent, false);
        context = parent.getContext();
        timeWorkDAO = new TimeWorkDAO(context);
        return new ViewHoderItemTimeWork(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemTimeWork holder, int position) {
        final int index = position;
        listTimeWork = timeWorkDAO.getAll();
        DTO_TimeWork obj = listTimeWork.get(index);
        holder.tvTimeWork.setText(obj.getSession());
        holder.tvUpdateTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTimeWork(obj, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTimeWork == null ? 0 : listTimeWork.size();
    }

    private TextInputLayout tilUsername;
    private AppCompatButton btnSaveShift;


    public void dialogAddTimeWork(DTO_TimeWork obj, int index) {
        Dialog dialog = new Dialog(context, com.airbnb.lottie.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_shift);
        tilUsername = dialog.findViewById(R.id.til_username);
        btnSaveShift = dialog.findViewById(R.id.btn_save_shift);
        tilUsername.getEditText().setText(obj.getSession());
        btnSaveShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tilUsername.getEditText().getText().toString().trim().isEmpty()) {
                    tilUsername.setError("Vui lòng không để trống mục này");
                } else {
                    tilUsername.setError("");
                    obj.setSession(tilUsername.getEditText().getText().toString().trim());
                    long res = timeWorkDAO.updateRow(obj);
                    if (res > 0) {
                        listTimeWork.set(index, obj);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Thêm ca làm việc thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Thêm ca làm việc thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
    }

    public class ViewHoderItemTimeWork extends RecyclerView.ViewHolder {
        private TextView tvTimeWork;
        private TextView tvUpdateTimeWork;

        public ViewHoderItemTimeWork(@NonNull View itemView) {
            super(itemView);
            tvTimeWork = itemView.findViewById(R.id.tv_time_work);
            tvUpdateTimeWork = itemView.findViewById(R.id.tv_update_time_work);

        }
    }
}
