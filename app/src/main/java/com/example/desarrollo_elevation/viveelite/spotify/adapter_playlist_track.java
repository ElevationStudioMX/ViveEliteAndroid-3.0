package com.example.desarrollo_elevation.viveelite.spotify;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_playlist;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.web_api_spotify.models.ArtistSimple;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.PlaylistTrack;
import com.google.common.base.Joiner;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.desarrollo_elevation.viveelite.R.id.txtTitulo;

/**
 * Created by Desarrollo_Elevation on 14/03/17.
 */

public class adapter_playlist_track extends RecyclerView.Adapter<adapter_playlist_track.ViewHolder>
{

private  final List<PlaylistTrack> mItem = new ArrayList<>();
private Context mContext;
private final ItemSelectedListener mListener;



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView title;
        public final TextView subtitle;
        public final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.trackmiusic);
            subtitle = (TextView) itemView.findViewById(R.id.artistmiusic);
            image = (ImageView) itemView.findViewById(R.id.imamiusic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            MainActivity_reproductor_playlist reproductor_playlist = new MainActivity_reproductor_playlist();
            reproductor_playlist.numtrack(getAdapterPosition());

            notifyItemChanged(getLayoutPosition());
            mListener.onItemSelected(view, mItem.get(getAdapterPosition()));

        }
    }

    public interface ItemSelectedListener{
        void onItemSelected(View itemview, PlaylistTrack item);
    }

    public adapter_playlist_track(Context context, ItemSelectedListener listener){
        mContext = context;
        mListener = listener;


    }

    public void clearData() {
        mItem.clear();
        //mItems.clear();
    }

    public void addData(List<PlaylistTrack > item){
        mItem.addAll(item);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist_track, parent, false);
        return new adapter_playlist_track.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PlaylistTrack item = mItem.get(position);

        String font = "fonts/OpenSans-Regular.ttf";

        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), font);





        holder.title.setText(item.track.name);
        holder.title.setTypeface(typeface);
        holder.title.setTextSize(20);

        holder.subtitle.setTypeface(typeface);
        holder.subtitle.setTextSize(14);

        List<String> names = new ArrayList<>();
        for (ArtistSimple i : item.track.artists)
        {
            names.add(i.name);
        }

        Joiner joiner = Joiner.on(", ");
        holder.subtitle.setText(joiner.join(names));

        Image image = item.track.album.images.get(0);

        if(image != null)
        {
            Picasso.with(mContext).load(image.url).resize(300,0).into(holder.image);
        }


    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }


}
