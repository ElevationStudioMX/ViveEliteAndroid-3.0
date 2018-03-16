package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.MainActivity_recetas;
import com.example.desarrollo_elevation.viveelite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 17/05/17.
 */

public class categoria_adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model_categorias> dataSet;
    private Context mcontext;
    int total_types;

    public static class categoriaTypeViewHolder extends RecyclerView.ViewHolder {


       // TextView txtIngrediente;

ImageView imgcategoria;

        public categoriaTypeViewHolder(View itemView) {

            super(itemView);

            this.imgcategoria = (ImageView)itemView.findViewById(R.id.imagencategoria);
         //   this.txtIngrediente = (TextView) itemView.findViewById(R.id.txtingredientesycantidad);




        }

    }


    public categoria_adapter(ArrayList<Model_categorias> data, Context context) {
        this.dataSet = data;
        this.mcontext = context;
        total_types = dataSet.size();

    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case Model_categorias.categoria:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contenido_categoria, parent, false);
                return new  categoria_adapter.categoriaTypeViewHolder(view);



        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 1:

                return Model_categorias.categoria;

            default:
                return -1;
        }


    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        final Model_categorias object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {

                case Model_categorias.categoria:



                    String font = "fonts/OpenSans-Light.ttf";
                    Typeface typeface = Typeface.createFromAsset(mcontext.getAssets(), font);

                    Picasso.with(mcontext).load(object.data).into(((categoriaTypeViewHolder)holder).imgcategoria);


                    ((categoriaTypeViewHolder)holder).imgcategoria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Bundle bundle = new Bundle();

                            bundle.putString("nombrecategoria", object.name);
                            bundle.putInt("id_categoria", object.id_categoria);

                            Intent intent = new Intent(mcontext, MainActivity_recetas.class);

                            intent.putExtras(bundle);

                            mcontext.startActivity(intent);

                        }
                    });




                   // ((IngredientesTypeViewHolder)holder).txtIngrediente.setTextSize(20);
                    //((IngredientesTypeViewHolder)holder).txtIngrediente.setTypeface(typeface);

                   //if(object.cantidad.equals(" "))
                    //{
                      //  Log.v("cantidad con vacio", object.ingrediente);
                      //  ((IngredientesTypeViewHolder) holder).txtIngrediente.setText(/*object.cantidad+*/object.ingrediente);
                    //}
                    //else {
                      //  Log.v("canitdad sin vacio", object.cantidad);
                        //((IngredientesTypeViewHolder) holder).txtIngrediente.setText(object.cantidad + " " + object.ingrediente);
                   // }


                    break;

            }
        }

    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }


}

