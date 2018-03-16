package com.example.desarrollo_elevation.viveelite.spotify;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.LinearGradient;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_user_free;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.web_api_spotify.SpotifyApi;
import com.example.web_api_spotify.models.ArtistSimple;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.PlaylistTrack;
import com.google.common.base.Joiner;
import com.spotify.sdk.android.player.*;
import com.squareup.picasso.Picasso;
//import com.spotify.sdk.android.player.Player;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.R.attr.description;
import static android.R.attr.name;
import static android.R.attr.numberPickerStyle;
import static com.example.desarrollo_elevation.viveelite.R.drawable.item;
import static com.example.desarrollo_elevation.viveelite.R.id.seekBar;
import static com.example.desarrollo_elevation.viveelite.R.style.se;
import static com.example.desarrollo_elevation.viveelite.spotify.serchpresent_playlist_track.getTimeFormattedString;

/**
 * Created by Desarrollo_Elevation on 16/03/17.
 */

public class serchpresent_playlist_track_use_open implements serch_playlist_track_use_open.ActionListener{

    private static final String TAG = serchpresent_playlist_track_use_open.class.getSimpleName();
    public static final  int SIEZ = 20;

    private String mCurrentQuery;
    private serchpager_playlist_track_use_open mSearchPager;
    private serchpager_playlist_track_use_open.CompleteListener mlistener;

    private final Context mContext;
    private final  serch_playlist_track_use_open.View mView;
    //private String mCurrentQuery;

    private Player mPlayer;
    double StartTime = 0 ;
    private double finalTiem =0;
    private String currentTrackurl = "";

private Runnable notificacion= null;

    private  final Handler handler = new Handler();
    private MediaPlayer mediaPlayer = getMediaPlayer();

    private ServiceConnection mServiceConection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mPlayer = ((PlayerService.PlayerBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public serchpresent_playlist_track_use_open(Context mContext, serch_playlist_track_use_open.View mView) {
        this.mContext = mContext;
        this.mView = mView;
    }


    @Override
    public void init(String token, String url) {

        SpotifyApi spotifyApi = new SpotifyApi();

        if(token != null)
        {
            spotifyApi.setAccessToken(token);
        }
        else {

        }

        mSearchPager = new serchpager_playlist_track_use_open(spotifyApi.getService());
        mContext.bindService(PlayerService.getIntent(mContext), mServiceConection, Activity.BIND_AUTO_CREATE);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //String my = String.valueOf(spotifyApi.getService().getMe().product);
            Log.v("desarrollo_elevation", String.valueOf(spotifyApi.getService().getPlaylist("desarrollo_elevation", url).description));
            //Log.v("Imagen", String.valueOf(spotifyApi.getService().getPlaylist("", "").images.get(0)));//.product));
            Log.v("Seguidores", String.valueOf(spotifyApi.getService().getPlaylist("desarrollo_elevation", url).followers.total   ));//)).uri));
            Log.v("total canciones", String.valueOf(spotifyApi.getService().getPlaylist("desarrollo_elevation", url).tracks.total));

            Log.v("nombre", String.valueOf(spotifyApi.getService().getPlaylist("desarrollo_elevation", url).name));

Log.v("que es", ""+spotifyApi.getService().getPlaylist("desarrollo_elevation", url).tracks.items);

            MainActivity_reproductor_user_free ma = new MainActivity_reproductor_user_free();

            TextView  nombre = ma.trackfree();
            String playlistas = String.valueOf(spotifyApi.getService().getPlaylist("desarrollo_elevation", url).name);
            nombre.setText(playlistas);

            String des = String.valueOf(spotifyApi.getService().getPlaylist("desarrollo_elevation", url).description);
            TextView description = ma.artistafree();
            if(des == "null")
            {
                description.setText("");
            }
            else {
                description.setText(des);

            }
            int total = spotifyApi.getService().getPlaylist("desarrollo_elevation", url).tracks.total;
            TextView datos = ma.Albumes();
            datos.setText("Creado por Elite º"+total+" canciones");

            ImageView img = ma.imagenRola();

            Image image = spotifyApi.getService().getPlaylist("desarrollo_elevation", url).images.get(0);
            Picasso.with(mContext).load(image.url).resize(1000,0).into(img);




            //tipo_de_cuenta = spotifyApi.getService().getMe().product;
        }

    }


    public MediaPlayer getMediaPlayer(/*String track*/){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });


        try {
           // mediaPlayer.setDataSource(track);
        }
        catch (Exception ex)
        {
            Log.d("e", ""+ex);
        }
        return mediaPlayer;
    }

    @Override
    public String getCurrentQuery() {
        return mCurrentQuery;
    }

    @Override
    public void search(String serch) {
 if (serch != null && !serch.isEmpty() && !serch.equals(mCurrentQuery))
 {
     mCurrentQuery = serch;
     mView.reset();
     mlistener = new serchpager_playlist_track_use_open.CompleteListener() {
         @Override
         public void onComplete(List<PlaylistTrack> items) {
             mView.addData(items);

         }

         @Override
         public void onError(Throwable error) {

         }
     };

     mSearchPager.getFristPage(serch, SIEZ, mlistener);

 }
    }

    @Override
    public void loadMoreResult() {
        Log.d(TAG, "Load more...");
        mSearchPager.getNextPage(mlistener);
    }

    @Override
    public void selectTrack(PlaylistTrack track) {


        try {

            final MainActivity_reproductor_user_free ma = new MainActivity_reproductor_user_free();

            TextView trackname = ma.trackfree();

            trackname.setText(track.track.name);

            TextView artistaname = ma.artistafree();

            List<String> names = new ArrayList<>();

            for(ArtistSimple i : track.track.artists){
                names.add(i.name);
            }
            Joiner joiner = Joiner.on(", ");
            artistaname.setText(joiner.join(names));

            TextView alb = ma.Albumes();

            alb.setText("Album: "+track.track.album.name);


            ImageView imagalbum = ma.imagenRola();
            Image image =  track.track.album.images.get(0);

            Picasso.with(mContext).load(image.url).resize(1000,0).into(imagalbum);





            String previewurl = track.track.preview_url;




            Log.d("url", track.track.uri);
            Log.d("ques", previewurl);





















          /*  if(mediaPlayer != null)
            {
                mediaPlayer.release();
            }*/

            Log.i("vija", "que es"+currentTrackurl);


            if(mediaPlayer.isPlaying()) {

                mediaPlayer.pause();

                mediaPlayer = getMediaPlayer();
                mediaPlayer.setDataSource(previewurl);
                mediaPlayer.prepare();
                mediaPlayer.start();
                ImageView playerstop = ma.playystop();
                playerstop.setImageResource(R.mipmap.resumenplay);
                playerstop.setEnabled(true);

            }

            else
            {

                mediaPlayer = getMediaPlayer();
                mediaPlayer.setDataSource(previewurl);
                mediaPlayer.prepare();
                //  mediaPlayer.release();
                mediaPlayer.start();
                ImageView playerstop = ma.playystop();
                playerstop.setImageResource(R.mipmap.resumenplay);
                playerstop.setEnabled(true);

            }


/*            if (currentTrackurl.equals(previewurl) null || !currentTrackurl.equals(previewurl))

            {



                if (mediaPlayer.isPlaying()) {

                    Log.i("segundo entra", "pasando por el estado ");

                    if(!currentTrackurl.equals(previewurl))
                    {
                        mediaPlayer.release();//.pause();

                        //.stop();
                    }
                    // mediaPlayer.release();//.stop();//.pause();
                    mediaPlayer.setDataSource(currentTrackurl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } else {

                    Log.i("primero entra", "pasa  por aqui");
                    currentTrackurl = previewurl;
                    mediaPlayer.setDataSource(currentTrackurl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                }

            }

            else{
                Log.i("primero entra", "pasa  por aqui");
                currentTrackurl = previewurl;
                mediaPlayer.setDataSource(currentTrackurl);
                mediaPlayer.prepare();
                mediaPlayer.start();

            }*/
            //mPlayer.play(previewurl);





                finalTiem = mediaPlayer.getDuration();

                Log.d("duration", ""+finalTiem);

                StartTime = mediaPlayer.getCurrentPosition();//.getposition();
                Log.d("position", ""+StartTime);




                TextView txttiem_end =ma.end();


                txttiem_end.setText(String.format("%s:%s",  getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) finalTiem)),
                        getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) finalTiem) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTiem)))));


                TextView txttiemp_start = ma.start();

                txttiemp_start.setText(String.format("%s:%s", getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) StartTime)),
                        getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) StartTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) StartTime)))));


            progressSeekbars();




            //SeekBar seekbars = ma.barra();


        /*seekbars.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/



            //}



         /*   mediaPlayer = getMediaPlayer();
            if(mediaPlayer.isPlaying())
            {
                return;
            }
            else{
            mediaPlayer.setDataSource(previewurl);

            mediaPlayer.prepare();

                mediaPlayer.

            if(mediaPlayer.isPlaying()) {

                mediaPlayer.stop();
                //mediaPlayer.start();

            }
            }

            else {mediaPlayer.start();}*/



        }
       catch (Exception e){
           Log.v("error",  "kn"+e);
       }
    }


    public static String getTimeFormattedString(long value) {
        if (value < 10)
            return "0" + value;

        return String.valueOf(value);
    }


    private void progressSeekbars(){
        if(mediaPlayer == null)
        {
            handler.removeCallbacks(notificacion);
            return;
        }

        try {

            final MainActivity_reproductor_user_free ma =  new MainActivity_reproductor_user_free();
            final SeekBar seekbars = ma.barra();

            int CurrentPosition = mediaPlayer.getCurrentPosition(); // mPlayer.getposition();
            final int seekbarspro =(int)(((float)CurrentPosition /finalTiem)*100);


            seekbars.setProgress(seekbarspro);

            Log.d("seekbars"," "+seekbarspro);

            final TextView starter = ma.start();


            starter.setText(String.format("%s:%s",
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) CurrentPosition)),
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) CurrentPosition) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) 0)))));


            starter.setText(String.format("%s:%s",
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) CurrentPosition)),
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) CurrentPosition) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) CurrentPosition))))
            );


            seekbars.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    seekbars.setProgress(seekbarspro);

                    /*if(b) {
                        mediaPlayer.seekTo(i);
                    }*/                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

           // int dato = seekbars.getProgress() +1;

            if(seekbars.getProgress() == 97)
            {

                ImageView playerstop = ma.playystop();
                playerstop.setImageResource(R.mipmap.play);

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);

                alert.setTitle("Sugerencia");
                alert.setMessage("Para escuchar musica completa y mas funciones contar con una cuenta premium o pulse aceptar para ir a la aplicacion de spotiy");
                alert.setIcon(R.mipmap.spotify);

                alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Intent launchIntent = mContext.getPackageManager().getLaunchIntentForPackage("com.spotify.music");
                        //mContext.startActivity(launchIntent);

                        Log.v("sigueinte ", ma.getURL());

                        try {

                            String uri= ma.getURL(); //"spotify:user:miguelrzk:playlist:0LUzMp4DFxOzGpUzKaAwP4";
                            Intent launcher = new Intent( Intent.ACTION_VIEW, Uri.parse(uri) );
                            mContext.startActivity(launcher);
                        }

                        catch (Exception e)
                        {
                            Toast.makeText(mContext, "No se encuentra instalada la aplicacion de spotify", Toast.LENGTH_LONG).show();
                        }



                    }
                });

                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();


                    }
                });

                alert.show();


            }



            if(mediaPlayer.isPlaying())
            {
                notificacion = new Runnable() {
                    @Override
                    public void run() {
                        progressSeekbars();



            //            Log.d("start", ""+ starter.getText().toString());

                    }
                };

           handler.postDelayed(notificacion, 320);

               // Log.v("handler", ""+handler);

            }




        }

        catch (Exception e)
        {

        }
    }

    @Override
    public void stooplay() {

        MainActivity_reproductor_user_free ma = new MainActivity_reproductor_user_free();
        ImageView playerstop = ma.playystop();


        if(mediaPlayer.isPlaying())
        {
            playerstop.setImageResource(R.mipmap.play);
            mediaPlayer.pause();
        }

        else {
            mediaPlayer.start();//.resume();
            playerstop.setImageResource(R.mipmap.resumenplay);
            progressSeekbars();
        }

    }

    @Override
    public void playpreview() {

        mediaPlayer.pause();
    }

    @Override
    public void playnext() {

    }

    @Override
    public void resume() {
 mContext.stopService(PlayerService.getIntent(mContext));
    }

    @Override
    public void pause() {

        mContext.startService(PlayerService.getIntent(mContext));
    }

    @Override
    public void destroy() {
        mContext.unbindService(mServiceConection);
        mPlayer.pause();
        mPlayer.release();
    }


    private void logError(String msg)
    {
        Log.e(TAG, msg);
    }

    private void logMessage(String msg)
    {
        Log.d(TAG, msg);
    }
}
