package com.example.cp17312_nhom6_duan1.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHoderAccount extends RecyclerView.ViewHolder {
    public CircleImageView imgDoctor;
    public TextView tvFullName;
    public TextView tvUserName;
    public TextView tvPassWord;
    public TextView tvPhoneNumber;



    public ViewHoderAccount(@NonNull View itemView) {
        super(itemView);
        imgDoctor = itemView.findViewById(R.id.imgDoctor);
        tvFullName = itemView.findViewById(R.id.tvFullName);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        tvPassWord = itemView.findViewById(R.id.tvPassWord);
        tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
    }


}
