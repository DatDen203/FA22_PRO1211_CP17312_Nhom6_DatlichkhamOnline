package com.example.cp17312_nhom6_duan1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ItemOrderYesCofirmViewHolder;
import com.example.cp17312_nhom6_duan1.dao.FileDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;

import java.util.ArrayList;

public class AdapterOrderYesConfirm extends RecyclerView.Adapter<ItemOrderYesCofirmViewHolder> {

    public ArrayList<OrderDoctorDTO> list = new ArrayList<>();
    public Context context;

    public AdapterOrderYesConfirm(ArrayList<OrderDoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemOrderYesCofirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_yes_confirm,parent,false);
        return new ItemOrderYesCofirmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemOrderYesCofirmViewHolder holder, int position) {
        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(context);
        OrderDoctorDTO orderDoctorDTO1 = list.get(position);
        OrderDAO orderDAO = new OrderDAO(context);
        OrderDTO orderDTO = orderDAO.getOrderDTOById(orderDoctorDTO1.getOrder_id());

        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDoctorDTO1.getId());

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        holder.tvFile.setText(fileDTO.getFullname());
        holder.tvDetailFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Th??ng tin chi ti???t b???nh nh??n");
                builder.setMessage("T??n b???nh nh??n: "+fileDTO.getFullname()+"\nNg??y sinh: "+fileDTO.getBirthday()+"\nC??n c?????c c??ng d??n: "+fileDTO.getCccd()+"\nQu???c t???ch: "+fileDTO.getCountry()+"\nB???o hi???m y t??? : "+fileDTO.getBhyt()+"\nC??ng vi???c :"+fileDTO.getJob()+"\n?????a ch??? n??i ??? : "+fileDTO.getAddress()+"\nGhi ch??: "+orderDTO.getNote());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.tvStartDate.setText("Ng??y kh??m: "+orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText("Gi??? kh??m: "+orderDoctorDTO.getStart_time());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
