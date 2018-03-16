package com.example.desarrollo_elevation.viveelite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import java.util.ArrayList;

public class MainActivity_detallewebview extends AppCompatActivity {
    WebView webs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detallewebview);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarweb);
        toolbar.setTitle("prueba WebView");
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Model> list= new ArrayList<>();

        /*list.add(new Model(Model.IMAGE_TYPE, "Comida para llenar el estomago bien rico", "", R.mipmap.hambre));
        list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios","jFYAp42rMEA",0));
        list.add(new Model(Model.IMAGE_TYPE,"una sopa deliciosa de la vieja escuela","", R.mipmap.espageti));
        list.add(new Model(Model.IMAGE_TYPE, "Tacos bien ricos mmmmmmmmmmmmmm", "",R.mipmap.tacos));
        list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios para niños","q1sCQMCyZtU",0));

*/
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layerweb);

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_detallewebview.this, mRecyclerView );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_detallewebview.this, OrientationHelper.HORIZONTAL, false);


        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);


          webs =(WebView)this.findViewById(R.id.wevb);

        webs.getSettings().setJavaScriptEnabled(true);
        webs.getSettings().setAppCacheEnabled(false);
        webs.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        String d="que honda este es el ejercicio de hoy abdominales extremos hasta el canzancio" +
                "correr trecientas centadillas, comer una tostada" +
                "un licuado de lechuga" +
                "una barra de fibra" +
                "10 lagartijas de series de 3" +
                "30 abdominales de series de 3" +
                "15 sentadillas con series de 3" +
                "trotar por todo el lugar durante 20 minutos " +
                "correr trecientos kilometros, ejercicios de respiraciòn" +
                "caminata por 15 minutos y por ultimo 10 lagartijas mas";

        String datos ="<html>\" +\n" +
                "                \"<body>\" +\n" +
                             " \" <p> hola</p>\"+\n"+

                "                \"</body>\" +\n" +
                "                \"</html>\"";

        String datatos ="<html><body bgcolor=#ffefe text=#000>" +
                "<p style=\"text-align: justify;\"> "+d+" </p>"+
                " </body>" +
                "</html>";

        webs.loadData(datatos, "text/html", "UTF-16");



    }
}
