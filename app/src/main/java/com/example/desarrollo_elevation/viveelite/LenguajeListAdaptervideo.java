package com.example.desarrollo_elevation.viveelite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
//import static com.example.desarrollo_elevation.viveelite.R.id.imageView;
import static com.example.desarrollo_elevation.viveelite.R.id.videoView;

/**
 * Created by Desarrollo_Elevation on 14/12/16.
 */

public class LenguajeListAdaptervideo extends ArrayAdapter<String>

{
    private final Activity context;
    private final String[] itemname;
    private final String[] integers;

    public LenguajeListAdaptervideo(Activity context, String[] itemname ,String[] integers) {
        super(context, R.layout.activity_main_pruebavideo, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.integers=integers;
    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_main_pruebavideo,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView2);
        final VideoView video = (VideoView) rowView.findViewById(videoView);
        //TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        txtTitle.setText(itemname[posicion]);
       // video.

        //MediaController mediacontroller = new MediaController();
        //mediacontroller.setAnchorView(video);


      //  video.setMediaController(mediacontroller);
        //imageView.setImageResource(integers[posicion]);
       // etxDescripcion.setText("Description "+itemname[posicion]);

        final ProgressDialog pDialog;


        // Execute StreamVideo AsyncTask

        // Create a progressbar
      // pDialog = new ProgressDialog(this.context);
        // Set progressbar title
       // pDialog.setTitle("Android Video Streaming Tutorial");
        // Set progressbar message
      //  pDialog.setMessage("Buffering...");
        //pDialog.setIndeterminate(false);
        //pDialog.setCancelable(false);
        // Show progressbar
       // pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(this.context);
            mediacontroller.setAnchorView(video);
            // Get the URL from String VideoURL
            //Uri video = Uri.parse(VideoURL);
            Uri videos = Uri.parse(integers[posicion]);

            video.setMediaController(mediacontroller);
            video.setVideoURI(videos);
            //videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
              //  pDialog.dismiss();
                video.start();
            }
        });


        return rowView;
    }

}
