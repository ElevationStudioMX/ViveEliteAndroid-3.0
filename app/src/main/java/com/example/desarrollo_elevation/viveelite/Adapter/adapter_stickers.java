package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.viveelite.TextViewEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 25/05/17.
 */

public class adapter_stickers extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private ArrayList<model_sticker> dataSet;
private Context mcontext;
        int total_types;

public static class stickersTypeViewHolder extends RecyclerView.ViewHolder {



    ImageView imgsticker;




    public stickersTypeViewHolder(View itemView) {

        super(itemView);

        this.imgsticker = (ImageView) itemView.findViewById(R.id.imgstickers);




    }

}


    public adapter_stickers(ArrayList<model_sticker> data, Context context) {
        this.dataSet = data;
        this.mcontext = context;
        total_types = dataSet.size();

    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case model_sticker.sticker_type:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticker, parent, false);
                return new adapter_stickers.stickersTypeViewHolder(view);



        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 1:

                return model_sticker.sticker_type;

            default:
                return -1;
        }


    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        model_sticker object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {

                case model_sticker.sticker_type:



                    Picasso.with(mcontext).load(object.sticker).resize(300,0).into( ((stickersTypeViewHolder)holder).imgsticker);

                    ((stickersTypeViewHolder)holder).imgsticker.setColorFilter(Color.WHITE);


                    break;

            }
        }

    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }


}