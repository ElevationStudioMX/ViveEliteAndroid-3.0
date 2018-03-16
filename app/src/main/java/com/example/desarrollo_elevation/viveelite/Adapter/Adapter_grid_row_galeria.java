package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Desarrollo_Elevation on 31/01/17.
 */

public class Adapter_grid_row_galeria extends BaseAdapter {
    Context context;
    //se utilizo para imagenes estaticas
    //  int[] imagen;
    LayoutInflater inflater;

    String []filepath;

    public Adapter_grid_row_galeria(Context context, /*int[] imagen*/String []filepath){

        this.context = context;

        this.filepath = filepath;
      //  this.imagen = imagen;



    }

    public int getCount() {
        // return titulo.length;
        return filepath.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View Convertview, ViewGroup parent) {
        TextView text;
        ImageView image;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.grid_row_galeria, parent, false);


        //text =(TextView)itemview.findViewById(R.id.item_text);
        image = (ImageView) itemview.findViewById(R.id.imageviewgaleria);
        Picasso.with(context).load(new File(filepath[position])).resize(250, 0).into(image);




        //  text.setText(titulo[position]);
        //image.setImageResource(imagen[position]);
        //image.setColorFilter(Color.WHITE);

        return itemview;
    }
}