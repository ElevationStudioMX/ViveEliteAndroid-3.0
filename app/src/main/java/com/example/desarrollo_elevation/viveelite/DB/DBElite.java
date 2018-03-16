package com.example.desarrollo_elevation.viveelite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Desarrollo_Elevation on 09/05/17.
 */

public class DBElite extends SQLiteOpenHelper{

   public DBElite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
   super(context, name, factory, version);


        }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
