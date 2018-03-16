package com.example.desarrollo_elevation.viveelite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Desarrollo_Elevation on 27/03/17.
 */

public class DBHome extends SQLiteOpenHelper {
    public DBHome(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table bandera(id_tipo integer primary key, Nombre text)");
        sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger,  id integer not null, titulo text not null, influencer text not null, categoria text, permarlink text, descripcion text not null, descripcion_ejercicio text, contenido text, preparacion text, duracion text, ELEV_TIMESTAMP text,fecha_publicacion text not null,fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
        //  sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger,  id integer not null, titulo text not null, influencer text not null, categoria text, permarlink text, descripcion text not null, contenido text, preparacion text, duracion text, fecha_publicacion text not null,fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
        sqLiteDatabase.execSQL("create table ingredientes(ingrediente_id integer not null,id integer not null,  cantidad text, ingrediente text)");
        sqLiteDatabase.execSQL("create table carrusel(id integer primary key autoincrement, id_contenido integer, id_tipo_contenido integer)");
        sqLiteDatabase.execSQL("create table tipo_contenido(id_tipo_contenido interger primary key, nombre text not null)");
        sqLiteDatabase.execSQL("create table categoria_receta(id_categoria_receta integer, nombre text, descripcion text,imagen text)");
        sqLiteDatabase.execSQL("create table bandera_popwindow(view integer)");

        sqLiteDatabase.execSQL("create table categoria_sticker(id integer primary key autoincrement, id_categoria_sticker integer, nombre text)");
        sqLiteDatabase.execSQL("create table sticker(id integer primary key autoincrement, id_categoria_sticker integer, color_filter integer, sticker text)");


        sqLiteDatabase.execSQL("insert into bandera_popwindow values (0)");

        sqLiteDatabase.execSQL("create table fecha_actualizacion(fecha text)");

       sqLiteDatabase.execSQL("insert into  fecha_actualizacion values('')");


        sqLiteDatabase.execSQL("create table fecha_actualizacion_receta(fecha text)");

        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_receta values('')");


        sqLiteDatabase.execSQL("create table fecha_actualizacion_ejercicio(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_ejercicio values('')");

        sqLiteDatabase.execSQL("create table fecha_actualizacion_sticker(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_sticker values('')");


        sqLiteDatabase.execSQL("insert into bandera values(1, 'Recetas')");
        sqLiteDatabase.execSQL("insert into bandera values(2, 'Ejercicios')");

        sqLiteDatabase.execSQL("insert into tipo_contenido values(1, 'imagenes')");
        sqLiteDatabase.execSQL("insert into tipo_contenido values(2, 'videos')");


/*        sqLiteDatabase.execSQL("insert into contenido(0, 'Muffin de platanos', 'como hacer los muffins', 'una taza de azucar" +
                " un kilo de chocolate  dos platanos  mas cosas', 'https://s-media-cache-ak0.pinimg.com/736x/78/5d/27/785d27ee814a904942f4d11cf44437cb.jpg', '', '2017-03-29', 1 )");
   */ }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("create table bandera(id_tipo integer primary key , Nombre text)");

        sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger,  id integer not null, titulo text not null, influencer text not null, categoria text, permarlink text, descripcion text not null, descripcion_ejercicio text, contenido text, preparacion text, duracion text, ELEV_TIMESTAMP text,fecha_publicacion text not null,fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
        //sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger,  id integer not null, titulo text not null, influencer text not null, categoria text, permarlink text, descripcion text not null, contenido text, preparacion text, duracion text, ELEV_TIMESTAMP text,fecha_publicacion text not null,fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
        //sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido integer, id text not null, link text not null, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
       // sqLiteDatabase.execSQL("create table contenido(id integer primary key AUTOINCREMENT, id_tipo integer not null, titulo text not null, subtitulos text not null, descripcion text not null, imagen text, video text, fecha text not null, favoritos integer not null)");
        sqLiteDatabase.execSQL("create table ingredientes(id integer not null, cantidad text, ingrediente text)");
        sqLiteDatabase.execSQL("create table carrusel(id integer primary key autoincrement, id_contenido integer, id_tipo_contenido integer)");
        sqLiteDatabase.execSQL("create table tipo_contenido(id_tipo_contenido interger primary key, nombre text not null)");
        sqLiteDatabase.execSQL("create table categoria_receta(id_categoria_receta integer, nombre text, descripcion text,imagen text)");
        sqLiteDatabase.execSQL("create table fecha_actualizacion(fecha text)");

        sqLiteDatabase.execSQL("create table categoria_sticker(id integer primary key autoincrement, id_categoria_sticker integer, nombre text)");

        sqLiteDatabase.execSQL("create table sticker(id integer primary key autoincrement, id_categoria_sticker integer, color_filter integer, sticker text)");

        sqLiteDatabase.execSQL("insert into  fecha_actualizacion values('')");


        sqLiteDatabase.execSQL("create table fecha_actualizacion_receta(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_receta values('')");

        sqLiteDatabase.execSQL("create table fecha_actualizacion_ejercicio(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_ejercicio values('')");

        sqLiteDatabase.execSQL("create table fecha_actualizacion_sticker(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_sticker values('')");


        sqLiteDatabase.execSQL("insert into bandera values(1, 'Recetas')");
        sqLiteDatabase.execSQL("insert into bandera values(2, 'Ejercicios')");

        sqLiteDatabase.execSQL("insert into tipo_contenido values(1, 'imagenes')");
        sqLiteDatabase.execSQL("insert into tipo_contenido values(2, 'videos')");
       // sqLiteDatabase.execSQL("select* from delte datos movliels android sqltie datos guardados");


       /* sqLiteDatabase.execSQL("insert into contenido(0, 'Muffin de platanos', 'como hacer los muffins', 'una taza de azucar" +
                " un kilo de chocolate  dos platanos  mas cosas', 'https://s-media-cache-ak0.pinimg.com/736x/78/5d/27/785d27ee814a904942f4d11cf44437cb.jpg', '', '2017-03-29', 1 )");
    */}
}
