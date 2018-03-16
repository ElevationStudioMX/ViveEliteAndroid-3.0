package com.example.desarrollo_elevation.viveelite;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.spotify.ResultListScrollListener;
import com.example.desarrollo_elevation.viveelite.spotify.adapter_playlist_track;
import com.example.desarrollo_elevation.viveelite.spotify.serch_playlist_track;
import com.example.desarrollo_elevation.viveelite.spotify.serchpresent_playlist_track;
import com.example.web_api_spotify.models.PlaylistTrack;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.attr.name;
import static android.graphics.Typeface.BOLD;
import static com.example.desarrollo_elevation.viveelite.R.id.toolbar;
import static com.example.desarrollo_elevation.viveelite.R.style.TextAppearance_AppCompat;
import static com.example.desarrollo_elevation.viveelite.R.style.se;

public class MainActivity_reproductor_playlist extends AppCompatActivity implements serch_playlist_track.View  {

    private  static TextView txtTrack;
    private  static TextView txtArtista;
    private  static  TextView txtAlbumes;
    private  static TextView txtstarter;
    private  static  TextView txtender;
    TextView texto;

    private static ImageView playerstop;
    private static ImageButton playnext;
    private static ImageButton playpreview;

    private static ImageView imagenalbum;


    private  static SeekBar barra;

    private  static int numerotrack;
    private  static  int seleccion_track;

    private static String id;
    private static String toker;
    private static String name;
    private static String tol;

    static final String EXTRA_TOKEN = "EXTRA_TOKEN";

    private static final String KEY_CURRENT_QUERY="CURRENT_QUERY";

    private serch_playlist_track.ActionListener mActionListener;

    private LinearLayoutManager mLinearLayout = new LinearLayoutManager(this);
    private MainActivity_reproductor_playlist.ScrollListener mScrolllistener = new MainActivity_reproductor_playlist.ScrollListener(mLinearLayout);
    private adapter_playlist_track mAdapter;


    private class ScrollListener extends ResultListScrollListener{

        public ScrollListener(LinearLayoutManager linearLayout){super(linearLayout);}

        @Override
        public void onLoadMore() {

            mActionListener.loadMoreResult();
        }
    }

    public static Intent CreateIntet(Context context)
    {
        return  new Intent(context, MainActivity_reproductor_playlist.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        /*final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
*/
       /* Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
*/

        setContentView(R.layout.activity_main_reproductor_playlist);
        txtTrack =(TextView)findViewById(R.id.txttrack);
        txtArtista=(TextView)findViewById(R.id.txtArtistas);
        txtAlbumes=(TextView)findViewById(R.id.txtAlbum);
        txtender =(TextView)findViewById(R.id.txtend);
        txtstarter =(TextView)findViewById(R.id.txtstart);
        playerstop =(ImageView) findViewById(R.id.btnplaystop);
        playnext =(ImageButton)findViewById(R.id.btnnext);
        playpreview=(ImageButton)findViewById(R.id.btnpreview);
        barra =(SeekBar)findViewById(R.id.seekBar);



        String font ="fonts/OpenSans-Regular.ttf";

        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), font);

        txtTrack.setTypeface(typeface);
        txtArtista.setTypeface(typeface);
        txtAlbumes.setTypeface(typeface);
        txtender.setTypeface(typeface);
        txtstarter.setTypeface(typeface);

        barra.setDrawingCacheBackgroundColor(Color.WHITE);

        //barra.setClickable(true);


        imagenalbum = (ImageView)findViewById(R.id.imagentrack);



        playpreview.getBackground().setAlpha(00);
        playnext.getBackground().setAlpha(00);


        Bundle bundle =  getIntent().getExtras();

        toker = bundle.getString("token");
        name = bundle.getString("key");
        id = bundle.getString("id");
        tol = bundle.getString("names");

        String opensans ="fonts/OpenSans-Regular.ttf";
        Typeface t = Typeface.createFromAsset(getApplicationContext().getAssets(), opensans);

       // typeface ter = Typeface.createFromFile("");
        //String d = Typeface.BOLD;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarreproductorplaylist);

        texto = (TextView)findViewById(R.id.txttoolbar_title);
        texto.setText(tol);
        texto.setTextSize(22);
        texto.setTypeface(t);

        toolbar.setTitle("");
        //toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // toolbar.displ.setDisplayHomeAsUpEnabled(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);


        mActionListener = new serchpresent_playlist_track(this, this);
        mActionListener.init(toker, id);

        mActionListener.search(id);

     mAdapter = new adapter_playlist_track(this, new adapter_playlist_track.ItemSelectedListener() {
         @Override
         public void onItemSelected(View itemview, PlaylistTrack item) {
             mActionListener.selectTrack(item, numerotrack, name);

         }
     });

        RecyclerView resultlist = (RecyclerView)findViewById(R.id.recyclerViewerreproductor);
        resultlist.setHasFixedSize(true);
        resultlist.setNestedScrollingEnabled(false);
        //resultlist.addItemDecoration(new  DividerItemDecoration(this, 1));

        resultlist.setLayoutManager(mLinearLayout);
        resultlist.setAdapter(mAdapter);
        resultlist.addOnScrollListener(mScrolllistener);


        if(savedInstanceState != null)
        {
            String currentquery = savedInstanceState.getString(KEY_CURRENT_QUERY);
            mActionListener.search(currentquery);
        }



        playerstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionListener.stopplay();
            }
        });

        playerstop.setEnabled(false);

playnext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mActionListener.playnext();
    }
});

        playnext.setEnabled(false);

playpreview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mActionListener.playpreview();
    }
});

        playpreview.setEnabled(false);

    }





    @Override
    public void reset() {
 mScrolllistener.reset();
    }

    @Override
    public void addData(List<PlaylistTrack> items) {
 mAdapter.addData(items);

    }


    @Override
    protected void onPause() {
        super.onPause();
        mActionListener.pause();
        Log.d("respuesta " , " con el premium");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActionListener.resume();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActionListener.getCurrentQuery() != null) {
            outState.putString(KEY_CURRENT_QUERY, mActionListener.getCurrentQuery());
        }
    }

    @Override
    protected void onDestroy() {
        mActionListener.destroy();
        super.onDestroy();

    }

    public  TextView texttrack(){

        return this.txtTrack;
    }

    public  TextView textArtista(){
        return  this.txtArtista;
    }

    public  TextView textAlbumnes(){

        return this.txtAlbumes;
    }

    public TextView textStart(){return  this.txtstarter;}

    public TextView textEnd(){return  this.txtender;}

    public ImageView imagalbum(){
        return  this.imagenalbum;
    }

    public ImageView imagenplaystop(){
        return  this.playerstop;

    }

    public  ImageButton btnplaynext(){
        return this.playnext;
    }

    public ImageButton btnPlaypreview(){
        return this.playpreview;
    }

    public SeekBar barradeprogreso(){
        return barra;
    }

    public int numtrack(int posision){
        this.numerotrack = posision;
        return this.numerotrack;
    }

    public int seleccionartrack(){
        this.seleccion_track = numerotrack;
        return this.seleccion_track;
    }


    @Override
    public boolean onSupportNavigateUp() {


//mActionListener.pause();
        mActionListener.pausarback();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onBackPressed();

                        Log.i("tag", "This'll run 300 milliseconds later");
                    }
                },
                100);


        //mActionListener.pausarback();
       // Log.v("se pauso", ""+2);


        //onBackPressed();
        //mActionListener.pausarback();

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() ==0)
        {
            mActionListener.pausarback();
        }
        return super.onKeyDown(keyCode, event);
    }





}
