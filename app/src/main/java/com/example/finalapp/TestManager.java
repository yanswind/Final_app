package com.example.finalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TestManager {
    String TAG = "TestManager";
    private DBHelper dbHelper;
    private String TBNAME;

    public TestManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(TestItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", item.getStudentId());
        values.put("sex", item.getSex());
        values.put("grade", item.getGrade());
        values.put("ItemName", item.getItemName());
        values.put("ItemRecord", item.getItemRecord());
        values.put("ItemScore", item.getItemScore());
        db.insert(TBNAME, null, values);
        //db.close();
    }

    public void addAll(List<TestItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (TestItem item : list) {
            ContentValues values = new ContentValues();
            values.put("ID", item.getStudentId());
            values.put("sex", item.getSex());
            values.put("grade", item.getGrade());
            values.put("ItemName", item.getItemName());
            values.put("ItemRecord", item.getItemRecord());
            values.put("ItemScore", item.getItemScore());
            db.insert(TBNAME, null, values);
        }
        //db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        //db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        //db.close();
    }

    public void update(TestItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", item.getStudentId());
        values.put("sex", item.getSex());
        values.put("grade", item.getGrade());
        values.put("ItemName", item.getItemName());
        values.put("ItemRecord", item.getItemRecord());
        values.put("ItemScore", item.getItemScore());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getStudentId())});
        //db.close();
    }

    public List<TestItem> listAll(){
        List<TestItem> testList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            testList = new ArrayList<>();
            while(cursor.moveToNext()){
                TestItem item = new TestItem();
                item.setStudentId(cursor.getInt(cursor.getColumnIndexOrThrow("ID")));
                item.setSex(cursor.getString(cursor.getColumnIndexOrThrow("sex")));
                item.setGrade(cursor.getInt(cursor.getColumnIndexOrThrow("grade")));
                item.setItemName(cursor.getString(cursor.getColumnIndexOrThrow("ItemName")));
                item.setItemRecord(cursor.getString(cursor.getColumnIndexOrThrow("ItemRecord")));
                item.setItemScore(cursor.getInt(cursor.getColumnIndexOrThrow("ItemScore")));
                testList.add(item);
            }
            cursor.close();
        }
        //db.close();
        return testList;

    }

    public List<TestItem> findByGrade(int grade){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "grade=?", new String[]{String.valueOf(grade)}, null, null, null);
        List<TestItem> testList = new ArrayList<>();
        if(cursor!=null && cursor.moveToFirst()){
            do {
                TestItem item = new TestItem();
                item.setItemName(cursor.getString(cursor.getColumnIndexOrThrow("ItemName")));
                item.setItemRecord(cursor.getString(cursor.getColumnIndexOrThrow("ItemRecord")));
                item.setItemScore(cursor.getInt(cursor.getColumnIndexOrThrow("ItemScore")));
                testList.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }
        //db.close();
        return testList;
    }
}

