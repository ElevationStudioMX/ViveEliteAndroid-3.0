package com.example.desarrollo_elevation.viveelite.spotify;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.R;
import com.example.web_api_spotify.models.ArtistSimple;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.Playlist;
import com.example.web_api_spotify.models.PlaylistTrack;
import com.google.common.base.Joiner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.example.desarrollo_elevation.viveelite.R.drawable.item;
import static com.example.desarrollo_elevation.viveelite.R.drawable.marco3;

/**
 * Created by Desarrollo_Elevation on 16/03/17.
 */

public class adapter_playlist_track_use_open extends RecyclerView.Adapter<adapter_playlist_track_use_open.ViewHolder> {

    private final List<PlaylistTrack> mItems = new ArrayList<>();

    private  final Context mContext;
    private final ItemSelectedListener mlisteneres;

public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView titulo;
    public final TextView artistas;
    public final ImageView image;

    public ViewHolder(View itemView) {
        super(itemView);

        titulo =(TextView)itemView.findViewById(R.id.trackmiusic);
        artistas =(TextView)itemView.findViewById(R.id.artistmiusic);
        image =(ImageView)itemView.findViewById(R.id.imamiusic);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        notifyItemChanged(getLayoutPosition());
        mlisteneres.onItemSelected(view, mItems.get(getAdapterPosition()));


    }
}


    public adapter_playlist_track_use_open(Context mContext, ItemSelectedListener mlistener) {
        this.mContext = mContext;
        this.mlisteneres = mlistener;
    }


    public interface ItemSelectedListener{
        void  onItemSelected(View itemView, PlaylistTrack item);

    }

    public void cleardata(){mItems.clear();}

    public void  addData(List<PlaylistTrack> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist_track, parent, false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String font ="fonts/OpenSans-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), font);

        PlaylistTrack item = mItems.get(position);
        holder.titulo.setText(item.track.name);
        holder.titulo.setTypeface(typeface);
        holder.titulo.setTextSize(20);

        List<String> names = new ArrayList<>();

        for(ArtistSimple i : item.track.artists)
        {
            names.add(i.name);
        }

        Joiner jon = Joiner.on(", ");

        holder.artistas.setText(jon.join(names));

        holder.artistas.setTypeface(typeface);
        holder.artistas.setTextSize(14);


        Image image = item.track.album.images.get(0);
        if(image != null){

            Picasso.with(mContext).load(image.url).resize(300,0).into(holder.image);

        }






    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
