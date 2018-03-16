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
import android.view.WindowManager;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity_Ejercicios extends AppCompatActivity {

    private static String context;
    private static Cursor fila;
    private  static ArrayList<Model> list;

    private static  Cursor cursos_fecha;

    private static  String linkfristreceta ="http://www.elevation.com.mx/pages/AppElite/web-services/elite/ejercicios-first";
    private static String link;
    private static String video;
    private static String imagen;
    private static  int nota;
    private  static  String fecha_destinada;
    private  static  String name_database_elite= "elite_v15";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__ejercicios);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarejercicio);
        toolbar.setTitle("Ejercicios");


        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*list= new ArrayList<>();

        DBHome dbHome = new DBHome(this, "vive_elitev2", null, 1);

        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

        //String sql= "select contenido.id_tipo, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.link from bandera, contenido, tipo_contenido where bandera.id_tipo = contenido.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo= 2";
        String sql= "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.link, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 2";
        fila = sqLiteDatabase.rawQuery(sql, null);

        while (fila.moveToNext())
        {
            int  id = Integer.parseInt(fila.getString(0));
            String descripcion = fila.getString(1);
            String titulo = fila.getString(2);
            String contenido = fila.getString(3);
            int id_tipo = Integer.parseInt(fila.getString(4));
            int favoritos = Integer.parseInt(fila.getString(5));
            String ideres = fila.getString(6);
            String link = fila.getString(7);
            String preparacion = fila.getString(8);
            String duration = fila.getString(9);


               if(id_tipo ==1){


                   if(favoritos == 0) {


                       Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, ideres, link, preparacion, duration);

                       list.add(model);
                   }

                   else if(favoritos == 1)
                   {
                       Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, ideres, link, preparacion, duration);
                       list.add(model);
                   }

               }

            else if (id_tipo == 2)

               {
                   if(favoritos ==0)
                   {
                   Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, ideres, link, preparacion, duration);
                   list.add(model);
                   }

                   else  if(favoritos == 1)
                   {
                       Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, ideres, link, preparacion, duration);
                       list.add(model);
                   }


               }
        }

        /*list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios de estiramiento y cardio","6J-cmRe-Kdg",0));
        list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios basicos","k_u8obkaGE4",0));
        list.add(new Model(Model.VIDEO_TYPE,"Ejercicio para notificar el cuerpo","diFjQVUL7wk",0));
        list.add(new Model(Model.VIDEO_TYPE,"Ejercicio para marcar abodmen y cuerpo","UelGJY7kYf4",0));*/
        //ist.add(new Model(Model.IMAGE_TYPE,"", "", 0));

/*


        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_Ejercicios.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Ejercicios.this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutejercicios);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);*/


        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



        String tiempo = String.valueOf(tiem.format(sq));


        DBHome dbHome = new DBHome(MainActivity_Ejercicios.this,name_database_elite, null, 1);

        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

        String fecha = "select fecha from fecha_actualizacion_ejercicio  ";





        fila =  sqLiteDatabase.rawQuery(fecha, null);

        if (fila.moveToFirst())
        {
            fecha_destinada =  fila.getString(0);
            Log.v("fecha destinada", fecha_destinada);

        }



        if (fecha_destinada.equals(""))
        {
            Log.v("donde paso el json", "json paso por ejercicios frist");

            Log.v("link homefrist", linkfristreceta);
            new JsonTaskejerciciosfrist().execute(linkfristreceta);
        }

        else {

            Log.v("donde paso el json", "json esta pasando por ejercicios");
            link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/ejercicios/"+fecha_destinada+"";

            Log.v("Link", link);

            new JsonTask().execute(link);


        }
        ContentValues v = new ContentValues();

        v.put("fecha", tiempo);
        sqLiteDatabase.update("fecha_actualizacion_ejercicio", v, null, null);
        String fecha_nueva = "select fecha from fecha_actualizacion_ejercicio";
        cursos_fecha = sqLiteDatabase.rawQuery(fecha_nueva,null);

        if(cursos_fecha.moveToFirst())
        {
            String fecha_new = cursos_fecha.getString(0);

            Log.v("fecham nueva", fecha_new);
        }

        /*list.add(new Model(Model.IMAGE_TYPE, "Ensalada rica para una dieta saludable", "", R.mipmap.ensalada_manzana));
        list.add(new Model(Model.VIDEO_TYPE,"Como hacer una buena comida","VJzSqx1nNKo",0));
        list.add(new Model(Model.IMAGE_TYPE,"una sopa deliciosa de la vieja escuela","", R.mipmap.espageti));
        list.add(new Model(Model.IMAGE_TYPE, "Postre rico y nutritivo", "",R.mipmap.postres));
        list.add(new Model(Model.VIDEO_TYPE,"Almuerzo listo para comenzar el dia","",R.mipmap.almuerzos));
*/


       /* DBHome home = new DBHome(this, "vive_elitev2", null, 1);

        SQLiteDatabase sqLiteDatabase = home.getWritableDatabase();

       // String Query= "select contenido.id_tipo_contenido, contenido.descripcion, contenido.contenido from bandera, tipo_contenido, contenido where bandera.id_tipo = contendio.id_tipo and contendio.id_tipo_contendio = tipo_contendio.id_tipo_contenido and contendio.id_tipo = 1";
        String Query= "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.link, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1";
        //String Query= "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.link, contenido.preparacion, contendio.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1";



        fila = sqLiteDatabase.rawQuery(Query, null);

        while (fila.moveToNext())
        {
            int id = Integer.parseInt(fila.getString(0));

            String descripcion = fila.getString(1);

            String titulo = fila.getString(2);

            String contenido = fila.getString(3);

            int  tipo = Integer.parseInt(fila.getString(4));

            int favoritos = Integer.parseInt(fila.getString(5));

            String idlink = fila.getString(6);

            String link = fila.getString(7);

            String preparacion = fila.getString(8);

            String duration = fila.getString(9);

            if(tipo == 1)

                 {
                     if(favoritos == 0)
                    {
                        Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, idlink, link, preparacion, duration);
                        list.add(model);
                    }

                     else if (favoritos==1)
                     {
                         Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, idlink, link, preparacion, duration);
                         list.add(model);
                     }
                }
            else if (tipo == 2) {

                if(favoritos ==0) {

                    Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, idlink, link, preparacion, duration);

                    list.add(model);
                    }

                else if(favoritos == 1 ){
                    Model model = new Model(id, descripcion, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, idlink, link, preparacion, duration);
                    list.add(model);

                }



                }



        }




        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);
*/

    }




    public class JsonTaskejerciciosfrist extends AsyncTask<String, String, ArrayList<Model>> {

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
                    //tvjsom.setText(buffer.toString());
                }

                //        Log.v("buffer", buffer.toString());
//                Log.v("que es line", line);

                String finaljson = buffer.toString();

                JSONObject parentjson = new JSONObject(finaljson);

                //Log.v("json", parentjson.getString("RestResponse"));
                //Log.v("json", parentjson.getJSONObject("RestResponse").getString("result"));

                JSONArray jsarreglo = parentjson.getJSONArray("DATA");


                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);

                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");



               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/


                DBHome db = new DBHome(MainActivity_Ejercicios.this, name_database_elite, null, 1);

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

                    //JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ j++) {

                        JSONObject object = jsonArrayvideo.getJSONObject(j);

                        video = object.getString("ELEV_YOUTUBE_CLAVE");

                        break;
                    }


                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    //JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {

                        JSONObject object = jsonArrayimagen.getJSONObject(x);

                        imagen = object.getString("NOMBRE");



                        break;



                    }


                    Log.v("imagen", ""+imagen);
                    Log.v("video", ""+video);


                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //if (tipo_Nota.equals("RECETA"))
                    // {
                    nota = 2;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }



                    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                    Log.v("id nota", ""+nota_id);
                    //Log.v("tipo nota", ""+tipo_Nota);
                    fila = database.rawQuery(query, null);

                    if(fila.moveToFirst())
                    {
                        int contador = fila.getInt(0);

                        Log.v("contador", ""+contador);

                        if(contador == 1)
                        {

                        }

                        else {

                            contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 2);
                            // }
                            //else  if (tipo_Nota.equals("EJERCICIO"))
                            //{
                            //  contentValues.put("id_tipo", 2);
                            //}




                            if (!video.equals(""))
                            {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            }
                            else if (!imagen.equals(""))
                            {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/ejercicios/"+imagen);

                            }







                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", /*"http://www.papeleselite.mx/EJERCICIO/"+*/permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);



                            database.insert("contenido", null, contentValues);


                        }


                    }


                }

                return list;





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
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




            return null;//"entro en esta condicion";
        }


        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);

            //  MainActivity_prueba_json.MovieAdapter movieAdapter = new MainActivity_prueba_json.MovieAdapter(getApplicationContext(), R.layout.item_json, result);
            //   listView.setAdapter(movieAdapter);

            /*adapter_jason_contry adapter_json = new adapter_jason_contry(result, Main_jason_rest.this);



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter_json);*/


            //textprueba.setText(result);
            //Log.v("json datos", result);




            DBHome dbHome = new DBHome(MainActivity_Ejercicios.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 2 order by fecha_publicacion desc ";

            fila = sqLiteDatabase.rawQuery(query, null);

            list= new ArrayList<>();




            while (fila.moveToNext())
            {
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
                // Log.v("fecha", fecha);

                if (tipo == 1)
                {

                    if(favor== 0) {


                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }

                    else if(favor ==1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }

                else if(tipo == 2)
                {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }

                    else if (favor == 1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }





            }

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutejercicios);

            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_Ejercicios.this, mRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Ejercicios.this, OrientationHelper.VERTICAL, false);


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
                    //tvjsom.setText(buffer.toString());
                }

                //        Log.v("buffer", buffer.toString());
//                Log.v("que es line", line);

                String finaljson = buffer.toString();

                JSONObject parentjson = new JSONObject(finaljson);

                //Log.v("json", parentjson.getString("RestResponse"));
                //Log.v("json", parentjson.getJSONObject("RestResponse").getString("result"));

                JSONArray jsarreglo = parentjson.getJSONArray("DATA");


                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);

                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");



               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/


                DBHome db = new DBHome(MainActivity_Ejercicios.this, name_database_elite, null, 1);

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

                    //JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ j++) {

                        JSONObject object = jsonArrayvideo.getJSONObject(j);

                        video = object.getString("ELEV_YOUTUBE_CLAVE");

                        break;
                    }


                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    //JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {

                        JSONObject object = jsonArrayimagen.getJSONObject(x);

                        imagen = object.getString("NOMBRE");



                        break;



                    }


                    Log.v("imagen", ""+imagen);
                    Log.v("video", ""+video);


                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //  if (tipo_Nota.equals("RECETA"))
                    // {
                    nota = 2;
                    //}
                    //else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //   nota = 2;
                    //}



                    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                    Log.v("id nota", ""+nota_id);
                    // Log.v("tipo nota", ""+tipo_Nota);
                    fila = database.rawQuery(query, null);

                    if(fila.moveToFirst())
                    {
                        int contador = fila.getInt(0);

                        Log.v("contador", ""+contador);

                        if(contador == 1)
                        {

                        }

                        else {

                            contentValues.put("id", nota_id);

                            // if (tipo_Nota.equals("RECETA"))
                            //{
                            contentValues.put("id_tipo", 2);
                            //}
                            //else  if (tipo_Nota.equals("EJERCICIO"))
                            //{
                            //  contentValues.put("id_tipo", 2);
                            //}




                            if (!video.equals(""))
                            {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            }
                            else if (!imagen.equals(""))
                            {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/ejercicios/"+imagen);

                            }







                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", /*"http://www.papeleselite.mx/EJERCICIO/"+*/permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);



                            database.insert("contenido", null, contentValues);


                        }


                    }


                }

                return list;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
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



            return null;//"entro en esta condicion";
        }


        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);




            DBHome dbHome = new DBHome(MainActivity_Ejercicios.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  and contenido.id_tipo = 2   order by fecha_publicacion desc ";

            fila = sqLiteDatabase.rawQuery(query, null);

            list= new ArrayList<>();




            while (fila.moveToNext())
            {
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
                // Log.v("fecha", fecha);

                if (tipo == 1)
                {

                    if(favor== 0) {


                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }

                    else if(favor ==1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }

                else if(tipo == 2)
                {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }

                    else if (favor == 1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }





            }


            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutejercicios);
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_Ejercicios.this, mRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Ejercicios.this, OrientationHelper.VERTICAL, false);


            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);






        }








    }











    public  String contex(){
        context = this.getClass().getSimpleName();
        return  context;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
        {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {

        Intent intent = new Intent(this, Main_Activity_inicio.class);
        startActivity(intent);


        //onBackPressed();

        return false;
    }
}
