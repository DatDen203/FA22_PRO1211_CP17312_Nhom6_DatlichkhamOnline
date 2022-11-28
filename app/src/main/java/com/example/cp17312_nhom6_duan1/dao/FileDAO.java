package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;

import java.util.ArrayList;

public class FileDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public FileDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertRow(FileDTO fileDTO){
        ContentValues val = new ContentValues();
        val.put(FileDTO.colFullName,fileDTO.getFullname());
        val.put(FileDTO.colUser_id,fileDTO.getUser_id());
        val.put(FileDTO.colBirthday,fileDTO.getBirthday());
        val.put(FileDTO.colCccd,fileDTO.getCccd());
        val.put(FileDTO.colCountry,fileDTO.getCountry());
        val.put(FileDTO.colBhyt,fileDTO.getBhyt());
        val.put(FileDTO.colJob,fileDTO.getJob());
        val.put(FileDTO.colEmail,fileDTO.getEmail());
        val.put(FileDTO.colAddress,fileDTO.getAddress());
        val.put(FileDTO.colDes,fileDTO.getDes());

        long res = db.insert(FileDTO.nameTable,null,val);
        return res;
    }
    public int updateRow(FileDTO fileDTO){
        ContentValues val = new ContentValues();
        val.put(FileDTO.colFullName,fileDTO.getFullname());
        val.put(FileDTO.colUser_id,fileDTO.getUser_id());
        val.put(FileDTO.colBirthday,fileDTO.getBirthday());
        val.put(FileDTO.colCccd,fileDTO.getCccd());
        val.put(FileDTO.colCountry,fileDTO.getCountry());
        val.put(FileDTO.colBhyt,fileDTO.getBhyt());
        val.put(FileDTO.colJob,fileDTO.getJob());
        val.put(FileDTO.colEmail,fileDTO.getEmail());
        val.put(FileDTO.colAddress,fileDTO.getAddress());
        val.put(FileDTO.colDes,fileDTO.getDes());

        String[] check = new String[]{fileDTO.getId()+""};
        int res = db.update(FileDTO.nameTable,val,"id = ?",check);
        return res;
    }
    public ArrayList<FileDTO> checkLIstFileYou(String fulName){
        ArrayList<FileDTO> listFileDto = new ArrayList<>();
        String where = "fullname = ?";
        String[] whereArgs = {fulName.trim()};
        Cursor cs = db.query(FileDTO.nameTable,null,where,whereArgs,null ,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                FileDTO fileDTO = new FileDTO();
                fileDTO.setId(cs.getInt(0));
                fileDTO.setFullname(cs.getString(1));
                fileDTO.setUser_id(cs.getInt(2));
                fileDTO.setBirthday(cs.getString(3));
                fileDTO.setCccd(cs.getString(4));
                fileDTO.setCountry(cs.getString(5));
                fileDTO.setBhyt(cs.getString(6));
                fileDTO.setJob(cs.getString(7));
                fileDTO.setEmail(cs.getString(8));
                fileDTO.setAddress(cs.getString(9));
                fileDTO.setDes(cs.getString(10));

                listFileDto.add(fileDTO);
                cs.moveToNext();
            }
        }
        return listFileDto;
    }
    public FileDTO getFileDToTop(){
        FileDTO fileDTO = new FileDTO();
        String orderBy = "id desc";
        Cursor cs = db.query(FileDTO.nameTable,null,null,null,null,null,orderBy,"1");
        if(cs.moveToFirst()){
            fileDTO.setId(cs.getInt(0));
            fileDTO.setFullname(cs.getString(1));
            fileDTO.setUser_id(cs.getInt(2));
            fileDTO.setBirthday(cs.getString(3));
            fileDTO.setCccd(cs.getString(4));
            fileDTO.setCountry(cs.getString(5));
            fileDTO.setBhyt(cs.getString(6));
            fileDTO.setJob(cs.getString(7));
            fileDTO.setEmail(cs.getString(8));
            fileDTO.setAddress(cs.getString(9));
            fileDTO.setDes(cs.getString(10));
        }
        return fileDTO;
    }

    public FileDTO getFileDToById(int id){
        FileDTO fileDTO = new FileDTO();
        String where = "id = ?";
        String[] whereArgs = {id+""};
        Cursor cs = db.query(FileDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            fileDTO.setId(cs.getInt(0));
            fileDTO.setFullname(cs.getString(1));
            fileDTO.setUser_id(cs.getInt(2));
            fileDTO.setBirthday(cs.getString(3));
            fileDTO.setCccd(cs.getString(4));
            fileDTO.setCountry(cs.getString(5));
            fileDTO.setBhyt(cs.getString(6));
            fileDTO.setJob(cs.getString(7));
            fileDTO.setEmail(cs.getString(8));
            fileDTO.setAddress(cs.getString(9));
            fileDTO.setDes(cs.getString(10));
        }
        return fileDTO;
    }
    public ArrayList<FileDTO> getAll(){
        ArrayList<FileDTO> list = new ArrayList<>();
        String select ="SELECT * FROM tbFile";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                FileDTO obj = new FileDTO();
                obj.setId(cursor.getInt(0));
                obj.setFullname(cursor.getString(1));
                obj.setUser_id(cursor.getInt(2));
                obj.setBirthday(cursor.getString(3));
                obj.setCccd(cursor.getString(4));
                obj.setBhyt(cursor.getString(5));
                obj.setCountry(cursor.getString(6));
                obj.setAddress(cursor.getString(7));
                obj.setDes(cursor.getString(8));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
