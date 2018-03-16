package com.example.desarrollo_elevation.viveelite.json;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.Adapter.adapterselectframe;
import com.example.desarrollo_elevation.viveelite.MainActivity_Editarphoto;
import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.Modelselectframe;
import com.example.desarrollo_elevation.viveelite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 04/04/17.
 */

public class Adapter_Json extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private ArrayList<Model_Json> dataSet;
        Context mContext;
        int total_types;
//MediaPlayer mPlayer;
private boolean fabStateVolume = false;
// ImageView marco;

private ImageView mar;



public static class TypeViewHolder extends RecyclerView.ViewHolder {

TextView adminCode2;
TextView adminCode3;
TextView adminName3;
TextView adminCode1;
TextView adminName2;
TextView lng;
TextView countrycode;
TextView postalcode;
TextView adminName1;
TextView placeName;
TextView lat;

    //TextView txtType;
    //ImageView image;
    //ImageView marco;
    // TextView detalleimg;


    public TypeViewHolder(View itemView) {
        super(itemView);

        this.adminCode2 =(TextView)itemView.findViewById(R.id.txtadminCode2);
        this.adminCode3 = (TextView)itemView.findViewById(R.id.txtadminCode3);
        this.adminName3 = (TextView)itemView.findViewById(R.id.txtadminName3);
        this.adminCode1 = (TextView)itemView.findViewById(R.id.txtadminCode1);
        this.adminName2 = (TextView)itemView.findViewById(R.id.txtadminName2);
        this.lng = (TextView)itemView.findViewById(R.id.txtlng);
        this.countrycode = (TextView)itemView.findViewById(R.id.txtcountryCode);
        this.postalcode = (TextView)itemView.findViewById(R.id.txtpostalcode);
        this.adminName1 = (TextView)itemView.findViewById(R.id.txtadminName1);
        this.placeName = (TextView)itemView.findViewById(R.id.txtplaceName);
        this.lat = (TextView)itemView.findViewById(R.id.txtlat);


        //this.txtType = (TextView) itemView.findViewById(R.id.txttextoimg);
      //  this.image = (ImageView) itemView.findViewById(R.id.imagemarcosparaseleccionar);
        //this.marco = (ImageView)itemView.findViewById(R.id.imagenviewmarco);


         /*   this.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    marco.setImageResource(R.drawable.marco2);
                }
            });*/

        //this.detalleimg=(TextView) itemView.findViewById(R.id.btndetalleimg);

    }

}






    public Adapter_Json(ArrayList<Model_Json> data, Context context) {
        this.dataSet = data;
        this.mContext = context;

        total_types = dataSet.size();

        Log.v("total tipos", ""+total_types);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        // View view1;
        switch (viewType) {

            case Model_Json.TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json2, parent, false);
                return new Adapter_Json.TypeViewHolder(view);

            /*view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main__editarphoto, parent, false);
            return new adapterselectframe.ImageTypeViewHolder(view1);*/

        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 1:

                return Model_Json.TYPE;

            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final Model_Json object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {

                case Model_Json.TYPE:


                    ((TypeViewHolder) holder).adminCode2.setText("adminCode2: "+object.admincode2);
                    ((TypeViewHolder) holder).adminCode3.setText("adminCode3: "+object.admincode3);
                    ((TypeViewHolder) holder).adminName3.setText("adminName3: "+object.adminName3);
                    ((TypeViewHolder) holder).adminCode1.setText("adminCode1: "+object.admincode1);
                    ((TypeViewHolder) holder).adminName2.setText("adminName2: "+object.adminName2);
                    ((TypeViewHolder) holder).lng.setText("lng: "+object.lng);
                    ((TypeViewHolder) holder).countrycode.setText("country code: "+object.countrycode);
                    ((TypeViewHolder) holder).postalcode.setText("postal code: "+object.postalcode);
                    ((TypeViewHolder) holder).adminName1.setText("adminName1: "+object.adminName1);
                    ((TypeViewHolder) holder).placeName.setText("placeName: "+object.placeName);
                    ((TypeViewHolder) holder).lat.setText("lat: "+object.lat);







                    break;



            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
private class InsideWebViewClient extends WebViewClient {
    @Override
    // Force links to be opened inside WebView and not in Default Browser
    // Thanks http://stackoverflow.com/a/33681975/1815624
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}


}
