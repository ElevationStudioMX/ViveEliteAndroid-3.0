package com.example.desarrollo_elevation.viveelite.spotify;

import android.support.annotation.Nullable;

public interface Player {

    void play(String url);

    void pause();

    void resume();

    boolean isPlaying();

    int getposition();

    int getDuration();

    @Nullable
    String getCurrentTrack();

    void release();
}
