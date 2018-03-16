package com.example.desarrollo_elevation.viveelite.spotify;

import com.example.web_api_spotify.SpotifyService;
import com.example.web_api_spotify.models.PlaylistTrack;

import java.util.List;

import static android.R.string.ok;

/**
 * Created by Desarrollo_Elevation on 14/03/17.
 */

public class serch_playlist_track
{



/*private final SpotifyService mSpotifyApi;
    private int mCurrentOffest;
    private int mPageSize;
    private String mCurrentQuery;


    public interface  CompleteListener{

    }*/


    public interface View {
        void reset();
        void addData(List<PlaylistTrack> items);
    }

    public interface ActionListener{
        void init(String token, String url);

        String getCurrentQuery();
        void search(String url);
        void loadMoreResult();
        void selectTrack(PlaylistTrack item, int numplay, String url);
        void stopplay();
        void playpreview();
        void playnext();
        void resume();
        void pause();
        void iniciandoplaylist(String url);
        void pausarback();
        void destroy();
        void backemergencia(String token);






    }


}
