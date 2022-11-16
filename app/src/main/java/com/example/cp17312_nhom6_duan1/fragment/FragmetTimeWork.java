package com.example.cp17312_nhom6_duan1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.dao.TimeWorkDAO;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWork;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmetTimeWork extends Fragment {
    Context context;
    TimeWorkDAO timeWorkDAO;
    ArrayList<DTO_TimeWork> listTimeWork;
    private RecyclerView rcvListTimeWork;
    private FloatingActionButton fabAddTimeWork;
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

    }
}
