package com.example.desarrollo_elevation.viveelite;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.vision.text.Text;
import com.example.desarrollo_elevation.viveelite.Adapter.Adapter_ingredientes;
import com.example.desarrollo_elevation.viveelite.Adapter.Model_Ingredientes;
import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.accountType;
import static android.R.attr.bitmap;
import static android.R.attr.id;
import static android.R.attr.imeFullscreenBackground;
import static android.R.attr.value;
import static com.example.desarrollo_elevation.viveelite.R.id.imageView;
import static com.example.desarrollo_elevation.viveelite.R.mipmap.favoritos;
import static com.example.desarrollo_elevation.viveelite.R.mipmap.iconeliminarstik;
import static com.example.desarrollo_elevation.viveelite.R.string.preparacion;
import static java.lang.System.load;
//import static com.example.desarrollo_elevation.viveelite.R.string.preparacion;

public class
MainActivity_contenidoreceta extends AppCompatActivity {
    TextView text;
    TextView titulo;
    TextView subtitulo;
    TextView prepa;
    TextView dura;
    TextView durateiemp;
    ImageButton favoritos;
    Button compartir;
    Button receta;

    ImageView imag;
    DBHome dbHome1;
    SQLiteDatabase ds;
     static int ider =0;
    private Cursor cursor, cursorer;
    static  String datos[]= new String[1000];
    ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
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

   /* TextView textTiutulo;
    TextView textSubtitulo;
    TextView ingrediente;
    TextView preparacion;
    TextView duracion;
    TextView tiempo;*/

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
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        /*mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(150000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();*/




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbareceta);


     //   toolbar.setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titul);
        //android:title="Recetas"
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        text = (TextView) findViewById(R.id.textView3);
        titulo = (TextView) findViewById(R.id.txtTitulo);
        subtitulo = (TextView) findViewById(R.id.txtsutitulo);
        dura =(TextView)findViewById(R.id.txtduraciones);
        durateiemp =(TextView)findViewById(R.id.txtduraciontiemp);
        prepa =(TextView)findViewById(R.id.txtPreparacion);
        favoritos = (ImageButton) findViewById(R.id.btnfavoritosreceta);
        compartir = (Button) findViewById(R.id.btncompartirreceta);
        receta = (Button) findViewById(R.id.btncontenidoreceta);
        imag = (ImageView) findViewById(R.id.imgrecetas);
      //  preparacion = (TextView)findViewById(R.id.preparacion);

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
        //text.setPadding(10,0,0,0);
        text.setTypeface(face);
        text.setMovementMethod(new ScrollingMovementMethod());






        /*Bundle be = getIntent().getExtras();
        String descripcion = be.getString("descripcion");

        text.setText("-140 gr de mantequilla sin sal, a temperatura ambiente"
                + "\n" + "- 1 taza de azùcar"
                + "\n" + "- 1 cucharadita de extracto de vainilla "
                + "\n" + "- 1 clara de huevo grande"
                + "\n" + "- 1 2/3 tazas de harina"
                +"\n\n\n"+"Preparacion:"+"\n"+
                "\n"
                +descripcion/*"Moler los frijoles en la licuadora. Una vez molidos, freír los frijoles con una cucharada de aceite, sazonar con sal y reservar.\n" +
                "En un sartén freír el chorizo con el chile serrano. Una vez frito, agrega el queso para que se funda."*/
               /* +"\n"+
                "\n"+
                "\n"+"Duracion: 30 minutos"
                +"\n"+
                "\n"

        );*/


      /*  preparacion.setText("Moler los frijoles en la licuadora. Una vez molidos, freír los frijoles con una cucharada de aceite, sazonar con sal y reservar.\n" +
                "En un sartén freír el chorizo con el chile serrano. Una vez frito, agrega el queso para que se funda.");*/


        Intent appLinkIntent = getIntent();
        final String appLinkAction = appLinkIntent.getAction();
        final Uri appLinkData = appLinkIntent.getData();

        //String app = appLinkData.getQueryParameter("p");

        if(Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {



            final String appp = appLinkData.getLastPathSegment();

            Log.v("estos datos a mostrar", appLinkData +" "+ appp);

            DBHome dbHome = new DBHome(this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();





           // int idobtenido = id;

            String sqltiem = "select id_tipo from contenido where permarlink='"+appp+"' ";

            filatiem = sqLiteDatabase.rawQuery(sqltiem, null);

            if (filatiem.moveToFirst() )
            {
                id_tipo_contenido = filatiem.getInt(0);
            }

            /*if (eleve_tiem == null)
            {
                eleve_tiem ="0000-00-00 00:00:00";
            }*/




            if(id_tipo_contenido == 1) {

               // Log.v("tiempo en recetas", eleve_tiem);
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

                //textSubtitulo.setVisibility(View.VISIBLE);


            }






            /*String sqlid = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+appp+"";

            cursor = sqLiteDatabase.rawQuery(sqlid, null);

            if(cursor.moveToFirst())
            {
                String title = cursor.getString(0);
                String imagenes = cursor.getString(1);
                String prepa = cursor.getString(2);
                String dura = cursor.getString(3);

                text.setText(prepa+"\n" /*true);/*despliegue+"\n\n"+
            preparacion+"\n\n\n"+"Duración: "+duracion+"\n\n\n");*/



//text.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

                //text.setJustify(true);
               /* durateiemp.setText(dura+"\n\n");



                getSupportActionBar().setTitle(title);
                titulo.setText(title);

                Picasso.with(this).load(imagenes).resize(1000,0).into(imag);




            }
*/
/*
            String sqlingredientes ="select cantidad, ingrediente from ingredientes where ingredientes.id="+appp+"";

            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);

            listado = new ArrayList<>();

            while (cursor.moveToNext())
            {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);

                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);

            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviweringredientes);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

*/
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



            favoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbHome1 = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                    ds = dbHome1.getWritableDatabase();


                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                    SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                    String tiempo = String.valueOf(tiem.format(sq));

                    //String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.titulo = '"+titulo.getText().toString()+"'";
                    String sqlite = "select favoritos from contenido where permarlink='" + appp/*titulo.getText().toString()*/ + "'";
                    ContentValues values = new ContentValues();
                    cursorer = ds.rawQuery(sqlite, null);


                    if (cursorer.moveToFirst())

                    {
                        int favor = Integer.parseInt(cursorer.getString(0));
                        Log.v("favorito", "" + favor);

                        if (favor == 0) {


                            values.put("favoritos", "1");
                            values.put("fecha_favoritos", tiempo);
                            ds.update("contenido", values, "permarlink='" + appp + "'", null);
                            favoritos.setBackgroundResource(R.mipmap.favoritos_check_red);
                            ds.close();
                        } else if (favor == 1) {

                            //   values = new ContentValues();
                            values.put("favoritos", "0");
                            values.put("fecha_favoritos", tiempo);
                            //ds.update("contenido", values, "titulo='"+titulo.getText().toString()+"'", null);
                            ds.update("contenido", values, "permarlink='" + appp + "'", null);
                            favoritos.setBackgroundResource(R.mipmap.favoritos);
                            ds.close();
                        }


                    }

                }

            });



            compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {





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


                }
            });








          /*  Log.v("datos ", "" + appLinkData + " " + appp);

            if(appp.equals("agua-de-jamaica"))
            {
                titulo.setText(appp);

                Log.v("esto es" , "un rico y deliscios "+appp+" Pero se la bañan con los ingredientes hubieran hecho minimo meterno en el refri o comprar sobre sale barato");
            }

            else
            {

            }*/




        }

        else {
            Bundle bundle = getIntent().getExtras();
            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");
            final int id_tipo = bundle.getInt("id_tipo");

          //  String preparacion = bundle.getString("preparacion");
           // String duracion = bundle.getString("duracion");

           // getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titul);
            //titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titul);



            //Picasso.with(this).load(imagen/*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);









            final DBHome dbHome = new DBHome(this, name_database_elite, null, 1);
            final SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();




            int idobtenido = id;

            String sqltiem = "select ELEV_TIMESTAMP from contenido where id="+idobtenido+" and id_tipo="+id_tipo+"";

            filatiem = sqLiteDatabase.rawQuery(sqltiem, null);

            if (filatiem.moveToFirst() )
            {
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
                //subtitulo.setPadding(10,0,0,0);
                subtitulo.setTypeface(face);

                //textSubtitulo.setVisibility(View.VISIBLE);


            }




















            //  int fa = bundle.getInt("favorito");
            //favoritos.setImageResource(fa);
            //favoritos.setBackgroundResource(fa);


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








            /*String[] campos = new String[]{"cantidad","ingrediente"};
            String[] args = new String[]{id};

            cursor = sqLiteDatabase.query("ingredientes", campos, "id=?", args, null, null, null);

            Log.v("Cursor", ""+cursor.getCount());*/


         /*   if (cursor.moveToFirst()) {
                do {
                  //  HashMap<String> map = new HashMap<String>();

                    String cantidad = cursor.getString(0);
                    String ingredeinte = cursor.getString(1);

                    for(int i=0; i<cursor.getColumnCount();i++)
                    {
                        //map.put(cantidad, ingredeinte);

                        datos[i] = cantidad + " " + ingredeinte + "\n";

                        /*cursor.getColumnName(i), cursor.getString(i)*///);
                   /* }


                    despliegue =datos.toString();
                   // System.out.println(datos);
                   // Log.v("ingredientes", datos);
                   // words.add(datos);
                    //text.setText(words.toString());
                    //despliegue = datos;
                    //words.add(datos);
                    //maplist.add(map);
                } while (cursor.moveToNext());
            }*/



            /*if(cursor.moveToFirst()) {

                do {

                    String cantidad = cursor.getString(0);
                    String ingredeinte = cursor.getString(1);



                    datos[100] = cantidad + " " + ingredeinte + "\n";
                }

                while (cursor.moveToNext());

            }*/
            //{


            //}





            TextView textext = new TextView(this);
            textext.setTypeface(typeface);
            textext.setTextSize(20);
            textext.setText("Preparación");
            textext.setTextColor(Color.parseColor("#5b6065"));
            /*SpannableString pre = new SpannableString("Preparación");
            pre.setSpan(new TypefaceSpan(this, "MyTypeface.otf"), 0, pre.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            String pre = "Preparcion";*/

             //String f = getResources().getString(preparacion);

            SpannableString SS = new SpannableString("preparacion");
            //SS.setSpan ( new StyleSpan(typeface.getStyle()), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            SS.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);


         //despliegue = words.toString();

           //text.setText("d"/*preparacion*/+"\n", true);/*despliegue+"\n\n"+
            //preparacion+"\n\n\n"+"Duración: "+duracion+"\n\n\n");*/


          // durateiemp.setText(duracion+"\n\n");



            favoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbHome1 = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);
                    ds = dbHome1.getWritableDatabase();


                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                    SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                    String tiempo = String.valueOf(tiem.format(sq));

                   String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.titulo = '"+titulo.getText().toString()+"'";
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

                }

            });



            // favoritos.getBackground().setAlpha(00);
            // compartir.getBackground().setAlpha(00);





            //imag.setImageResource(imagen);



            compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(id_tipo == 1)
                    {
                        nombre_tipo="receta";
                    }

                    else if(id_tipo == 2)
                    {
                        nombre_tipo="ejercicio";
                    }



                    //  imag.buildDrawingCache();

                    //  Bitmap bitmap = imag.getDrawingCache();
                    try {

                /*    File file = new File(getApplicationContext().getCacheDir(), bitmap + ".png");
                    FileOutputStream fo = null;
                    fo = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fo);
                    fo.flush();
                    fo.close();
                    file.setReadable(true, false);*/
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


                }
            });



            /*String sqlingredientes ="select cantidad, ingrediente from ingredientes where ingredientes.id="+id+"";

            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);

            listado = new ArrayList<>();

            while (cursor.moveToNext())
            {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);

                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);

            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviweringredientes);
            recyclerView.setLayoutManager(linearLayoutManager);


            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
          //  recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
*/



        }

        // ATTENTION: This was auto-generated to handle app links.
     //   handelr();
    }
/*
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handelr();
    }*/

    private void handelr() {
       /* Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();


        String  datos = appLinkData.getQueryParameter("p");

       /* if(datos == "1131")
        {*/
    //   Log.v("debe mostrar esto", datos);

      //      Toast.makeText(this, "este es el unico que muestra eso"+ datos, Toast.LENGTH_LONG).show();

       /* }

        else{

        }*/
      /*  if(appLinkData != null)
        {
            String  urldato = appLinkData.getLastPathSegment();

            Uri urirecuperacion = Uri.parse("content://http://www.papeleselite.mx/receta/").buildUpon()
                    .appendPath(urldato).build();



        }*/


       /// String d = appLinkData.get

     //  ider = Integer.parseInt(appLinkData.getQueryParameter("p"));




      //  Log.d("datos", appLinkAction);

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


                        Log.v("id nota", ""+nota_id);
                        //Log.v("tipo nota", ""+tipo_Nota);
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
                                    //unidad_anidada ="";
                                    contentingredientes.put("cantidad", canidad_anidada+unidad_anidada);
                                }

                                else{
                                    contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                }

                                if(canidad_anidada.equals("") || unidad_anidada.equals(""))
                                {
                                    contentingredientes.put("cantidad", "");
                                }

                               // contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.update("ingredientes", contentingredientes, "ingrediente_id="+id_ingrediente +" and id ="+nota_id+"",null);


                            }

                            else {

                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);

                                contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.insert("ingredientes", null, contentingredientes);
                            }


                            // break;
                        }

                    }





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
                    nota = 1;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }



                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

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

*/

                            /*contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 1);
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
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/RECETA/"+imagen);

                            }*/



                    if (!video.equals(""))
                    {
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






/*
                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", "http://www.papeleselite.mx/RECETA/"+permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);*/






                    // database.insert("contenido", null, contentValues);


                    //   }


                    // }


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


            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);



            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();


            Bundle bundle = getIntent().getExtras();


            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");

            //  String preparacion = bundle.getString("preparacion");
            // String duracion = bundle.getString("duracion");




            String query = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and  contenido.id = "+id+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1";

            fila = sqLiteDatabase.rawQuery(query, null);


            if (fila.moveToFirst())
            {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String preparaciones = fila.getString(2);
                String duraciones = fila.getString(3);
              //  int id_ingredientes = fila.getInt(4);


                Log.v("preparacion comida", preparaciones);

                getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
               // Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 540).into(imag);

               // Uri uri = Uri.parse(contenido);

                Bitmap bmp = null;
                try {
                  /*  URL url = new URL(contenido);
                     bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    imag.setImageBitmap(bmp);
*/
                   Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).into(new Target() {
                       @Override
                       public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                           imag.setImageBitmap(bitmap);

                           DisplayMetrics displayMetrics = new DisplayMetrics();
                           getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                           int height = displayMetrics.heightPixels;
                           int width = displayMetrics.widthPixels;


                           int alto = bitmap.getHeight();

                           int ancho = bitmap.getWidth();



                           float razon =  (float)alto/ancho;

                           float nuevo_alto = razon*width;

                           imag.getLayoutParams().height = (int)nuevo_alto;



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

                //imag.setImageURI(Uri.parse(contenido));

                text.setText(preparaciones+"\n" /*, true*/);
                durateiemp.setText(duraciones+"\n\n");

                prepa.setVisibility(View.VISIBLE);
                dura.setVisibility(View.VISIBLE);
                subtitulo.setVisibility(View.VISIBLE);
                durateiemp.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);


              //  id_ingrediente = id_ingredientes;

               // textTiutulo.setText(titulo);

                //tex.setText(preparaciones+"\n", true);

                /*tiempo.setVisibility(View.VISIBLE);
                preparacion.setVisibility(View.VISIBLE);
                duracion.setVisibility(View.VISIBLE);
                textSubtitulo.setVisibility(View.VISIBLE);
                tiempo.setText(duraciones);
*/

                //tex.setText("");









            }


            String sqlingredientes ="select ingredientes.cantidad, ingredientes.ingrediente from ingredientes, contenido where contenido.id = ingredientes.id and contenido.id = "+id+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1 order by ingrediente_id";

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
            //  recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);




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




           /* DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1 order by fecha_publicacion desc ";

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


                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    }

                    else if(favor ==1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }

                else if(tipo == 2)
                {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    }

                    else if (favor == 1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }





            }



            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

*/






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
                    String descripcion_ejercicio =ob.getString("DESCRIPCION");
                    // String preparacion = ob.getString("PREPARACION");
                    //  String duracion = ob.getString("DURACION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");


                   /* JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");

                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {

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

                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.insert("ingredientes", null, contentingredientes);
                            }


                            // break;
                        }

                    }*/





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



                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

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

*/

                            /*contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 1);
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
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/RECETA/"+imagen);

                            }


*/

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

                    else {

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
                    //contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);

                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);



/*
                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", "http://www.papeleselite.mx/RECETA/"+permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);*/






                    // database.insert("contenido", null, contentValues);


                    //   }


                    // }


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


            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);



            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();


            Bundle bundle = getIntent().getExtras();

           /* String title = bundle.getString("ytitulo");
            final int idlink = Integer.parseInt(bundle.getString("id"));
            final String link = bundle.getString("link");
            int tiempos = (int)bundle.getDouble("yttiempo");
*/




            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");

            String query = "select contenido.titulo, contenido.contenido, contenido.descripcion_ejercicio from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+id+" and contenido.id_tipo = 2 and contenido.id_tipo_contenido = 1";

            fila = sqLiteDatabase.rawQuery(query, null);


            if (fila.moveToFirst())
            {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String descripcion_ejercicio = fila.getString(2);


                getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);
                subtitulo.setText(descripcion_ejercicio+"\n"/*, true*/);
                //durateiemp.setText(duraciones+"\n\n");
               // .setText("Preparación");


                //textTiutulo.setText(titulo);
                //text.setText(descripcion_ejercicio+"\n", true);
                //tiempo.setText("");



                //tex.setText("");








            }


          /*  String sqlingredientes ="select cantidad, ingrediente from ingredientes where ingredientes.id="+idlink+"";

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
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviwervideodetalle);
            recyclerView.setLayoutManager(linearLayoutManager);


            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //  recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
*/



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




           /* DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1 order by fecha_publicacion desc ";

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


                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    }

                    else if(favor ==1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }

                else if(tipo == 2)
                {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    }

                    else if (favor == 1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }





            }



            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

*/






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


                        Log.v("id nota", ""+nota_id);
                        //Log.v("tipo nota", ""+tipo_Nota);
                        fila = database.rawQuery(query, null);

                        if(fila.moveToFirst())
                        {
                            int contador = fila.getInt(0);

                            Log.v("contador", ""+contador);

                            if(contador == 1)
                            {
                                contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.update("ingredientes", contentingredientes, "ingrediente_id="+id_ingrediente +" and id ="+nota_id+"",null);

                            }

                            else {

                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.insert("ingredientes", null, contentingredientes);
                            }


                            // break;
                        }

                    }





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
                    nota = 1;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }



                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

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

*/

                            /*contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 1);
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
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/RECETA/"+imagen);

                            }
*/

                    if (!video.equals(""))
                    {
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





/*
                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", "http://www.papeleselite.mx/RECETA/"+permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);*/






                    // database.insert("contenido", null, contentValues);


                    //   }


                    // }


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


            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);



            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();


           /* Bundle bundle = getIntent().getExtras();


            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");
*/

            //  String preparacion = bundle.getString("preparacion");
            // String duracion = bundle.getString("duracion");


            Intent appLinkIntent = getIntent();
            final String appLinkAction = appLinkIntent.getAction();
            final Uri appLinkData = appLinkIntent.getData();
            final String appp = appLinkData.getLastPathSegment();

            String query = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion, contenido.id from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '"+appp+"' and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 1";

            fila = sqLiteDatabase.rawQuery(query, null);


            if (fila.moveToFirst())
            {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String preparaciones = fila.getString(2);
                String duraciones = fila.getString(3);
                int id_contenido = fila.getInt(4);


                Log.v("preparacion comida", preparaciones);

                getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
               // Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);

                Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        imag.setImageBitmap(bitmap);

                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        int height = displayMetrics.heightPixels;
                        int width = displayMetrics.widthPixels;


                        int alto = bitmap.getHeight();

                        int ancho = bitmap.getWidth();



                        float razon =  (float)alto/ancho;

                        float nuevo_alto = razon*width;

                        imag.getLayoutParams().height = (int)nuevo_alto;



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


                // textTiutulo.setText(titulo);

                //tex.setText(preparaciones+"\n", true);

                /*tiempo.setVisibility(View.VISIBLE);
                preparacion.setVisibility(View.VISIBLE);
                duracion.setVisibility(View.VISIBLE);
                textSubtitulo.setVisibility(View.VISIBLE);
                tiempo.setText(duraciones);
*/

                //tex.setText("");



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
            //  recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);




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




           /* DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1 order by fecha_publicacion desc ";

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


                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    }

                    else if(favor ==1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }

                else if(tipo == 2)
                {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    }

                    else if (favor == 1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }





            }



            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

*/






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
                    String descripcion_ejercicio =ob.getString("DESCRIPCION");
                    // String preparacion = ob.getString("PREPARACION");
                    //  String duracion = ob.getString("DURACION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");


                   /* JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");

                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {

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

                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);

                                database.insert("ingredientes", null, contentingredientes);
                            }


                            // break;
                        }

                    }*/





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



                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

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

*/

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

                    else {

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
                    //contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);

                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);


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


            DBHome dbHome = new DBHome(MainActivity_contenidoreceta.this, name_database_elite, null, 1);



            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();


            //Bundle bundle = getIntent().getExtras();

           /* String title = bundle.getString("ytitulo");
            final int idlink = Integer.parseInt(bundle.getString("id"));
            final String link = bundle.getString("link");
            int tiempos = (int)bundle.getDouble("yttiempo");
*/




          /*  final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link");
*/



            Intent appLinkIntent = getIntent();
            final String appLinkAction = appLinkIntent.getAction();
            final Uri appLinkData = appLinkIntent.getData();
            final String appp = appLinkData.getLastPathSegment();



            String query = "select contenido.titulo, contenido.contenido, contenido.descripcion_ejercicio from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '"+appp+"' and contenido.id_tipo = 2 and contenido.id_tipo_contenido = 1";

            fila = sqLiteDatabase.rawQuery(query, null);


            if (fila.moveToFirst())
            {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String descripcion_ejercicio = fila.getString(2);


                getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);
                subtitulo.setText(descripcion_ejercicio+"\n"/*, true*/);
                //durateiemp.setText(duraciones+"\n\n");
                // .setText("Preparación");


                //textTiutulo.setText(titulo);
                //text.setText(descripcion_ejercicio+"\n", true);
                //tiempo.setText("");



                //tex.setText("");








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
        }

        else{

        int ruta = bundle.getInt("contextreceta");

        if(ruta == 1)
        {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);
        }

       else if(ruta == 2)


        {
            int id_cate = bundle.getInt("id_categoria");
            String bund_name = bundle.getString("nombrecategoria");

            Intent intent = new Intent(this, MainActivity_recetas.class);
            Bundle bundle1 = new Bundle();
            bundle1.putInt("id_categoria", id_cate);
            bundle1.putString("nombrecategoria", bund_name);

            intent.putExtras(bundle1);
            startActivity(intent);
            //overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);

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

        if(ruta == 1)
        {
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
        }

        else {
            Intent intent = new Intent(this, Main_Activity_inicio.class);
            startActivity(intent);
        }

        }

        //onBackPressed();
        return true;
    }
}
