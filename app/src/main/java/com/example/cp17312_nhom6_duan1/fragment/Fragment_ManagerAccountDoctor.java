package com.example.cp17312_nhom6_duan1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterAccount;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;

import java.util.ArrayList;


public class Fragment_ManagerAccountDoctor extends Fragment {

    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccount;
    AdapterAccount adapterAccount;
    private RecyclerView rcvManagerAccountDoctor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__manager_account_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvManagerAccountDoctor = view.findViewById(R.id.rcv_managerAccountDoctor);
        accountDAO = new AccountDAO(getContext());
        listAccount = accountDAO.getAccountDoctor();
        adapterAccount = new AdapterAccount(accountDAO, listAccount);
        rcvManagerAccountDoctor.setAdapter(adapterAccount);
    }
}