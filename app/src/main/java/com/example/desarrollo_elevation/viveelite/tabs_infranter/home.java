package com.example.desarrollo_elevation.viveelite.tabs_infranter;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.EndlessRecyclerOnScrollListener;
import com.example.desarrollo_elevation.viveelite.MainActivity_menutolbbed;
import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_playlist;
import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.MultiViewTypeAdapter;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.viveelite.popwindow;
import com.example.desarrollo_elevation.viveelite.spotify.ResultListScrollListener;

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

import static android.R.attr.name;
import static com.example.desarrollo_elevation.viveelite.R.id.view;

/**
 * Created by Desarrollo_Elevation on 16/05/17.
 */

public class home extends Fragment {

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

    public static ArrayList<Model> list;
    private  String urlslink[]= new String[]{"http://www.androidbegin.com/tutorial/AndroidCommercial.3gp","http://www.androidbegin.com/tutorial/AndroidCommercial.3gp","http://www.androidbegin.com/tutorial/AndroidCommercial.3gp"};

    private  static


    Bundle bundle;


    private static String contex;


    private static String link;


    private  static  String linkfristhome;// ="http://www.elevation.com.mx/pages/AppElite/web-services/elite/home-first";


    private  static  int  bandera = 0;

    private  static  String video;
    private  static  String imagen;
    private  static  int nota;

    private  static  String fecha_destinada;


    private  static  String name_database_elite= "elite_v15";
    private  static String query;

    private static  String name_tipo;

    private Cursor cursor;
    private ArrayList<Model> models;

    private  static  int offest = 0;
    private  static int pagesize =3;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 2;
    int firstVisibleItem, visibleItemCount, totalItemCount;




    private MultiViewTypeAdapter adapter;
    //MultiViewTypeAdapter adapter; //= new MultiViewTypeAdapter(list, getContext());
    private RecyclerView mRecyclerView;
    private  LinearLayoutManager linearLayoutManager;// = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
    private Handler handler;

    private   int contador = 0;

private  static  String link_tercer_caso;
    private  static  String fecha;

   /* private home.ScrollListener mScrolllistener = new home.ScrollListener(linearLayoutManager);

    private class ScrollListener extends ResultListScrollListener {

        public ScrollListener(LinearLayoutManager linearLayout){super(linearLayout);}

        @Override
        public void onLoadMore() {

            offest += pagesize;
            query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  order by fecha_publicacion desc limit "+offest+", "+offest+" ";
            Log.v("offest", "offest: "+offest+" pagesize"+pagesize);
//                    mActionListener.loadMoreResult();

            DBHome dbHome = new DBHome(getContext(), name_database_elite, null, 1);
            //startActivity(new Intent(getContext(), popwindow.class));
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

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

                 adapter = new MultiViewTypeAdapter(list, getContext());

                adapter.addData(list);




            }









        }
    }*/


    private static int current_page = 1;

    private int ival = 0;
    private int loadLimit = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewerhome);
       // Log.v("linea 246", fecha );




        DBHome dbHome = new DBHome(getContext(), name_database_elite, null, 1);
        //startActivity(new Intent(getContext(), popwindow.class));
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            ContentValues values = new ContentValues();
            String sql = "select view from bandera_popwindow";
            filacursor= sqLiteDatabase.rawQuery(sql, null );
            if (filacursor.moveToFirst())
            {
                int vista = filacursor.getInt(0);

                if(vista == 0)
                {
                  //  Log.v("vista pop", ""+vista);

                    values.put("view", 1);
                    sqLiteDatabase.update("bandera_popwindow", values, null,null);
                    startActivity(new Intent(getContext(), popwindow.class));

                }

                else{

                }


            }





        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



        String tiempo = String.valueOf(tiem.format(sq));


        //dbHome = new DBHome(getContext(),name_database_elite, null, 1);

       // SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

        String fecha = "select fecha from fecha_actualizacion";

        filacursor =  sqLiteDatabase.rawQuery(fecha, null);

        if (filacursor.moveToFirst())
        {
            fecha_destinada =  filacursor.getString(0);
          //  Log.v("fecha destinada", fecha_destinada);

        }



        if (fecha_destinada.equals(""))
        {
          //  Log.v("donde paso el json", "json paso por home frist");

//            Log.v("link homefrist", linkfristhome);


            link ="http://www.elevation.com.mx/pages/AppElite/web-services/elite/home2/"+bandera+"/"+tiempo+"";
            new JsonTask().execute(link);
        }

        else {



            bandera = 1;
            //Log.v("donde paso el json", "json esta pasando por home");
           // Log.v("fecha de actualizacion", fecha_destinada);
            link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/home2/"+bandera+"/"+fecha_destinada+"";

           // Log.v("Link", link);

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

           // Log.v("fecham nueva", fecha_new);
        }



        return  view;
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


                //Log.v("parentjson", ""+parentjson);
                //Log.v("DATA", ""+jsarreglo);

                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");



               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/


                DBHome db = new DBHome(getContext(), name_database_elite, null, 1);

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

                    name_tipo ="";


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


                    //Log.v("imagen", ""+imagen);
                    //Log.v("video", ""+video);


                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    if (tipo_Nota.equals("RECETA"))
                    {
                        nota = 1;
                        name_tipo="recetas";
                    }
                    else  if (tipo_Nota.equals("EJERCICIO"))
                    {
                        nota = 2;
                        name_tipo ="ejercicios";
                    }



                    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                    //Log.v("id nota", ""+nota_id);
                    //Log.v("tipo nota", ""+tipo_Nota);
                    filacursor = database.rawQuery(query, null);

                    if(filacursor.moveToFirst())
                    {
                        int contador = filacursor.getInt(0);

                        //Log.v("contador", ""+contador);

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
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+/*tipo_Nota*/name_tipo+"/"+imagen);

                            }

                            else {

                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/no-imagen.jpg"/*"http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen*/);
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



            DBHome dbHome = new DBHome(getContext(), name_database_elite, null, 1);

            final SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

        //    list = new ArrayList<>();

             query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha_publicacion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  order by fecha_publicacion desc limit 0,10";

            filacursor = sqLiteDatabase.rawQuery(query, null);

            list= new ArrayList<>();



            //Log.v("702", ""+list.size());
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
                 fecha =  filacursor.getString(10);

               // Log.v("Linea 735", fecha);
                Log.v("Linea 742", "id "+id+" titulo "+titulo+" url imagen" +contenido);

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

                //Log.v("752", ""+list.size());



            }

            //Log.v("769", fecha);
            //Log.v("758", ""+list.size());


            //loadData(current_page);
            handler = new Handler();

            //mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);
           /* MultiViewTypeAdapter*/
           // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);

          // RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);


           // adapter.addData(list);


            linearLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            adapter = new MultiViewTypeAdapter(list, getContext(), mRecyclerView);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(adapter);




            adapter.setOnLoadMoreListener(new MultiViewTypeAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
            //    list.add(null);


                    bandera = 0;


                   //adapter.notifyItemInserted(list.size()) ;

                    link_tercer_caso= "http://www.elevation.com.mx/pages/AppElite/web-services/elite/home2/"+bandera+"/"+fecha+"";
                    new JsonTaskhomerfrist().execute(link_tercer_caso);


                    handler.postDelayed(new Runnable()


                    {
                        @Override
                        public void run() {
//                            list.remove(list.size() /*- 1*/);
                          //  adapter.notifyItemRemoved(list.size());
                           //for (int i = 0; i < 5; i++) {


                         //   Log.v("Linea 829", "bandera "+bandera+" fecha"+fecha);
                            //Log.v("Linea 820", "bandera "+bandera+" fecha"+fecha);





                                //Log.v("ester", "paso por aqui");
                                contador = contador + 1;
                                query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha_publicacion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  order by fecha_publicacion desc limit "+contador*10+",10";

                           // Log.v("Linea 840", query);

                                filacursor = sqLiteDatabase.rawQuery(query, null);


                            //Log.v("813", ""+list.size());
                          //      list= new ArrayList<>();




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
                                     fecha =  filacursor.getString(10);

                                    //Log.v("base de datos", "id "+id+ " titulo "+titulo+" contenido "+contenido);

                                    //Log.v("832", ""+id_tipo_contenido);
                                    // Log.v("fecha", fecha);

                                    if (tipo == 1)
                                    {

                                        if(favor== 0) {


                                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                                            list.add(model);
                                            adapter.notifyItemInserted(list.size());
                                        }

                                        else if(favor ==1)
                                        {
                                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                                            list.add(model);
                                            adapter.notifyItemInserted(list.size());
                                        }
                                    }

                                    else if(tipo == 2)
                                    {
                                        if(favor==0) {
                                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion, tipo);
                                            list.add(model);
                                            adapter.notifyItemInserted(list.size());
                                        }

                                        else if (favor == 1)
                                        {
                                            Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check_red, id, link, preparacion, duracion, tipo);
                                            list.add(model);
                                            adapter.notifyItemInserted(list.size());
                                        }
                                    }





                                }




                                //list.add("Item" + (list.size() + 1));








                            //}
                            adapter.setLoaded();
                        }
                    }, 2000);
                    System.out.println("load");
                }
            });


          /*  mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                                                  @Override
                                                  public void onLoadMore(int current_page) {


                                                      loadMoreData(current_page);
                                                  }
                                              });*/


                    // mScrolllistener.reset();

               /* adapter_jason_contry adapter_json = new adapter_jason_contry(list, Main_jason_rest.this);



                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter_json);*/


        }








    }



    private void loadData(int current_page) {

        // I have not used current page for showing demo, if u use a webservice
        // then it is useful for every call request

        DBHome dbHome = new DBHome(getContext(), name_database_elite, null, 1);

        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

        query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  order by fecha_publicacion desc";


        list = new ArrayList<>();

        filacursor = sqLiteDatabase.rawQuery(query, null);

        for (int i = ival; i <= loadLimit; i++) {


//Log.v("iva1", ""+ival);

         //list= new ArrayList<>();




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

                //Log.v("contenitdo", contenido);
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
            ival++;
            //Log.v("iva2", ""+ival);
        }

    }
    // adding 10 object creating dymically to arraylist and updating recyclerview when ever we reached last item
    private void loadMoreData(int current_page) {

        // I have not used current page for showing demo, if u use a webservice
        // then it is useful for every call request


        DBHome dbHome = new DBHome(getContext(), name_database_elite, null, 1);

        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

        query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido  order by fecha_publicacion desc";


        list = new ArrayList<>();

        filacursor = sqLiteDatabase.rawQuery(query, null);


        loadLimit = ival + 10;

        //Log.v("limit",  ""+loadLimit);

        for (int i = ival; i <= loadLimit; i++) {
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
                 fecha =  filacursor.getString(10);

                //Log.v("contenitdo", contenido);
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








            ival++;
        }

        adapter.notifyDataSetChanged();

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


                //Log.v("parentjson", ""+parentjson);
                //Log.v("DATA", ""+jsarreglo);

                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");



               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/


                DBHome db = new DBHome(/*MainActivity_menutolbbed.*/getContext(), name_database_elite, null, 1);

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
                    name_tipo="";
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


                    //Log.v("imagen", ""+imagen);
                    //Log.v("video", ""+video);


                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    if (tipo_Nota.equals("RECETA"))
                    {
                        nota = 1;
                        name_tipo="recetas";
                    }
                    else  if (tipo_Nota.equals("EJERCICIO"))
                    {
                        nota = 2;
                        name_tipo="ejercicios";
                    }



                    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                    //Log.v("id nota", ""+nota_id);
                    //Log.v("tipo nota", ""+tipo_Nota);
                    filacursor = database.rawQuery(query, null);
                    //Log.v("linea 1284", ""+query);
                    //Log.v("linea 1285", ""+filacursor.getCount());
                    if(filacursor.moveToFirst())
                    {
                        int contador = filacursor.getInt(0);

                        //Log.v("contador", ""+contador);

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
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+/*tipo_Nota*/name_tipo+"/"+imagen);

                            }


                            else {

                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/no-imagen.jpg"/*"http://www.elevation.com.mx/pages/AppElite/admin/assets/images/"+tipo_Nota+"/"+imagen*/);
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



/*
            DBHome dbHome = new DBHome(getContext(), name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list = new ArrayList<>();

            query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido order by fecha_publicacion desc ";

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



             adapter = new MultiViewTypeAdapter(list, getContext(), mRecyclerView);
            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);

           // RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer);

          /*  mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int current_page) {

                }
            });*/

          /*  mRecyclerView.setLayoutManager(linearLayoutManager);


            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
           // mRecyclerView.addOnScrollListener(mScrolllistener);




            mRecyclerView.setAdapter(adapter);
            adapter.setOnLoadMoreListener(new MultiViewTypeAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    adapter.notifyItemInserted(list.size() - 1);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list.remove(list.size() - 1);
                            adapter.notifyItemRemoved(list.size());
                            for (int i = 0; i < 15; i++) {


                               // list.add("Item" + (list.size() + 1));







                                adapter.notifyItemInserted(list.size());
                            }
                            adapter.setLoaded();
                        }
                    }, 2000);
                    System.out.println("load");
                }
            });

            //mScrolllistener.reset();
               /* adapter_jason_contry adapter_json = new adapter_jason_contry(list, Main_jason_rest.this);



                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter_json);*/






        }








    }


    /*@Override
    public void reset() {
        mScrolllistener.reset();
    }*/





}