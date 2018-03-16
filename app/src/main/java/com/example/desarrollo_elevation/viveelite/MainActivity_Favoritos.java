package com.example.desarrollo_elevation.viveelite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.DB.DBHome;

import java.util.ArrayList;


public class MainActivity_Favoritos extends AppCompatActivity {

    private Cursor cursor;
    private ArrayList<Model> models;
    private static String context;
    TextView texto;
    private  static  String name_database_elite= "elite_v15";
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main__favoritos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfavoritos);
        toolbar.setTitle("Favoritos");
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    //   new AsyncTask_load().execute();


        String font = "fonts/OpenSans-Light.ttf";
        texto = (TextView)findViewById(R.id.texto_favorito);
        Typeface typeface = Typeface.createFromAsset(MainActivity_Favoritos.this.getAssets(), font);
        texto.setTypeface(typeface);
        texto.setTextSize(16);



        DBHome dbhome = new DBHome(MainActivity_Favoritos.this, name_database_elite, null, 1);

        SQLiteDatabase sqLiteDatabase = dbhome.getWritableDatabase();

        String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and favoritos=1 order by fecha_favoritos desc ";


        models = new ArrayList<>();

        cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            int id_tipo_contenido  = cursor.getInt(0);
            String descrption = cursor.getString(1);
            String titulo = cursor.getString(2);
            String contenido = cursor.getString(3);
            int tipo_contenido = cursor.getInt(4);
            String id = cursor.getString(5);
            String link = cursor.getString(6);
            String preparacion = cursor.getString(7);
            String duration = cursor.getString(8);

            if(tipo_contenido == 1)
            {

                Model model = new Model(id_tipo_contenido, descrption, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duration, tipo_contenido);
                models.add(model);

            }

            else if(tipo_contenido == 2){

                Model model = new Model(id_tipo_contenido, descrption, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duration, tipo_contenido);
                models.add(model);
            }

        }

        if(models.size() == 0)
        {
            texto.setVisibility(View.VISIBLE);
            texto.setText("Aún no haz agregado favoritos, presiona el corazón en los posts para marcarlos como favoritos y acceder a ellos fácilmente.");


        }

        else {
            texto.setVisibility(View.INVISIBLE);
        }



        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewerfavoritos);
        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(models, MainActivity_Favoritos.this, recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Favoritos.this);



        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);






    }






  /*  public class AsyncTask_load extends AsyncTask<Void, Integer, Void> {

        int progreso;


        @Override
        protected void onPreExecute() {


            progreso = 0;
            setProgressBarIndeterminateVisibility(true);
            progressDialog = ProgressDialog.show(MainActivity_Favoritos.this,
                    "Cargando", "Por favor espera unos segundos");







        }

        @Override
        protected Void doInBackground(Void... params) {

            while(progreso < 100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(20);
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {


            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {


            String font = "fonts/OpenSans-Light.ttf";
            texto = (TextView)findViewById(R.id.texto_favorito);
            Typeface typeface = Typeface.createFromAsset(MainActivity_Favoritos.this.getAssets(), font);
            texto.setTypeface(typeface);
            texto.setTextSize(16);



            DBHome dbhome = new DBHome(MainActivity_Favoritos.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbhome.getWritableDatabase();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and favoritos=1 order by fecha_favoritos desc ";


            models = new ArrayList<>();

            cursor = sqLiteDatabase.rawQuery(query, null);

            while (cursor.moveToNext())
            {
                int id_tipo_contenido  = cursor.getInt(0);
                String descrption = cursor.getString(1);
                String titulo = cursor.getString(2);
                String contenido = cursor.getString(3);
                int tipo_contenido = cursor.getInt(4);
                String id = cursor.getString(5);
                String link = cursor.getString(6);
                String preparacion = cursor.getString(7);
                String duration = cursor.getString(8);

                if(tipo_contenido == 1)
                {

                    Model model = new Model(id_tipo_contenido, descrption, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duration, tipo_contenido);
                    models.add(model);

                }

                else if(tipo_contenido == 2){

                    Model model = new Model(id_tipo_contenido, descrption, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duration, tipo_contenido);
                    models.add(model);
                }

            }

            if(models.size() == 0)
            {
                texto.setVisibility(View.VISIBLE);
                texto.setText("Aún no haz agregado favoritos, presiona el corazón en los posts para marcarlos como favoritos y acceder a ellos fácilmente.");


            }

            else {
                texto.setVisibility(View.INVISIBLE);
            }

            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewerfavoritos);
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(models, MainActivity_Favoritos.this, recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Favoritos.this);



            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);


            progressDialog.dismiss();
        }


    }*/









    @Override
    public boolean onSupportNavigateUp() {

        Intent intent = new Intent(this, Main_Activity_inicio.class);
        startActivity(intent);


        //onBackPressed();

        return false;
    }


public String contex(){

    context = this.getClass().getSimpleName();
    return context;

}


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
    {
        Intent intent = new Intent(MainActivity_Favoritos.this, Main_Activity_inicio.class);
        startActivity(intent);
    }

        return super.onKeyDown(keyCode, event);
    }
}
