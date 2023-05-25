package com.example.tomerge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class DBImages extends SQLiteOpenHelper {

    public DBImages(Context context) {
        super(context, "name.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tableimage (name text, image blob);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop table if exists tableimage");
    }

    public boolean insertdata(byte[] img){
        SQLiteDatabase dbImages = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("image", img);

        long result = dbImages.insert("tableimage", null, contentValues);
            if(result == -1){
                return false;
            }else {
                return true;
            }
    }

    public Bitmap getImage(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tableimage", null);
        cursor.moveToFirst();
        byte[] bitmap = cursor.getBlob(0);
        Bitmap image = BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
        return image;
    }
}
