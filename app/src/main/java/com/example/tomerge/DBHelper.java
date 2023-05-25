package com.example.tomerge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
     public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, equipment TEXT, collection TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name, String equipment, String collection){
         SQLiteDatabase DB = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("name", name);
         contentValues.put("equipment", equipment);
         contentValues.put("collection", collection);

         long result = DB.insert("Userdetails", null, contentValues);
            if(result == -1){
                return false;
            }else {
                return true;
            }
    }

    public Cursor getData(){
         SQLiteDatabase DB = this.getWritableDatabase();
         Cursor cursor = DB.rawQuery( "Select * from Userdetails", null);
         return cursor;
    }
}
