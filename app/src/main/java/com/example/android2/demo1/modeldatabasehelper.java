package com.example.android2.demo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class modeldatabasehelper extends SQLiteOpenHelper {
    public modeldatabasehelper( Context context) {
        super(context,"DB",null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE model (\n" +
                "id integer PRIMARY KEY,\n" +
                "titel text,\n" +
                "des text\n" +
                ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS model");
            onCreate(db);
        }

    }
    public void them(model d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titel",d.getTitel());
        values.put("des",d.getDes());
        db.insert("model",null,values);
        db.close();
        Log.d("them", "Added model: Title = " + d.getTitel() + ", Description = " + d.getDes());
    }
    public List<model> getAllData(){
        List<model> list = new ArrayList<>();
        String query = "SELECT * FROM model";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                model model = new model();
                model.setTitel(cursor.getString(1));
                model.setDes(cursor.getString(2));
                list.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
