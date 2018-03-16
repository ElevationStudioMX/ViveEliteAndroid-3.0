package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.MainActivity_Editarphoto;
import com.example.desarrollo_elevation.viveelite.MainActivity_preview_image;
import com.example.desarrollo_elevation.viveelite.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import static com.example.desarrollo_elevation.viveelite.R.id.view;

/**
 * Created by Desarrollo_Elevation on 07/04/17.
 */

public class Adapter_galeria extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mcontext;
    String [] filepath;

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


       ImageView imagen_galeria;



        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            imagen_galeria =(ImageView)itemView.findViewById(R.id.imageviewgaleria);
        }
    }

    public Adapter_galeria(Context context, String []file)
    {
        this.mcontext = context;
        this.filepath = file;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_row_galeria, parent, false);
        return new Adapter_galeria.ImageTypeViewHolder(view);

    //    return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        Picasso.with(mcontext).load(new File(filepath[position])).resize(250,0).into( ((ImageTypeViewHolder)holder).imagen_galeria);

        ((ImageTypeViewHolder)holder).imagen_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mcontext, MainActivity_preview_image.class);
                Bundle bundle = new Bundle();
                String immg = filepath[position];
                bundle.putString("keyimagen",immg);
                intent.putExtras(bundle);
                mcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return filepath.length;
    }
}
