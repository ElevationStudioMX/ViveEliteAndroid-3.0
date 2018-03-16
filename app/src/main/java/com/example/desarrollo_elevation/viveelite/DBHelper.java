package com.example.desarrollo_elevation.viveelite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.SurfaceHolder;

/**
 * Created by Desarrollo_Elevation on 14/12/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(Num_control integer primary key,Nombre text,imagen text)");
        db.execSQL("insert into usuarios values(01,'comida rica y delisiosa','R.mipmap.hambre')");
        db.execSQL("insert into usuarios values(02,'Tacos sabrosos','R.mipmap.tacos')");

}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios(Num_control integer primary key,Nombre text,imagen text)");
        db.execSQL("insert into usuarios values(01,'comida rica y delisiosa','R.mipmap.hambre')");
        db.execSQL("insert into usuarios values(02,'Tacos sabrosos','R.mipmap.tacos')");
    }
}
