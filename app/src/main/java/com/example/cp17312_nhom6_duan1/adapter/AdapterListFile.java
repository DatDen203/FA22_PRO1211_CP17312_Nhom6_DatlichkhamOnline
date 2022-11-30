package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.ItemFileDetailActivity;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dao.FileDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;

import java.util.ArrayList;

public class AdapterListFile extends RecyclerView.Adapter<AdapterListFile.ViewHoderItemListFile> {
    ArrayList<FileDTO> listFile;
    FileDAO fileDAO;
    Context context;
    AccountDAO accountDAO;

    public AdapterListFile(ArrayList<FileDTO> listFile, FileDAO fileDAO) {
        this.listFile = listFile;
        this.fileDAO = fileDAO;
    }

    @NonNull
    @Override
    public ViewHoderItemListFile onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.item_list_file, parent, false);
        context = parent.getContext();
        fileDAO = new FileDAO(context);
        accountDAO = new AccountDAO(context);
        return new ViewHoderItemListFile(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemListFile holder, int position) {
        final int index = position;
        FileDTO fileDTO = listFile.get(index);
        AccountDTO accountDTO = accountDAO.getDtoAccount(fileDTO.getUser_id());
        holder.tvFullName.setText(fileDTO.getFullname());
        holder.tvAddress.setText(fileDTO.getAddress());
        holder.tvPhoneNumber.setText(accountDTO.getPhoneNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemFileDetailActivity.class);
                intent.putExtra("id",fileDTO.getId());
                intent.putExtra("idUser",fileDTO.getUser_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFile==null?0: listFile.size();
    }

    public class ViewHoderItemListFile extends RecyclerView.ViewHolder {
        private TextView tvFullName;
        private TextView tvAddress;
        private TextView tvPhoneNumber;
        public ViewHoderItemListFile(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tv_fullName);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhoneNumber = itemView.findViewById(R.id.tv_phoneNumber);

        }
    }
}
