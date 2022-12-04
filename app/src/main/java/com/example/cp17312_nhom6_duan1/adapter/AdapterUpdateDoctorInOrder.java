package com.example.cp17312_nhom6_duan1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.dao.DoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDetailDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dao.RoomsDAO;
import com.example.cp17312_nhom6_duan1.dao.ServicesDAO;
import com.example.cp17312_nhom6_duan1.dto.AllDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.RoomsDTO;
import com.example.cp17312_nhom6_duan1.dto.ServicesDTO;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterUpdateDoctorInOrder extends RecyclerView.Adapter<AdapterUpdateDoctorInOrder.ViewHoderItemUpdateDoctorInOrder> {
    OrderDoctorDTO orderDoctorDTO;
    SpinnerDoctorAdapter spinnerDoctorAdapter;
    ArrayList<DoctorDTO> listDoctor;
    OrderDetailDAO orderDetailDAO;
    ArrayList<AllDTO> listOrder;
    Context context;
    AllDTO allDTO;
    DoctorDAO doctorDAO;
    RoomsDAO roomsDAO;
    RoomsDTO roomsDTO;
    ServicesDAO servicesDAO;
    ServicesDTO servicesDTO;
    OrderDoctorDAO orderDoctorDAO;
    String TAG = "zzzzzzzzzzzzzzzzz";
    String check;
    int idFile;


    public AdapterUpdateDoctorInOrder(ArrayList<AllDTO> listOrder, String check, int idFile) {
        this.listOrder = listOrder;
        this.check = check;
        this.idFile = idFile;
    }

    @NonNull
    @Override
    public ViewHoderItemUpdateDoctorInOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_order_doctor2, parent, false);
        context = parent.getContext();
        orderDetailDAO = new OrderDetailDAO(context);
        doctorDAO = new DoctorDAO(context);
        roomsDAO = new RoomsDAO(context);
        servicesDAO = new ServicesDAO(context);
        orderDoctorDAO = new OrderDoctorDAO(context);

        return new ViewHoderItemUpdateDoctorInOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemUpdateDoctorInOrder holder, int position) {
        final int index = position;
        allDTO = listOrder.get(index);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(allDTO.getIdDoctor());
        listDoctor = doctorDAO.getListDoctorByTimeWord(allDTO.getStartTime(), doctorDTO.getService_id(), doctorDTO.getTimework_id());
        spinnerDoctorAdapter = new SpinnerDoctorAdapter(listDoctor, context);
        holder.tvfullName.setText(allDTO.getFullameUser());
        listDoctor.add(0, doctorDTO);
        for (int i = 0; i < listDoctor.size(); i++) {
            if (allDTO.getIdDoctor() == listDoctor.get(i).getId()) {
                holder.spNameDoctor.setSelected(true);
                holder.spNameDoctor.setSelection(i);
            }
        }
        orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(doctorDTO.getId());
        servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameService.setText(servicesDTO.getServicesName());
        holder.tvNameRooms.setText(roomsDTO.getName());
        holder.spNameDoctor.setAdapter(spinnerDoctorAdapter);
        holder.tvStartDate.setText(formatDate(allDTO.getStartDate()));
        holder.tvStartTime.setText(allDTO.getStartTime());
        if (check.equalsIgnoreCase("nocomfrim")) {
            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogupdate2(holder.spNameDoctor);

                }
            });
            holder.llNote.setVisibility(View.GONE);
        } else if (check.equalsIgnoreCase("yescomfrim")) {
            holder.btnUpdate.setVisibility(View.INVISIBLE);
            holder.llNote.setVisibility(View.VISIBLE);
            holder.tvNote.setText(allDTO.getNote());
            holder.spNameDoctor.setEnabled(false);
        }


    }

    @Override
    public int getItemCount() {
        return listOrder == null ? 0 : listOrder.size();
    }

    public String formatDate(String a) {
        String newDate = "";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd/mm/yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public void dialogupdate2(Spinner spinner) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Cập nhật thành công ");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DoctorDTO doctorDTO = (DoctorDTO) spinner.getSelectedItem();
                Log.e(TAG, "onClick: " + doctorDTO.getId());
                orderDoctorDTO.setDoctor_id(doctorDTO.getId());
                int res = orderDoctorDAO.updateRow(orderDoctorDTO);
                if (res > 0) {
                    listOrder.clear();
                    orderDetailDAO = new OrderDetailDAO(context);
                    listOrder = orderDetailDAO.getListOrderByIdFile(idFile);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public class ViewHoderItemUpdateDoctorInOrder extends RecyclerView.ViewHolder {
        private TextView tvfullName;
        private Spinner spNameDoctor;
        private TextView tvNameService;
        private TextView tvNameRooms;
        private TextView tvStartDate;
        private TextView tvStartTime;
        private MaterialButton btnUpdate;
        private LinearLayout llNote;
        private TextView tvNote;

        public ViewHoderItemUpdateDoctorInOrder(@NonNull View itemView) {
            super(itemView);
            tvfullName = itemView.findViewById(R.id.tvfullName);
            spNameDoctor = itemView.findViewById(R.id.spNameDoctor);
            tvNameService = itemView.findViewById(R.id.tvNameService);
            tvNameRooms = itemView.findViewById(R.id.tvNameRooms);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            llNote = itemView.findViewById(R.id.ll_note);
            tvNote = itemView.findViewById(R.id.tvNote);
        }
    }
}
