package com.example.desarrollo_elevation.viveelite;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.DB.DB_prueba_json;
import com.example.desarrollo_elevation.viveelite.json.MainActivity_prueba_json;
import com.example.desarrollo_elevation.viveelite.json.Main_jason_rest;
import com.example.desarrollo_elevation.viveelite.json.adapter_jason_contry;
import com.example.desarrollo_elevation.viveelite.json.model_json_contry;
import com.example.desarrollo_elevation.viveelite.json.segunda_prueba_json;
import com.squareup.picasso.Callback;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.y;
import static android.R.id.list;
import static android.R.id.widget_frame;
import static android.view.KeyCharacterMap.load;
import static com.example.desarrollo_elevation.viveelite.R.id.view;
import static com.example.desarrollo_elevation.viveelite.R.layout.fragment_main_activity_menutabbed;
import static com.example.desarrollo_elevation.viveelite.R.mipmap.recetas;
import static com.example.desarrollo_elevation.viveelite.TextJustifyUtils.p;

public class MainActivity_menutolbbed extends AppCompatActivity {

    private static final int ALPHA_SELECTED = 255;
    private static final int ALPHA_UNSELECTED = 128;
    private static final int NUM_TABS = 3;
    Resources res;
    private TabLayout tabLayout;
    RecyclerView recyclerView;
    private Cursor filacursor;

     private  Cursor curstiempo;
    ListView Lista;
    private Cursor fila;
    SimpleAdapter AD;
    TextView tp;
    private ListView listas;
   private ListView listare;
    Button detalleimg;
    Button btnfavorito, btnreceta, btnejercicios, btnmusicas, btnmemoria, btnpromocionesQR, productos;
    AbsoluteLayout Absol;
    ProgressDialog pDialog;
    private String lenguajeProgramacion[]=new String[]{"Comida rica","Espageti delicioso","Tacos de antojo"};

    VideoView videoView;
   private Integer[] imgid = {R.mipmap.hambre,R.mipmap.espageti,R.mipmap.tacos};

    private static ArrayList<Model> list;
   private  String urlslink[]= new String[]{"http://www.androidbegin.com/tutorial/AndroidCommercial.3gp","http://www.androidbegin.com/tutorial/AndroidCommercial.3gp","http://www.androidbegin.com/tutorial/AndroidCommercial.3gp"};

    private  static


    Bundle bundle;


private static String contex;


    private static String link;


    private  static  String linkfristhome ="http://www.elevation.com.mx/pages/AppElite/web-services/elite/home-first";


    private  static  String video;
    private  static  String imagen;
    private  static  int nota;

    private  static  String fecha_destinada;


    private  static  String name_database_elite= "elite_v4";



    private Cursor cursor;
    private ArrayList<Model> models;


     Button borrarsqliter;

    // private List<home> myuser = new ArrayList<home>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menutolbbed);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainActivity_menutolbbed.MainPageAdapter());

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

      //  tp = (TextView)findViewById(R.id.textView);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getBackground().setAlpha(00);

        tabLayout.getTabAt(0).setIcon(R.mipmap.iconmenu);
        tabLayout.getTabAt(1).setIcon(R.mipmap.logoelites);

        tabLayout.getTabAt(2).setIcon(R.mipmap.favoritos_menu_2);
       //bundle = getIntent().getExtras();
//        favoritos_menu();

        //if(bundle == null)
        //{
            //String e = bundle.getString("menu");
            viewPager.setCurrentItem(1);
        //}

        //else {
          //  viewPager.setCurrentItem(0);
        //}

        //int  comienzo = bundle.getInt("menu");

        //if(comienzo == 2)
       // {
         //   viewPager.setCurrentItem(0);
        //}
       // else {
           // viewPager.setCurrentItem(1);// inicializa con cual tab comienza primero
        //}// tabLayout.getTabAt(3).setIcon(R.mipmap.tab4);

        selectIcon(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /*public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //... Acciones al seleccionar una opción de la lista
                Log.i("tolbar 2", "Seleccionada opción " + 2);
            }*/

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //nothing here
            }

            @Override
            public void onPageSelected(int position) {

                selectIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //nothing here
                //selectIcon(1);
            }
        });





    }



    private void selectIcon(int position) {
        for (int i = 0; i < NUM_TABS; i++) {
            if (i == position) {
                tabLayout.getTabAt(i).getIcon().setAlpha(ALPHA_SELECTED);
            } else {
                tabLayout.getTabAt(i).getIcon().setAlpha(ALPHA_UNSELECTED);
            }
        }

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://danielme" +
                        ".com/2016/03/26/diseno-android-tutorial-pestanas-con-material-design")));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }*/


    class MainPageAdapter extends PagerAdapter {

        private AbsoluteLayout page1;
        private RelativeLayout page2;
        private AbsoluteLayout page3;
        //private LinearLayout page4;
        private final int[] titles = { R.string.page1, R.string.page2, R.string.page3 /*,R.string.page4*/};

        @Override
        public int getCount() {
            return NUM_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {

            final View page;
          //  page=page1;

            switch (position) {



                case 0:
                if (page1 == null) {
                    page1 = (AbsoluteLayout) LayoutInflater.from(MainActivity_menutolbbed.this).inflate(R.layout
                            .activity_main_menu, collection, false);
                    Log.v("paso por 1","paso 1");

                   /* backgraounconpicasso();*/

                   /* Picasso.with( page2.getContext()).load(R.drawable.backgroundmenu).into(new Target(){

                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            page2.setBackground(new BitmapDrawable(page2.getContext().getResources(), bitmap));
                        }

                        @Override
                        public void onBitmapFailed(final Drawable errorDrawable) {
                            Log.d("TAG", "FAILED");
                        }

                        @Override
                        public void onPrepareLoad(final Drawable placeHolderDrawable) {
                            Log.d("TAG", "Prepare Load");
                        }
                    });*/


                    //  listimagen();



                }
                page = page1;
                break;

                case 1:
                    if (page2 == null) {
                        page2 = (RelativeLayout) LayoutInflater.from(MainActivity_menutolbbed.this).inflate(R.layout
                                .activity_main, collection, false);


                        Log.v("paso por 2","paso 2");


                        //   listvideo();

                        //  populateCarList();
                        //  populateListView();
//listimagen();


                    }
                    page = page2;
                    break;


                case 2:
                    if (page3 == null) {
                        page3 = (AbsoluteLayout) LayoutInflater.from(MainActivity_menutolbbed.this).inflate(R.layout

                               /*.activity_main__favoritos*/.fragment_main_activity_menutabbed, collection, false);
                        Log.v("paso por 3","paso 3");

                       // favoritos_menu();

                        java.util.Date utilDate = new java.util.Date();
                        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



                        String tiempo = String.valueOf(tiem.format(sq));


                        DBHome dbHome = new DBHome(MainActivity_menutolbbed.this,name_database_elite, null, 1);

                        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

                        String fecha = "select fecha from fecha_actualizacion";

                        filacursor =  sqLiteDatabase.rawQuery(fecha, null);

                        if (filacursor.moveToFirst())
                        {
                            fecha_destinada =  filacursor.getString(0);
                            Log.v("fecha destinada", fecha_destinada);

                        }



                        if (fecha_destinada.equals(""))
                        {
                            Log.v("donde paso el json", "json paso por home frist");

                            Log.v("link homefrist", linkfristhome);
                            new JsonTaskhomerfrist().execute(linkfristhome);
                        }

                        else {

                            Log.v("donde paso el json", "json esta pasando por home");
                            link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/home/" + fecha_destinada + "";

                            Log.v("Link", link);

                            new JsonTask().execute(link);


                        }
                        ContentValues v = new ContentValues();

                        v.put("fecha", tiempo);
                        sqLiteDatabase.update("fecha_actualizacion", v, null, null);
                        String fecha_nueva = "select fecha from fecha_actualizacion";
                        curstiempo = sqLiteDatabase.rawQuery(fecha_nueva,null);

                        if(curstiempo.moveToFirst())
                        {
                            String fecha_new = curstiempo.getString(0);

                            Log.v("fecham nueva", fecha_new);
                        }
                        popwindow();

                        //rec();
                       // recycleviwe();
                        /*listimagen(R.mipmap.hambre);
                        listvideo();
                        listimagen(R.mipmap.tacos);*/
                        productos();
                        favoritos();
                        recetas();
                        ejercicios();
                        musica();
                        memorias();
                        promociones();

                        //borrarsqlite();
                       // backgraounconpicasso();

                       // favoritos_menu();
                       // favoritos_menu();
                    }

                   // favoritos_menu();
                    page = page3;
                    break;


                default:
                    if (page1 == null) {
                        page1 = (AbsoluteLayout) LayoutInflater.from(MainActivity_menutolbbed.this).inflate(R.layout
                                .activity_main, collection, false);
                        //   listvideo();
                        Log.v("paso por default","paso por default");
                        //  populateCarList();
                        //  populateListView();
//listimagen();favoritos_menu();
                     //   favoritos_menu();
                       // favoritos_menu();
                    }
                    page = page1;
                    break;
                        //initListView();

                    /*ImageView view;
                        view = (ImageView)findViewById(R.id.imageView);


                       /* Drawable drawable;

                        String ed;

                       ed = res.getResourceName(R.mipmap.corazon);
                        **/
                       // String ed = "R.mimap.corazon";

                       // ed = "R.mimap.corazon";
                       // view.setImageResource(Integer.parseInt(ed));

                        //listimagen();

//listimagen();






                /*default:
                    if (page4 == null) {
                        page4 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout
                                .page_four, collection, false);
                    }
                    page = page4;
                    break;*/
            }

            collection.addView(page, 0);


            return page;
        }








        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        /*public void  botonborrar(View view){

            DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, "elite_pruebav9", null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
//Metodo para eliminar datos de la base de datos

            sqLiteDatabase.execSQL("delete from contenido where id_tipo=1 and  id=1");

            Log.v("mensaje", "do de la base de datos borrado exitosamente :) .l.");
        }*/


       /* public void  borrarsqlite(){

            borrarsqliter = (Button)findViewById(R.id.borrardatosql);



            borrarsqliter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, "elite_pruebav9", null, 1);

                    SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
//Metodo para eliminar datos de la base de datos

                    sqLiteDatabase.execSQL("delete from contenido where id_tipo=1 and  id=1");

                    Log.v("mensaje", "do de la base de datos borrado exitosamente :) .l.");
                }
            });




/*            borrarsqlite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, "elite_pruebav9", null, 1);

                    SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
//Metodo para eliminar datos de la base de datos

                    sqLiteDatabase.execSQL("delete from contenido where id_tipo=1 and  id=1");

                    Log.v("mensaje", "do de la base de datos borrado exitosamente :) .l.");

                }
            });*/



        //}


        public void  popwindow(){
            DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);
            startActivity(new Intent(MainActivity_menutolbbed.this, popwindow.class));
            /*SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            ContentValues values = new ContentValues();
            String sql = "select view from bandera_popwindow";
            filacursor= sqLiteDatabase.rawQuery(sql, null );
            if (filacursor.moveToFirst())
            {
                int vista = filacursor.getInt(0);

                if(vista == 0)
                {
                    Log.v("vista pop", ""+vista);

                    values.put("view", 1);
                    sqLiteDatabase.update("bandera_popwindow", values, null,null);
                    startActivity(new Intent(MainActivity_menutolbbed.this, popwindow.class));

                }

                else{

                }


            }*/


        }


        public  void  productos(){

            productos =(Button)findViewById(R.id.btnproductos);
            productos.getBackground().setAlpha(00);
            productos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity_menutolbbed.this, MainActivity_productos_elite.class);
                    startActivity(intent);
                }
            });
        }


      public void favoritos(){

          btnfavorito =(Button)findViewById(R.id.btnfavoritos);
          btnfavorito.getBackground().setAlpha(00);
          btnfavorito.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent intent = new Intent(MainActivity_menutolbbed.this, MainActivity_Favoritos.class);
                  startActivity(intent);
              }
          });
}


        public  void  recetas(){

            btnreceta = (Button) findViewById(R.id.btnrecetas);

            btnreceta.getBackground().setAlpha(00);

            btnreceta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    Intent inte = new Intent(MainActivity_menutolbbed.this, MainActivity_recetas.class /*prueba_recycleviwer_base_datos.class*/);
                    startActivity(inte);
                }
            });
        }


        public void ejercicios(){
            btnejercicios = (Button) findViewById(R.id.btnejercicio);
            btnejercicios.getBackground().setAlpha(00);

            btnejercicios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inte = new Intent(MainActivity_menutolbbed.this, MainActivity_Ejercicios.class /*segunda_prueba_json.class*/);
                    startActivity(inte);
                }
            });
        }

        public  void musica(){



            btnmusicas =(Button) findViewById(R.id.btnmusica);

            btnmusicas.getBackground().setAlpha(00);
            btnmusicas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inte = new Intent(MainActivity_menutolbbed.this, MainActivity_loginspotify.class);
                    startActivity(inte);
                }
            });

        }

        public void memorias(){



            btnmemoria =(Button)findViewById(R.id.btnmemorias);
            btnmemoria.getBackground().setAlpha(00);
            btnmemoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inte = new Intent(MainActivity_menutolbbed.this, MainActivity_galeria_imagen.class);
                    startActivity(inte);
                }
            });
        }


        public  void  promociones(){


            btnpromocionesQR =(Button)findViewById(R.id.btnpromociones);
            btnmemoria.getBackground().setAlpha(00);
            btnpromocionesQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent  intent = new Intent(MainActivity_menutolbbed.this, MainActivity_QR.class/* Main_jason_rest.class*/);
                   startActivity(intent);

                    /*DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

                    SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
//Metodo para eliminar datos de la base de datos

                    sqLiteDatabase.execSQL("delete from contenido where id_tipo=1 and  id=1");
                    sqLiteDatabase.execSQL("delete from contenido where id_tipo=2 and  id=1");
                    //sqLiteDatabase.execSQL("delete from fecha_actualizacion_receta");

                    Log.v("mensaje", "do de la base de datos borrado exitosamente :) .l.");*/


                }
           });


        }



        public void favoritos_menu(){



            DBHome dbhome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

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

                    Model model = new Model(id_tipo_contenido, descrption, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duration, tipo_contenido);
                    models.add(model);

                }

                else if(tipo_contenido == 2){

                    Model model = new Model(id_tipo_contenido, descrption, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duration, tipo_contenido);
                    models.add(model);
                }

            }


//            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_menutolbbed.this);
            LinearLayoutManager linearLayoutManagerer = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);


            Log.v("layoir",""+linearLayoutManagerer);


            recyclerView = (RecyclerView)findViewById(R.id.recyclerViewerfavoritos2);

            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(models, MainActivity_menutolbbed.this, recyclerView);
            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);*/





            Log.v("layoir",""+recyclerView);


            recyclerView.setLayoutManager(linearLayoutManagerer);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);




        }




        public  void backgraounconpicasso(){





         // Picasso.with(MainActivity_menutolbbed.this).load(R.drawable.backgroundmenu).resize(500,0).into(mTarget);



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


                    DBHome db = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

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
                        String tipo_Nota = ob.getString("TIPO_NOTA");
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
                        if (tipo_Nota.equals("RECETA"))
                        {
                            nota = 1;
                        }
                        else  if (tipo_Nota.equals("EJERCICIO"))
                        {
                            nota = 2;
                        }



                        String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                        Log.v("id nota", ""+nota_id);
                        Log.v("tipo nota", ""+tipo_Nota);
                        filacursor = database.rawQuery(query, null);

                       if(filacursor.moveToFirst())
                       {
                           int contador = filacursor.getInt(0);

                           Log.v("contador", ""+contador);

                           if(contador == 1)
                           {

                           }

                           else {

                               contentValues.put("id", nota_id);

                               if (tipo_Nota.equals("RECETA"))
                               {
                                   contentValues.put("id_tipo", 1);
                               }
                               else  if (tipo_Nota.equals("EJERCICIO"))
                               {
                                   contentValues.put("id_tipo", 2);
                               }




                               if (!video.equals(""))
                               {
                                   contentValues.put("id_tipo_contenido", 2);
                                   contentValues.put("contenido", video);
                               }
                               else if (!imagen.equals(""))
                               {
                                   contentValues.put("id_tipo_contenido", 1);
                                   contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen);

                               }







                               contentValues.put("titulo", titulo);
                               contentValues.put("descripcion", descripcion_corta);
                               contentValues.put("categoria", categoria);
                               contentValues.put("influencer", influencer);
                               contentValues.put("permarlink", /*"http://www.papeleselite.mx/"+tipo_Nota+"/"+*/permalink);
                               contentValues.put("fecha_publicacion", fecha_publicacion);
                               contentValues.put("fecha", tiempo);
                               contentValues.put("favoritos", 0);
                               contentValues.put("fecha_favoritos", tiempo);



                               database.insert("contenido", null, contentValues);


                           }


                       }











                  /*  String admincode1 = ob.getString("adminCode1");
                    String adminName2 = ob.getString("adminName2");
                    Double lng = ob.getDouble("lng");
                    String countrycode = ob.getString("countryCode");
                    int postalcode = ob.getInt("postalcode");
                    String adminName1 = ob.getString("adminName1");
                    String placeName = ob.getString("placeName");
                    Double lat = ob.getDouble("lat");*/

                        //model_json_contry model_json = new model_json_contry(1, name, alpha2_code, alpha3_code);

                        //list.add(model_json);

                    }

                    return list;


                    // StringBuffer finalbuferString = new StringBuffer();


              /*  List<MovieModel> movieModelList = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    MovieModel m = new MovieModel();

                    JSONObject finalobject = jsarreglo.getJSONObject(i);

                    m.setMovie(finalobject.getString("movie"));
                    m.setYear(finalobject.getInt("year"));
                    m.setReating((float) finalobject.getDouble("rating"));
                    m.setDirector(finalobject.getString("director"));
                    m.setDuration(finalobject.getString("duration"));
                    m.setTagline(finalobject.getString("tagline"));
                    m.setImage(finalobject.getString("image"));
                    m.setStory(finalobject.getString("story"));
                    m.setDuration(finalobject.getString("duration"));

                    List<MovieModel.Cast> castlist = new ArrayList<>();
                    for (int j = 0; j < finalobject.getJSONArray("cast").length(); j++) {
                        //  JSONObject castobject =
                        MovieModel.Cast cast = new MovieModel.Cast();

                        cast.SetName(finalobject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castlist.add(cast);
                    }

                    m.setCastList(castlist);

                    movieModelList.add(m);*/
                    //     String moviname = finalobject.getString("movie");
                    //   int año = Integer.parseInt(finalobject.getString("year"));

                    //    finalbuferString.append(moviname +" "+ año +"\n");

                    //return movieModelList;   //finalbuferString.toString(); //moviname +" "+año; //buffer.toString();


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


            /*catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                if(connection != null) {connection.disconnect();}
                try {
                    if(reader != null)
                    {reader.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/

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




                DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

                SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

                list = new ArrayList<>();

                String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido order by fecha_publicacion desc ";

                filacursor = sqLiteDatabase.rawQuery(query, null);

                list= new ArrayList<>();




                while (filacursor.moveToNext())
                {
                    int id_tipo_contenido = Integer.parseInt(filacursor.getString(0));
                    String descrpition = filacursor.getString(1);
                    String titulo = filacursor.getString(2);

                    String contenido = filacursor.getString(3);
                    int tipo = Integer.parseInt(filacursor.getString(4));
                    int favor = Integer.parseInt(filacursor.getString(5));
                    String id = filacursor.getString(6);
                    String link = filacursor.getString(7);
                    String preparacion = filacursor.getString(8);
                    String duracion = filacursor.getString(9);
                    String fecha =  filacursor.getString(10);

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
                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion, tipo);
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
                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion, tipo);
                            list.add(model);
                        }
                    }





                }




                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);

                MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_menutolbbed.this, mRecyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);


                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);

               /* adapter_jason_contry adapter_json = new adapter_jason_contry(list, Main_jason_rest.this);



                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter_json);*/






            }








        }






        public class JsonTaskhomerfrist extends AsyncTask<String, String, ArrayList<Model>> {

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


                    DBHome db = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

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
                        String tipo_Nota = ob.getString("TIPO_NOTA");
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
                        if (tipo_Nota.equals("RECETA"))
                        {
                            nota = 1;
                        }
                        else  if (tipo_Nota.equals("EJERCICIO"))
                        {
                            nota = 2;
                        }



                        String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                        Log.v("id nota", ""+nota_id);
                        Log.v("tipo nota", ""+tipo_Nota);
                        filacursor = database.rawQuery(query, null);

                        if(filacursor.moveToFirst())
                        {
                            int contador = filacursor.getInt(0);

                            Log.v("contador", ""+contador);

                            if(contador == 1)
                            {

                            }

                            else {

                                contentValues.put("id", nota_id);

                                if (tipo_Nota.equals("RECETA"))
                                {
                                    contentValues.put("id_tipo", 1);
                                }
                                else  if (tipo_Nota.equals("EJERCICIO"))
                                {
                                    contentValues.put("id_tipo", 2);
                                }




                                if (!video.equals(""))
                                {
                                    contentValues.put("id_tipo_contenido", 2);
                                    contentValues.put("contenido", video);
                                }
                                else if (!imagen.equals(""))
                                {
                                    contentValues.put("id_tipo_contenido", 1);
                                    contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen);

                                }







                                contentValues.put("titulo", titulo);
                                contentValues.put("descripcion", descripcion_corta);
                                contentValues.put("categoria", categoria);
                                contentValues.put("influencer", influencer);
                                contentValues.put("permarlink", /*"http://www.papeleselite.mx/"+tipo_Nota+"/"+*/permalink);
                                contentValues.put("fecha_publicacion", fecha_publicacion);
                                contentValues.put("fecha", tiempo);
                                contentValues.put("favoritos", 0);
                                contentValues.put("fecha_favoritos", tiempo);



                                database.insert("contenido", null, contentValues);


                            }


                        }











                  /*  String admincode1 = ob.getString("adminCode1");
                    String adminName2 = ob.getString("adminName2");
                    Double lng = ob.getDouble("lng");
                    String countrycode = ob.getString("countryCode");
                    int postalcode = ob.getInt("postalcode");
                    String adminName1 = ob.getString("adminName1");
                    String placeName = ob.getString("placeName");
                    Double lat = ob.getDouble("lat");*/

                        //model_json_contry model_json = new model_json_contry(1, name, alpha2_code, alpha3_code);

                        //list.add(model_json);

                    }

                    return list;


                    // StringBuffer finalbuferString = new StringBuffer();


              /*  List<MovieModel> movieModelList = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    MovieModel m = new MovieModel();

                    JSONObject finalobject = jsarreglo.getJSONObject(i);

                    m.setMovie(finalobject.getString("movie"));
                    m.setYear(finalobject.getInt("year"));
                    m.setReating((float) finalobject.getDouble("rating"));
                    m.setDirector(finalobject.getString("director"));
                    m.setDuration(finalobject.getString("duration"));
                    m.setTagline(finalobject.getString("tagline"));
                    m.setImage(finalobject.getString("image"));
                    m.setStory(finalobject.getString("story"));
                    m.setDuration(finalobject.getString("duration"));

                    List<MovieModel.Cast> castlist = new ArrayList<>();
                    for (int j = 0; j < finalobject.getJSONArray("cast").length(); j++) {
                        //  JSONObject castobject =
                        MovieModel.Cast cast = new MovieModel.Cast();

                        cast.SetName(finalobject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castlist.add(cast);
                    }

                    m.setCastList(castlist);

                    movieModelList.add(m);*/
                    //     String moviname = finalobject.getString("movie");
                    //   int año = Integer.parseInt(finalobject.getString("year"));

                    //    finalbuferString.append(moviname +" "+ año +"\n");

                    //return movieModelList;   //finalbuferString.toString(); //moviname +" "+año; //buffer.toString();


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


            /*catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                if(connection != null) {connection.disconnect();}
                try {
                    if(reader != null)
                    {reader.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/

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




                DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

                SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

                list = new ArrayList<>();

                String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido order by fecha_publicacion desc ";

                filacursor = sqLiteDatabase.rawQuery(query, null);

                list= new ArrayList<>();




                while (filacursor.moveToNext())
                {
                    int id_tipo_contenido = Integer.parseInt(filacursor.getString(0));
                    String descrpition = filacursor.getString(1);
                    String titulo = filacursor.getString(2);

                    String contenido = filacursor.getString(3);
                    int tipo = Integer.parseInt(filacursor.getString(4));
                    int favor = Integer.parseInt(filacursor.getString(5));
                    String id = filacursor.getString(6);
                    String link = filacursor.getString(7);
                    String preparacion = filacursor.getString(8);
                    String duracion = filacursor.getString(9);
                    String fecha =  filacursor.getString(10);

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
                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion, tipo);
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
                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion, tipo);
                            list.add(model);
                        }
                    }





                }



                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);
                MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_menutolbbed.this, mRecyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);


                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);

               /* adapter_jason_contry adapter_json = new adapter_jason_contry(list, Main_jason_rest.this);



                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter_json);*/






            }








        }





























        /*public  void  rec (){

            DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, "elite_pruebav3", null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido order by fecha_publicacion desc ";

            filacursor = sqLiteDatabase.rawQuery(query, null);

            list= new ArrayList<>();


            while (filacursor.moveToNext())
            {
                int id_tipo_contenido = Integer.parseInt(filacursor.getString(0));
                String descrpition = filacursor.getString(1);
                String titulo = filacursor.getString(2);

                String contenido = filacursor.getString(3);
                int tipo = Integer.parseInt(filacursor.getString(4));
                int favor = Integer.parseInt(filacursor.getString(5));
                String id = filacursor.getString(6);
                String link = filacursor.getString(7);
                String preparacion = filacursor.getString(8);
                String duracion = filacursor.getString(9);

                Log.v("contenitdo", contenido);

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



            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_menutolbbed.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }

*/




        public  void recycleviwe(){

            DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
//Metodo para eliminar datos de la base de datos

     // sqLiteDatabase.execSQL("delete from contenido");
       //sqLiteDatabase.execSQL("delete from ingredientes");



/*

        java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

            SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



            String tiempo = String.valueOf(tiem.format(sq));

            ContentValues Valuares5 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares5.put("id_tipo", 1 );
            Valuares5.put("id_tipo_contenido", 1);
            Valuares5.put("id", "churros-con-chocolate");
            Valuares5.put("link", "http://www.papeleselite.mx/receta/churros-con-chocolate/");
            Valuares5.put("titulo", "Churros con chocolates");
            Valuares5.put("subtitulos", "que se necesita para ser los churros con chocolates");
            Valuares5.put("descripcion", "En una olla pon el agua y la mantequilla a fuego medio y deja que derrita. Cuando esté hirviendo agrega toda la harina y con una cuchara de madera bate hasta que se haga masa.");
            Valuares5.put("contenido", "http://www.papeleselite.mx/appelite/churros.png");
            Valuares5.put("preparacion", "En una olla pon el agua y la mantequilla a fuego medio y deja que derrita. Cuando esté hirviendo agrega toda la harina y con una cuchara de madera bate hasta que se haga una masa. Cocina por 1 minuto mientras sigues mezclando. Transfiere a una batidora o a un envase si lo harás a mano y añade los huevos uno por uno. Espera que se unan bien por separado para añadir el próximo. Mezcla hasta que se haga una masa suave y pierda el exceso de humedad.\n" +
                    "Pon la mezcla en una manga pastelera con boquilla de estrella, pero si no tienes puedes tomar una bolsa de plástico resistente o una de cierre mágico y abres un hueco en un extremo y haces los churros directamente encima del aceite bien caliente. Cocina por cada lado hasta que estén dorados. Saca y pon de inmediato en azúcar en polvo para que se pegue.\n" +
                    "Para hacer el chocolate, mezclamos la leche y la harina de maíz y ponemos en una olla con el chocolate y el azúcar. Cocina a fuego bajo, mezclando y deja que derrita.\n" +
                    "Al hervir no te separes de la olla, mezcla constantemente hasta que espese.\n" +
                    "Sirve caliente con los churros.\t");
            Valuares5.put("duracion", "1 hora");
            Valuares5.put("fecha_publicacion",tiempo);
            Valuares5.put("fecha", tiempo );
            Valuares5.put("favoritos", 0);
            Valuares5.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares5);



            ContentValues contentValuesingre = new ContentValues();

            contentValuesingre.put("id", "churros-con-chocolate");
            contentValuesingre.put("cantidad", "1");

            contentValuesingre.put("ingrediente", "Taza de agua");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre);


            ContentValues contentValuesingre11 = new ContentValues();

            contentValuesingre11.put("id", "churros-con-chocolate");
            contentValuesingre11.put("cantidad", "1/4");
            contentValuesingre11.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre11);


            ContentValues contentValuesingre12 = new ContentValues();

            contentValuesingre12.put("id", "churros-con-chocolate");
            contentValuesingre12.put("cantidad", "3");
            contentValuesingre12.put("ingrediente", "huevos");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre12);



            ContentValues contentValuesingre13 = new ContentValues();

            contentValuesingre13.put("id", "churros-con-chocolate");
            contentValuesingre13.put("cantidad", "1");
            contentValuesingre13.put("ingrediente", "Taza de harina de trigo todo uso");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre13);



            ContentValues contentValuesingre14 = new ContentValues();

            contentValuesingre14.put("id", "churros-con-chocolate");
            contentValuesingre14.put("cantidad", "1");
            contentValuesingre14.put("ingrediente", "Aceite de vegetal en polvo");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre14);


            ContentValues contentValuesingre15 = new ContentValues();

            contentValuesingre15.put("id", "churros-con-chocolate");
            contentValuesingre15.put("cantidad", "1");
            contentValuesingre15.put("ingrediente", "cucharada de harina de maiz");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre15);


            ContentValues contentValuesingre16 = new ContentValues();

            contentValuesingre16.put("id", "churros-con-chocolate");
            contentValuesingre16.put("cantidad", "2");
            contentValuesingre16.put("ingrediente", "Taza de leche enteras");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre16);


            ContentValues contentValuesingre17 = new ContentValues();

            contentValuesingre17.put("id", "churros-con-chocolate");
            contentValuesingre17.put("cantidad", "2");
            contentValuesingre17.put("ingrediente", "Taza de leche enteras");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre17);


            ContentValues contentValuesingre18 = new ContentValues();

            contentValuesingre18.put("id", "churros-con-chocolate");
            contentValuesingre18.put("cantidad", "1/2");
            contentValuesingre18.put("ingrediente", "Taza de chocoloata");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre18);


            ContentValues contentValuesingre19 = new ContentValues();

            contentValuesingre19.put("id", "churros-con-chocolate");
            contentValuesingre19.put("cantidad", "1/4");
            contentValuesingre19.put("ingrediente", "Taza de azucar");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre19);



            ContentValues contentValuesingre5 = new ContentValues();

            contentValuesingre5.put("id", "churros-con-chocolate");
            contentValuesingre5.put("cantidad", "1/4");
            contentValuesingre5.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre5);

            ContentValues contentValuesingre51 = new ContentValues();

            contentValuesingre51.put("id", "churros-con-chocolate");
            contentValuesingre51.put("cantidad", "1/4");
            contentValuesingre51.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre51);


            ContentValues contentValuesingre52 = new ContentValues();

            contentValuesingre52.put("id", "churros-con-chocolate");
            contentValuesingre52.put("cantidad", "1/4");
            contentValuesingre52.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre52);

            ContentValues contentValuesingre53 = new ContentValues();

            contentValuesingre53.put("id", "churros-con-chocolate");
            contentValuesingre53.put("cantidad", "1/4");
            contentValuesingre53.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre53);
            ContentValues contentValuesingre54 = new ContentValues();

            contentValuesingre54.put("id", "churros-con-chocolate");
            contentValuesingre54.put("cantidad", "1/4");
            contentValuesingre54.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre54);
























            ContentValues Valuares = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares.put("id_tipo", 1 );
            Valuares.put("id_tipo_contenido", 1);
            Valuares.put("id", "enfrijoladas-con-queso-y-chorizo");
            Valuares.put("link", "http://www.papeleselite.mx/receta/enfrijoladas-con-queso-y-chorizo/");
            Valuares.put("titulo", "Enfrijoladas con chorizo y queso");
            Valuares.put("subtitulos", "que se necesita para las enfrijoladas");
            Valuares.put("descripcion", "Moler los frijoles en la licuadora. Una vez molidos, freír los frijoles con una cucharada de aceite, sazonar con sal y reservar.");
            Valuares.put("contenido", "http://www.papeleselite.mx/appelite/enfrijoladas.png");
            Valuares.put("preparacion", "Moler los frijoles en la licuadora. Una vez molidos, freír los frijoles con una cucharada de aceite, sazonar con sal y reservar.\n" +
                    "En un sartén freír el chorizo con el chile serrano. Una vez frito, agrega el queso para que se funda.\n" +
                    "Calentar las tortillas sobre el comal y rellenar con la mezcla de chorizo con queso. \u000BColoca los taquitos en un plato y báñalas con la salsa de frijol.\n" +
                    "Servir calientes decoradas con un poquito de queso encima.\t");
            Valuares.put("duracion", "35 minutos");
            Valuares.put("fecha_publicacion",tiempo);
            Valuares.put("fecha", tiempo );
            Valuares.put("favoritos", 0);
            Valuares.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares);



            ContentValues contentValuesingre21 = new ContentValues();

            contentValuesingre21.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre21.put("cantidad", "2");
            contentValuesingre21.put("ingrediente", "tazas de frijoles cocidos enteros");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre21);

            ContentValues contentValuesingre22 = new ContentValues();

            contentValuesingre22.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre22.put("cantidad", "1");
            contentValuesingre22.put("ingrediente", "chile serrano");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre22);

            ContentValues contentValuesingre23 = new ContentValues();

            contentValuesingre23.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre23.put("cantidad", "12");
            contentValuesingre23.put("ingrediente", "tortillas");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre23);


            ContentValues contentValuesingre24 = new ContentValues();

            contentValuesingre24.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre24.put("cantidad", "300gr");
            contentValuesingre24.put("ingrediente", "chorizo");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre24);

            ContentValues contentValuesingre25 = new ContentValues();

            contentValuesingre25.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre25.put("cantidad", "1");
            contentValuesingre25.put("ingrediente", "tazas de queso machego rollado");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre25);

            ContentValues contentValuesingre26 = new ContentValues();

            contentValuesingre26.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre26.put("cantidad", "");
            contentValuesingre26.put("ingrediente", "sal al gusto");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre26);

            ContentValues contentValuesingre27 = new ContentValues();

            contentValuesingre27.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre27.put("cantidad", "1");
            contentValuesingre27.put("ingrediente", "cucharada de aceite de oliva");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre27);









            ContentValues Valuares2 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares2.put("id_tipo", 2 );
            Valuares2.put("id_tipo_contenido", 2);
            Valuares2.put("id", "Baile funcional");
            Valuares2.put("link", "http://www.papeleselite.mx/receta/enfrijoladas-con-queso-y-chorizo/");
            Valuares2.put("titulo", "Baile funcional");
            Valuares2.put("subtitulos", "para comenzar el ejericicio de hoy");
            Valuares2.put("descripcion", "¡Vamos a ejercitarnos con Elite y Sisy Garza!");
            Valuares2.put("contenido", "25WeoeH46W0");
            Valuares2.put("preparacion", "¡Vamos a ejercitarnos con #ViveElite y Sisy Garza (https://sisygarza.com/)! Sigamos cumpliendo metas y sintiéndonos de maravilla.");
            Valuares2.put("duracion", "15 minutos");
            Valuares2.put("fecha_publicacion",tiempo);
            Valuares2.put("fecha", tiempo );
            Valuares2.put("favoritos", 0);
            Valuares2.put("fecha_favoritos", tiempo);
            sqLiteDatabase.insert("contenido", null, Valuares2);



            ContentValues Valuares7 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares7.put("id_tipo", 1 );
            Valuares7.put("id_tipo_contenido", 1);
            Valuares7.put("id", "hamburguesas-especiales");
            Valuares7.put("link", "http://www.papeleselite.mx/receta/hamburguesas-especiales/");
            Valuares7.put("titulo", "Hamburgesas especiales");
            Valuares7.put("subtitulos", "que se necesita para las Hamburgesas");
            Valuares7.put("descripcion", "En un recipiente, mezcla la carne con el tocino ahumado picado.");
            Valuares7.put("contenido", "http://www.papeleselite.mx/appelite/hamburguesas.png");
            Valuares7.put("preparacion", "En un recipiente, mezcla la carne con el tocino ahumado picado.\n" +
                    "Después, ve agregando cada ingrediente uno a uno. Mezcla con la mano entre cada uno. Forma las hamburguesas y cubre al final con el queso parmesano. Pon a freír hasta que se doren.\n" +
                    "Disfruta con cebolla caramelizada, rebanadas de tomate, lechuga\u000B y mayonesa chipotle.");
            Valuares7.put("duracion", "35 minutos");
            Valuares7.put("fecha_publicacion",tiempo);
            Valuares7.put("fecha", tiempo );
            Valuares7.put("favoritos", 0);
            Valuares7.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares7);


            ContentValues contentValuesingre31 = new ContentValues();

            contentValuesingre31.put("id", "hamburguesas-especiales");
            contentValuesingre31.put("cantidad", "450gr");
            contentValuesingre31.put("ingrediente", "carne molida");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre31);


            ContentValues contentValuesingre32 = new ContentValues();

            contentValuesingre32.put("id", "hamburguesas-especiales");
            contentValuesingre32.put("cantidad", "4");
            contentValuesingre32.put("ingrediente", "rebanadas de tocino ahumado picadito");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre32);


            ContentValues contentValuesingre33 = new ContentValues();

            contentValuesingre33.put("id", "hamburguesas-especiales");
            contentValuesingre33.put("cantidad", "1/4");
            contentValuesingre33.put("ingrediente", "cebollin picado");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre33);

            ContentValues contentValuesingre34 = new ContentValues();

            contentValuesingre34.put("id", "hamburguesas-especiales");
            contentValuesingre34.put("cantidad", "2");
            contentValuesingre34.put("ingrediente", "huevos");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre34);


            ContentValues contentValuesingre35 = new ContentValues();

            contentValuesingre35.put("id", "hamburguesas-especiales");
            contentValuesingre35.put("cantidad", "1 1/2");
            contentValuesingre35.put("ingrediente", "cada sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre35);



            ContentValues contentValuesingre36 = new ContentValues();

            contentValuesingre36.put("id", "hamburguesas-especiales");
            contentValuesingre36.put("cantidad", "1");
            contentValuesingre36.put("ingrediente", "cdita de pimienta");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre36);










            ContentValues Valuares8 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares8.put("id_tipo", 1 );
            Valuares8.put("id_tipo_contenido", 1);
            Valuares8.put("id", "jugo-desintoxicante");
            Valuares8.put("link", "http://www.papeleselite.mx/receta/jugo-desintoxicante/");
            Valuares8.put("titulo", "Jugo desintoxicante");
            Valuares8.put("subtitulos", "que se necesita para hacer el jugo");
            Valuares8.put("descripcion", "Agrega los ingredientes a la licuadora, licúa con un poco de hielo para que esté frío y ¡Listo!");
            Valuares8.put("contenido", "http://www.papeleselite.mx/appelite/jugo.png");
            Valuares8.put("preparacion", "Agrega los ingredientes a la licuadora, licúa con un poco de hielo para que esté frío y ¡Listo!\t");
            Valuares8.put("duracion", "15 minutos");
            Valuares8.put("fecha_publicacion",tiempo);
            Valuares8.put("fecha", tiempo );
            Valuares8.put("favoritos", 0);
            Valuares8.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares8);




            ContentValues contentValuesingre41 = new ContentValues();

            contentValuesingre41.put("id", "jugo-desintoxicante");
            contentValuesingre41.put("cantidad", "1");
            contentValuesingre41.put("ingrediente", "pepino");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre41);

            ContentValues contentValuesingre42 = new ContentValues();

            contentValuesingre42.put("id", "jugo-desintoxicante");
            contentValuesingre42.put("cantidad", "2");
            contentValuesingre42.put("ingrediente", "zanahorias");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre42);


            ContentValues contentValuesingre43 = new ContentValues();

            contentValuesingre43.put("id", "jugo-desintoxicante");
            contentValuesingre43.put("cantidad", "2");
            contentValuesingre43.put("ingrediente", "limones");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre43);



            ContentValues contentValuesingre44 = new ContentValues();

            contentValuesingre44.put("id", "jugo-desintoxicante");
            contentValuesingre44.put("cantidad", "1");
            contentValuesingre44.put("ingrediente", "1 taza de espinacas");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre44);



            ContentValues contentValuesingre45 = new ContentValues();

            contentValuesingre45.put("id", "jugo-desintoxicante");
            contentValuesingre45.put("cantidad", "1");
            contentValuesingre45.put("ingrediente", "naranja");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre45);









            ContentValues Valuares4 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares4.put("id_tipo", 2 );
            Valuares4.put("id_tipo_contenido", 2);
            Valuares4.put("titulo", "Pilates 2");
            Valuares4.put("id", "Pilates-2");
            Valuares4.put("link", "http://www.papeleselite.mx/receta/enfrijoladas-con-queso-y-chorizo/");
            Valuares4.put("subtitulos", "que se necesita para ser los pilates");
            Valuares4.put("descripcion", "¿Qué tal van esos propósitos de año nuevo? ");
            Valuares4.put("contenido", "ZtYN-PuXHTg");
            Valuares4.put("preparacion", "¿Qué tal van esos propósitos de año nuevo? La segunda parte de Sisy Pilates te ayudará a seguir con un entrenamiento muy funcional. #ViveElite y Sisy Garza (https://sisygarza.com) están contigo en cada nuevo reto.");

            Valuares4.put("duracion", "15 minutos");
            Valuares4.put("fecha_publicacion",tiempo);
            Valuares4.put("fecha", tiempo );
            Valuares4.put("favoritos", 0);
            Valuares4.put("fecha_favoritos", tiempo);
            sqLiteDatabase.insert("contenido", null, Valuares4);








        /*    ContentValues Valuares = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares.put("id_tipo", 2 );
            Valuares.put("id_tipo_contenido", 2);
            Valuares.put("id", "enfrijoladas-con-queso-y-chorizo");
            Valuares.put("link", "http://www.papeleselite.mx/receta/enfrijoladas-con-queso-y-chorizo/");
            Valuares.put("titulo", "Enfrijoladas con chorizo y queso");
            Valuares.put("subtitulos", "que se necesita para las enfrijoladas");
            Valuares.put("descripcion", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem");
            Valuares.put("contenido", "http://www.papeleselite.mx/wp-content/uploads/2016/05/lunes_am-2.png");
            Valuares.put("fecha", tiempo );
            Valuares.put("favoritos", 0);
            Valuares.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares);
*/

/*

            ContentValues Valuares5 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares5.put("id_tipo", 1 );
            Valuares5.put("id_tipo_contenido", 1);
            Valuares5.put("id", "churros-con-chocolate");
            Valuares5.put("link", "http://www.papeleselite.mx/receta/churros-con-chocolate/");
            Valuares5.put("titulo", "Churros con chocolates");
            Valuares5.put("subtitulos", "que se necesita para ser los churros con chocolates");
            Valuares5.put("descripcion", "un rico y deliciosos churros de chocolates para un postre despues de una rica comida para convivir con toda la familia");
            Valuares5.put("contenido", "http://www.papeleselite.mx/wp-content/uploads/2016/11/Jueves_pm-3.png");
            Valuares5.put("preparacion", "En una olla pon el agua y la mantequilla a fuego medio y deja que derrita. Cuando esté hirviendo agrega toda la harina y con una cuchara de madera bate hasta que se haga una masa. Cocina por 1 minuto mientras sigues mezclando. Transfiere a una batidora o a un envase si lo harás a mano y añade los huevos uno por uno. Espera que se unan bien por separado para añadir el próximo. Mezcla hasta que se haga una masa suave y pierda el exceso de humedad.\n" +
                    "Pon la mezcla en una manga pastelera con boquilla de estrella, pero si no tienes puedes tomar una bolsa de plástico resistente o una de cierre mágico y abres un hueco en un extremo y haces los churros directamente encima del aceite bien caliente. Cocina por cada lado hasta que estén dorados. Saca y pon de inmediato en azúcar en polvo para que se pegue.\n" +
                    "Para hacer el chocolate, mezclamos la leche y la harina de maíz y ponemos en una olla con el chocolate y el azúcar. Cocina a fuego bajo, mezclando y deja que derrita.\n" +
                    "Al hervir no te separes de la olla, mezcla constantemente hasta que espese.\n" +
                    "Sirve caliente con los churros.\t");
            Valuares5.put("duracion", "1 hora");
            Valuares5.put("fecha_publicacion",tiempo);
            Valuares5.put("fecha", tiempo );

            Valuares5.put("favoritos", 0);
            Valuares5.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares5);

            ContentValues contentValuesingre = new ContentValues();

            contentValuesingre.put("id", "churros-con-chocolate");
            contentValuesingre.put("cantidad", "1");

            contentValuesingre.put("ingrediente", "Taza de agua");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre);


            ContentValues contentValuesingre11 = new ContentValues();

            contentValuesingre11.put("id", "churros-con-chocolate");
            contentValuesingre11.put("cantidad", "1/4");
            contentValuesingre11.put("ingrediente", "cuchardadita de sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre11);


            ContentValues contentValuesingre12 = new ContentValues();

            contentValuesingre12.put("id", "churros-con-chocolate");
            contentValuesingre12.put("cantidad", "3");
            contentValuesingre12.put("ingrediente", "huevos");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre12);



            ContentValues contentValuesingre13 = new ContentValues();

            contentValuesingre13.put("id", "churros-con-chocolate");
            contentValuesingre13.put("cantidad", "1");
            contentValuesingre13.put("ingrediente", "Taza de harina de trigo todo uso");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre13);



            ContentValues contentValuesingre14 = new ContentValues();

            contentValuesingre14.put("id", "churros-con-chocolate");
            contentValuesingre14.put("cantidad", "1");
            contentValuesingre14.put("ingrediente", "Aceite de vegetal en polvo");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre14);


            ContentValues contentValuesingre15 = new ContentValues();

            contentValuesingre15.put("id", "churros-con-chocolate");
            contentValuesingre15.put("cantidad", "1");
            contentValuesingre15.put("ingrediente", "cucharada de harina de maiz");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre15);


            ContentValues contentValuesingre16 = new ContentValues();

            contentValuesingre16.put("id", "churros-con-chocolate");
            contentValuesingre16.put("cantidad", "2");
            contentValuesingre16.put("ingrediente", "Taza de leche enteras");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre16);


            ContentValues contentValuesingre17 = new ContentValues();

            contentValuesingre17.put("id", "churros-con-chocolate");
            contentValuesingre17.put("cantidad", "2");
            contentValuesingre17.put("ingrediente", "Taza de leche enteras");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre17);


            ContentValues contentValuesingre18 = new ContentValues();

            contentValuesingre18.put("id", "churros-con-chocolate");
            contentValuesingre18.put("cantidad", "1/2");
            contentValuesingre18.put("ingrediente", "Taza de chocoloata");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre18);


            ContentValues contentValuesingre19 = new ContentValues();

            contentValuesingre19.put("id", "churros-con-chocolate");
            contentValuesingre19.put("cantidad", "1/4");
            contentValuesingre19.put("ingrediente", "Taza de azucar");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre19);
















            ContentValues Valuares = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares.put("id_tipo", 1 );
            Valuares.put("id_tipo_contenido", 1);
            Valuares.put("id", "enfrijoladas-con-queso-y-chorizo");
            Valuares.put("link", "http://www.papeleselite.mx/receta/enfrijoladas-con-queso-y-chorizo/");
            Valuares.put("titulo", "Enfrijoladas con chorizo y queso");
            Valuares.put("subtitulos", "que se necesita para las enfrijoladas");
            Valuares.put("descripcion", "enfrijolada con queso y chorizo, un buen desayuno para comenzar el dia");
            Valuares.put("contenido", "http://www.papeleselite.mx/wp-content/uploads/2016/05/lunes_am-2.png");
            Valuares.put("preparacion", "En una olla pon el agua y la mantequilla a fuego medio y deja que derrita. Cuando esté hirviendo agrega toda la harina y con una cuchara de madera bate hasta que se haga una masa. Cocina por 1 minuto mientras sigues mezclando. Transfiere a una batidora o a un envase si lo harás a mano y añade los huevos uno por uno. Espera que se unan bien por separado para añadir el próximo. Mezcla hasta que se haga una masa suave y pierda el exceso de humedad.\\n\" +\n" +
                    "                    \"Pon la mezcla en una manga pastelera con boquilla de estrella, pero si no tienes puedes tomar una bolsa de plástico resistente o una de cierre mágico y abres un hueco en un extremo y haces los churros directamente encima del aceite bien caliente. Cocina por cada lado hasta que estén dorados. Saca y pon de inmediato en azúcar en polvo para que se pegue.\\n\" +\n" +
                    "                    \"Para hacer el chocolate, mezclamos la leche y la harina de maíz y ponemos en una olla con el chocolate y el azúcar. Cocina a fuego bajo, mezclando y deja que derrita.\\n\" +\n" +
                    "                    \"Al hervir no te separes de la olla, mezcla constantemente hasta que espese.\\n\" +\n" +
                    "                    \"Sirve caliente con los churros.\\t\");\n" +
                    "            Valuares5.put(\"duracion\", \"1 hora\");\n" +
                    "            Valuares5.put(\"fecha_publicacion\",tiempo);");
            Valuares.put("duracion", "35 minutos");
            Valuares.put("fecha_publicacion",tiempo);
            Valuares.put("fecha", tiempo );
            Valuares.put("favoritos", 0);
            Valuares.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares);


            ContentValues contentValuesingre21 = new ContentValues();

            contentValuesingre21.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre21.put("cantidad", "2");
            contentValuesingre21.put("ingrediente", "tazas de frijoles cocidos enteros");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre21);

            ContentValues contentValuesingre22 = new ContentValues();

            contentValuesingre22.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre22.put("cantidad", "1");
            contentValuesingre22.put("ingrediente", "chile serrano");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre22);

            ContentValues contentValuesingre23 = new ContentValues();

            contentValuesingre23.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre23.put("cantidad", "12");
            contentValuesingre23.put("ingrediente", "tortillas");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre23);


            ContentValues contentValuesingre24 = new ContentValues();

            contentValuesingre24.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre24.put("cantidad", "300gr");
            contentValuesingre24.put("ingrediente", "chorizo");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre24);

            ContentValues contentValuesingre25 = new ContentValues();

            contentValuesingre25.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre25.put("cantidad", "1");
            contentValuesingre25.put("ingrediente", "tazas de queso machego rollado");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre25);

            ContentValues contentValuesingre26 = new ContentValues();

            contentValuesingre26.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre26.put("cantidad", "");
            contentValuesingre26.put("ingrediente", "sal al gusto");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre26);

            ContentValues contentValuesingre27 = new ContentValues();

            contentValuesingre27.put("id", "enfrijoladas-con-queso-y-chorizo");
            contentValuesingre27.put("cantidad", "1");
            contentValuesingre27.put("ingrediente", "cucharada de aceite de oliva");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre27);



            ContentValues Valuares7 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares7.put("id_tipo", 1 );
            Valuares7.put("id_tipo_contenido", 1);
            Valuares7.put("id", "hamburguesas-especiales");
            Valuares7.put("link", "http://www.papeleselite.mx/receta/hamburguesas-especiales/");
            Valuares7.put("titulo", "Hamburgesas especiales");
            Valuares7.put("subtitulos", "que se necesita para las Hamburgesas");
            Valuares7.put("descripcion", "Una ricas hamburgesas para convivir con la familia y los amigos");
            Valuares7.put("contenido", "http://www.papeleselite.mx/wp-content/uploads/2016/11/hamburguesa.png");
            Valuares7.put("preparacion", "En un recipiente, mezcla la carne con el tocino ahumado picado.\n" +
                    "Después, ve agregando cada ingrediente uno a uno. Mezcla con la mano entre cada uno. Forma las hamburguesas y cubre al final con el queso parmesano. Pon a freír hasta que se doren.\n" +
                    "Disfruta con cebolla caramelizada, rebanadas de tomate, lechuga\u000B y mayonesa chipotle.");
            Valuares7.put("duracion", "35 minutos");
            Valuares7.put("fecha_publicacion",tiempo);
            Valuares7.put("fecha", tiempo );
            Valuares7.put("favoritos", 0);
            Valuares7.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares7);


            ContentValues contentValuesingre31 = new ContentValues();

            contentValuesingre31.put("id", "hamburguesas-especiales");
            contentValuesingre31.put("cantidad", "450gr");
            contentValuesingre31.put("ingrediente", "carne molida");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre31);


            ContentValues contentValuesingre32 = new ContentValues();

            contentValuesingre32.put("id", "hamburguesas-especiales");
            contentValuesingre32.put("cantidad", "4");
            contentValuesingre32.put("ingrediente", "rebanadas de tocino ahumado picadito");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre32);


            ContentValues contentValuesingre33 = new ContentValues();

            contentValuesingre33.put("id", "hamburguesas-especiales");
            contentValuesingre33.put("cantidad", "1/4");
            contentValuesingre33.put("ingrediente", "cebollin picado");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre33);

            ContentValues contentValuesingre34 = new ContentValues();

            contentValuesingre34.put("id", "hamburguesas-especiales");
            contentValuesingre34.put("cantidad", "2");
            contentValuesingre34.put("ingrediente", "huevos");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre34);


            ContentValues contentValuesingre35 = new ContentValues();

            contentValuesingre35.put("id", "hamburguesas-especiales");
            contentValuesingre35.put("cantidad", "1 1/2");
            contentValuesingre35.put("ingrediente", "cada sal");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre35);



            ContentValues contentValuesingre36 = new ContentValues();

            contentValuesingre36.put("id", "hamburguesas-especiales");
            contentValuesingre36.put("cantidad", "1");
            contentValuesingre36.put("ingrediente", "cdita de pimienta");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre36);




            ContentValues Valuares8 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares8.put("id_tipo", 1 );
            Valuares8.put("id_tipo_contenido", 1);
            Valuares8.put("id", "jugo-desintoxicante");
            Valuares8.put("link", "http://www.papeleselite.mx/receta/jugo-desintoxicante/");
            Valuares8.put("titulo", "Jugo desintoxicante");
            Valuares8.put("subtitulos", "que se necesita para hacer el jugo");
            Valuares8.put("descripcion", "Una rico jugo para desintoxicar el cuerpo del exceso de grasa");
            Valuares8.put("contenido", "http://www.papeleselite.mx/wp-content/uploads/2016/05/lunes_am.png");
            Valuares8.put("preparacion", "Agrega los ingredientes a la licuadora, licúa con un poco de hielo para que esté frío y ¡Listo!\t");
            Valuares8.put("duracion", "15 minutos");
            Valuares8.put("fecha_publicacion",tiempo);

            Valuares8.put("fecha", tiempo );
            Valuares8.put("favoritos", 0);
            Valuares8.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares8);




            ContentValues contentValuesingre41 = new ContentValues();

            contentValuesingre41.put("id", "jugo-desintoxicante");
            contentValuesingre41.put("cantidad", "1");
            contentValuesingre41.put("ingrediente", "pepino");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre41);

            ContentValues contentValuesingre42 = new ContentValues();

            contentValuesingre42.put("id", "jugo-desintoxicante");
            contentValuesingre42.put("cantidad", "2");
            contentValuesingre42.put("ingrediente", "zanahorias");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre42);


            ContentValues contentValuesingre43 = new ContentValues();

            contentValuesingre43.put("id", "jugo-desintoxicante");
            contentValuesingre43.put("cantidad", "2");
            contentValuesingre43.put("ingrediente", "limones");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre43);



            ContentValues contentValuesingre44 = new ContentValues();

            contentValuesingre44.put("id", "jugo-desintoxicante");
            contentValuesingre44.put("cantidad", "1");
            contentValuesingre44.put("ingrediente", "1 taza de espinacas");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre44);



            ContentValues contentValuesingre45 = new ContentValues();

            contentValuesingre45.put("id", "jugo-desintoxicante");
            contentValuesingre45.put("cantidad", "1");
            contentValuesingre45.put("ingrediente", "naranja");

            sqLiteDatabase.insert("ingredientes", null, contentValuesingre45);





/*
            ContentValues Valuares2 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares2.put("id_tipo", 2 );
            Valuares2.put("id_tipo_contenido", 2);
            Valuares2.put("titulo", "Vive Elite 2017: Entrenamiento SisyFusión 2");
            Valuares2.put("subtitulos", "para comenzar el ejericicio de hoy");
            Valuares2.put("descripcion", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem");
            Valuares2.put("contenido", "ZAtQrU-t0JU");
            Valuares2.put("fecha", tiempo );
            Valuares2.put("favoritos", 0);
            Valuares2.put("fecha_favoritos", tiempo);
            sqLiteDatabase.insert("contenido", null, Valuares2);



            ContentValues Valuares3 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares3.put("id_tipo", 1 );
            Valuares3.put("id_tipo_contenido", 2);
            Valuares3.put("titulo", "Como preparar cereal con leche");
            Valuares3.put("subtitulos", "que se necesita para ser cereal con leche");
            Valuares3.put("descripcion", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem");
            Valuares3.put("contenido", "p7kwp-kOp-g");
            Valuares3.put("fecha", tiempo );
            Valuares3.put("favoritos", 0);
            Valuares3.put("fecha_favoritos", tiempo);
            sqLiteDatabase.insert("contenido", null, Valuares3);




            ContentValues Valuares4 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares4.put("id_tipo", 2 );
            Valuares4.put("id_tipo_contenido", 2);
            Valuares4.put("titulo", "Vive Elite 2017: Pilates 2");
            Valuares4.put("subtitulos", "que se necesita para ser los pilates");
            Valuares4.put("descripcion", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem");
            Valuares4.put("contenido", "ZtYN-PuXHTg");
            Valuares4.put("fecha", tiempo );
            Valuares4.put("favoritos", 0);
            Valuares4.put("fecha_favoritos", tiempo);
            sqLiteDatabase.insert("contenido", null, Valuares4);


           /* ContentValues Valuares5 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares.put("id_tipo", 1 );
            Valuares.put("id_tipo_contenido", 1);
            Valuares.put("titulo", "Pay de manzana");
            Valuares.put("subtitulos", "que se necesita para ser el pay de manzana");
            Valuares.put("descripcion", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem");
            Valuares.put("contenido", "http://www.wikihow.com/images/f/f0/Bake-an-Apple-Pie-from-Scratch-Intro.jpg");
            Valuares.put("fecha", tiempo );
            Valuares.put("favoritos", 0);

            sqLiteDatabase.insert("contenido", null, Valuares);


            fEQDBtkqRHQ*/






         /*   java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

            SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



            String tiempo = String.valueOf(tiem.format(sq));



            ContentValues Valuares5 = new ContentValues();
//            sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger, titulo text not null, subtitulos text not null, descripcion text not null, contenido text, fecha text not null, favoritos integer not null)");
            Valuares5.put("id_tipo", 2 );
            Valuares5.put("id_tipo_contenido", 2);
            Valuares5.put("titulo", "video de prueba");
            Valuares5.put("subtitulos", "video del buen franco escamilla");
            Valuares5.put("descripcion", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem");
            Valuares5.put("contenido", "fEQDBtkqRHQ");
            Valuares5.put("fecha", tiempo );
            Valuares5.put("favoritos", 1);
            Valuares5.put("fecha_favoritos", tiempo);

            sqLiteDatabase.insert("contenido", null, Valuares5);*/




            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.link, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido";

           filacursor = sqLiteDatabase.rawQuery(query, null);

             list= new ArrayList<>();


            while (filacursor.moveToNext())
            {
                int id_tipo_contenido = Integer.parseInt(filacursor.getString(0));
                String descrpition = filacursor.getString(1);
                String titulo = filacursor.getString(2);

                String contenido = filacursor.getString(3);
                int tipo = Integer.parseInt(filacursor.getString(4));
                 int favor = Integer.parseInt(filacursor.getString(5));
                String id = filacursor.getString(6);
                String link = filacursor.getString(7);
                String preparacion = filacursor.getString(8);
                String duracion = filacursor.getString(9);


                if (tipo == 1)
                {

                    if(favor== 0) {


                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }

                    else if(favor ==1)
                    {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion, tipo);
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
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion, tipo);
                        list.add(model);
                    }
                }





            }


            /* list.add(new Model(Model.IMAGE_TYPE, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor luptatem", "", R.drawable.muffinsplatano));
            list.add(new Model(Model.IMAGE_TYPE, "Ensalada rica para una dieta saludable con ingredidentes listos para prepar en casa o en cualquier otro lugar", "", R.drawable .ensalada_manzana));
            list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios de calentamiento, estiramiento y cardio","diFjQVUL7wk"/*"y5m9l8O1K_8"*///,0));
            /*list.add(new Model(Model.IMAGE_TYPE,"una sopa deliciosa de la vieja escuela","", R.mipmap.espageti));
            list.add(new Model(Model.IMAGE_TYPE, "Postre rico y nutritivo", "",R.mipmap.postres));
            list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios basicos","k_u8obkaGE4",0));

            Log.v("que es", ""+ new Model(Model.IMAGE_TYPE, "", "", 0));
*/

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);

            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_menutolbbed.this, mRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);


            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

             /*detalleimg = (Button)findViewById(R.id.btndetalleimg);
            detalleimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity_menutolbbed.this, MainActivity_contenidoreceta.class);
                    startActivity(intent);
                }
            });*/
        }



       /*public  void listimagen(int imagen) {



           LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearmenu);

           ImageView imgview = new ImageView(MainActivity_menutolbbed.this);
           imgview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT));
           imgview.setImageResource(imagen);
           //textView5.setBackground(); // hex color 0xAARRGGBB
           imgview.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
           linearLayout.addView(imgview);



           TextView Textviewer = new TextView(MainActivity_menutolbbed.this);
           Textviewer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT));
           Textviewer.setText("Una rica carne  rica y deliciosa en el que la familia pueda disfrutarlo");
           //Textviewer.setBackgroundColor(); // hex color 0xAARRGGBB
           Textviewer.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
           linearLayout.addView(Textviewer);

           ImageButton imagbuton = new ImageButton(MainActivity_menutolbbed.this);
           imagbuton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT));
           //Textviewer.setText("Una rica carne  rica y deliciosa en el que la familia pueda disfrutarlo");
           //Textviewer.setBackgroundColor(); // hex color 0xAARRGGBB
           imagbuton.setImageResource(R.mipmap.corazon);
           imagbuton.setBackgroundColor(0000);
           imagbuton.setPadding(20, 20, 20, 20);
          // String color ="#fff";
         //  int col = Integer.parseInt(color);
           //imagbuton.setBackgroundResource(R.mipmap.blanco);
           // in pixels (left, top, right, bottom)
           linearLayout.addView(imagbuton);

          /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT);
           layoutParams.gravity = Gravity.CENTER;
           layoutParams.setMargins(10, 10, 10, 10);*/

          /* ImageButton imagbuton2 = new ImageButton(MainActivity_menutolbbed.this);
          imagbuton2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT));
           //Textviewer.setText("Una rica carne  rica y deliciosa en el que la familia pueda disfrutarlo");
           //Textviewer.setBackgroundColor(); // hex color 0xAARRGGBB
           //imagbuton2.setLayoutDirection(new LinearLayout.);
//linearLayout.setLayoutParams(layoutParams);


           imagbuton2.setImageResource(R.mipmap.flecha);
           imagbuton2.setBackgroundColor(0000);
           imagbuton2.setPadding(0, 0, 20, 20);


           // String color ="#fff";
           //  int col = Integer.parseInt(color);
           //imagbuton.setBackgroundResource(R.mipmap.blanco);
           // in pixels (left, top, right, bottom)
           linearLayout.addView(imagbuton2);


           imgview.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   Intent inte  = new Intent(MainActivity_menutolbbed.this, MainActivity_contenidoreceta.class);
                   startActivity(inte);
               }
           });

            /*LenguajeListAdapter adapter = new LenguajeListAdapter(MainActivity_menutolbbed.this, lenguajeProgramacion, imgid);
            listas = (ListView) findViewById(R.id.listviewer);
            listas.setAdapter(adapter);
            listas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Slecteditem = lenguajeProgramacion[+position];
                    Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                }
            });*/
       // }








         /*   LenguajeListAdaptervideo adaptere = new LenguajeListAdaptervideo(MainActivity_menutolbbed.this, lenguajeProgramacion ,urlslink);
            listare = (ListView) findViewById(R.id.listviewer);
            listare.setAdapter(adaptere);
            listare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Slecteditem = lenguajeProgramacion[+position];
                    Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();



                }
            });*/
      //  }



   /* public  void listvideo() {
       // final ProgressDialog pDialog;

         VideoView video = new VideoView(MainActivity_menutolbbed.this);
       // try {


            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearmenu);

            video.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));


        video.setVideoPath(
                "http://www.ebookfrenzy.com/android_book/movie.mp4");

        video.start();

            // Start the MediaController
         /*  MediaController mediacontroller = new MediaController(MainActivity_menutolbbed.this);
            mediacontroller.setAnchorView(video);
            // Get the URL from String VideoURL
            //Uri video = Uri.parse(VideoURL);
            Uri videos = Uri.parse("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");

           video.setMediaController(mediacontroller);
            video.setVideoURI(videos);
            video.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
*/
         //   linearLayout.addView(video);
            //videoview.setVideoURI(video);

               /* TextView Textviewer = new TextView(MainActivity_menutolbbed.this);
                Textviewer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                Textviewer.setText("Un video de ejercicio listo para trabajar los pilates y el cuerpo");
                //Textviewer.setBackgroundColor(); // hex color 0xAARRGGBB
                Textviewer.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
                linearLayout.addView(Textviewer);

                ImageButton imagbuton = new ImageButton(MainActivity_menutolbbed.this);
                imagbuton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                //Textviewer.setText("Una rica carne  rica y deliciosa en el que la familia pueda disfrutarlo");
                //Textviewer.setBackgroundColor(); // hex color 0xAARRGGBB
                imagbuton.setImageResource(R.mipmap.corazon);
                imagbuton.setBackgroundColor(0000);
                imagbuton.setPadding(20, 20, 20, 20);
                // String color ="#fff";
                //  int col = Integer.parseInt(color);
                //imagbuton.setBackgroundResource(R.mipmap.blanco);
                // in pixels (left, top, right, bottom)
                linearLayout.addView(imagbuton);*/



      /*  } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                //  pDialog.dismiss();
                //video.start();
              //  video.start();
                video.pause();
            }
        });*/



    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
        {
            return false;
        }


        return super.onKeyDown(keyCode, event);
    }

    public String conte(){
        contex = this.getClass().getSimpleName().toString();
        return contex;

    }







}

/*


            DBHelper admin=new DBHelper(MainActivity_menutolbbed.this,"Usuario",null,1);
            SQLiteDatabase db = admin.getWritableDatabase();
            //String usuario=et1.getText().toString();
            //String contrasena=et2.getText().toString();

           // int uno =  Integer.parseInt(tp.getText().toString());

            String sqlquery= "select Nombre, imagen  from usuarios where Num_control = 1";
            fila = db.rawQuery(sqlquery,null);
            //  fila
       if (fila.moveToFirst()) {
            String texto = fila.getString(0);
            //String imagen = fila.getString(1);
            int img = Integer.parseInt(fila.getString(1));
           String tiempo = fila.getString(2);
           String aciertos = fila.getString(3);
           String resultados = fila.getString(4);
            String Nombre = fila.getString(2);


            List<Map<String, String>> data = null;
            data = new ArrayList<Map<String,String>>();

            while(fila.moveToNext()){           // percorrer nosso ResultSet em todos os registro, enquanto existir um prox.
                Map<String, String> datanum = new HashMap<String, String>();

                //int img = Integer.parseInt(fila.getString(1));
                datanum.put("A", fila.getString(0));
                //datanum.put("B", fila.getString(1));
                //  datanum.put("C", fila.getString(2));
                //    datanum.put("D", fila.getString(3));
                data.add(datanum);
            }
            String[] from = {"A"/*"B"*///};
           // int[] views = {R.id.textView,/*R.id.imageView }; // vamos criar um modelo para as linhas do Adapter
         //   AD = new SimpleAdapter(MainActivity_menutolbbed.this, data, R.layout.activity_main_listview, from, views);
        //    Lista.setAdapter(AD);
      //  }*/

       /* private void populateListView() {
            // TODO Auto-generated method stub

            ArrayAdapter<home> adapter = new MyListAdapter();
            ListView list = (ListView)findViewById(R.id.listviewer);
            list.setAdapter(adapter);
        }

        private class MyListAdapter extends ArrayAdapter<home>{

            public MyListAdapter(){

                super(MainActivity_menutolbbed.this, R.layout.activity_main_listview, myuser);
            }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //return super.getView(position, convertView, parent);
                View itemView = convertView;
                if(itemView == null){

                    itemView = getLayoutInflater().inflate(R.layout.activity_main_listview, parent, false);
                }

                home currentCar =  myuser.get(position);
                ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

                imageView.setImageResource(currentCar.geticondId());


               /* TextView makeText = (TextView) itemView.findViewById(R.id.textView);
                makeText.setText(currentCar.getMake());


                TextView yearText = (TextView) itemView.findViewById(R.id.textView);
                yearText.setText(""+ currentCar.getyearl());

                /*TextView ConectionText = (TextView) itemView.findViewById(R.id.item_txtCondition);
                ConectionText.setText(currentCar.getcondition());

                return itemView;


            }

        }
        private void populateCarList() {
            // TODO Auto-generated method stub
            myuser.add(new home("comida rica y dispensable", R.mipmap.tacos));
            myuser.add(new home("espageti gurme", R.mipmap.espageti ));
            myuser.add(new home("algo de carne", R.mipmap.hambre));

        }*/








        /*private void initListView() {
            String[] items = new String[50];
            for (int i = 0; i < 50; i++) {
                items[i] = "Item " + i;
            }
            page3.setAdapter(new ArrayAdapter<String>(MainActivity_menutabbed.this, R.layout.textview, items));
            page3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity_menutabbed.this, (String) parent.getItemAtPosition(position), Toast
                            .LENGTH_SHORT).show();
                }
            });*/


