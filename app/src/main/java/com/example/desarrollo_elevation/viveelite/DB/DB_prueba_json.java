package com.example.desarrollo_elevation.viveelite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Desarrollo_Elevation on 08/05/17.
 */

public class DB_prueba_json extends SQLiteOpenHelper {


    public DB_prueba_json(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table contry(id integer primary key AUTOINCREMENT, name text, alpha2_code text, alpha3_code text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("create table contry(id integer primary key AUTOINCREMENT, name text, alpha2_code text, alpha3_code text)");
    }
}