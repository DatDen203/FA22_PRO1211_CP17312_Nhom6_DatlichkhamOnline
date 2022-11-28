package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dao.FileDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemFileDetailActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private TextInputLayout tilNameFullName;
    private TextInputLayout tilPhoneNumber;
    private TextView tvBirthday;
    private ImageView imgBirthday;
    private TextInputLayout tilEmail;
    private TextInputLayout tilCccd;
    private TextInputLayout tilCountry;
    private RadioButton rdoYes;
    private RadioButton rdoNo;
    private TextInputLayout tilJob;
    private TextInputLayout tilAddress;
    private TextInputLayout tilDes;
    FileDAO fileDAO;
    FileDTO fileDTO;
    AccountDTO accountDTO;
    AccountDAO accountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_file_detail);
        findViewID();
        fileDAO = new FileDAO(getApplicationContext());
        accountDAO = new AccountDAO(getApplicationContext());
       toolBar.setNavigationOnClickListener(view->{
           finish();
       });
        Intent intent = getIntent();
        fileDTO = fileDAO.getFileDToById(intent.getIntExtra("id",-1));
        accountDTO = accountDAO.getDtoAccount(intent.getIntExtra("idUser",-1));
        tilNameFullName.getEditText().setText(fileDTO.getFullname());
        tilAddress.getEditText().setText(fileDTO.getAddress());
        tilCccd.getEditText().setText(fileDTO.getCccd());
        tilPhoneNumber.getEditText().setText(accountDTO.getPhoneNumber());
        tvBirthday.setText(formatDate2(fileDTO.getBirthday()));
        tilEmail.getEditText().setText(fileDTO.getEmail());
        tilCountry.getEditText().setText(fileDTO.getCountry());
        tilJob.getEditText().setText(fileDTO.getJob());
        if(fileDTO.getBhyt().equalsIgnoreCase("yes")){
            rdoYes.setChecked(true);
            rdoNo.setChecked(false);
        }else if(fileDTO.getBhyt().equalsIgnoreCase("no")){
            rdoYes.setChecked(false);
            rdoNo.setChecked(true);
        }
        tilDes.getEditText().setText(fileDTO.getDes());

    }
    public String formatDate2(String a) {
        String newDate ="";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 =a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd/mm/yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }
    public String formatDate(String a) {
        String newDate ="";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 =a;
        SimpleDateFormat Format2 = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("yyyy/mm/dd", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }
    private void findViewID() {
        toolBar = findViewById(R.id.toolBar);
        tilNameFullName = findViewById(R.id.tilNameFullName);
        tilPhoneNumber = findViewById(R.id.tilPhoneNumber);
        tvBirthday = findViewById(R.id.tvBirthday);
        imgBirthday = findViewById(R.id.imgBirthday);
        tilEmail = findViewById(R.id.tilEmail);
        tilCccd = findViewById(R.id.tilCccd);
        tilCountry = findViewById(R.id.tilCountry);
        rdoYes = findViewById(R.id.rdoYes);
        rdoNo = findViewById(R.id.rdoNo);
        tilJob = findViewById(R.id.tilJob);
        tilAddress = findViewById(R.id.tilAddress);
        tilDes = findViewById(R.id.tilDes);
    }
}