package com.example.desarrollo_elevation.viveelite.spotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_playlist;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.web_api_spotify.SpotifyApi;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.PlaylistTrack;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Connectivity;
import com.spotify.sdk.android.player.Metadata;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
import com.spotify.sdk.android.player.*;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.R.attr.description;
import static android.R.attr.name;
import static android.R.attr.text;
import static com.example.desarrollo_elevation.viveelite.R.id.layer;
import static com.example.desarrollo_elevation.viveelite.R.id.seekBar;
import static com.example.desarrollo_elevation.viveelite.R.mipmap.play;
import static com.example.desarrollo_elevation.viveelite.spotify.serchpresent_playlist_track_use_open.getTimeFormattedString;

/**
 * Created by Desarrollo_Elevation on 14/03/17.
 */

public class serchpresent_playlist_track implements serch_playlist_track.ActionListener, com.spotify.sdk.android.player.Player.NotificationCallback

{
    private static final  String TAG = serchpresent_playlist_track.class.getSimpleName();
    public static final int PAGE_SIZE = 20;

    private String mCurrentQuery;

    private  final Context mContext;
    private  serchpager_playlist_track mserchpager;
    private serchpager_playlist_track.CompleteListener mslistener;
    private final serch_playlist_track.View mView;
    private static  final String CLIENT_ID = "a2535973bdd84b22b6509ea3c2a56173";

    //private Player player;
    private SpotifyPlayer Player;
    private Metadata metadata;
    private static double finalTime = 0;
    double startTime = 0;
    private Runnable notificacion = null;
    private PlaybackState mCurrentPlaybackState;
    private BroadcastReceiver mNetworkStateReceiver;
    private static String KEY_CURRENT_QUERY = "CURRENT_QUERY";
    private static int nada;

    private  static  int datos;

    Handler mhandler = new Handler();

    public serchpresent_playlist_track(Context mContext, serch_playlist_track.View mView) {
        this.mContext = mContext;

        this.mView = mView;
    }


    private final com.spotify.sdk.android.player.Player.OperationCallback mOperationCallback = new com.spotify.sdk.android.player.Player.OperationCallback() {
        @Override
        public void onSuccess() {
            logStatus("OK!");
        }

        @Override
        public void onError(Error error) {
            logStatus("ERROR:" + error+ "necesitas premium ");
        }
    };


    @Override
    public void init(String token, String url) {
//logMessage("Api Client crearted");
        SpotifyApi spotifyApi = new SpotifyApi();
        if(token != null)
        {
            spotifyApi.setAccessToken(token);

            Config playerConfig = new Config(mContext, token, CLIENT_ID );
            Player = Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                @Override
                public void onInitialized(SpotifyPlayer spotifyPlayer) {

                    Player.setConnectivityStatus(mOperationCallback, getNetworkConnectivity(mContext));

                }

                @Override
                public void onError(Throwable throwable) {

                }
            });


            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);


                MainActivity_reproductor_playlist playlist = new MainActivity_reproductor_playlist();

                TextView textname = playlist.texttrack();
            String name = spotifyApi.getService().getPlaylist("desarrollo_elevation", url).name;



                textname.setText(name);


                TextView textdescription = playlist.textArtista();
                String description = spotifyApi.getService().getPlaylist("desarrollo_elevation", url).description;

                if(description == "null")
                {
                 textdescription.setText("");

                }
                else {

                    textdescription.setText(description);
                }
               TextView textTotal = playlist.textAlbumnes();
                int Total = spotifyApi.getService().getPlaylist("desarrollo_elevation", url).tracks.total;

                textTotal.setText("Creado por Elite º"+Total+" canciones");

                Image image = spotifyApi.getService().getPlaylist("desarrollo_elevation", url).images.get(0);

                ImageView imageplaylist = playlist.imagalbum();

                Picasso.with(mContext).load(image.url).resize(500,0).into(imageplaylist);
              //  el amigo piccaso


            }
        }

        else {
            Log.d("Mensaje", "No valid access token");
        }

        mserchpager = new serchpager_playlist_track(spotifyApi.getService());

        Actualizar();
    }


    private void onAuthenticationComplete(AuthenticationResponse authResponse) {
        // Once we have obtained an authorization token, we can proceed with creating a Player.
        logStatus("Got authentication token");
        if (Player == null) {
            Config playerConfig = new Config(mContext, authResponse.getAccessToken(), CLIENT_ID);
            // Since the Player is a static singleton owned by the Spotify class, we pass "this" as

            // the second argument in order to refcount it properly. Note that the method
            // Spotify.destroyPlayer() also takes an Object argument, which must be the same as the
            // one passed in here. If you pass different instances to Spotify.getPlayer() and
            // Spotify.destroyPlayer(), that will definitely result in resource leaks.
            Player = Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                @Override
                public void onInitialized(SpotifyPlayer player) {
                    logStatus("-- Player initialized --");
                    Player.setConnectivityStatus(mOperationCallback, getNetworkConnectivity(mContext));
                    Player.addNotificationCallback((com.spotify.sdk.android.player.Player.NotificationCallback) mContext);
                    Player.addConnectionStateCallback((ConnectionStateCallback) mContext);
                    // Trigger UI refresh
                    //updateView();
                    Actualizar();
                }

                @Override
                public void onError(Throwable error) {
                    logStatus("Error in initialization: " + error.getMessage());
                }
            });
        } else {
            Player.login(authResponse.getAccessToken());
        }
    }




    private Connectivity getNetworkConnectivity(Context context) {
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return Connectivity.fromNetworkType(activeNetwork.getType());
        } else {
            return Connectivity.OFFLINE;
        }
    }

    @Override
    public String getCurrentQuery() {
        return mCurrentQuery;
    }

    @Override
    public void search(String searchquery) {

        if(searchquery != null && !searchquery.isEmpty() && !searchquery.equals(mCurrentQuery))
        {
            //logMessage("query text submit" + searchquery);
            mCurrentQuery = searchquery;
            Log.d("mcurrentQuery", mCurrentQuery);
            mView.reset();
            mslistener = new serchpager_playlist_track.CompleteListener() {
                @Override
                public void onComplete(List<PlaylistTrack> item) {
                    mView.addData(item);
                }

                @Override
                public void onError(Throwable error) {

                }
            };

            mserchpager.getFristPage(searchquery, PAGE_SIZE, mslistener);




        }

    }

    @Override
    public void loadMoreResult() {
     Log.d(TAG, "Load more....");
        mserchpager.getNextPage(mslistener);
    }

    @Override
    public void selectTrack(PlaylistTrack item, int numplay, String url) {

        try {
            String previewUrl = item.track.album.uri;


            MainActivity_reproductor_playlist playlist = new MainActivity_reproductor_playlist();
            ImageView playstop = playlist.imagenplaystop();
            ImageButton next = playlist.btnplaynext();
            ImageButton preview = playlist.btnPlaypreview();


            String TEST_PLAYLIST_URI = url;

            Log.d("url", TEST_PLAYLIST_URI);
            Log.v("numplay", ""+numplay);
            Log.d("preview", previewUrl);
            Log.d("otra cosa", ""+mOperationCallback);

            Player.playUri(mOperationCallback, TEST_PLAYLIST_URI, numplay, 0);

            playstop.setEnabled(true);
            next.setEnabled(true);
            preview.setEnabled(true);

            playstop.setImageResource(R.mipmap.resumenplay);




        }

        catch (Exception e)
        {
            Log.d("mensaje", ""+e);
        }
    }

    @Override
    public void stopplay() {

        MainActivity_reproductor_playlist playlist = new MainActivity_reproductor_playlist();
        ImageView playsto = playlist.imagenplaystop();

        if(mCurrentPlaybackState != null && mCurrentPlaybackState.isPlaying)
{


    Player.pause(mOperationCallback);
    playsto.setImageResource(R.mipmap.play);

}

        else {
    Player.resume(mOperationCallback);

            playsto.setImageResource(R.mipmap.resumenplay);

}
    }

    @Override
    public void playpreview() {

        MainActivity_reproductor_playlist playlist = new MainActivity_reproductor_playlist();
        ImageView playstop = playlist.imagenplaystop();

        Player.skipToPrevious(mOperationCallback);

            playstop.setImageResource(R.mipmap.resumenplay);

    }

    @Override
    public void playnext() {

        MainActivity_reproductor_playlist playlist = new MainActivity_reproductor_playlist();

        ImageView Playstp = playlist.imagenplaystop();



        Player.skipToNext(mOperationCallback);


    Playstp.setImageResource(R.mipmap.resumenplay);

    }

    @Override
    public void resume() {

        mNetworkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
          if(Player != null){

              Connectivity connectivity = getNetworkConnectivity(mContext);
              logStatus("Network state changed: "+ connectivity.toString());
              Player.setConnectivityStatus(mOperationCallback, connectivity);
          }

            }
        };

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(mNetworkStateReceiver, filter);

        if(Player != null)
        {
            Player.addNotificationCallback(this);
        }


    }

    @Override
    public void pause() {
    mContext.unregisterReceiver(mNetworkStateReceiver);
    if(Player !=  null)
    {
        Player.removeNotificationCallback(this);
    }


    }

    @Override
    public void iniciandoplaylist(String url) {

    }

    @Override
    public void pausarback() {



        MainActivity_reproductor_playlist playlist = new MainActivity_reproductor_playlist();
        final ImageView playstop = playlist.imagenplaystop();


                Player.pause(mOperationCallback);
                     //Player.removeNotificationCallback(this);
                 playstop.setImageResource(R.mipmap.play);





       // Player.destroy();


    }



    @Override
    public void destroy() {

        Spotify.destroyPlayer(this);
    }

    @Override
    public void backemergencia(String token) {


    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {

     metadata = Player.getMetadata();
     mCurrentPlaybackState = Player.getPlaybackState();
     Actualizar();

    }

    @Override
    public void onPlaybackError(Error error) {

    }

    public void Actualizar(){

        if(metadata != null && metadata.currentTrack != null)
        {
            MainActivity_reproductor_playlist reproductor = new MainActivity_reproductor_playlist();

            TextView trackName = reproductor.texttrack();

            trackName.setText(metadata.currentTrack.name);

            TextView artista = reproductor.textArtista();
            artista.setText(metadata.currentTrack.artistName);

            TextView albumnaem = reproductor.textAlbumnes();
            albumnaem.setText("Album: "+ metadata.currentTrack.albumName);

            ImageView ImagenAlbum = reproductor.imagalbum();

            Picasso.with(mContext).load(metadata.currentTrack.albumCoverWebUrl).resize(1000,0).into(ImagenAlbum);

            TextView start = reproductor.textStart();

            TextView end = reproductor.textEnd();

            finalTime=(int)metadata.currentTrack.durationMs;
            startTime= (int)mCurrentPlaybackState.positionMs;

            end.setText(String.format("%s:%s",
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)),
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))))
            );

            start.setText(String.format("%s:%s",  getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) startTime)),
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))))
            );


            primarySeekbarprogresss();

          //  final SeekBar se = reproductor.barradeprogreso();

  /*        se.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(b) {
                        Player.seekToPosition(mOperationCallback, se.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


*/
        }

        else
        {
            Log.d("Error", "Error");
        }


    }


    public static String getTimeFormattedString(long value)
    {
        if(value<10)
            return  "0"+value;
        return  String.valueOf(value);
    }

    private void  primarySeekbarprogresss(){
        if(Player == null)
        {
            mhandler.removeCallbacks(notificacion);
            return;
        }

        try {
            final MainActivity_reproductor_playlist reproductor_playlist = new MainActivity_reproductor_playlist();

            final SeekBar seekBare = reproductor_playlist.barradeprogreso();

            finalTime =(int)metadata.currentTrack.durationMs;

            int Currentposition =(int)Player.getPlaybackState().positionMs;
            final int Seekbarprogress=(int)((float)Currentposition / finalTime *100);
            seekBare.setProgress(Seekbarprogress);



            seekBare.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                  //  int dato = seekBare.getProgress();
                   seekBar.setProgress(Seekbarprogress);
                  /*  if(b) {
V/tiempo transcurrido: 1
V/tiempo transcurrido: 1

                    }*/
                  /*  if(b) {
                          Player.seekToPosition(mOperationCallback, dato);
                    }*/
               }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    //primarySeekbarprogresss();
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
               // primarySeekbarprogresss();

                }
            });

            final TextView star= reproductor_playlist.textStart();

            star.setText(String.format("%s:%s",
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) Currentposition)),
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) Currentposition) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) 0)))));

            star.setText(String.format("%s:%s",
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toMinutes((long) Currentposition)),
                    getTimeFormattedString(TimeUnit.MILLISECONDS.toSeconds((long) Currentposition) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) Currentposition))))


            );

            if(seekBare.getProgress() == 99)
            {
                ImageView pl = reproductor_playlist.imagenplaystop();
                pl.setImageResource(R.mipmap.play);
            }

            else if(seekBare.getProgress() == 1)
            {
                ImageView pd = reproductor_playlist.imagenplaystop();
                pd.setImageResource(R.mipmap.resumenplay);
            }


            Log.v("tiempo transcurrido", ""+seekBare.getProgress());

            if(mCurrentPlaybackState.isPlaying)
            {
                notificacion = new Runnable() {
                    @Override
                    public void run() {


                primarySeekbarprogresss();




                    }
                };

                mhandler.postDelayed(notificacion, /*850*/250);
                           }

            else {

            }



        }




        catch (Exception ex)
        {
            Log.d("error", ""+ex);
        }



         }

    private void logError(String msg) {
        Toast.makeText(mContext, "Error: " + msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg);
        //  Log.d("necesitas premium", "mendigo pobre y jodid");

    }

    private void logMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
        //Log.d("necesitas premium", "mendigo pobre y jodid");
    }

    private void logStatus(String status) {
        Log.i(TAG, status);
        // Log.d("necesitas premium", "mendigo pobre y jodid");

    }
}
