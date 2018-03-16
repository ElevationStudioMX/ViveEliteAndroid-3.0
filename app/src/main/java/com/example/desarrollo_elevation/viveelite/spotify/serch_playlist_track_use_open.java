package com.example.desarrollo_elevation.viveelite.spotify;

import com.example.web_api_spotify.models.PlaylistTrack;

import java.util.List;

/**
 * Created by Desarrollo_Elevation on 16/03/17.
 */

public class serch_playlist_track_use_open {

     public interface  View{
         void reset();

         void addData(List<PlaylistTrack> item);

     }

    public interface  ActionListener {
        void init(String token, String url);

        String getCurrentQuery();

        void search (String serch);

        void loadMoreResult();

        void selectTrack(PlaylistTrack track);

        void stooplay();

        void playpreview();

        void playnext();

        void resume();

        void pause();

        void destroy();


    }
}
