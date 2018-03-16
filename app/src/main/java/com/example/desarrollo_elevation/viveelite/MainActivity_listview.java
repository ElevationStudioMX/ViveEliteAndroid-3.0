package com.example.desarrollo_elevation.viveelite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity_listview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarlayer);
        toolbar.setTitle("layer");
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Model> list= new ArrayList<>();

       /* list.add(new Model(Model.IMAGE_TYPE, "Comida para llenar el estomago bien rico", "", R.mipmap.hambre));
        list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios","jFYAp42rMEA",0));
        list.add(new Model(Model.IMAGE_TYPE,"una sopa deliciosa de la vieja escuela","", R.mipmap.espageti));
        list.add(new Model(Model.IMAGE_TYPE, "Tacos bien ricos mmmmmmmmmmmmmm", "",R.mipmap.tacos));
        list.add(new Model(Model.VIDEO_TYPE,"Video para realizar ejercicios para niños","q1sCQMCyZtU",0));
*/



        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layer);
        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_listview.this, mRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_listview.this, OrientationHelper.HORIZONTAL, false);


        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }
}
