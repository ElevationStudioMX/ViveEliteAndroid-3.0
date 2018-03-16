package com.example.desarrollo_elevation.viveelite.tabs_infranter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.desarrollo_elevation.viveelite.MainActivity_Ejercicios;
import com.example.desarrollo_elevation.viveelite.MainActivity_Favoritos;
import com.example.desarrollo_elevation.viveelite.MainActivity_QR;
import com.example.desarrollo_elevation.viveelite.MainActivity_categorias;
import com.example.desarrollo_elevation.viveelite.MainActivity_galeria_imagen;
import com.example.desarrollo_elevation.viveelite.MainActivity_loginspotify;
import com.example.desarrollo_elevation.viveelite.MainActivity_menutolbbed;
import com.example.desarrollo_elevation.viveelite.MainActivity_productos_elite;
import com.example.desarrollo_elevation.viveelite.MainActivity_recetas;
import com.example.desarrollo_elevation.viveelite.Main_Activity_inicio;
import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.R;

import java.util.ArrayList;

import static com.example.desarrollo_elevation.viveelite.R.id.view;

/**
 * Created by Desarrollo_Elevation on 16/05/17.
 */

public class Menu extends Fragment {

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menu_activity, container, false);

        productos =(Button)view.findViewById(R.id.btnproductosm);
        productos.getBackground().setAlpha(00);
        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity_productos_elite.class);
                startActivity(intent);
            }
        });

        btnfavorito =(Button)view.findViewById(R.id.btnfavoritosm);
        btnfavorito.getBackground().setAlpha(00);
        btnfavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MainActivity_Favoritos.class);
                startActivity(intent);
            }
        });


        btnreceta = (Button) view.findViewById(R.id.btnrecetasm);

        btnreceta.getBackground().setAlpha(00);

        btnreceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent inte = new Intent(getContext(), MainActivity_categorias.class/*MainActivity_recetas.class*/ /*prueba_recycleviwer_base_datos.class*/);
                startActivity(inte);
            }
        });


        btnejercicios = (Button)view.findViewById(R.id.btnejerciciom);
        btnejercicios.getBackground().setAlpha(00);

        btnejercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getContext(), MainActivity_Ejercicios.class /*segunda_prueba_json.class*/);
                startActivity(inte);
            }
        });


        btnmemoria =(Button)view.findViewById(R.id.btnmemoriasm);
        btnmemoria.getBackground().setAlpha(00);
        btnmemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getContext(), MainActivity_galeria_imagen.class);
                startActivity(inte);

            }
        });
        btnmusicas =(Button) view.findViewById(R.id.btnmusicam);

        btnmusicas.getBackground().setAlpha(00);
        btnmusicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getContext(), MainActivity_loginspotify.class);
                startActivity(inte);
            }
        });

        btnpromocionesQR =(Button)view.findViewById(R.id.btnpromocionesm);
        btnpromocionesQR.getBackground().setAlpha(00);
        btnpromocionesQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity_QR.class/* Main_jason_rest.class*/);
                startActivity(intent);

                    /*DBHome dbHome = new DBHome(MainActivity_menutolbbed.this, name_database_elite, null, 1);

                    SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
//Metodo para eliminar datos de la base de datos

                    sqLiteDatabase.execSQL("delete from contenido where id_tipo=1 and  id=1");
                    sqLiteDatabase.execSQL("delete from contenido where id_tipo=2 and  id=1");
                    //sqLiteDatabase.execSQL("delete from fecha_actualizacion_receta");

                    Log.v("mensaje", "do de la base de datos borrado exitosamente :) .l.");*/


            }}
        );




        return  view;
    }
}
