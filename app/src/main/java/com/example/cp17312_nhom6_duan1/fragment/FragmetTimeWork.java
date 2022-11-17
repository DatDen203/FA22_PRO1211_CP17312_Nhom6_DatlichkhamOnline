package com.example.cp17312_nhom6_duan1.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterTimeWork;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDAO;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWork;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmetTimeWork extends Fragment {
    Context context;
    TimeWorkDAO timeWorkDAO;
    ArrayList<DTO_TimeWork> listTimeWork;
    private RecyclerView rcvListTimeWork;
    private FloatingActionButton fabAddTimeWork;
    AdapterTimeWork adapterTimeWork;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_time_work, container, false);
        context = getContext();
        timeWorkDAO = new TimeWorkDAO(context);
        rcvListTimeWork = view.findViewById(R.id.rcv_list_time_work);
        fabAddTimeWork = view.findViewById(R.id.fab_add_time_work);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showData();
        fabAddTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTimeWork();
                showData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    public void showData(){
        listTimeWork = timeWorkDAO.getAll();
        adapterTimeWork = new AdapterTimeWork(timeWorkDAO, listTimeWork);
        rcvListTimeWork.setAdapter(adapterTimeWork);
    }

    private TextInputLayout tilUsername;
    private AppCompatButton btnAddTimeWork;



    public void dialogAddTimeWork(){
        Dialog dialog = new Dialog(context, com.airbnb.lottie.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_shift);
        tilUsername = dialog.findViewById(R.id.til_username);
        btnAddTimeWork = dialog.findViewById(R.id.btn_add_time_work);
        btnAddTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tilUsername.getEditText().getText().toString().trim().isEmpty()){
                    tilUsername.setError("Vui lòng không để trống mục này");
                }else{
                    tilUsername.setError("");
                    DTO_TimeWork obj = new DTO_TimeWork();
                    obj.setSession(tilUsername.getEditText().getText().toString().trim());
                    long res = timeWorkDAO.insertRow(obj);
                    if(res>0){
                        listTimeWork.clear();
                        listTimeWork.addAll(timeWorkDAO.getAll());
                        Toast.makeText(context, "Thêm ca làm việc thành công" , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Thêm ca làm việc thất bại" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
    }
}
