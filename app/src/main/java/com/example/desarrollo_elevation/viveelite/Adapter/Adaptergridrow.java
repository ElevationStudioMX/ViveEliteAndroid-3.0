package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Picture;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 16/01/17.
 */

public class Adaptergridrow extends BaseAdapter {
    Context context;
    //int layoutResourceId;
    // String[] titulo;
    //int[] imagen;
    //String [] imagen;

    ArrayList<String> imagen;
    private  String name_database_elite= "elite_v15";
    private Cursor cursor;
    int total_types;
//    String []filepath;



    LayoutInflater inflater;
    // ArrayList<ClipData.Item> data = new ArrayList<ClipData.Item>();

    public Adaptergridrow(Context context, /*String[] titulo,*/ ArrayList<String> imagen )/*int layoutResourceId, ArrayList<ClipData.Item> data*/ {
        // super(context, layoutResourceId, data); this.layoutResourceId = layoutResourceId;
        this.context = context; //this.data = data;
        //this.titulo = titulo;
        this.imagen = imagen;
      //  this.total_types = imagen.size();

    }

    public int getCount() {
        // return titulo.length;
       // return imagen.length;

        return imagen.size();
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
        View itemview = inflater.inflate(R.layout.row_grid, parent, false);


        //text =(TextView)itemview.findViewById(R.id.item_text);
        image = (ImageView) itemview.findViewById(R.id.item_image);
        Picasso.with(context).load(imagen.get(position)).resize(300, 0).into(image);

        DBHome home = new DBHome(context, name_database_elite, null, 1);

        SQLiteDatabase database = home.getWritableDatabase();

        String query ="select color_filter from sticker where sticker ='"+imagen.get(position)+"'";
        cursor = database.rawQuery(query, null);


        while (cursor.moveToNext())
        {
            int col_filt = cursor.getInt(0);

            Log.v("LINEA 97", "LINEA 97 col_filt "+col_filt);

            if(col_filt == 1)
            {
                image.setColorFilter(Color.WHITE);
            }

            else
            {

            }


        }

        database.close();

        //  text.setText(titulo[position]);
        //image.setImageResource(imagen[position]);

        /*Log.v("position", ""+imagen.get(position));

        if(position > 2)
        {
            image.setColorFilter(Color.WHITE);
        }*/



        return itemview;



    }
}