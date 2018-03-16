package com.example.desarrollo_elevation.viveelite.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.MainActivity_Ejercicios;
import com.example.desarrollo_elevation.viveelite.MainActivity_contenidoreceta;
import com.example.desarrollo_elevation.viveelite.MainActivity_menutolbbed;
//import com.example.desarrollo_elevation.viveelite.MainActivity_recetas;
//import com.example.desarrollo_elevation.viveelite.MainActivity_recetas;
import com.example.desarrollo_elevation.viveelite.MainActivity_recetas;
//import com.example.desarrollo_elevation.viveelite.Model;
//import com.example.desarrollo_elevation.viveelite.MultiViewTypeAdapter;
import com.example.desarrollo_elevation.viveelite.MultiViewTypeAdapter;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.viveelite.VideoEnabledWebChromeClient;
import com.example.desarrollo_elevation.viveelite.details_video;
import com.example.helperyoutube.JLog;
import com.example.helperyoutube.YTParams;
import com.example.helperyoutube.YoutubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 27/03/17.
 */

public class Adapter_BD extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private ArrayList<Model_BD> dataSet;
    private Context mContext;
    //Activity activity = (Activity) mContext;

    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;
    String tiemp;


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;
        Button detalleimg;
        ImageButton favoritos;
        ImageButton compartir;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.txttextoimg);
            this.image = (ImageView) itemView.findViewById(R.id.background);
            this.detalleimg=(Button) itemView.findViewById(R.id.btndetalleimg);
            this.favoritos = (ImageButton)itemView.findViewById(R.id.idimgfavoritosimg);
            this.compartir = (ImageButton)itemView.findViewById(R.id.idimgcompartirimg);


        }

    }




    public static class VideoTypeViewHolder extends RecyclerView.ViewHolder {


        Context cont;
        VideoEnabledWebChromeClient webChromeClient;

        TextView txtTyper;
        //      VideoEnabledWebView  video;
        YoutubePlayerView video;
        // Button detallevideo;
        Button detallevideo;
        ImageButton favoritos;
        ImageButton Compartir;


        public VideoTypeViewHolder(View itemView) {
            super(itemView);

            this.txtTyper = (TextView) itemView.findViewById(R.id.txttextovideo);
            this.video = (YoutubePlayerView) itemView.findViewById(R.id.videro2/*videoView*/);
            // this.detallevideo=(Button)itemView.findViewById(R.id.btndetallevideo);
            this.detallevideo =(Button) itemView.findViewById(R.id.btndetallevideo);

            this.favoritos =(ImageButton) itemView.findViewById(R.id.idimgfavoritosvideo);
            this.Compartir =(ImageButton)itemView.findViewById(R.id.idimgcompartirvideo);







        }

    }

    public Adapter_BD(ArrayList<Model_BD> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case Model_BD.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_image, parent, false);
                return new Adapter_BD.ImageTypeViewHolder(view);


            case Model_BD.VIDEO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_video2, parent, false);
                return new Adapter_BD.VideoTypeViewHolder(view);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 1:

                return Model_BD.IMAGE_TYPE;
            case 2:
                return Model_BD.VIDEO_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int listPosition) {

        final Model_BD object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {

                case Model_BD.IMAGE_TYPE:

                    Log.v("es", ""+Model_BD.IMAGE_TYPE);

                    String font = "fonts/OpenSans-Light.ttf";
                    String fonter1 = "fonts/OpenSans-Regular.ttf";
                   /* String font2 = "fonts/leadcoat.ttf";
                    String font3 = "fonts/stocky.ttf";
                    String font4 = "fonts/ASMAN.TTF";*/

                    Typeface face= Typeface.createFromAsset(mContext.getAssets(),font);
                    Typeface facei= Typeface.createFromAsset(mContext.getAssets(),fonter1);
                    ((Adapter_BD.ImageTypeViewHolder) holder).txtType.setTypeface(face);
                    ((Adapter_BD.ImageTypeViewHolder) holder).txtType.setTextSize(16);
                    ((Adapter_BD.ImageTypeViewHolder) holder).txtType.setText(object.text);

                    Picasso.with(mContext).load(object.data).resize(1000,0).into(((Adapter_BD.ImageTypeViewHolder) holder).image);
                    //((ImageTypeViewHolder) holder).image.setImageResource(object.img);

                    ((Adapter_BD.ImageTypeViewHolder) holder).detalleimg.getBackground().setAlpha(00);
                    ((Adapter_BD.ImageTypeViewHolder) holder).favoritos.getBackground().setAlpha(00);
                    ((Adapter_BD.ImageTypeViewHolder) holder).compartir.getBackground().setAlpha(00);
                    ((Adapter_BD.ImageTypeViewHolder) holder).detalleimg.setTypeface(facei);
                    ((Adapter_BD.ImageTypeViewHolder) holder).detalleimg.setTextSize(18);


                    ((Adapter_BD.ImageTypeViewHolder) holder).detalleimg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int nameclasnum;

                            MainActivity_menutolbbed inicio  = new MainActivity_menutolbbed();
                            String contexto_inicio = inicio.conte();


                            MainActivity_recetas recetas = new MainActivity_recetas();
                            String contexto_receta = recetas.contex();

                            String contexto = mContext.getClass().getSimpleName().toString();

                            Log.d("contexto imag " ,contexto);

                            if(contexto.equals(contexto_inicio))
                            {
                                nameclasnum = 1;

                            }

                            else if(contexto.equals(contexto_receta))
                            {
                                nameclasnum = 2;
                            }

                            else{
                                nameclasnum =0;
                            }


                            Log.d("name class", ""+nameclasnum);

                            Bundle bundle = new  Bundle();

                            bundle.putString("imagen", object.data);
                            bundle.putInt("contextreceta", nameclasnum);

                            Intent intent = new Intent(mContext,MainActivity_contenidoreceta.class);
                            intent.putExtras(bundle);



                            //(MultiViewTypeAdapter.this, MainActivity_contenidoreceta.class);
                            Activity activity = (Activity) mContext;
                            mContext.startActivity(intent);
                            // activity.overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                            //view.getContext().overridePendingTransition(R.anim.left_in, R.anim.left_out);

                        }
                    });



                    break;


                case Model_BD.VIDEO_TYPE:


                    String fonter = "fonts/OpenSans-Light.ttf";
                    String font1 = "fonts/OpenSans-Regular.ttf";


                    Typeface facer= Typeface.createFromAsset(mContext.getAssets(),fonter);

                    ((Adapter_BD.VideoTypeViewHolder) holder).txtTyper.setTypeface(facer);
                    ((Adapter_BD.VideoTypeViewHolder) holder).txtTyper.setTextSize(16);
                    ((Adapter_BD.VideoTypeViewHolder) holder).txtTyper.setText(object.text);



                    ((Adapter_BD.VideoTypeViewHolder) holder).favoritos.getBackground().setAlpha(00);
                    ((Adapter_BD.VideoTypeViewHolder) holder).Compartir.getBackground().setAlpha(00);


                    ((Adapter_BD.VideoTypeViewHolder) holder).video.setAutoPlayerHeight(mContext);


                    YTParams params = new YTParams();
                    params.setControls(1);
                    params.setRel(0);
                    // params.setAutoplay(1);
                    params.setFs(0);//.setfs(0);


                    ((Adapter_BD.VideoTypeViewHolder) holder).video.initialize(object.data,/*"WCchr07kLPE""ayoHgRnwf_U",,*/ params,new YoutubePlayerView.YouTubeListener() {



                        @Override
                        public void onReady() {
                            // when player is ready.
                            JLog.i("onReady()");
                        }

                        @Override
                        public void onStateChange(YoutubePlayerView.STATE state) {

                            // YoutubePlayerView.STATE

                            // UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, CUED, NONE



                            //JLog.i("onStateChange(" + //state + ")");
                        }

                        @Override
                        public void onPlaybackQualityChange(String arg) {
                            JLog.i("onPlaybackQualityChange(" + arg + ")");
                        }

                        @Override
                        public void onPlaybackRateChange(String arg) {
                            JLog.i("onPlaybackRateChange(" + arg + ")");
                        }

                        @Override
                        public void onError(String arg) {
                            JLog.e("onError(" + arg + ")");
                        }

                        @Override
                        public void onApiChange(String arg) {
                            JLog.i("onApiChange(" + arg + ")");
                        }

                        @Override
                        public void onCurrentSecond(double second) {
                            // currentTime callback
                            Message msg = new Message();
                            msg.obj = second;
                            handler.sendMessage(msg);
                        }

                        @Override
                        public void onDuration(double duration) {
                            // total duration
                            JLog.i("onDuration(" + duration + ")");
                        }

                        @Override
                        public void logs(String log) {
                            // javascript debug log. you don't need to use it.
                            JLog.d(log);
                        }
                    });

                    Typeface facere= Typeface.createFromAsset(mContext.getAssets(),font1);

                    ((Adapter_BD.VideoTypeViewHolder) holder).detallevideo.setTypeface(facere);
                    ((Adapter_BD.VideoTypeViewHolder) holder).detallevideo.getBackground().setAlpha(00);
                    ((Adapter_BD.VideoTypeViewHolder) holder).detallevideo.setTextSize(18);



                    ((Adapter_BD.VideoTypeViewHolder) holder).detallevideo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Bundle bundle  = new Bundle();

                            int nameclas;

                            MainActivity_menutolbbed inicio  = new MainActivity_menutolbbed();
                            String contexto_inicio = inicio.conte();

                            MainActivity_Ejercicios ejercicios = new MainActivity_Ejercicios();
                            String contexto_ejercicio = ejercicios.contex();

                            MainActivity_recetas recetas = new MainActivity_recetas();
                            String contexto_receta = recetas.contex();

                            String contexto = mContext.getClass().getSimpleName().toString();

                            if(contexto.equals(contexto_inicio))
                            {
                                nameclas=1;
                            }

                            else if(contexto.equals(contexto_ejercicio)) {
                                nameclas=2;

                            }

                            else if(contexto.equals(contexto_receta))
                            {
                                nameclas = 3;
                            }
                            else {

                                nameclas =0;
                            }



                            Double te;



                            if(tiemp == null)
                            {
                                te = 0.0;
                            }
                            else  {

                                te = Double.parseDouble(tiemp);
                            }




                            bundle.putDouble("yttiempo", te);
                            bundle.putString("ytvideo", object.data);
                            bundle.putInt("context", nameclas);


                            Intent intent = new Intent(mContext, details_video.class);

                            intent.putExtras(bundle);

                            Activity activity=(Activity)mContext;

                            mContext.startActivity(intent);
                            //activity.overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);



                        }
                    });




                    break;
            }
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // you can cast with float that I recommend.
            double sec = (double) msg.obj;
            tiemp = (String.valueOf(sec));
        }
    };


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

