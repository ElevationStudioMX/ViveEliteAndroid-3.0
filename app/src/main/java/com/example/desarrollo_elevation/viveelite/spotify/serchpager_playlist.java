package com.example.desarrollo_elevation.viveelite.spotify;

import com.example.web_api_spotify.SpotifyCallback;
import com.example.web_api_spotify.SpotifyError;
import com.example.web_api_spotify.SpotifyService;
import com.example.web_api_spotify.models.Pager;
import com.example.web_api_spotify.models.PlaylistSimple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.client.Response;

/**
 * Created by Desarrollo_Elevation on 09/03/17.
 */

public class serchpager_playlist {

    private final SpotifyService mSpotifyApi;
    private int mCurrentOffset;
    private int mPageSize;
    private String mCurrentQuery;

    public interface CompleteListeneres {
        void onCompletes(List<PlaylistSimple> items/*trackListitems*/);

        void onError(Throwable error);
    }

    public serchpager_playlist(SpotifyService spotifyApi) {
        mSpotifyApi = spotifyApi;
    }

    public void getFirstPage(String query, int pageSize, CompleteListeneres listeneres) {
        mCurrentOffset = 0;
        mPageSize = pageSize;
        mCurrentQuery = query;
        getData(query, 0, pageSize, listeneres);
    }

    public void getNextPage(CompleteListeneres listener) {
        mCurrentOffset += mPageSize;
        getData(mCurrentQuery, mCurrentOffset, mPageSize, listener);
    }

    private void getData(String query, int offset, final int limit, final CompleteListeneres listener) {

        Map<String, Object> options = new HashMap<>();
        options.put(SpotifyService.OFFSET, offset);
        options.put(SpotifyService.LIMIT, limit);



        mSpotifyApi.getPlaylists("desarrollo_elevation"/*query*/, options, new SpotifyCallback<Pager<PlaylistSimple>>() {
            @Override
            public void failure(SpotifyError error) {

            }

            @Override
            public void success(Pager<PlaylistSimple> playlistSimplePager, Response response) {

                listener.onCompletes(playlistSimplePager.items);

            }
        });



    }
}