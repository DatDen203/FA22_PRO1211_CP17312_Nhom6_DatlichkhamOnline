package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;

import java.util.ArrayList;

public class OrderDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDetailDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long innsertRow(OrderDetailDTO orderDetailDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDetailDTO.colOrderId,orderDetailDTO.getOrder_id());
        val.put(OrderDetailDTO.colOrderDoctorId,orderDetailDTO.getOrderDoctor_id());

        long res = db.insert(OrderDetailDTO.nameTable,null,val);
        return res;
    }

    public ArrayList<OrderDetailDTO> selectAll(){
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        Cursor cs  =db.query(OrderDetailDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));

                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderDetailDtoById (int idUser){
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs ={idUser+""};
        String select = "select tbOrderDetail.order_id ,tbOrderDetail.orderDoctor_id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbFile.user_id = tbAccount.id where tbAccount.id =?";
        Cursor cs = db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

}
