package com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterHistoryOrder;
import com.example.cp17312_nhom6_duan1.dao.OrderDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;

import java.util.ArrayList;


public class Fragment_bookingHistory extends Fragment {
    private RecyclerView rcv_bookingHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_bookingHistory = view.findViewById(R.id.rcv_bookingHistory);

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(getContext());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser", -1);
        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getListOrderDetailDtoById(idUser);

        AdapterHistoryOrder adapterOrder = new AdapterHistoryOrder(listOrderDetail, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_bookingHistory.setLayoutManager(manager);
        rcv_bookingHistory.setAdapter(adapterOrder);
    }
}