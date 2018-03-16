package com.example.desarrollo_elevation.viveelite;

import android.app.Service;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.desarrollo_elevation.viveelite.Adapter.Adapter_BD;
import com.example.desarrollo_elevation.viveelite.Adapter.Model_BD;
import com.example.desarrollo_elevation.viveelite.DB.DBHome;
///import com.example.web_api_spotify.models.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.value;
import static android.R.id.list;


public class prueba_recycleviwer_base_datos extends AppCompatActivity {

    private Cursor fila, filacount, filare;
    private  static ArrayList<Model_BD> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_recycleviwer_base_datos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarbasededatos);
        toolbar.setTitle("prueba base de datos");
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);

        DBHome db_home = new DBHome(this, "elite_ht", null, 1);
        SQLiteDatabase sqLiteDatabase = db_home.getWritableDatabase();


       /* sqLiteDatabase.execSQL("select * from bandera, contendio where bandera.id_tipo = contenido.id_tipo and id_tipo=0");
        sqLiteDatabase.execSQL("delete from where contenido.id= 0");

        */


        ContentValues value = new ContentValues();


       /* sqLiteDatabase.execSQL("create table bandera(id_tipo integer primary key , Nombre text)");
        sqLiteDatabase.execSQL("create table contenido(id integer primary key AUTOINCREMENT, id_tipo integer not null, titulo text not null, subtitulos text not null, descripcion text not null, imagen text, video text, fecha text not null, favoritos integer not null)");


        value.put("id_tipo", 0);
        value.put("Nombre", "Receta");
        sqLiteDatabase.insert("bandera", null, value);*/

        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



        String tiempo = String.valueOf(tiem.format(sq));


      /* sqLiteDatabase.execSQL("insert into tipo_contenido values(0, 'imagenes')");
        sqLiteDatabase.execSQL("insert into tipo_contenido values(1, 'videos')");*/

    /* ContentValues values = new ContentValues();
        values.put("id_tipo", 1);
        value.put("id_tipo_contenido", 1);
        values.put("titulo", "comida casera que rico mmmm");
        values.put("subtitulos", "como hacer los muffin");
        values.put("descripcion", "3 kilos de arina, 4 platanos vien ricos mmmm");
        values.put("contenido", "https://i.ytimg.com/vi/2DJtHzn7kZo/maxresdefault.jpg" );
        //values.put("video", "");
        values.put("fecha", tiempo);
        values.put("favoritos", 1);


        sqLiteDatabase.insert("contenido", null, values);
*/
      /* ContentValues valueser = new ContentValues();
        valueser.put("id_tipo", 2);
        valueser.put("id_tipo_contenido", 2);
        valueser.put("titulo", "programacion");
        valueser.put("subtitulos", "como hacer los muffin");
        valueser.put("descripcion", "3 kilos de arina, 4 platanos vien ricos mmmm");
     //   valueser.put("imagen", "" );
        valueser.put("contenido", "U-JxaFZDSn4");
        valueser.put("fecha", "2016-12-12");
        valueser.put("favoritos", 1);



        sqLiteDatabase.insert("contenido", null, valueser);
*/

        String sqlquery = "select contenido.id_tipo_contenido, contenido.titulo, contenido.contenido  from bandera, contenido, tipo_contenido where bandera.id_tipo = contenido.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido";

        fila = sqLiteDatabase.rawQuery(sqlquery, null);


        filare = sqLiteDatabase.rawQuery("select contenido.id_tipo_contenido, contenido.titulo, contenido.contenido  from contenido where contenido.id_tipo_contenido=1",null);

if (filare.moveToFirst())
{
    Log.v( "esto es lo que pasa", "es "+ filare.getString(0));
    Log.v( "esto es lo que pasa", "es "+ filare.getString(1));
}
       // sqLiteDatabase.execSQL(sqlquery);

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String,String>>();



                list = new ArrayList<>();






                /*String querycount = "select count(*)  from bandera, contenido where bandera.id_tipo = contenido.id_tipo";
                filacount = sqLiteDatabase.rawQuery(querycount, null);
                filacount.moveToFirst();

                int contador = filacount.getInt(0);*/

              //  for(int co = 1; co < contador; co++) {




            while (fila.moveToNext()){

                int id = Integer.parseInt(fila.getString(0));
                String titulo = fila.getString(1);
                String contenido = fila.getString(2);
                //String imagen = fila.getString(3);

                Model_BD model_bd = new Model_BD(id, titulo, contenido);

                list.add(model_bd);

                Log.v("esto es" , ""+id);
        }




      //  while (fila.moveToNext())
       // {           // percorrer nosso ResultSet em todos os registro, enquanto existir um prox.


         //   ArrayList<Model_BD> list= new ArrayList<>();

           /* int id = Integer.parseInt(fila.getString(0));
            String titulo = fila.getString(1);
            String video = fila.getString(2);
            String imagen = fila.getString(3);

            list.add(new Model_BD(id, titulo, video, imagen));*/



            Adapter_BD adapter = new Adapter_BD(list, prueba_recycleviwer_base_datos.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(prueba_recycleviwer_base_datos.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerBD);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);


     /*       Map<String, String> datanum = new HashMap<String, String>();
            datanum.put("A", fila.getString(0));
            datanum.put("B", fila.getString(1));
            datanum.put("C", fila.getString(2));
            datanum.put("D", fila.getString(3));
            data.add(datanum)*/;
        }


      //  Log.v("que es", ""+ new Model(Model_BD.IMAGE_TYPE, "", "", 0));






    }

