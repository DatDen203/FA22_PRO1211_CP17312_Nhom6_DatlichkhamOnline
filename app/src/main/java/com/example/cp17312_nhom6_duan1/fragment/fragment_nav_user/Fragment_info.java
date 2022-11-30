package com.example.cp17312_nhom6_duan1.fragment.fragment_nav_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.UpdateUserActivity;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.google.android.gms.common.internal.AccountType;


public class Fragment_info extends Fragment {
    private ImageView imgInfoUser;
    private TextView tvUserName;
    private TextView tvPassWord;
    private TextView tvFullName;
    private TextView tvPhoneNumber;
    private AppCompatButton btnUpdate;
    AccountDAO accountDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountDAO=new AccountDAO(getContext());
        return inflater.inflate(R.layout.fragment_info_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int idUser= preferences.getInt("idUser",-1);
        AccountDTO account= accountDAO.getDtoAccount(idUser);

        tvFullName.setText(account.getFullName());
        tvUserName.setText(account.getUserName());
        tvPassWord.setText(account.getPassWord());
        tvPhoneNumber.setText(account.getPhoneNumber());

        btnUpdate.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), UpdateUserActivity.class);
            startActivity(intent);
        });
    }


    public void init(View view) {

        imgInfoUser = (ImageView) view.findViewById(R.id.img_infoUser);
        tvUserName = (TextView) view.findViewById(R.id.tv_userName);
        tvPassWord = (TextView) view.findViewById(R.id.tv_passWord);
        tvFullName = (TextView) view.findViewById(R.id.tv_fullName);
        tvPhoneNumber = (TextView) view.findViewById(R.id.tv_phoneNumber);
        btnUpdate = (AppCompatButton) view.findViewById(R.id.btn_update);

    }
}