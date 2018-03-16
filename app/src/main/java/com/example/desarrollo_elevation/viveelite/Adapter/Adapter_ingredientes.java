package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.R;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 24/04/17.
 */

public class Adapter_ingredientes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model_Ingredientes> dataSet;
    private Context mcontext;
    int total_types;

    public static class IngredientesTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtIngrediente;



        public IngredientesTypeViewHolder(View itemView) {

            super(itemView);

            this.txtIngrediente = (TextView) itemView.findViewById(R.id.txtingredientesycantidad);




        }

    }


    public Adapter_ingredientes(ArrayList<Model_Ingredientes> data, Context context) {
        this.dataSet = data;
        this.mcontext = context;
        total_types = dataSet.size();

    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case Model_Ingredientes.Ingredientes:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredientes, parent, false);
                return new Adapter_ingredientes.IngredientesTypeViewHolder(view);



        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 1:

                return Model_Ingredientes.Ingredientes;

            default:
                return -1;
        }


    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Model_Ingredientes object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {

                case Model_Ingredientes.Ingredientes:



                    String font = "fonts/OpenSans-Light.ttf";
                    Typeface typeface = Typeface.createFromAsset(mcontext.getAssets(), font);

                    ((IngredientesTypeViewHolder)holder).txtIngrediente.setTextSize(20);
                    ((IngredientesTypeViewHolder)holder).txtIngrediente.setTypeface(typeface);

                    if(object.cantidad.equals(" "))
                    {
                        Log.v("cantidad con vacio", object.ingrediente);
                        ((IngredientesTypeViewHolder) holder).txtIngrediente.setText(/*object.cantidad+*/object.ingrediente);
                    }
                    else {
                        Log.v("canitdad sin vacio", object.cantidad);
                        ((IngredientesTypeViewHolder) holder).txtIngrediente.setText(object.cantidad + " " + object.ingrediente);
                    }


            break;

            }
        }

    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }


}
