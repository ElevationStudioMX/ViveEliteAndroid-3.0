package com.example.desarrollo_elevation.viveelite;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.Adapter.Adapter_ingredientes;
import com.example.desarrollo_elevation.viveelite.Adapter.Model_Ingredientes;
import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity_contenidoreceta extends AppCompatActivity {
    TextView text;
    TextView titulo;
    TextView subtitulo;
    TextView prepa;
    TextView dura;
    TextView durateiemp;
    ImageButton favoritos;
    Button compartir;
    Button receta;
    ImageView image_dv;
    DBHome dbHome1;
    SQLiteDatabase ds;
     static int ider =0;
    private Cursor cursor, cursorer;
    static  String datos[]= new String[1000];
    ArrayList<HashMap<String, String>> maplist = new ArrayList<>();
    //private static ArrayList<Model_Ingredientes> listados;
    private static String despliegue;
    private  static  String name_database_elite= "elite_v15";
    private static String video;
    private static String imagen;
    private static  int nota;
    private  static Cursor fila;
    private  static  Cursor filatiem;
    private  static Cursor fila_tipo;
    private static  String eleve_tiem;
    private static ArrayList<Model_Ingredientes> listado;
    private static String linkreceta;
    private static String linkejercicios;
    private static  String canidad_anidada;
    private static String unidad_anidada;
    private  static  String nombre_tipo;
    private  static  int id_ingrediente;
    private  static  int id_tipo_contenido;
    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contenidoreceta);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbareceta);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        text = findViewById(R.id.textView3);
        titulo = findViewById(R.id.txtTitulo);
        subtitulo = findViewById(R.id.txtsutitulo);
        dura = findViewById(R.id.txtduraciones);
        durateiemp = findViewById(R.id.txtduraciontiemp);
        prepa = findViewById(R.id.txtPreparacion);
        favoritos = findViewById(R.id.btnfavoritosreceta);
        compartir = findViewById(R.id.btncompartirreceta);
        receta = findViewById(R.id.btncontenidoreceta);
        image_dv = findViewById(R.id.imgrecetas);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String font = "fonts/OpenSans-Light.ttf";
        String font1 = "fonts/OpenSans-Bold.ttf";
        String font2 = "fonts/OpenSans-Regular.ttf";
        String font3 = "fonts/Gotham-Medium.ttf";

        final Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), font);

        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), font2);
        Typeface typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), font1);
        Typeface typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), font3);
        receta.setTypeface(typeface2);
        receta.setTextSize(22);
        titulo.setTypeface(typeface1);
        subtitulo.setTypeface(typeface);
        subtitulo.setTextSize(22);
        prepa.setText("\nPreparación:");
        prepa.setTypeface(typeface);
        prepa.setTextSize(22);
        dura.setTypeface(typeface);
        dura.setTextSize(22);
        durateiemp.setTextSize(20);
        durateiemp.setTypeface(face);
        titulo.setTextSize(28);
        receta.getBackground().setAlpha(00);
        text.setTextSize(18);
        text.setTypeface(face);
        text.setMovementMethod(new ScrollingMovementMethod());

        Intent appLinkIntent = getIntent();
        final String appLinkAction = appLinkIntent.getAction();
        final Uri appLinkData = appLinkIntent.getData();

        if(Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {
            final String appp = appLinkData.getLastPathSegment();
            Log.v("estos datos a mostrar", appLinkData +" "+ appp);
            DBHome dbHome = new DBHome(this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            String sqltiem = "select id_tipo from contenido where permarlink='"+appp+"' ";

            filatiem = sqLiteDatabase.rawQuery(sqltiem, null);

            if (filatiem.moveToFirst() ) {
                id_tipo_contenido = filatiem.getInt(0);
            }

            if(id_tipo_contenido == 1) {
                Log.v("paso por aqui", "recetas");
                subtitulo.setText("Ingredientes: ");
                linkreceta = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/receta-permalink/"+appp+"";
                new JsonTaskrecetaspermarlink().execute(linkreceta);
            }

            else if(id_tipo_contenido == 2){
            //    Log.v("tiempo en ejercicio", eleve_tiem);
                Log.v("paso por aqui", "ejercicios");
                linkejercicios = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/ejercicio-permalink/"+appp+"";
                new JsonTaskejerciciospermarlink().execute(linkejercicios);
                receta.setText("E J E R C I C I O S");
                receta.setTextColor(Color.parseColor("#6dd8d8"));
                Drawable img = getApplicationContext().getResources().getDrawable(R.mipmap.imgejercicio);
                img.setBounds(0,0,60,60);
                receta.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                subtitulo.setTextSize(18);
                subtitulo.setPadding(10,0,0,0);
                subtitulo.setTypeface(face);
            }
            String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '" + appp + "'";
            cursor = sqLiteDatabase.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                int far = Integer.parseInt(cursor.getString(0));
                if (far == 0) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos);
                } else if (far == 1) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos_check_red);
                }
            }

            favoritos.setOnClickListener(view -> {
                dbHome1 = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                ds = dbHome1.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                String sqlite = "select favoritos from contenido where permarlink='" + appp/*titulo.getText().toString()*/ + "'";
                ContentValues values = new ContentValues();
                cursorer = ds.rawQuery(sqlite, null);

                if (cursorer.moveToFirst()) {
                    int favor = Integer.parseInt(cursorer.getString(0));
                    Log.v("favorito", "" + favor);

                    if (favor == 0) {
                        values.put("favoritos", "1");
                        values.put("fecha_favoritos", tiempo);
                        ds.update("contenido", values, "permarlink='" + appp + "'", null);
                        favoritos.setBackgroundResource(R.mipmap.favoritos_check_red);
                        ds.close();
                    } else if (favor == 1) {
                        values.put("favoritos", "0");
                        values.put("fecha_favoritos", tiempo);
                        //ds.update("contenido", values, "titulo='"+titulo.getText().toString()+"'", null);
                        ds.update("contenido", values, "permarlink='" + appp + "'", null);
                        favoritos.setBackgroundResource(R.mipmap.favoritos);
                        ds.close();
                    }
                }
            });

            compartir.setOnClickListener(view -> {
                try {
                    String direccion = String.valueOf(appLinkData);
                  Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, direccion/*titulo.getText().toString()*/);
                    intent.putExtra(Intent.EXTRA_SUBJECT, titulo.getText().toString());
                    intent.putExtra(Intent.EXTRA_TITLE, titulo.getText().toString());
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "Compartir con"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        else {
            Bundle bundle = getIntent().getExtras();
            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");
            final int id_tipo = bundle.getInt("id_tipo");
            final DBHome dbHome = new DBHome(this, name_database_elite, null, 1);
            final SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            int idobtenido = id;
            String sqltiem = "select ELEV_TIMESTAMP from contenido where id="+idobtenido+" and id_tipo="+id_tipo+"";
            filatiem = sqLiteDatabase.rawQuery(sqltiem, null);
            if (filatiem.moveToFirst() ) {
                eleve_tiem = filatiem.getString(0);
            }

           if (eleve_tiem == null)
           {
                eleve_tiem ="0000-00-00 00:00:00";
            }
            if(id_tipo == 1) {
                Log.v("tiempo en recetas", eleve_tiem);
                Log.v("paso por aqui", "recetas");
                subtitulo.setText("Ingredientes: ");
                linkreceta = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/receta/" + idobtenido + "/"+eleve_tiem+"";
                new JsonTaskrecetas().execute(linkreceta);
            }

            else if(id_tipo == 2){
              Log.v("tiempo en ejercicio", eleve_tiem);
                Log.v("paso por aqui", "ejercicios");
                linkejercicios = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/ejercicio/" + idobtenido + "/"+eleve_tiem+"";
                new JsonTaskejercicios().execute(linkejercicios);
                receta.setText("E J E R C I C I O S");
                receta.setTextColor(Color.parseColor("#6dd8d8"));
                Drawable img = getApplicationContext().getResources().getDrawable(R.mipmap.imgejercicio);
                img.setBounds(0,0,60,60);
                receta.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                subtitulo.setTextSize(18);
                subtitulo.setTypeface(face);
            }

            String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = " + id + " and contenido.id_tipo="+id_tipo+"";
            cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                int far = Integer.parseInt(cursor.getString(0));

                if (far == 0) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos);
                } else if (far == 1) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos_check_red);
                }
            }
            TextView textext = new TextView(this);
            textext.setTypeface(typeface);
            textext.setTextSize(20);
            textext.setText("Preparación");
            textext.setTextColor(Color.parseColor("#5b6065"));
            SpannableString SS = new SpannableString("preparacion");
            SS.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);

            favoritos.setOnClickListener(view -> {
                dbHome1 = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                ds = dbHome1.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
               String sql1 = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.titulo = '"+titulo.getText().toString()+"'";
               String sqlite = "select favoritos from contenido where id=" + id/*titulo.getText().toString()*/ + " and id_tipo="+id_tipo+"";
                ContentValues values = new ContentValues();
               cursorer = ds.rawQuery(sqlite, null);


                if (cursorer.moveToFirst())

                {
                    int favor = Integer.parseInt(cursorer.getString(0));
                    Log.v("favorito", "" + favor);

                    if (favor == 0) {


                        values.put("favoritos", "1");
                        values.put("fecha_favoritos", tiempo);
                        ds.update("contenido", values, "id=" + id + " and id_tipo="+id_tipo+"", null);
                        favoritos.setBackgroundResource(R.mipmap.favoritos_check_red);
                        ds.close();
                    } else if (favor == 1) {

                        //   values = new ContentValues();
                        values.put("favoritos", "0");
                        values.put("fecha_favoritos", tiempo);
                        //ds.update("contenido", values, "titulo='"+titulo.getText().toString()+"'", null);
                        ds.update("contenido", values, "id=" + id + " and id_tipo="+id_tipo+" ", null);
                        favoritos.setBackgroundResource(R.mipmap.favoritos);
                        ds.close();
                    }
                }
            });

            compartir.setOnClickListener(view -> {
                if(id_tipo == 1)
                {
                    nombre_tipo="receta";
                }

                else if(id_tipo == 2)
                {
                    nombre_tipo="ejercicio";
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);

                    intent.putExtra(Intent.EXTRA_TEXT, "http://www.papeleselite.mx/"+nombre_tipo+"/"+link/*titulo.getText().toString()*/);
                    intent.putExtra(Intent.EXTRA_SUBJECT, titulo.getText().toString());
                    intent.putExtra(Intent.EXTRA_TITLE, titulo.getText().toString());
                    //intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "Compartir con"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public class JsonTaskrecetas extends AsyncTask<String, String, ArrayList<Model>> {
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
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                DBHome db = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
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
                    String preparacion = ob.getString("PREPARACION");
                    String duracion = ob.getString("DURACION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");


                    JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");

                    for (int l = 0; l < jsonArrayingredientes.length();/*.length();*/ l++) {

                        canidad_anidada = "";
                        unidad_anidada= "";

                        JSONObject object = jsonArrayingredientes.getJSONObject(l);
                        int id_ingrediente = object.getInt("INGREDIENTE_ID");
                        String cantidad = object.getString("CANTIDAD");
                        String unidad = object.getString("UNIDAD");
                        String ingrediente = object.getString("INGREDIENTE");

                        if(cantidad.equals("null"))
                        {

                            canidad_anidada= "";
                        }

                        else{
                            canidad_anidada = cantidad;
                        }

                        if (unidad.equals("null"))
                        {
                            unidad_anidada ="";

                        }

                        else{
                            unidad_anidada = unidad;
                        }
                        String query = "select count(*) from ingredientes where ingrediente_id="+id_ingrediente +" and id ="+nota_id+"";
                        fila = database.rawQuery(query, null);

                        if(fila.moveToFirst())
                        {
                            int contador = fila.getInt(0);

                            Log.v("contador", ""+contador);

                            if(contador == 1)
                            {
                                if(canidad_anidada.equals(""))
                                {

                                    contentingredientes.put("cantidad", canidad_anidada+unidad_anidada);
                                }

                                else{
                                    contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                }

                                if (unidad_anidada.equals(""))
                                {
                                    contentingredientes.put("cantidad", canidad_anidada+unidad_anidada);
                                }

                                else{
                                    contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                }

                                if(canidad_anidada.equals("") || unidad_anidada.equals(""))
                                {
                                    contentingredientes.put("cantidad", "");
                                }

                                contentingredientes.put("ingrediente", ingrediente);
                                database.update("ingredientes", contentingredientes, "ingrediente_id="+id_ingrediente +" and id ="+nota_id+"",null);
                            }  else {
                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.insert("ingredientes", null, contentingredientes);
                            }
                        }
                    }
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ j++) {
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
                    nota = 1;

                    if (!video.equals(""))
                    {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    }
                    else if (!imagen.equals(""))
                    {
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
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/RECETA/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("preparacion", preparacion);
                    contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
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
            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            Bundle bundle = getIntent().getExtras();
            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");
            String query = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and  contenido.id = "+id+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1";
            fila = sqLiteDatabase.rawQuery(query, null);


            if (fila.moveToFirst()) {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String preparaciones = fila.getString(2);
                String duraciones = fila.getString(3);
                Log.v("preparacion comida", preparaciones);

                getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                titulo.setText(titulos);

                Bitmap bmp = null;
                try {
                   Picasso.with(MainActivity_contenidoreceta.this).load(contenido).into(new Target() {
                       @Override
                       public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                           image_dv.setImageBitmap(bitmap);
                           DisplayMetrics displayMetrics = new DisplayMetrics();
                           getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                           int height = displayMetrics.heightPixels;
                           int width = displayMetrics.widthPixels;
                           int alto = bitmap.getHeight();
                           int ancho = bitmap.getWidth();
                           float razon =  (float)alto/ancho;
                           float nuevo_alto = razon*width;
                           image_dv.getLayoutParams().height = (int)nuevo_alto;
                       }

                       @Override
                       public void onBitmapFailed(Drawable errorDrawable) {
                       }

                       @Override
                       public void onPrepareLoad(Drawable placeHolderDrawable) {
                       }
                   });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                text.setText(preparaciones+"\n");
                durateiemp.setText(duraciones+"\n\n");
                prepa.setVisibility(View.VISIBLE);
                dura.setVisibility(View.VISIBLE);
                subtitulo.setVisibility(View.VISIBLE);
                durateiemp.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
            }

            String sqlingredientes ="select ingredientes.cantidad, ingredientes.ingrediente from ingredientes, contenido where contenido.id = ingredientes.id and contenido.id = "+id+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1 order by ingrediente_id";
            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);
            listado = new ArrayList<>();
            while (cursor.moveToNext()) {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);
                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_contenidoreceta.this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, MainActivity_contenidoreceta.this);
            RecyclerView recyclerView = findViewById(R.id.recicleviweringredientes);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
        }
    }

    public class JsonTaskejercicios extends AsyncTask<String, String, ArrayList<Model>> {

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

                DBHome db = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
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
                    String descripcion_ejercicio =ob.getString("DESCRIPCION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");
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
                    nota = 2;

                    if (!video.equals(""))
                    {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    }
                    else if (!imagen.equals("")) {
                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/ejercicios/"+imagen);

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
                    contentValues.put("descripcion_ejercicio", descripcion_ejercicio);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
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
            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            Bundle bundle = getIntent().getExtras();
            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");
            String query = "select contenido.titulo, contenido.contenido, contenido.descripcion_ejercicio from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+id+" and contenido.id_tipo = 2 and contenido.id_tipo_contenido = 1";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String descripcion_ejercicio = fila.getString(2);
                getSupportActionBar().setTitle(titulos);
                titulo.setText(titulos);
                Picasso.with(MainActivity_contenidoreceta.this).load(contenido).resize(1000, 0).into(image_dv);
                subtitulo.setText(descripcion_ejercicio+"\n");
            }
        }
    }

    public class JsonTaskrecetaspermarlink extends AsyncTask<String, String, ArrayList<Model>> {

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

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);
                DBHome db = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();

                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();

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
                    String preparacion = ob.getString("PREPARACION");
                    String duracion = ob.getString("DURACION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");
                    JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");

                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {
                        canidad_anidada = "";
                        unidad_anidada= "";
                        JSONObject object = jsonArrayingredientes.getJSONObject(l);
                        int id_ingrediente = object.getInt("INGREDIENTE_ID");
                        String cantidad = object.getString("CANTIDAD");
                        String unidad = object.getString("UNIDAD");
                        String ingrediente = object.getString("INGREDIENTE");

                        if(cantidad.equals("null")) {
                            canidad_anidada= "";
                        }

                        else{
                            canidad_anidada = cantidad;
                        }

                        if (unidad.equals("null")) {
                            unidad_anidada ="";

                        } else {
                            unidad_anidada = unidad;
                        }
                        String query = "select count(*) from ingredientes where ingrediente_id="+id_ingrediente +" and id ="+nota_id+"";
                        fila = database.rawQuery(query, null);

                        if(fila.moveToFirst()) {
                            int contador = fila.getInt(0);
                            Log.v("contador", ""+contador);

                            if(contador == 1) {
                                contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.update("ingredientes", contentingredientes, "ingrediente_id="+id_ingrediente +" and id ="+nota_id+"",null);

                            } else {
                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.insert("ingredientes", null, contentingredientes);
                            }
                        }
                    }
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ j++) {
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

                    if (!video.equals("")) {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    }
                    else if (!imagen.equals(""))
                    {
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
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/RECETA/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("preparacion", preparacion);
                    contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
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
            return null;//"entro en esta condicion";
        }


        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            Intent appLinkIntent = getIntent();
            final String appLinkAction = appLinkIntent.getAction();
            final Uri appLinkData = appLinkIntent.getData();
            final String appp = appLinkData.getLastPathSegment();
            String query = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion, contenido.id from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '"+appp+"' and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String preparaciones = fila.getString(2);
                String duraciones = fila.getString(3);
                int id_contenido = fila.getInt(4);
                Log.v("preparacion comida", preparaciones);

                getSupportActionBar().setTitle(titulos);
                titulo.setText(titulos);

                Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        image_dv.setImageBitmap(bitmap);
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        int height = displayMetrics.heightPixels;
                        int width = displayMetrics.widthPixels;
                        int alto = bitmap.getHeight();
                        int ancho = bitmap.getWidth();
                        float razon =  (float)alto/ancho;
                        float nuevo_alto = razon*width;
                        image_dv.getLayoutParams().height = (int)nuevo_alto;
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                text.setText(preparaciones+"\n"/*, true*/);
                durateiemp.setText(duraciones+"\n\n");

                prepa.setVisibility(View.VISIBLE);
                dura.setVisibility(View.VISIBLE);
                subtitulo.setVisibility(View.VISIBLE);
                durateiemp.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                id_ingrediente = id_contenido;
            }
            String sqlingredientes ="select cantidad, ingrediente from ingredientes, contenido where contenido.id = ingredientes.id and ingredientes.id="+id_ingrediente+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1  order by ingrediente_id";
            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);
            listado = new ArrayList<>();

            while (cursor.moveToNext())
            {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);

                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);

            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_contenidoreceta.this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, MainActivity_contenidoreceta.this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviweringredientes);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
        }
    }

    public class JsonTaskejerciciospermarlink extends AsyncTask<String, String, ArrayList<Model>> {
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
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);
                DBHome db = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
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
                    String descripcion_ejercicio =ob.getString("DESCRIPCION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");
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
                    nota = 2;

                    if (!video.equals("")) {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    }
                    else if (!imagen.equals("")) {
                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/ejercicios/"+imagen);
                    } else {

                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/no-imagen.jpg"/*"http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen*/);
                    }

                    contentValues.put("titulo", titulo);
                    contentValues.put("descripcion", descripcion_corta);
                    contentValues.put("categoria", categoria);
                    contentValues.put("influencer", influencer);
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/ejercicios/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("descripcion_ejercicio", descripcion_ejercicio);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
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
            return null;//"entro en esta condicion";
        }


        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            Intent appLinkIntent = getIntent();
            final String appLinkAction = appLinkIntent.getAction();
            final Uri appLinkData = appLinkIntent.getData();
            final String appp = appLinkData.getLastPathSegment();
            String query = "select contenido.titulo, contenido.contenido, contenido.descripcion_ejercicio from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '"+appp+"' and contenido.id_tipo = 2 and contenido.id_tipo_contenido = 1";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String descripcion_ejercicio = fila.getString(2);
                getSupportActionBar().setTitle(titulos);
                titulo.setText(titulos);
                Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 0).into(image_dv);
                subtitulo.setText(descripcion_ejercicio+"\n");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
    {
        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
        {
            Intent intent = new Intent(this, MainActivity_menutolbbed.class);
            startActivity(intent);
        } else {

        int ruta = bundle.getInt("contextreceta");

        if(ruta == 1) {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
        }
       else if(ruta == 2) {
            int id_cate = bundle.getInt("id_categoria");
            String bund_name = bundle.getString("nombrecategoria");
            Intent intent = new Intent(this, MainActivity_recetas.class);
            Bundle bundle1 = new Bundle();
            bundle1.putInt("id_categoria", id_cate);
            bundle1.putString("nombrecategoria", bund_name);
            intent.putExtras(bundle1);
            startActivity(intent);
        }

        else if (ruta == 3)
        {
            Intent intent = new Intent(this, MainActivity_Favoritos.class);
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
        }
        }
    }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
        {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
        }

        else{
        int ruta = bundle.getInt("contextreceta");

        if(ruta == 1) {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
         //   overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);
        }

        else if(ruta == 2) {
            Intent intent = new Intent(this, MainActivity_recetas.class);

            int id_cate = bundle.getInt("id_categoria");
            String bund_name = bundle.getString("nombrecategoria");

            //Intent intent = new Intent(this, MainActivity_recetas.class);
            Bundle bundle1 = new Bundle();
            bundle1.putInt("id_categoria", id_cate);
            bundle1.putString("nombrecategoria", bund_name);
            intent.putExtras(bundle1);

            startActivity(intent);
           // overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);
        }

        else if (ruta == 3) {
            Intent intent = new Intent(this, MainActivity_Favoritos.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
        }
        }

        //onBackPressed();
        return true;
    }
}