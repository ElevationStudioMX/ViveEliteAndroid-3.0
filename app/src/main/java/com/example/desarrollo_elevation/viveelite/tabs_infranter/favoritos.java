package com.example.desarrollo_elevation.viveelite.tabs_infranter;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.example.desarrollo_elevation.viveelite.MainActivity_menutolbbed;
import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.MultiViewTypeAdapter;
import com.example.desarrollo_elevation.viveelite.R;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 16/05/17.
 */

public class favoritos extends Fragment {

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


    private  static  String name_database_elite= "elite_v15";



    private Cursor cursor;
    private ArrayList<Model> models;

    RecyclerView mRecyclerView;

    TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favoritos, container, false);

         text = (TextView)view.findViewById(R.id.id_textos_favoritos);

        String font = "fonts/OpenSans-Light.ttf";
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), font);

        text.setTypeface(typeface);
        text.setTextSize(16);



        DBHome dbhome = new DBHome(getContext(), name_database_elite, null, 1);

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


        if(models.size() ==0)
        {
            text.setVisibility(View.VISIBLE);
            text.setText("Aún no haz agregado favoritos, presiona el corazón en los posts para marcarlos como favoritos y acceder a ellos fácilmente.");
        }

        else {
            text.setVisibility(View.INVISIBLE);
        }

//            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_menutolbbed.this);
        LinearLayoutManager linearLayoutManagerer = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);


        Log.v("layoir",""+linearLayoutManagerer);



        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewerfavoritosnew);

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(models, getContext(), recyclerView);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_menutolbbed.this, OrientationHelper.VERTICAL, false);*/



        Log.v("vitctor", ""+R.id.recyclerViewerfavoritos2);

        Log.v("layoir",""+recyclerView);


        recyclerView.setLayoutManager(linearLayoutManagerer);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        return  view;
    }
}