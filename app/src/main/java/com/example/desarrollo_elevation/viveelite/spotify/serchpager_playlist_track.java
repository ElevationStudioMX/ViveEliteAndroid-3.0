package com.example.desarrollo_elevation.viveelite.spotify;

import com.example.web_api_spotify.SpotifyCallback;
import com.example.web_api_spotify.SpotifyError;
import com.example.web_api_spotify.SpotifyService;
import com.example.web_api_spotify.models.Pager;
import com.example.web_api_spotify.models.PlaylistTrack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit.client.Response;

/**
 * Created by Desarrollo_Elevation on 14/03/17.
 */

public class serchpager_playlist_track {

    private final SpotifyService mSpotifyApi;
    private int mCurrentOffest;
    private int mPageSize;
    private String mCurrentQuery;


    public interface  CompleteListener{
     void onComplete(List<PlaylistTrack> item);
     void onError(Throwable error);

    }

    public serchpager_playlist_track(SpotifyService spotifyService){
        mSpotifyApi = spotifyService;
    }

    public void getFristPage(String query, int Pagesize, CompleteListener listener)
    {
        mCurrentOffest=0;
        mPageSize = Pagesize;
        mCurrentQuery = query;
        getData(query, 0, Pagesize, listener);

    }

    public void getNextPage(CompleteListener listener)
    {
        mCurrentOffest += mPageSize;
        getData(mCurrentQuery, mCurrentOffest, mPageSize, listener);
    }

    private void getData(String query, int offest, final int limit, final CompleteListener listener)
    {
        Map<String, Object> options = new HashMap<>();

        options.put(SpotifyService.OFFSET, offest);
        options.put(SpotifyService.LIMIT, limit );

        mSpotifyApi.getPlaylistTracks("desarrollo_elevation", query, options, new SpotifyCallback<Pager<PlaylistTrack>>() {
            @Override
            public void failure(SpotifyError error) {

            }

            @Override
            public void success(Pager<PlaylistTrack> playlistTrackPager, Response response) {

                listener.onComplete(playlistTrackPager.items);

            }
        });

    }


}
