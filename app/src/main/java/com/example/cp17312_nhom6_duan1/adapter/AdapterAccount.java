package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ViewHoderAccount;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;

import java.util.ArrayList;

public class AdapterAccount extends RecyclerView.Adapter<ViewHoderAccount> {
    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccount;
    Context context;

    public AdapterAccount(AccountDAO accountDAO, ArrayList<AccountDTO> listAccount) {
        this.accountDAO = accountDAO;
        this.listAccount = listAccount;
    }

    @NonNull
    @Override
    public ViewHoderAccount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_account, parent,false);
        context = parent.getContext();
        accountDAO = new AccountDAO(context);
        return new ViewHoderAccount(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderAccount holder, int position) {
        final int index =position;
        AccountDTO accountDTO = listAccount.get(index);
        holder.tvFullName.setText(accountDTO.getFullName());
        holder.tvUserName.setText(accountDTO.getUserName());
        holder.tvPassWord.setText(accountDTO.getPassWord());
        holder.tvPhoneNumber.setText(accountDTO.getPhoneNumber());
        if(accountDTO.getImg()!=null){
            Uri uri = Uri.parse(accountDTO.getImg());
            holder.imgDoctor.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return listAccount==null?0: listAccount.size();
    }
}
