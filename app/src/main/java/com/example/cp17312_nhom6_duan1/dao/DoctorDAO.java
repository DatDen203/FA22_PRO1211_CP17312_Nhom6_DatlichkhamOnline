package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.DoctorDTO;

import java.util.ArrayList;

public class DoctorDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public DoctorDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertRow(DoctorDTO doctorDTO) {
        ContentValues val = new ContentValues();
        val.put(DoctorDTO.colIdUser, doctorDTO.getUser_id());
        val.put(DoctorDTO.colBirthday, doctorDTO.getBirthday());
        val.put(DoctorDTO.colService_id, doctorDTO.getService_id());
        val.put(DoctorDTO.colRoom_id, doctorDTO.getRoom_id());
        val.put(DoctorDTO.colDescription, doctorDTO.getDescription());
        val.put(DoctorDTO.colTimework_id, doctorDTO.getTimework_id());

        long res = db.insert(DoctorDTO.nameTable, null, val);
        return res;
    }

    public int updateRow(DoctorDTO doctorDTO) {
        ContentValues val = new ContentValues();
        val.put(DoctorDTO.colIdUser, doctorDTO.getUser_id());
        val.put(DoctorDTO.colBirthday, doctorDTO.getBirthday());
        val.put(DoctorDTO.colService_id, doctorDTO.getService_id());
        val.put(DoctorDTO.colRoom_id, doctorDTO.getRoom_id());
        val.put(DoctorDTO.colDescription, doctorDTO.getDescription());
        val.put(DoctorDTO.colTimework_id, doctorDTO.getTimework_id());

        String[] check = new String[]{doctorDTO.getId() + ""};

        int res = db.update(DoctorDTO.nameTable, val, "id = ?", check);
        return res;
    }

    public ArrayList<DoctorDTO> selectAll() {
        ArrayList<DoctorDTO> listDtoDoctor = new ArrayList<>();
        Cursor cs = db.query(DoctorDTO.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(cs.getInt(0));
                doctorDTO.setUser_id(cs.getInt(1));
                doctorDTO.setBirthday(cs.getString(2));
                doctorDTO.setService_id(cs.getInt(3));
                doctorDTO.setRoom_id(cs.getInt(4));
                doctorDTO.setDescription(cs.getString(5));
                doctorDTO.setTimework_id(cs.getInt(6));

                listDtoDoctor.add(doctorDTO);
                cs.moveToNext();
            }
        }
        return listDtoDoctor;
    }
}
