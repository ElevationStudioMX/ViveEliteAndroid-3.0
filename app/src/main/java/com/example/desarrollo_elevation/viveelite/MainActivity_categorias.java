package com.example.desarrollo_elevation.viveelite;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.example.desarrollo_elevation.viveelite.Adapter.Model_categorias;
import com.example.desarrollo_elevation.viveelite.Adapter.categoria_adapter;
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

public class  MainActivity_categorias extends AppCompatActivity {

    private  static  String name_database_elite= "elite_v15";
    Toolbar toolbar;
   private static ArrayList<Model_categorias> list;
    private  static String imagen;
    private  static Cursor fila;
    private  static String link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/recetas-categorias";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_categorias);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = (Toolbar) findViewById(R.id.toolbarcategorias);
        toolbar.setTitle("Recetas");
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

/*
        categoria_adapter adapter = new categoria_adapter(list , this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.layoutcategorias);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

*/

new JsonTaskcategoria().execute(link);







    }



    public class JsonTaskcategoria extends AsyncTask<String, Integer,ArrayList<Model>> {

      //  int progreso;

        @Override
        protected void onPreExecute() {


            //progreso = 0;
           /* setProgressBarIndeterminateVisibility(true);
            progressDialog = ProgressDialog.show(MainActivity_categorias.this,
                    "Loading", "Por favor espere unos segunodo!");*/

            DBHome dbHome = new DBHome(MainActivity_categorias.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select id_categoria_receta, nombre, imagen from categoria_receta ";

            fila = sqLiteDatabase.rawQuery(query, null);

            //list= new ArrayList<>();




            while (fila.moveToNext())
            {
                int id_categoria = fila.getInt(0);
                String nombre = fila.getString(1);
                String imagen = fila.getString(2);

                Log.v("nombre", nombre);
                Log.v("imagen", imagen);


                Model_categorias model = new Model_categorias(1, id_categoria , imagen, nombre );



                list.add(model);







            }


            categoria_adapter adapter = new categoria_adapter(list , MainActivity_categorias.this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity_categorias.this, 2);
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.layoutcategorias);

            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);






        }


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


                DBHome db = new DBHome(MainActivity_categorias.this, name_database_elite, null, 1);

                SQLiteDatabase database = db.getWritableDatabase();

                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



                String tiempo = String.valueOf(tiem.format(sq));

                ContentValues contentValues = new ContentValues();

                ArrayList<Model> list = new ArrayList<>();


                for (int i = 0; i < jsarreglo.length(); i++) {
                   // video = "";
                    imagen = "";
                   // nota = 0;

                    JSONObject ob = jsarreglo.getJSONObject(i);



                    int receta_categoria_id = ob.getInt("RECETA_CATEGORIA_ID");
                    // String tipo_Nota = ob.getString("TIPO_NOTA");
                    String nombre = ob.getString("NOMBRE");
                    String descripcion = ob.getString("DESCRIPCION");
                    /*String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");

                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    //JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ //j++) {

                       /* JSONObject object = jsonArrayvideo.getJSONObject(j);

                        video = object.getString("ELEV_YOUTUBE_CLAVE");

                        break;
                    }*/


                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    //JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {

                        JSONObject object = jsonArrayimagen.getJSONObject(x);

                        imagen = object.getString("NOMBRE");



                        break;



                    }


                    //Log.v("imagen", ""+imagen);
                   // Log.v("video", ""+video);


                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //if (tipo_Nota.equals("RECETA"))
                    // {
                    //nota = 1;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }



                    String query = "select count(*) from categoria_receta  where  id_categoria_receta="+receta_categoria_id+"";

                    //Log.v("id nota", ""+nota_id);
                    //Log.v("tipo nota", ""+tipo_Nota);
                    fila = database.rawQuery(query, null);

                    if(fila.moveToFirst())
                    {
                        int contador = fila.getInt(0);

                        Log.v("contador", ""+contador);

                        if(contador == 1)
                        {
                            contentValues.put("nombre", nombre);
                            contentValues.put("descripcion", descripcion);
                            contentValues.put("imagen", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas_categorias/"+imagen);

                            database.update("categoria_receta", contentValues, "id_categoria_receta="+receta_categoria_id+"", null);

                        }

                        else {

                           // contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                         //   contentValues.put("id_tipo", 1);
                            // }
                            //else  if (tipo_Nota.equals("EJERCICIO"))
                            //{
                            //  contentValues.put("id_tipo", 2);
                            //}




                     /*       if (!video.equals(""))
                            {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            }
                            else if (!imagen.equals(""))
                            {
                                contentValues.put("id_tipo_contenido", 1);


                            }*/







                            contentValues.put("id_categoria_receta", receta_categoria_id);
                            contentValues.put("nombre", nombre);
                            contentValues.put("descripcion", descripcion);
                            contentValues.put("imagen", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas_categorias/"+imagen);
                            //contentValues.put("influencer", influencer);
                            //contentValues.put("permarlink", /*"http://www.papeleselite.mx/RECETA/"+*/permalink);
                            /*contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);
*/


                            database.insert("categoria_receta", null, contentValues);


                        }


                    }


                }


                /*while(progreso < 100){
                    progreso++;
                    publishProgress(progreso);
                    SystemClock.sleep(20);
                }*/

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


       /* @Override
        protected void onProgressUpdate(Integer... values) {

           progressDialog.setProgress(values[0]);

        }*/



        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);


            //setProgressBarIndeterminateVisibility(true);
          //  progressDialog.dismiss();

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










           /* MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);

            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);*/








        }

    }










    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, Main_Activity_inicio.class);
        startActivity(intent);

        // onBackPressed();
        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
        {
            Intent i  = new Intent(MainActivity_categorias.this, Main_Activity_inicio.class);
            startActivity(i);
        }

        return super.onKeyDown(keyCode, event);
    }
}
