package com.example.desarrollo_elevation.viveelite;

/**
 * Created by Desarrollo_Elevation on 20/05/17.
 */

import android.view.View;
import android.widget.ProgressBar;

public class ProgressViewHolder extends RecyclerViewHolders{
    public ProgressBar progressBar;
    public ProgressViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);


    }





}