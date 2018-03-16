package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.MainActivity_productos_elite;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.viveelite.TextViewEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 09/05/17.
 */

public class adapter_productos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Model_productos> dataSet;
    private Context mcontext;
    int total_types;

    public static class productosTypeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgproductos;
        TextView titulo;
        TextViewEx contenido;

        public productosTypeViewHolder(View itemView) {
            super(itemView);
            this.imgproductos = (ImageView) itemView.findViewById(R.id.imagenproductos);
            this.titulo =(TextView)itemView.findViewById(R.id.txtproductotitulo);
            this.contenido=(TextViewEx)itemView.findViewById(R.id.txtproductocontenido);
        }
    }

    public adapter_productos(ArrayList<Model_productos> data, Context context) {
        this.dataSet = data;
        this.mcontext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Model_productos.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos, parent, false);
                return new adapter_productos.productosTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 1:

                return Model_productos.IMAGE_TYPE;

            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Model_productos object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {
                case Model_productos.IMAGE_TYPE:
                    Picasso.with(mcontext).load(object.imagen).resize(1000,0).into( ((productosTypeViewHolder)holder).imgproductos);
                    String font = "fonts/OpenSans-Light.ttf";
                    String font1 = "fonts/Gotham-Medium.ttf";

                    Typeface facei= Typeface.createFromAsset(mcontext.getAssets(),font);

                    Typeface typeface = Typeface.createFromAsset(mcontext.getAssets(), font1);

                    ((productosTypeViewHolder)holder).titulo.setTypeface(typeface);
                    ((productosTypeViewHolder)holder).titulo.setText(object.titulo);
                    ((productosTypeViewHolder)holder).titulo.setTextSize(26);
                    ((productosTypeViewHolder)holder).titulo.setTextColor(Color.parseColor("#3eb1e2"));

                    ((productosTypeViewHolder)holder).contenido.setTypeface(facei);
                    ((productosTypeViewHolder)holder).contenido.setText(object.contenido, true);
                    ((productosTypeViewHolder)holder).contenido.setTextSize(18);
                    Log.e(this.getClass().getSimpleName(), " ---------->" + object.contenido);
                    ((productosTypeViewHolder)holder).contenido.setTextColor(Color.parseColor("#7c7c7c"));
                    //((productosTypeViewHolder)holder).imgproductos.setImageResource(object.imagen);

                 //   ((productosTypeViewHolder)holder).imgproductos.setScaleType(ImageView.ScaleType.FIT_XY);

                    /*MainActivity_productos_elite elite = new MainActivity_productos_elite();

                    int heigth = elite.getAlturatotal();

                    ((productosTypeViewHolder)holder).imgproductos.getLayoutParams().height = heigth/4;


                    ((productosTypeViewHolder)holder).imgproductos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
*/
                   /* String font = "fonts/OpenSans-Light.ttf";
                    Typeface typeface = Typeface.createFromAsset(mcontext.getAssets(), font);

                    ((IngredientesTypeViewHolder)holder).txtIngrediente.setTextSize(20);
                    ((IngredientesTypeViewHolder)holder).txtIngrediente.setTypeface(typeface);
                    ((IngredientesTypeViewHolder)holder).txtIngrediente.setText(object.cantidad+" "+object.ingrediente);

*/
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}