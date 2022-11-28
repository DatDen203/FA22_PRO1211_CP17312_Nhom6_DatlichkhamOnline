package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.OrderDTO;

import java.util.ArrayList;

public class OrderDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertRow(OrderDTO orderDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDTO.colFile_id,orderDTO.getFile_id());
        val.put(OrderDTO.colOrder_time,orderDTO.getOrder_time());
        val.put(OrderDTO.colOrder_date,orderDTO.getOrder_date());

        long res = db.insert(OrderDTO.nameTable,null,val);
        return res;
    }
    public ArrayList<OrderDTO> selectDesc(){
        ArrayList<OrderDTO> list = new ArrayList<>();
        String orderBy = "id desc";
        Cursor cs = db.query(OrderDTO.nameTable,null,null,null,null,null,orderBy);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(cs.getInt(0));
                orderDTO.setFile_id(cs.getInt(1));
                orderDTO.setOrder_time(cs.getString(2));
                orderDTO.setOrder_date(cs.getString(3));

                list.add(orderDTO);
                cs.moveToNext();
            }
        }
        return list;
    }
    public OrderDTO getOrderDTOById(int idOrder){
        OrderDTO orderDTO = new OrderDTO();
        String where = "id = ?";
        String[] whereArgs = {idOrder+""};
        Cursor cs = db.query(OrderDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            orderDTO.setId(cs.getInt(0));
            orderDTO.setFile_id(cs.getInt(1));
            orderDTO.setOrder_time(cs.getString(2));
            orderDTO.setOrder_date(cs.getString(3));
        }
        return orderDTO;
    }
}
