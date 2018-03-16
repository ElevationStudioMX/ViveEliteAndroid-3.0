package com.example.desarrollo_elevation.viveelite.spotify;

import com.example.web_api_spotify.models.PlaylistSimple;

import java.util.List;

/**
 * Created by Desarrollo_Elevation on 09/03/17.
 */

public class serch_playlist {

    public interface View {
        void reset();

        void addData(List<PlaylistSimple> items);
    }

    public interface ActionListener {

        void init(String token);

        String getCurrentQuery();

        void search(/*String searchQuery*/);

        void loadMoreResults();

        void selectTrack(/*Track*/PlaylistSimple item, String token);

        void stopplay();

        void playpreview();

        void playnext();

        void resume();

        void pause();

        void destroy();

    }



}
