package com.example.desarrollo_elevation.viveelite.spotify;

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

import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_playlist;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.PlaylistSimple;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desarrollo_Elevation on 09/03/17.
 */

public class adapter_playlist extends RecyclerView.Adapter<adapter_playlist.ViewHolder> {

    private final List</*Track*/PlaylistSimple> mItems = new ArrayList<>();

    //private final List<Album> mI = new ArrayList<>();
    private final Context mContext;
    private final ItemSelectedListener mListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView nameplaylist;
        //public final TextView numeroplaylist;
        public final ImageView imagenplaylis;

        public ViewHolder(View itemView) {
            super(itemView);
            nameplaylist = (TextView) itemView.findViewById(R.id.idnameplaylist);
           // numeroplaylist = (TextView) itemView.findViewById(R.id.idtotalcancionesplaylist);
            imagenplaylis = (ImageView) itemView.findViewById(R.id.imageViewplaylist);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {




            //MainActivity_pruebacombinacion ma = new MainActivity_pruebacombinacion();
            //ma.chinga(getAdapterPosition());
            //int S=ma.chinga(getAdapterPosition());





            notifyItemChanged(getLayoutPosition());

            mListener.onItemSelected(v, mItems.get(getAdapterPosition()));

            PlaylistSimple item = mItems.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            String url = item.uri;
            bundle.putString("key", url);

           /* Intent intent = new Intent(mContext, MainActivity_mostrar_track_playlist.class);
            intent.putExtras(bundle);
            mContext.startService(intent);*/


            //  Log.d("se mamaron otra vez", ""+S);

        }
    }

    public interface ItemSelectedListener {
        void onItemSelected(View itemView, /*Track*/PlaylistSimple item);
    }

    public adapter_playlist(Context context, ItemSelectedListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void clearData() {
        mItems.clear();
    }

    public void addData(List</*Track*/PlaylistSimple> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaylistSimple item = mItems.get(position);
//        Album it = mI.get(position);
        String fonter1 = "fonts/OpenSans-Semibold.ttf";

        Typeface t = Typeface.createFromAsset(mContext.getAssets(), fonter1);

        holder.nameplaylist.setText(item.name);
        holder.nameplaylist.setTypeface(t);
        holder.nameplaylist.setTextSize(16);

        /*List<String> names = new ArrayList<>();
        for (ArtistSimple i : item.track.artists) {
            names.add(i.name);
        }
        Joiner joiner = Joiner.on(", ");
        holder.subtitle.setText(joiner.join(names));
*/
        String total = String.valueOf(item.tracks.total);

        //holder.numeroplaylist.setText("Canciones: "+total);


        Image image = item.images.get(0);//).album.images.get(0);
        if (image != null) {
            Picasso.with(mContext).load(image.url).resize(500,0).into(holder.imagenplaylis);
        }

        Log.d("item", ""+item.uri/*mI.get(position).uri*/ );






    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
