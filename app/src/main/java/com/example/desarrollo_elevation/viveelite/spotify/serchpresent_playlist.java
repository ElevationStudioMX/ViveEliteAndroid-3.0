package com.example.desarrollo_elevation.viveelite.spotify;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_playlist;
import com.example.desarrollo_elevation.viveelite.MainActivity_reproductor_user_free;
import com.example.web_api_spotify.SpotifyApi;
import com.example.web_api_spotify.SpotifyCallback;
import com.example.web_api_spotify.SpotifyError;
import com.example.web_api_spotify.SpotifyService;
import com.example.web_api_spotify.models.Album;
import com.example.web_api_spotify.models.PlaylistSimple;
import com.example.web_api_spotify.models.UserPrivate;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Desarrollo_Elevation on 09/03/17.
 */

public class serchpresent_playlist implements serch_playlist.ActionListener {

    private static final String TAG = serchpresent_playlist.class.getSimpleName();
    public static final int PAGE_SIZE = 20;

    private final Context mContext;
    private final serch_playlist.View mView;
    private String mCurrentQuery;

    private serchpager_playlist mSearchPager;
    private UserPrivate userPrivate;
    //private serchpager_user mserchuser;
    private serchpager_playlist.CompleteListeneres mSearchListener;

    private Player mPlayer;

    private static String tipo_de_cuenta;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayer = ((PlayerService.PlayerBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPlayer = null;
        }
    };

    public serchpresent_playlist(Context context, serch_playlist.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void init(final String accessToken) {
      //  logMessage("Api Client created");
        SpotifyApi spotifyApi = new SpotifyApi();


        if (accessToken != null) {
            spotifyApi.setAccessToken(accessToken);

        } else {
            logError("Error al ingresar");
        }

        mSearchPager = new serchpager_playlist(spotifyApi.getService());

        //private final  String CLIENT ="";

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SpotifyApi.SPOTIFY_WEB_API_ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                        request.addHeader("Authorization", "Bearer " + accessToken);
                        //request.addHeader("");
                        Log.d("esear run", "Bearer " + accessToken);
                        //Log.e("se", ""+request.addHeader("Authorization", "Bearer "+accessToken));
                    }
                })
                .build();

        SpotifyService spotify = restAdapter.create(SpotifyService.class);

        //Log.v("spot", ""+spotify.getMe());



        //  SpotifyService spotify = restAdapter.create(SpotifyService.class);

/*         UserPrivate priv = spotify.getMe();

        Log.e("oruv", priv.uri);
          mserchuser = new serchPager_user(spotifyApi.getService());*/

//        mserchuser.getuser();


        spotifyApi.getService().getAlbum("3OEOCO0FGZioFylDHbkDDG", new SpotifyCallback<Album>() {
            @Override
            public void success(Album album, Response response) {

                Log.v("nombre", album.name);
            }

            @Override
            public void failure(SpotifyError error) {

            }



        });

        try {


            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                //String my = String.valueOf(spotifyApi.getService().getMe().product);
                Log.v("Producto", String.valueOf(spotifyApi.getService().getMe().product));
                Log.v("Nombre de compra", String.valueOf(spotifyApi.getService().getMe().uri));
                Log.v("id", String.valueOf(spotifyApi.getService().getMe().id));
                Log.v("pais", String.valueOf(spotifyApi.getService().getMe().country));


                tipo_de_cuenta = spotifyApi.getService().getMe().product;

            }
            // Log.v("sfun se la mamamaron", String.valueOf(spotifyApi.getService().getAlbum("3OEOCO0FGZioFylDHbkDDG").name));
            //spotifyApi.getService().getMe();


      /*      Album albumsPager = spotifyApi.getService().getAlbum("3OEOCO0FGZioFylDHbkDDG");
            String leyend= albumsPager.name;

            Log.i("reundion", leyend);

            /*UserPrivate priv = spotify.getMe();

            Log.e("oruv", priv.uri);
            mserchuser = new serchPager_user(spotifyApi.getService());

            String spot = spotify.getMe().id;
            System.out.println(spot);*/
          /*  String href = spotifyApi.getService().getMe().href;
            Log.e("href", href);*/
        }
        catch (RetrofitError error)
        {
            System.out.println(error);
        }
        /*try {

         // UserPrivate Private = spotifyApi.getService().getMe();
         // UserPrivate userPrivate1 = spotify.getMe();

           // userPrivate = spotifyApi.getMe();


           //String ser = Private.product;
         //  String se = userPrivate1.id;
           //Log.d("si fun se la mamaron", ser);
        //   Log.d("si fun es una mamada", se);

       }
       catch (RetrofitError error) {
           SpotifyError spotifyError = SpotifyError.fromRetrofitError(error);
           Log.d("Errores que fastidian", "error "+spotifyError);

           // handle error
       }*/

        //SpotifyService service = spotifyApi.getService();
        //String s = service.getMe().product;


        // String se = userPrivate.id;
        // String ser = service.getMe().id;

        //Log.d("datos user", s+" "+ser);







        mContext.bindService(PlayerService.getIntent(mContext), mServiceConnection, Activity.BIND_AUTO_CREATE);
        //   mserchuser.getuser();
    }


    @Override
    public void search(/*@Nullable String searchQuery*/) {
        String searchQuery="desarrollo_elevation";

        if (searchQuery != null && !searchQuery.isEmpty() && !searchQuery.equals(mCurrentQuery)) {
          //  logMessage("query text submit " + searchQuery);
            mCurrentQuery = "desarrollo_elevation";//searchQuery;
            mView.reset();
            mSearchListener = new serchpager_playlist.CompleteListeneres() {
              /*  @Override
                public void onComplete(List<PlaylistTrack> items /*items*///) {
                /*    mView.addData(items);
                }*/

                @Override
                public void onCompletes(List<PlaylistSimple> items) {

                    mView.addData(items);
                }

                @Override
                public void onError(Throwable error) {
                    logError(error.getMessage());
                }
            };
            mSearchPager.getFirstPage(searchQuery, PAGE_SIZE, mSearchListener);
//            mserchuser.getuser();
        }
    }


    @Override
    public void destroy() {
        mContext.unbindService(mServiceConnection);
    }

    @Override
    @Nullable
    public String getCurrentQuery() {
        return mCurrentQuery;
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
    public void loadMoreResults() {
        Log.d(TAG, "Load more...");
        mSearchPager.getNextPage(mSearchListener);
    }

    @Override
    public void selectTrack(PlaylistSimple item, String token) {


        String url = item.uri;
        String id = item.id;
        String name = item.name;

      //  int total = item.tracks.total;


        //int duration =
        //Log.v("des", ""+descripcion);

        Bundle bundle = new Bundle();
        bundle.putString("key", url);
        bundle.putString("token", token);
        bundle.putString("id", id);
        bundle.putString("names", name);

        if(tipo_de_cuenta.equals(null))
        {
            Toast.makeText(mContext, "problemas de conexion intente de nuevo ingresar", Toast.LENGTH_LONG).show();
            return;
        }


      if(tipo_de_cuenta.equals("premium")){

        Intent intent = new Intent(mContext, MainActivity_reproductor_playlist.class);

        intent.putExtras(bundle);

        mContext.startActivity(intent);
        }

    else if (tipo_de_cuenta.equals("open")) {

          Intent intent = new Intent(mContext, MainActivity_reproductor_user_free.class);

          intent.putExtras(bundle);

          mContext.startActivity(intent);

      }
    }

    /*@Override
    public void selectTrack(/*TrackPlaylistTrack item, int s) {
        String previewUrl = item.track.uri;//.preview_url/*.preview_url;

        if (previewUrl == null) {
            logMessage("Track doesn't have a preview");
            return;
        }

        if (mPlayer == null) return;

        String currentTrackUrl = mPlayer.getCurrentTrack();

        if (currentTrackUrl == null || !currentTrackUrl.equals(previewUrl)) {
            mPlayer.play(previewUrl);
        } else if (mPlayer.isPlaying()) {
            mPlayer.pause();
            Log.d("se pauso esta mamada", ""+item.track.uri);
        } else {
            mPlayer.resume();
        }
    }*/

    @Override
    public void stopplay() {

    }

    @Override
    public void playpreview() {

    }

    @Override
    public void playnext() {

    }

    private void logError(String msg) {
        Toast.makeText(mContext, "Error: " + msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg);
    }

    private void logMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
    }
}