package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWorkDetail;

import java.util.ArrayList;

public class TimeWorkDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbhelper;

    public TimeWorkDetailDAO(Context context){
        dbhelper = new MyDbHelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DTO_TimeWorkDetail dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(DTO_TimeWorkDetail.colTime,dtoTimeWorkDetail.getTime());
        val.put(DTO_TimeWorkDetail.colTimework_id,dtoTimeWorkDetail.getTimework_id());

        long res  =db.insert(DTO_TimeWorkDetail.nameTable,null,val);
        return res;
    }
    public int deleteRow(DTO_TimeWorkDetail dtoTimeWorkDetail){
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};
        int res = db.delete(DTO_TimeWorkDetail.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DTO_TimeWorkDetail dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(DTO_TimeWorkDetail.colTime,dtoTimeWorkDetail.getTime());
        val.put(DTO_TimeWorkDetail.colTimework_id,dtoTimeWorkDetail.getTimework_id());
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};

        int res = db.update(DTO_TimeWorkDetail.nameTable,val,"id = ?",check);
        return res;
    }

    public ArrayList<DTO_TimeWorkDetail> selectAll(){
        ArrayList<DTO_TimeWorkDetail> listTimeWorkDetail = new ArrayList<>();
        Cursor cs = db.query(DTO_TimeWorkDetail.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DTO_TimeWorkDetail dtoTimeWorkDetail = new DTO_TimeWorkDetail();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }

    public ArrayList<DTO_TimeWorkDetail> selectTimeWorkDetailByTimeWorkId(int idTimeWork){
        ArrayList<DTO_TimeWorkDetail> listTimeWorkDetail = new ArrayList<>();
        String where = "timework_id = ?";
        String[] whereArgs = new String[]{idTimeWork+""};
        Cursor cs = db.query(DTO_TimeWorkDetail.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DTO_TimeWorkDetail dtoTimeWorkDetail = new DTO_TimeWorkDetail();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }
}
