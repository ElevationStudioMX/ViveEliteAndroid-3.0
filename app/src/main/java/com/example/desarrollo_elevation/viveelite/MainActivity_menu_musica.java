package com.example.desarrollo_elevation.viveelite;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.spotify.ResultListScrollListener;
import com.example.desarrollo_elevation.viveelite.spotify.adapter_playlist;
import com.example.desarrollo_elevation.viveelite.spotify.serch_playlist;
import com.example.desarrollo_elevation.viveelite.spotify.serchpresent_playlist;
import com.example.web_api_spotify.models.PlaylistSimple;

import java.util.List;

import static android.R.attr.name;
import static android.R.attr.typeface;
import static com.example.desarrollo_elevation.viveelite.R.id.toolbar;

public class MainActivity_menu_musica extends AppCompatActivity implements serch_playlist.View {


    private static final String KEY_CURRENT_QUERY = "CURRENT_QUERY";
    public static final String EXTRA_TOKEN = "EXTRA_TOKEN" ;

    private serch_playlist.ActionListener mActionListener;
private  static  String getclas;
    private GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    private MainActivity_menu_musica.ScrollListener mScrollListener = new MainActivity_menu_musica.ScrollListener(mLayoutManager);
    private adapter_playlist mAdapter;
    TextView nameplaylist;


    private class ScrollListener extends ResultListScrollListener {

        public ScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public void onLoadMore() {
            mActionListener.loadMoreResults();
        }
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity_menu_musica.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_musica);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String fonter1 = "fonts/OpenSans-Regular.ttf";

        Typeface t = Typeface.createFromAsset(getApplicationContext().getAssets(), fonter1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmusica);
        nameplaylist = (TextView)findViewById(R.id.txttoolbar_title_playlist);
        toolbar.setTitle("");
        nameplaylist.setText("Playlist");
        nameplaylist.setTextSize(22);
        nameplaylist.setTypeface(t);

        // toolbar.displ.setDisplayHomeAsUpEnabled(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno );




        mActionListener = new serchpresent_playlist(this, this);
        Intent intent = getIntent();
        final String token = intent.getStringExtra(EXTRA_TOKEN);
        mActionListener.init(token);

       /* SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();

        userPrivate = service.getMe();


        String s = userPrivate.product;
        Log.d("usuario ", s);*/

        //  String toker = bundle.getString("toker");
        //  if(toker == null) {
        //

        //   }
       /* else{



            mActionListener.init(toker);
        }
*/



        mActionListener.search();


        // Setup search field
/*        final SearchView searchView = (SearchView) findViewById(R.id.search_resultsesplaylist);
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = "miguelrzk";
                mActionListener.search(/*"miguelrzk"*//*query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/

//        mActionListener.search("miguelrzk");


        // Setup search results list
        mAdapter = new adapter_playlist(this, new adapter_playlist.ItemSelectedListener() {
            @Override
            public void onItemSelected(View itemView, PlaylistSimple item) {

                mActionListener.selectTrack(item, token);
            }
        });


        RecyclerView resultsList = (RecyclerView) findViewById(R.id.recyclerViewerplaylist);
        resultsList.setHasFixedSize(true);
        resultsList.setNestedScrollingEnabled(false);

        resultsList.setLayoutManager(mLayoutManager);
        resultsList.setAdapter(mAdapter);
        resultsList.addOnScrollListener(mScrollListener);

        // If Activity was recreated wit active search restore it
        if (savedInstanceState != null) {
            String currentQuery = savedInstanceState.getString(KEY_CURRENT_QUERY);
            mActionListener.search();/*currentQuery*///);

        }
    }

            /*@Override
            public void onItemSelected(View itemView, /*TrackPlaylist item) {
              //  mActionListener.selectTrack(item /*chingatumadre);
           // }
       // });

        RecyclerView resultsList = (RecyclerView) findViewById(R.id.search_resultses);
        resultsList.setHasFixedSize(true);

        resultsList.setLayoutManager(mLayoutManager);
        resultsList.setAdapter(mAdapter);
        resultsList.addOnScrollListener(mScrollListener);

        // If Activity was recreated wit active search restore it
        if (savedInstanceState != null) {
            String currentQuery = savedInstanceState.getString(KEY_CURRENT_QUERY);
            mActionListener.search(/*currentQuery*///);


       /* pley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //serchpresntcombinado ser = new serchpresntcombinado(this, this);
                mActionListener.stopplay();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionListener.playnext();
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionListener.playpreview();
            }
        });*/



    @Override
    public void reset() {
        mScrollListener.reset();
//        mAdapter.clearData();
    }

    @Override
    public void addData(List<PlaylistSimple> items) {

        mAdapter.addData(items);
    }

    /*@Override
    public void addData(List</*TrackPlaylistTrack> items) {
        mAdapter.addData(items);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        mActionListener.pause();

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






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
