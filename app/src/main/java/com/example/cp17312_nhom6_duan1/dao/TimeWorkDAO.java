package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.DTO_TimeWork;

import java.util.ArrayList;

public class TimeWorkDAO {
    SQLiteDatabase db;
    MyDbHelper myDbHelper;
    public TimeWorkDAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public void open(){
        db = myDbHelper.getWritableDatabase();
    }
    public long insertRow(DTO_TimeWork obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("session", obj.getSession());
        return db.insert("tbTimeWork", null, contentValues);

    }
    public int updateRow(DTO_TimeWork obj ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("session", obj.getSession());
        return db.update("tbTimeWork", contentValues, "id=?", new String[]{obj.getId()+""});
    }
    public int deleteRow(DTO_TimeWork obj){
        return db.delete("tbTimeWork", "id=?", new String[]{obj.getId()+""});
    }
    public ArrayList<DTO_TimeWork> getAll(){
        ArrayList<DTO_TimeWork> list = new ArrayList<>();
        String select = "select * from tbTimeWork";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                DTO_TimeWork obj = new DTO_TimeWork();
                obj.setId(cursor.getInt(0));
                obj.setSession(cursor.getString(1));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
