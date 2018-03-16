package com.example.desarrollo_elevation.viveelite;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.spotify.ResultListScrollListener;
import com.example.desarrollo_elevation.viveelite.spotify.adapter_playlist_track_use_open;
import com.example.desarrollo_elevation.viveelite.spotify.serch_playlist_track_use_open;
import com.example.desarrollo_elevation.viveelite.spotify.serchpresent_playlist_track_use_open;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.PlaylistTrack;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.desarrollo_elevation.viveelite.R.mipmap.musica;
import static com.example.desarrollo_elevation.viveelite.R.style.se;

public class MainActivity_reproductor_user_free extends AppCompatActivity implements serch_playlist_track_use_open.View {

    private static TextView txttrack;
    private static TextView txtArtista;
    private static TextView txtalbum;
    private static TextView txtend;
    private static TextView txtstart;
    private static SeekBar seekBar;
    private static ImageView imgablum;
    private static ImageView playstorp;
    private static ImageButton playnext;
    private static ImageButton playpreview;
    Toolbar toolbar;

    private  TextView texto;

    private  static  String URL;
    Bundle bundle;

    static final String EXTRA_TOKEN = "EXTRA_TOKEN";
    private static final String KEY_CURRENT_QUERY = "CURRENT_QUERY";

    private serch_playlist_track_use_open.ActionListener mActionlistener;

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private ScrollListener scrollListener = new ScrollListener(linearLayoutManager);
    private adapter_playlist_track_use_open mAdapter;


    private class ScrollListener extends ResultListScrollListener {

        public ScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public void onLoadMore() {
            mActionlistener.loadMoreResult();
        }
    }

    public static Intent createIntet(Context context) {
        return new Intent(context, MainActivity_reproductor_user_free.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reproductor_user_free);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String front ="fonts/OpenSans-Regular.ttf";

        Typeface t = Typeface.createFromAsset(getApplicationContext().getAssets(), front);

        txttrack = (TextView) findViewById(R.id.trackfree);
        txttrack.setTextSize(19);
        txttrack.setTypeface(t);

        txtArtista = (TextView) findViewById(R.id.trackartistafree);
        txtArtista.setTextSize(13);
        txtArtista.setTypeface(t);


        txtalbum = (TextView) findViewById(R.id.trackalbumfree);
        txtalbum.setTextSize(13);
        txtalbum.setTypeface(t);

        txtend = (TextView) findViewById(R.id.endtrackdfree);
        txtend.setTypeface(t);
        txtstart = (TextView) findViewById(R.id.startrackfree);
        txtstart.setTypeface(t);
        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        seekBar.setClickable(false);

     //   seekBar.setEnabled(false);

        imgablum = (ImageView) findViewById(R.id.imagtrackfree);
        playstorp = (ImageView) findViewById(R.id.imageplaystopfree);
        playnext = (ImageButton) findViewById(R.id.imageplaynextfree);
        playpreview=(ImageButton)findViewById(R.id.imageplypreviewfree);

        playnext.getBackground().setAlpha(00);
        playpreview.getBackground().setAlpha(00);

        playstorp.setEnabled(false);

        toolbar = (Toolbar)findViewById(R.id.toolbarreproductorfree);
        toolbar.setTitle("");

        texto = (TextView)findViewById(R.id.txttoolbar_title_reproductor_userfree);
        texto.setTextSize(22);
        texto.setTypeface(t);
        //toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // toolbar.displ.setDisplayHomeAsUpEnabled(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);


        Intent intent = getIntent();
        String toke = intent.getStringExtra(EXTRA_TOKEN);

        bundle = getIntent().getExtras();

        String toker = bundle.getString("token");
        String id = bundle.getString("id");
        String name_playlist = bundle.getString("names");
        URL = bundle.getString("key");


        texto.setText(name_playlist);

        mActionlistener = new serchpresent_playlist_track_use_open(this, this);
        mActionlistener.init(toker, id);

        mActionlistener.search(id);

        mAdapter = new adapter_playlist_track_use_open(this, new adapter_playlist_track_use_open.ItemSelectedListener() {
            @Override
            public void onItemSelected(View itemView, PlaylistTrack item) {

           mActionlistener.selectTrack(item);


            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewerreproductorfree);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(scrollListener);

        if(savedInstanceState != null){
            String currentquery = savedInstanceState.getString(KEY_CURRENT_QUERY);
            mActionlistener.search(currentquery);

        }


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

               // Toast.makeText(this, 1, Toast.LENGTH_LONG).show();

            }
        });

        playstorp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionlistener.stooplay();
            }
        });
    }




    @Override
    public void reset() {
scrollListener.reset();
    }

    @Override
    public void addData(List<PlaylistTrack> item) {

        mAdapter.addData(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mActionlistener.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mActionlistener.resume();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActionlistener.getCurrentQuery() != null) {
            outState.putString(KEY_CURRENT_QUERY, mActionlistener.getCurrentQuery());
        }
    }

    @Override
    protected void onDestroy() {
        mActionlistener.destroy();
        super.onDestroy();

    }

    public  TextView trackfree(){
        return this.txttrack;
    }

    public TextView artistafree(){
        return this.txtArtista;

    }

    public TextView Albumes(){
        return  this.txtalbum;
    }

    public ImageView imagenRola(){
        return  this.imgablum;
    }

    public TextView start(){
        return this.txtstart;

    }

    public  ImageView playystop(){
        return  this.playstorp;
    }

    public TextView end(){
      return  this.txtend;
    }

 public SeekBar barra (){
     return this.seekBar;
 }

    public String getURL()
    {

       return this.URL;
    }


    @Override
    public boolean onSupportNavigateUp() {
        mActionlistener.playpreview();
        playstorp.setImageResource(R.mipmap.play);
        onBackPressed();
        return false;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            mActionlistener.playpreview();
            playstorp.setImageResource(R.mipmap.play);
        }

        return super.onKeyDown(keyCode, event);
    }
}