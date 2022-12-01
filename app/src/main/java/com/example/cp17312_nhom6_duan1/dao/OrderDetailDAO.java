package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.AllDTO;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;
import com.example.cp17312_nhom6_duan1.dto.StatisticalDTO;

import java.util.ArrayList;

public class OrderDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDetailDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long innsertRow(OrderDetailDTO orderDetailDTO) {
        ContentValues val = new ContentValues();
        val.put(OrderDetailDTO.colOrderId, orderDetailDTO.getOrder_id());
        val.put(OrderDetailDTO.colOrderDoctorId, orderDetailDTO.getOrderDoctor_id());

        long res = db.insert(OrderDetailDTO.nameTable, null, val);
        return res;
    }

    public ArrayList<OrderDetailDTO> selectAll() {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        Cursor cs = db.query(OrderDetailDTO.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));

                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderDetailDtoById(int idUser) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {idUser + ""};
        String select = "select tbOrderDetail.order_id ,tbOrderDetail.orderDoctor_id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbFile.user_id = tbAccount.id where tbAccount.id =?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public StatisticalDTO getStatisticalDTOByStartToDay(String toDay) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {toDay.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.start_date = ?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public StatisticalDTO getStatisticalDTOByOrderToDay(String OrderToDay) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {OrderToDay.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrders.order_date = ?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public StatisticalDTO getStatisticalDTOByToMonth(String month1, String month2) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {month1.trim(), month2.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.start_date > ?  and tbOrderDoctor.start_date <?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public StatisticalDTO getStatisticalDTOByOrderMonth(String month1, String month2) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {month1.trim(), month2.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrders.order_date > ?  and tbOrders.order_date <?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public ArrayList<DoctorDTO> getListSumPriceByOrderMonth(String month1, String month2) {
        ArrayList<DoctorDTO> list = new ArrayList<>();
        String[] whereArds = {month1.trim(), month2.trim()};
        String select = "select tbOrderDoctor.doctor_id ,sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbDoctor on tbOrderDoctor.doctor_id = tbDoctor.id   where tbOrders.order_date >?  and tbOrders.order_date< ? group by tbDoctor.id order by sum(tbOrderDoctor.total) desc";
        Cursor cs = db.rawQuery(select, whereArds);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(cs.getInt(0));
                doctorDTO.setSumPrice(cs.getFloat(1));

                list.add(doctorDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
    }

    public ArrayList<AllDTO> getListPriceByDayDoctor(String date, int id) {
        ArrayList<AllDTO> list = new ArrayList<>();
        String select = "select tbDoctor.id,tbOrderDoctor.start_time,tbOrderDoctor.total from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                "inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id \n" +
                "where tbOrderDoctor.doctor_id= "+id+" and tbOrderDoctor.start_date ='"+date+"'";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AllDTO obj = new AllDTO();
                obj.setIdDoctor(cursor.getInt(0));
                obj.setTime(cursor.getString(1));
                obj.setTotal(cursor.getFloat(2));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public ArrayList<AllDTO> getListPriceByMonthDoctor(String startDate, String endDate, int id) {
        ArrayList<AllDTO> list = new ArrayList<>();
        String select = "select tbDoctor.id,tbOrderDoctor.start_date,sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                "inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id \n" +
                "where tbOrderDoctor.doctor_id = "+id+" and tbOrderDoctor.start_date>= '"+startDate+"' and tbOrderDoctor.start_date<= '"+endDate+"' group by tbOrderDoctor.start_date having sum(tbOrderDoctor.total)";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AllDTO obj = new AllDTO();
                obj.setIdDoctor(cursor.getInt(0));
                obj.setDay(cursor.getString(1));
                obj.setTotal(cursor.getFloat(2));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public double getSumPriceByMonthDoctor(String startDate, String endDate, int id) {
        AllDTO obj = new AllDTO();
        String select = "select sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                " inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id join tbServices on tbDoctor.service_id=tbServices.id\n" +
                "  where tbOrderDoctor.doctor_id= " + id + " and tbOrderDoctor.start_date>= '" + startDate + "' and tbOrderDoctor.start_date<= '" + endDate + " '";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                obj.setTotal(cursor.getFloat(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return obj.getTotal();
    }

    public double getSumPriceByDayDoctor(String startDate, int id) {
        AllDTO obj = new AllDTO();
        String select = "select sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                " inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id join tbServices on tbDoctor.service_id=tbServices.id\n" +
                "  where tbOrderDoctor.doctor_id= " + id + " and tbOrderDoctor.start_date>= '" + startDate + "'";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                obj.setTotal(cursor.getFloat(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return obj.getTotal();
    }

}
