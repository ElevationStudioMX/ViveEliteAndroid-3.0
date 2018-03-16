package com.example.desarrollo_elevation.viveelite;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.Adapter.Model_Ingredientes;
import com.example.desarrollo_elevation.viveelite.Adapter.Model_productos;
import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.id;
import static com.example.desarrollo_elevation.viveelite.R.string.preparacion;

//import static android.R.attr.id;
//import static com.example.desarrollo_elevation.viveelite.R.string.preparacion;

/**
 * Created by Desarrollo_Elevation on 11/05/17.
 */

public class view_detalle  extends AppCompatActivity {

    //private static ArrayList<Model_productos> list;



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
   // static  String datos[]= new String[1000];
  //  ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
    private static ArrayList<Model_Ingredientes> listado;
    private static String despliegue;

    private  static  String name_database_elite= "elite_v5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contenidoreceta);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Log.v("entrada", "entro por aqui hehehehehehheehehhehehds");

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
        //  subtitulo.setText("Ingredientes:");

        prepa.setText("\nPreparaciòn:");
        prepa.setTypeface(typeface);
        prepa.setTextSize(22);
        dura.setTypeface(typeface);
        dura.setTextSize(22);

        text.setTextSize(18);
        text.setPadding(10,0,0,0);
        durateiemp.setTextSize(20);
        durateiemp.setTypeface(face);
        titulo.setTextSize(28);


        receta.getBackground().setAlpha(00);


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
            String sqlid = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+appp+"";

            cursor = sqLiteDatabase.rawQuery(sqlid, null);

            if(cursor.moveToFirst())
            {
                String title = cursor.getString(0);
                String imagenes = cursor.getString(1);
                String prepa = cursor.getString(2);
                String dura = cursor.getString(3);

                text.setText(prepa+"\n");/*despliegue+"\n\n"+
            preparacion+"\n\n\n"+"Duración: "+duracion+"\n\n\n");*/



//text.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

                //text.setJustify(true);
                durateiemp.setText(dura+"\n\n");



                getSupportActionBar().setTitle(title);
                titulo.setText(title);

                Picasso.with(this).load(imagenes).resize(1000,0).into(imag);




            }


            String sqlingredientes ="select cantidad, ingrediente from ingredientes where ingredientes.id="+appp+"";

            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);

            listado = new ArrayList<>();

           /* while (cursor.moveToNext())
            {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);

                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);

            }*/

           /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviweringredientes);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
*/

            String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = " + appp + "";

            cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                int far = Integer.parseInt(cursor.getString(0));

                if (far == 0) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos);
                } else if (far == 1) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos_check);
                }


            }



            favoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbHome1 = new DBHome(view_detalle.this, name_database_elite, null, 1);
                    ds = dbHome1.getWritableDatabase();


                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                    SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                    String tiempo = String.valueOf(tiem.format(sq));

                    //String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.titulo = '"+titulo.getText().toString()+"'";
                    String sqlite = "select favoritos from contenido where id=" + appp/*titulo.getText().toString()*/ + "";
                    ContentValues values = new ContentValues();
                    cursorer = ds.rawQuery(sqlite, null);


                    if (cursorer.moveToFirst())

                    {
                        int favor = Integer.parseInt(cursorer.getString(0));
                        Log.v("favorito", "" + favor);

                        if (favor == 0) {


                            values.put("favoritos", "1");
                            values.put("fecha_favoritos", tiempo);
                            ds.update("contenido", values, "id=" + appp + "", null);
                            favoritos.setBackgroundResource(R.mipmap.favoritos_check);
                            ds.close();
                        } else if (favor == 1) {

                            //   values = new ContentValues();
                            values.put("favoritos", "0");
                            values.put("fecha_favoritos", tiempo);
                            //ds.update("contenido", values, "titulo='"+titulo.getText().toString()+"'", null);
                            ds.update("contenido", values, "id=" + appp + "", null);
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
          //  Bundle bundle = getIntent().getExtras();
         //   final String titul = bundle.getString("titulo");
       //     final String imagen = bundle.getString("imagen");
            //final int id = 1;//Integer.parseInt(bundle.getString("idlink"));
            //final String link = "http://www.papeleselite.mx/receta/pan-tostado-gratinado-al-horno/";//bundle.getString("link");

            //  String preparacion = bundle.getString("preparacion");
            // String duracion = bundle.getString("duracion");

            getSupportActionBar().setTitle(/*"Muffins de Plàtano con Crema de Cacahuate"*/"hola"/*titul*/);
            titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*//*titul*/"hey");
            Picasso.with(this).load("http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/58507dd5e6ef5_martes_am.png"/*imagen*//*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);



            final DBHome dbHome = new DBHome(this, name_database_elite, null, 1);
            final SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            //  int fa = bundle.getInt("favorito");
            //favoritos.setImageResource(fa);
            //favoritos.setBackgroundResource(fa);


           /* String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = " + id + "";

            cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                int far = Integer.parseInt(cursor.getString(0));

                if (far == 0) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos);
                } else if (far == 1) {
                    favoritos.setBackgroundResource(R.mipmap.favoritos_check);
                }


            }*/








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





           // TextView textext = new TextView(this);
            //textext.setTypeface(typeface);
            //textext.setTextSize(20);
            //textext.setText("Preparación");
            //textext.setTextColor(Color.parseColor("#5b6065"));
            /*SpannableString pre = new SpannableString("Preparación");
            pre.setSpan(new TypefaceSpan(this, "MyTypeface.otf"), 0, pre.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            String pre = "Preparcion";*/

            //String f = getResources().getString(preparacion);

          //  SpannableString SS = new SpannableString("preparacion");
            //SS.setSpan ( new StyleSpan(typeface.getStyle()), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            //SS.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);


            //despliegue = words.toString();

            text.setText(/*preparacion*/"consecuencia"+"\n");/*despliegue+"\n\n"+
            preparacion+"\n\n\n"+"Duración: "+duracion+"\n\n\n");*/


            // durateiemp.setText(duracion+"\n\n");



            favoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbHome1 = new DBHome(view_detalle.this, name_database_elite, null, 1);
                    ds = dbHome1.getWritableDatabase();


                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                    SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                    String tiempo = String.valueOf(tiem.format(sq));

                    //String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.titulo = '"+titulo.getText().toString()+"'";
                   // String sqlite = "select favoritos from contenido where id=" + id/*titulo.getText().toString()*/ + "";
                    ContentValues values = new ContentValues();
                   // cursorer = ds.rawQuery(sqlite, null);


                    if (cursorer.moveToFirst())

                    {
                        int favor = Integer.parseInt(cursorer.getString(0));
                        Log.v("favorito", "" + favor);

                        if (favor == 0) {


                            values.put("favoritos", "1");
                            values.put("fecha_favoritos", tiempo);
                         //   ds.update("contenido", values, "id=" + id + "", null);
                            favoritos.setBackgroundResource(R.mipmap.favoritos_check);
                            ds.close();
                        } else if (favor == 1) {

                            //   values = new ContentValues();
                            values.put("favoritos", "0");
                            values.put("fecha_favoritos", tiempo);
                            //ds.update("contenido", values, "titulo='"+titulo.getText().toString()+"'", null);
                            //ds.update("contenido", values, "id=" + id + "", null);
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

                        intent.putExtra(Intent.EXTRA_TEXT, "");//link/*titulo.getText().toString()*/);
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
                    Intent intent = new Intent(this, MainActivity_menutolbbed.class);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);
                }

                else if(ruta == 2)
                {
                    Intent intent = new Intent(this, MainActivity_recetas.class);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);

                }

                else if (ruta == 3)
                {
                    Intent intent = new Intent(this, MainActivity_Favoritos.class);
                    startActivity(intent);
                }

                else{
                    Intent intent = new Intent(this, MainActivity_menutolbbed.class);
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
            Intent intent = new Intent(this, MainActivity_menutolbbed.class);
            startActivity(intent);
        }

        else{
            int ruta = bundle.getInt("contextreceta");

            if(ruta == 1)
            {
                Intent intent = new Intent(this, MainActivity_menutolbbed.class);
                startActivity(intent);
                //   overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);
            }

            else if(ruta == 2) {
                Intent intent = new Intent(this, MainActivity_recetas.class);

                startActivity(intent);
                // overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);
            }




            else if (ruta == 3) {
                Intent intent = new Intent(this, MainActivity_Favoritos.class);
                startActivity(intent);
            }

            else {
                Intent intent = new Intent(this, MainActivity_menutolbbed.class);
                startActivity(intent);
            }

        }

        //onBackPressed();
        return true;
    }
}
