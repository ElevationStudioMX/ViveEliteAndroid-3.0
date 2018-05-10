package com.example.desarrollo_elevation.viveelite;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.VideoView;

import com.example.desarrollo_elevation.viveelite.DB.DBHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLClientInfoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity_recetas extends AppCompatActivity {
    private  static  String contex;
    private String lenguajeProgramacion[]=new String[]{"Comida rica","Espageti delicioso","Tacos de antojo"};
    VideoView videoView;
    private Integer[] imgid = {R.mipmap.hambre,R.mipmap.espageti,R.mipmap.tacos};
    private ListView Lista;
    private  static Cursor fila;
    private static  Cursor cursos_fecha;
    private static  String linkfristreceta;
    private static String link;
    private static String video;
    private static String imagen;
    private static  int nota;
    private  static  String fecha_destinada;
    private  static  ArrayList<Model> list;
    private  static  String name_database_elite= "elite_v15";
    private  static  int  bunde_receta =0;
    private  static String bundel_receta_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recetas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Recetas");
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        list= new ArrayList<>();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String tiempo = String.valueOf(tiem.format(sq));
        DBHome dbHome = new DBHome(MainActivity_recetas.this,name_database_elite, null, 1);
        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
        String fecha = "select fecha from fecha_actualizacion_receta  ";
        fila =  sqLiteDatabase.rawQuery(fecha, null);

        if (fila.moveToFirst()) {
            fecha_destinada =  fila.getString(0);
            Log.v("fecha destinada", fecha_destinada);
        }
        Bundle b = getIntent().getExtras();
        int id_cate = b.getInt("id_categoria");
        String name = b.getString("nombrecategoria");
         bunde_receta = id_cate;
        getSupportActionBar().setTitle(name);

        if (fecha_destinada.equals("")) {
            Log.v("donde paso el json", "json paso por recetas frist");
            linkfristreceta = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/recetas-first/"+id_cate+"";
            new JsonTaskrecetafrist().execute(linkfristreceta);
        } else {
            Log.v("donde paso el json", "json esta pasando por recetas");
            link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/recetas/"+id_cate+"/"+fecha_destinada+"";
            Log.v("Link", link);
            new JsonTask().execute(link);
        }
        ContentValues v = new ContentValues();
        v.put("fecha", tiempo);
        sqLiteDatabase.update("fecha_actualizacion_receta", v, null, null);
        String fecha_nueva = "select fecha from fecha_actualizacion_receta";
        cursos_fecha = sqLiteDatabase.rawQuery(fecha_nueva,null);

        if(cursos_fecha.moveToFirst()) {
            String fecha_new = cursos_fecha.getString(0);
            Log.v("fecham nueva", fecha_new);
        }
    }

    public class JsonTaskrecetafrist extends AsyncTask<String, String, ArrayList<Model>> {
        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);
                DBHome db = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ArrayList<Model> list = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    video = "";
                    imagen = "";
                    nota = 0;
                    JSONObject ob = jsarreglo.getJSONObject(i);
                    int nota_id = ob.getInt("NOTA_ID");
                    String categoria = ob.getString("CATEGORIA");
                    String titulo = ob.getString("TITULO");
                    String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length(); j++) {
                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        video = object.getString("ELEV_YOUTUBE_CLAVE");
                        break;
                    }
                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");
                    for (int x = 0; x < jsonArrayimagen.length(); x++) {
                        JSONObject object = jsonArrayimagen.getJSONObject(x);
                        imagen = object.getString("NOMBRE");
                        break;
                    }
                    Log.v("imagen", ""+imagen);
                    Log.v("video", ""+video);
                    nota = 1;
                    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";
                    Log.v("id nota", ""+nota_id);
                    fila = database.rawQuery(query, null);

                    if(fila.moveToFirst()) {
                        int contador = fila.getInt(0);
                        Log.v("contador", ""+contador);
                        if(contador == 1) {
                        } else {
                            contentValues.put("id", nota_id);
                            contentValues.put("id_tipo", 1);

                            if (!video.equals("")) {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            } else if (!imagen.equals("")) {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/"+imagen);
                            } else {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/no-imagen.jpg"/*"http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen*/);
                            }

                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);
                            database.insert("contenido", null, contentValues);
                        }
                    }
                }
                return list;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            list = new ArrayList<>();
            Bundle bundle = getIntent().getExtras();
            String name = bundle.getString("nombrecategoria");
            bundel_receta_name = name;
            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1 and categoria = '"+name+"' order by fecha_publicacion desc ";
            fila = sqLiteDatabase.rawQuery(query, null);
            list= new ArrayList<>();

            while (fila.moveToNext()) {
                int id_tipo_contenido = Integer.parseInt(fila.getString(0));
                String descrpition = fila.getString(1);
                String titulo = fila.getString(2);

                String contenido = fila.getString(3);
                int tipo = Integer.parseInt(fila.getString(4));
                int favor = Integer.parseInt(fila.getString(5));
                String id = fila.getString(6);
                String link = fila.getString(7);
                String preparacion = fila.getString(8);
                String duracion = fila.getString(9);
                String fecha =  fila.getString(10);

                Log.v("contenitdo", contenido);

                if (tipo == 1) {
                    if(favor== 0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    } else if(favor == 1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                } else if(tipo == 2) {
                    if(favor == 0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    } else if (favor == 1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }
            }
            RecyclerView mRecyclerView = findViewById(R.id.layoutrecetas);
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this, mRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    public class JsonTask extends AsyncTask<String, String, ArrayList<Model>> {
        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();


                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);
                DBHome db = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ArrayList<Model> list = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    video = "";
                    imagen = "";
                    nota = 0;

                    JSONObject ob = jsarreglo.getJSONObject(i);
                    int nota_id = ob.getInt("NOTA_ID");
                   // String tipo_Nota = ob.getString("TIPO_NOTA");
                    String categoria = ob.getString("CATEGORIA");
                    String titulo = ob.getString("TITULO");
                    String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length(); j++) {
                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        video = object.getString("ELEV_YOUTUBE_CLAVE");
                        break;
                    }
                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {
                        JSONObject object = jsonArrayimagen.getJSONObject(x);
                        imagen = object.getString("NOMBRE");
                        break;
                    }
                    Log.v("imagen", ""+imagen);
                    Log.v("video", ""+video);
                    nota = 1;
                    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";
                    Log.v("id nota", ""+nota_id);

                    fila = database.rawQuery(query, null);
                    if(fila.moveToFirst()) {
                        int contador = fila.getInt(0);
                        Log.v("contador", ""+contador);
                        if(contador == 1) {
                        } else {
                            contentValues.put("id", nota_id);
                            contentValues.put("id_tipo", 1);

                            if (!video.equals("")) {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            } else if (!imagen.equals("")) {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/"+imagen);
                            }
                            else {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/no-imagen.jpg"/*"http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen*/);
                            }

                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);
                            database.insert("contenido", null, contentValues);
                        }
                    }
                }
                return list;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            list = new ArrayList<>();
            Bundle bundle = getIntent().getExtras();
            String name = bundle.getString("nombrecategoria");
            bundel_receta_name = name;
            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  and contenido.id_tipo = 1 and categoria = '"+name+"'  order by fecha_publicacion desc ";
            fila = sqLiteDatabase.rawQuery(query, null);
            list= new ArrayList<>();

            while (fila.moveToNext()) {
                int id_tipo_contenido = Integer.parseInt(fila.getString(0));
                String descrpition = fila.getString(1);
                String titulo = fila.getString(2);
                String contenido = fila.getString(3);
                int tipo = Integer.parseInt(fila.getString(4));
                int favor = Integer.parseInt(fila.getString(5));
                String id = fila.getString(6);
                String link = fila.getString(7);
                String preparacion = fila.getString(8);
                String duracion = fila.getString(9);
                String fecha =  fila.getString(10);

                Log.v("contenitdo", contenido);

                if (tipo == 1) {
                    if(favor== 0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    } else if(favor ==1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                } else if(tipo == 2) {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    } else if (favor == 1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }
            }
            RecyclerView mRecyclerView = findViewById(R.id.layoutrecetas);
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this, mRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

   public int getBunde_receta(){
     return  this.bunde_receta;
   }

   public String getBundel_receta_name (){
       return  this.bundel_receta_name;
   }

 public String contex(){

     contex = this.getClass().getSimpleName();
     return contex;

 }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    if(keyCode == KeyEvent.KEYCODE_BACK  && event.getRepeatCount()==0) {
        Intent intent = new Intent(this, MainActivity_categorias.class);
        startActivity(intent);

    }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
       Intent intent = new Intent(this, MainActivity_categorias.class);
        startActivity(intent);
        return false;
    }
}