package com.example.desarrollo_elevation.viveelite;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;


//import com.example.helperyoutube.VideoEnabledWebChromeClient;
//import com.jaedongchicken.ytplayer.YoutubePlayerView;
//import com.jaedongchicken.ytplayer.model.YTParams;

import com.example.desarrollo_elevation.viveelite.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.tabs_infranter.favoritos;
import com.example.helperyoutube.JLog;
import com.example.helperyoutube.YTParams;
import com.example.helperyoutube.YoutubePlayerView;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.PlaylistTrack;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static android.R.attr.bitmap;
import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.helperyoutube.YoutubePlayerView.STATE.BUFFERING;
import static com.example.helperyoutube.YoutubePlayerView.STATE.CUED;
import static com.example.helperyoutube.YoutubePlayerView.STATE.ENDED;
import static com.example.helperyoutube.YoutubePlayerView.STATE.PAUSED;
import static com.example.helperyoutube.YoutubePlayerView.STATE.PLAYING;
import static com.example.helperyoutube.YoutubePlayerView.STATE.UNSTARTED;
import static java.security.AccessController.getContext;

/**
 * Created by Desarrollo_Elevation on 21/12/16.
 */

public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
   private Context mContext;
    //Activity activity = (Activity) mContext;

    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;
    String tiemp;

    private  static  String name_database_elite= "elite_v15";
    private  static  String nombre_tipo;



    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;
        Button detalleimg;
        ImageButton favoritos;
        ImageButton compartir;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.txttextoimg);
            this.image = (ImageView) itemView.findViewById(R.id.background);
            this.detalleimg=(Button) itemView.findViewById(R.id.btndetalleimg);
            this.favoritos = (ImageButton)itemView.findViewById(R.id.idimgfavoritosimg);
            this.compartir = (ImageButton)itemView.findViewById(R.id.idimgcompartirimg);


        }

    }




    public static class VideoTypeViewHolder extends RecyclerView.ViewHolder {


         Context cont;
        VideoEnabledWebChromeClient webChromeClient;

        TextView txtTyper;
  //      VideoEnabledWebView  video;
  YoutubePlayerView video;
       // Button detallevideo;
       Button detallevideo;
        ImageButton favoritos;
        ImageButton Compartir;
//        VideoEnabledWebChromeClient webChromeClient;

       // Activity activity;


        //View nonVideoLayout; // Your own view, read class comments
        //ViewGroup videoLayout; // Your own view, read class comments
        //noinspection all
        //View loadingView;

       // LayoutInflater inflater = LayoutInflater.from(cont); //= getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        //;

        public VideoTypeViewHolder(View itemView) {
            super(itemView);

            this.txtTyper = (TextView) itemView.findViewById(R.id.txttextovideo);
            this.video = (YoutubePlayerView) itemView.findViewById(R.id.videro2/*videoView*/);
           // this.detallevideo=(Button)itemView.findViewById(R.id.btndetallevideo);
            this.detallevideo =(Button) itemView.findViewById(R.id.btndetallevideo);

            this.favoritos =(ImageButton) itemView.findViewById(R.id.idimgfavoritosvideo);
            this.Compartir =(ImageButton)itemView.findViewById(R.id.idimgcompartirvideo);




            this.webChromeClient = webChromeClient;
          //  this.activity = activity;

           // this.nonVideoLayout = itemView.findViewById(R.id.nonVideoLayout); // Your own view, read class comments
            //this.videoLayout = (ViewGroup)itemView.findViewById(R.id.videoLayout); // Your own view, read class comments
            //noinspection all


            //this.inflater = LayoutInflater.from(context);



          //  this.loadingView = this.inflater.inflate(R.layout.view_loading_video, null);



            // View nonVideoLayout = itemView.findViewById(R.id.nonVideoLayouter); // Your own view, read class comments
          //  ViewGroup videoLayout = (ViewGroup)itemView.findViewById(R.id.videoLayouter); // Your own view, read class comments
            //noinspection all
           // View loadingView =  getLayoutInflater().inflate(R.layout.view_loading_video, null);


          /*  webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, video) // See all available constructors...
            {
                // Subscribe to standard events, such as onProgressChanged()...
                @Override
                public void onProgressChanged(WebView view, int progress)
                {
                    // Your code...
                }
            };*/


        }

    }

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context, RecyclerView recyclerView) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();



        //Log.v("linea 207", "linea 207");
        //Log.v("linea 208", ""+recyclerView.getClass().getInterfaces()[0].toString()  +" // "+recyclerView.getClass().getSuperclass().toString()+" // "+recyclerView.getClass().getCanonicalName().toString());
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
         //   Log.v("linea 209", "linea 209");
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
          //          Log.v("linea 214", "linea 214");

            //        Log.v("linea 220", "totalitemcount "+totalItemCount+ " lastvisibleitem "+lastVisibleItem+ "loading "+ loading+ " visibletehoold "+ visibleThreshold );

                    if(!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
              //          Log.v("linea 216", "linea 216");
                        if(onLoadMoreListener != null){
                //            Log.v("linea 218", "linea 218");
                          // loading = true;
                            onLoadMoreListener.onLoadMore();
                            //loading = false;
                        }
                        loading = true;
                    }
                }
            });
        }

    }




   /* public void addData(ArrayList<Model> item){
        dataSet.addAll(item);
        notifyDataSetChanged();
    }*/





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {

            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_image, parent, false);
                return new ImageTypeViewHolder(view);


            case Model.VIDEO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_video2, parent, false);
                return new VideoTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 1:

                return Model.IMAGE_TYPE;
            case 2:
               return Model.VIDEO_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final Model object = dataSet.get(listPosition);
        Log.v("posisiones",""+listPosition);

        if (object != null) {
            switch (object.type) {

                case Model.IMAGE_TYPE:

                    //Log.v("es", ""+Model.IMAGE_TYPE);

                   // Log.v("imagen", object.data);

                    String font = "fonts/OpenSans-Light.ttf";
                    String fonter1 = "fonts/OpenSans-Regular.ttf";
                   /* String font2 = "fonts/leadcoat.ttf";
                    String font3 = "fonts/stocky.ttf";
                    String font4 = "fonts/ASMAN.TTF";*/

                    final Typeface face= Typeface.createFromAsset(mContext.getAssets(),font);
                    Typeface facei= Typeface.createFromAsset(mContext.getAssets(),fonter1);
                    ((ImageTypeViewHolder) holder).txtType.setTypeface(face);
                    ((ImageTypeViewHolder) holder).txtType.setTextSize(16);
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);

                    Picasso.with(mContext).load(object.data).into(((ImageTypeViewHolder)holder).image);

                    /*Picasso.with(mContext).load(object.data).networkPolicy(NetworkPolicy.OFFLINE).into(((ImageTypeViewHolder) holder).image, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                            Picasso.with(mContext).load(object.data).into(((ImageTypeViewHolder) holder).image, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                }
                            });

                        }
                    });*/

                    /*Picasso.with(mContext).load(object.data).networkPolicy(NetworkPolicy.OFFLINE).into(((ImageTypeViewHolder) holder).image, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Picasso.with(mContext).load(object.data).into(((ImageTypeViewHolder) holder).image);
                        }
                    });*/


                    //((ImageTypeViewHolder) holder).image.setImageResource(object.img);

                    ((ImageTypeViewHolder) holder).detalleimg.getBackground().setAlpha(00);
                    ((ImageTypeViewHolder) holder).favoritos.getBackground().setAlpha(00);
                    ((ImageTypeViewHolder) holder).compartir.getBackground().setAlpha(00);
                    ((ImageTypeViewHolder) holder).detalleimg.setTypeface(facei);
                    ((ImageTypeViewHolder) holder).detalleimg.setTextSize(17);
                    ((ImageTypeViewHolder) holder).detalleimg.setText(object.titulo);

                    ((ImageTypeViewHolder) holder).detalleimg.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);



                    ((ImageTypeViewHolder)holder).favoritos.setImageResource(object.favoritos);

                    ((ImageTypeViewHolder)holder).favoritos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            DBHome dbHome = new DBHome(mContext, name_database_elite, null, 1);

                            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();


                            Cursor cursor;

                            String sql = "select favoritos from contenido where id="+object.idlink+" and id_tipo="+object.id_tipo+"";
                            ContentValues Valuares = new ContentValues();
                            cursor = sqLiteDatabase.rawQuery(sql, null);


                            java.util.Date utilDate = new java.util.Date();
                            java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                            SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



                            String tiempo = String.valueOf(tiem.format(sq));

                            if(cursor.moveToFirst())
                            {
                                int favorit = cursor.getInt(0);

                                if(favorit == 0)
                                {

                                    Valuares.put("favoritos", "1");
                                    Valuares.put("fecha_favoritos", tiempo);
                                    sqLiteDatabase.update("contenido", Valuares, "id="+object.idlink+" and id_tipo="+object.id_tipo+"", null);

                                    ((ImageTypeViewHolder)holder).favoritos.setImageResource(R.mipmap.favoritos_check_red);

                                    sqLiteDatabase.close();
                                }

                                else if (favorit == 1)
                                {
                                    Valuares.put("favoritos", "0");
                                    Valuares.put("fecha_favoritos", tiempo);
                                    sqLiteDatabase.update("contenido", Valuares, "id="+object.idlink+" and id_tipo="+object.id_tipo+" ", null);
                                    ((ImageTypeViewHolder)holder).favoritos.setImageResource(R.mipmap.favoritos);
                                    sqLiteDatabase.close();
                                }

                            }










                        }
                    });

                    Drawable img = mContext.getResources().getDrawable(object.img);
                    img.setBounds(0,0,60,60);
                    ((ImageTypeViewHolder)holder).detalleimg.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);



                    ((ImageTypeViewHolder)holder).detalleimg.setTextColor(Color.parseColor(object.colores));



                  ((ImageTypeViewHolder) holder).detalleimg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Log.v("boton", "entro por aqui");
                            int nameclasnum;

                            MainActivity_menutolbbed inicio  = new MainActivity_menutolbbed();
                            String contexto_inicio = inicio.conte();


                            MainActivity_recetas recetas = new MainActivity_recetas();
                            String contexto_receta = recetas.contex();

                            String contexto = mContext.getClass().getSimpleName().toString();

                            MainActivity_Favoritos mf = new MainActivity_Favoritos();
                            String contexto_favoritos = mf.contex();





                            Log.d("contexto imag " ,contexto);

                            if(contexto.equals(contexto_inicio))
                            {
                                nameclasnum = 1;

                            }

                            else if(contexto.equals(contexto_receta))
                            {
                                nameclasnum = 2;
                            }

                            else if(contexto.equals(contexto_favoritos)){

                                nameclasnum = 3;
                            }

                            else{
                                nameclasnum =0;
                            }


                      Log.d("name class", ""+nameclasnum);




                            Bundle bundle = new  Bundle();


                            int buidlreceta = recetas.getBunde_receta();
                            String bundle_receta_name = recetas.getBundel_receta_name();

                            if (buidlreceta == 0)
                            {

                            }

                            else {

                                bundle.putInt("id_categoria", buidlreceta);

                            }

                            if (bundle_receta_name.equals(""))
                            {

                            }

                            else {
                                bundle.putString("nombrecategoria", bundle_receta_name);
                            }


                          bundle.putString("imagen", object.data);
                          bundle.putString("titulo", object.titulo);

                            //bundle.putInt("favorito", object.favoritos);
                          bundle.putInt("contextreceta", nameclasnum);
                           bundle.putString("idlink", object.idlink);
                           bundle.putString("link", object.link);
                           bundle.putString("preparacion", object.preparacion);
                            bundle.putString("duracion", object.duracion);
                            bundle.putInt("id_tipo", object.id_tipo);

                            Log.v("parametros ", object.data+" "+object.titulo+" "+object.idlink+" "+object.link+" "+nameclasnum);


                            Intent intent = new Intent(mContext, MainActivity_contenidoreceta.class);
                           intent.putExtras(bundle);



                            //(MultiViewTypeAdapter.this, MainActivity_contenidoreceta.class);
                           // Activity activity = (Activity) mContext;

                            Log.v("boton 1", "entro por aqui ");
                            mContext.startActivity(intent);
                           // activity.overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                            //view.getContext().overridePendingTransition(R.anim.left_in, R.anim.left_out);
                            Log.v("boton 2", "entro por aqui ");
                        }
                    });

                   /* ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int imagenes = (R.mipmap.hambre);

                            if(imagenes == imagenes)
                            {


                                Intent inte  = new Intent(mContext, MainActivity_listview.class);
                                mContext.startActivity(inte);

                            }

                            else {

                            }

                        }
                    });*/

                    ((ImageTypeViewHolder)holder).compartir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            //((ImageTypeViewHolder)holder).image.buildDrawingCache();
                            //Bitmap bitmap = ((ImageTypeViewHolder)holder).image.getDrawingCache();

                            if (object.id_tipo == 1)
                            {
                                nombre_tipo = "receta";
                            }

                            else if(object.id_tipo == 2)
                            {
                                nombre_tipo = "ejercicio";
                            }


                            try {

                              /*  File file = new File(mContext.getCacheDir(), bitmap + ".png");
                                FileOutputStream fo = null;
                                fo = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fo);
                                fo.flush();
                                fo.close();
                                file.setReadable(true, false);*/
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra(Intent.EXTRA_TITLE, object.titulo);
                                intent.putExtra(Intent.EXTRA_TEXT, "http://www.papeleselite.mx/"+nombre_tipo+"/"+object.link);
                                intent.putExtra(Intent.EXTRA_SUBJECT, object.titulo);
                                //intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setType("text/plain");
                                mContext.startActivity(Intent.createChooser(intent, "Compartir con"));

                            }

                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });

                    break;


            case Model.VIDEO_TYPE:


                String fonter = "fonts/OpenSans-Light.ttf";
              String font1 = "fonts/OpenSans-Regular.ttf";

               // Log.v("video", object.data);

                Typeface facer= Typeface.createFromAsset(mContext.getAssets(),fonter);

                ((VideoTypeViewHolder) holder).txtTyper.setTypeface(facer);
                ((VideoTypeViewHolder) holder).txtTyper.setTextSize(16);
               ((VideoTypeViewHolder) holder).txtTyper.setText(object.text);



                ((VideoTypeViewHolder) holder).favoritos.getBackground().setAlpha(00);
                ((VideoTypeViewHolder) holder).Compartir.getBackground().setAlpha(00);


                ((VideoTypeViewHolder)holder).favoritos.setImageResource(object.favoritos);
                ((VideoTypeViewHolder)holder).favoritos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Cursor cursoer;

                        DBHome dbHome = new DBHome(mContext, name_database_elite, null, 1);

                        SQLiteDatabase sqLiteData = dbHome.getWritableDatabase();

                        ContentValues values = new ContentValues();

                        String sqlite = "select favoritos from contenido where id="+object.idlink+" and id_tipo="+object.id_tipo+"";

                        //sqLiteData.execSQL(sqlite, null);

                        cursoer = sqLiteData.rawQuery(sqlite, null);


                        java.util.Date utilDate = new java.util.Date();
                        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



                        String tiempo = String.valueOf(tiem.format(sq));

                        if(cursoer.moveToFirst())
                        {
                            int favoriter = cursoer.getInt(0);



                            if(favoriter == 0)
                            {
                                values.put("favoritos", "1");
                                values.put("fecha_favoritos", tiempo);
                                sqLiteData.update("contenido", values, "id="+object.idlink+"  and id_tipo="+object.id_tipo+"", null);
                                ((VideoTypeViewHolder)holder).favoritos.setImageResource(R.mipmap.favoritos_check_red);
                                sqLiteData.close();


                            }

                            else if(favoriter == 1)
                            {
                                values.put("favoritos", "0");
                                values.put("fecha_favoritos", tiempo);
                                sqLiteData.update("contenido", values, "id="+object.idlink+" and id_tipo="+object.id_tipo+"", null);
                                ((VideoTypeViewHolder)holder).favoritos.setImageResource(R.mipmap.favoritos);
                                sqLiteData.close();


                            }
                        }
                    }
                });



               /* ((VideoTypeViewHolder) holder).video.getSettings().setJavaScriptEnabled(true);
               ((VideoTypeViewHolder) holder).video.getSettings().setAppCacheEnabled(false);
                ((VideoTypeViewHolder) holder).video.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
               ((VideoTypeViewHolder) holder).video.setWebChromeClient(new WebChromeClient());
               ((VideoTypeViewHolder) holder).video.setWebViewClient(new InsideWebViewClient());*/

             //   ((VideoTypeViewHolder)holder).video.getLayoutParams().width = 700;

              /*  ((VideoTypeViewHolder) holder).webChromeClient = new VideoEnabledWebChromeClient(((VideoTypeViewHolder) holder).nonVideoLayout, ((VideoTypeViewHolder) holder).videoLayout, ((VideoTypeViewHolder) holder).loadingView, ((VideoTypeViewHolder) holder).video) // See all available constructors...
                {
                    // Subscribe to standard events, such as onProgressChanged()...
                    @Override
                    public void onProgressChanged(WebView view, int progress)
                    {
                        // Your code...
                    }
                };

                ((VideoTypeViewHolder) holder).webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback() {
                    @Override
                    public void toggledFullscreen(boolean fullscreen) {

                        if (fullscreen)
                        {
                            WindowManager.LayoutParams attrs = ((VideoTypeViewHolder) holder).activity.getWindow().getAttributes();
                            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                            attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                            ((VideoTypeViewHolder) holder).activity.getWindow().setAttributes(attrs);
                            if (android.os.Build.VERSION.SDK_INT >= 14)
                            {
                                //noinspection all
                                ((VideoTypeViewHolder) holder).activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                                //  textView=(TextView)findViewById(R.id.textViewer);
                                //textView.setVisibility(View.INVISIBLE);

                                //textView.setVisibility(View.INVISIBLE);
                                //toolbar.setVisibility(View.INVISIBLE);
                    /*    textvideo=(TextView)findViewById(R.id.textViewer);
                        textvideo.setVisibility(View.INVISIBLE);

                        btnregreseo=(ImageButton)findViewById(R.id.imageregresar);
                        btnregreseo.setVisibility(View.INVISIBLE);
                     //   toolbar.setVisibility(View.INVISIBLE);*/
                         /*   }
                        }
                        else
                        {
                            WindowManager.LayoutParams attrs = ((VideoTypeViewHolder) holder).activity.getWindow().getAttributes();
                            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                            attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                            ((VideoTypeViewHolder) holder).activity.getWindow().setAttributes(attrs);
                            if (android.os.Build.VERSION.SDK_INT >= 14)
                            {*/
                                //noinspection all
                               // ((VideoTypeViewHolder) holder).activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                                //textView.setVisibility(View.VISIBLE);
                                //toolbar.setVisibility(View.VISIBLE);
                       /* textView=(TextView)findViewById(R.id.textViewer);
                        textView.setVisibility(View.VISIBLE);

                       /* textvideo=(TextView)findViewById(R.id.textViewer);
                        textvideo.setVisibility(View.VISIBLE);

                        btnregreseo=(ImageButton)findViewById(R.id.imageregresar);
                        btnregreseo.setVisibility(View.VISIBLE);
                        //toolbar.setVisibility(View.VISIBLE);*/
                          /*  }
                        }



                    }
                });
*/

               // ((VideoTypeViewHolder) holder).video.setWebChromeClient(((VideoTypeViewHolder) holder).webChromeClient);
                // Call private class InsideWebViewClient
                //((VideoTypeViewHolder) holder).video.setWebViewClient(new InsideWebViewClient());

           /*     String d="que honda este es el ejercicio de hoy abdominales extremos hasta el canzancio" +
                        "correr trecientas centadillas, comer una tostado hechada a perder" +
                        "unos cuantos clavos en el licuado de lechuga" +
                        "una tomatas de doña doris para el aguante" +
                        "10 lagartijas de series de 3" +
                        "30 abdominales de series de 3" +
                        "15 sentadillas con series de 3" +
                        "busca como se llega  a la roma " +
                        "correr trecientos kilometros hasta desmallarse, ejercicios de respiracion ya sea de inala y excalall" +
                        "tambien trotar hasta que se quemen los pies y por ultimo 10 lagartijas mas si no hay tabla";

                String datos ="<html>\" +\n" +
                        "                \"<body>\" +\n" +
                        " \" <p> hola</p>\"+\n"+

                        "                \"</body>\" +\n" +
                        "                \"</html>\"";*/

                /*String datatos ="<html><body bgcolor=#ffefe text=#000>" +
                        "<p style=\"text-align: justify;\"> "+d+" </p>"+
                        " </body>" +
                        "</html>";*/

             //  String html = "";
               // html += "<html><body>";
               // html += "<html><body>Youtube video .. <br> <iframe width="320" height="315" src="https://www.youtube.com/embed/lY2H2ZP56K4" frameborder="0" allowfullscreen></iframe></body></html>";
                //html += "<iframe width=\"100%\" height=\"100%\" src=\"http://www.youtube.com/embed/"+object.data+"\"  frameborder=\"0\" allowfullscreen></iframe>";
                //html += "<iframe width=\"100%\" height=\"100%\" src=\"http://www.youtube.com/embed/"+object.data+"?rel=0&autohide=0&autoplay=0&theme=light&fs=0\\\"  allowfullscreen></iframe>";
                //html += "</body></html>";

                //String frameVideo = "<html><body>Youtube video .. <br> <iframe width=\"320\" height=\"315\" src=\"https://www.youtube.com/"+object.data+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

               /* String html = "<iframe class=\"youtube-player\" style=\"border: 0; width: 100%; height: 95%; padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\"http://www.youtube.com/embed/"+object.data+""
                        + "?fs=0\" frameborder=\"0\">\n"
                        + "</iframe>";*/
                /*String html = "";   // frameborder=\"0\"
                html += "<html><body width=\"105%\" height=\"100%\" >";
                html += "<iframe width=\"105%\" height=\"100%\" scrolling=\"no\" transparency=\"transparency\"  src=\"http://www.youtube.com/embed/"+object.data+"?rel=0&fs=1\" frameborder=\"0\" allowfullscreen></iframe>";
                html += "</body></html>";

                ((VideoTypeViewHolder) holder).video
                      .loadData(html, "text/html", "UTF-16");

                /*((VideoTypeViewHolder) holder).video
                        .loadUrl("https://www.youtube.com/watch?v="+object.data+"");*/



              /*  ((VideoTypeViewHolder) holder).detallevideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, MainActivity_detalle_video_tam.class);

                        mContext.startActivity(intent);
                    }
                });*/

                // es un cabo pendiente ?rel=0&autohide=0&autoplay=0&theme=light&fs=0

               ((VideoTypeViewHolder) holder).video.setAutoPlayerHeight(mContext);

                //(VideoTypeViewHolder)holder).video.setAutoPlayerHeight(mContext);

                // if you want to change white backgrond, #default is black.
                // youtubePlayerView.setWhiteBackgroundColor();

                // Control values : see more # https://developers.google.com/youtube/player_parameters?hl=en
               YTParams params = new YTParams();
              params.setControls(1);
               params.setRel(0);
               // params.setAutoplay(1);
                params.setFs(0);//.setfs(0);

               // params.setAutohide(1);
                //params.setShowinfo(0);
             // params.setAutoplay(1);

                // initialize YoutubePlayerCallBackListener with Params and VideoID
                // youtubePlayerView.initialize("WCchr07kLPE", params, new YoutubePlayerView.YouTubeListener())

                // initialize YoutubePlayerCallBackListener and VideoID
                ((VideoTypeViewHolder) holder).video.initialize(object.data,/*"WCchr07kLPE""ayoHgRnwf_U",,*/ params,new YoutubePlayerView.YouTubeListener() {



                    @Override
                    public void onReady() {
                        // when player is ready.
                        JLog.i("onReady()");
                    }

                    @Override
                    public void onStateChange(YoutubePlayerView.STATE state) {

                         // YoutubePlayerView.STATE

                        // UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, CUED, NONE



                        //JLog.i("onStateChange(" + //state + ")");
                    }

                    @Override
                    public void onPlaybackQualityChange(String arg) {
                        JLog.i("onPlaybackQualityChange(" + arg + ")");
                    }

                    @Override
                    public void onPlaybackRateChange(String arg) {
                        JLog.i("onPlaybackRateChange(" + arg + ")");
                    }

                    @Override
                    public void onError(String arg) {
                        JLog.e("onError(" + arg + ")");
                    }

                    @Override
                    public void onApiChange(String arg) {
                        JLog.i("onApiChange(" + arg + ")");
                    }

                    @Override
                    public void onCurrentSecond(double second) {
                        // currentTime callback
                        Message msg = new Message();
                        msg.obj = second;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onDuration(double duration) {
                        // total duration
                        JLog.i("onDuration(" + duration + ")");
                    }

                    @Override
                    public void logs(String log) {
                        // javascript debug log. you don't need to use it.
                        JLog.d(log);
                    }
                });

                Typeface facere= Typeface.createFromAsset(mContext.getAssets(),font1);

                ((VideoTypeViewHolder) holder).detallevideo.setTypeface(facere);
                ((VideoTypeViewHolder) holder).detallevideo.getBackground().setAlpha(00);
                ((VideoTypeViewHolder) holder).detallevideo.setTextSize(17);
                ((VideoTypeViewHolder) holder).detallevideo.setText(object.titulo);

                Drawable imger = mContext.getResources().getDrawable(object.img);
                imger.setBounds(0,0,60,60);
                ((VideoTypeViewHolder)holder).detallevideo.setCompoundDrawablesWithIntrinsicBounds(imger, null, null, null);
                ((VideoTypeViewHolder)holder).detallevideo.setTextColor(Color.parseColor(object.colores));

              //  ((VideoTypeViewHolder) holder).detallevideo;


               /* lo dejamos para derrato por si cualquier cosa pasa Drawable img = mContext.getResources().getDrawable( object.img );
                img.setBounds( 0, 0, 60, 60 );
                ((VideoTypeViewHolder) holder).detallevideo.setCompoundDrawablesWithIntrinsicBounds(img,null, null, null);*/


                ((VideoTypeViewHolder) holder).detallevideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle bundle  = new Bundle();

                       int nameclas;

                        MainActivity_menutolbbed inicio  = new MainActivity_menutolbbed();
                        String contexto_inicio = inicio.conte();

                        MainActivity_Ejercicios ejercicios = new MainActivity_Ejercicios();
                        String contexto_ejercicio = ejercicios.contex();

                        MainActivity_recetas recetas = new MainActivity_recetas();
                        String contexto_receta = recetas.contex();

                        String contexto = mContext.getClass().getSimpleName().toString();

                        MainActivity_Favoritos mainActivity_favoritos = new MainActivity_Favoritos();
                        String contexto_favorito = mainActivity_favoritos.contex();

                        if(contexto.equals(contexto_inicio))
                        {
                            nameclas=1;
                        }

                         else if(contexto.equals(contexto_ejercicio)) {
                            nameclas=2;

                        }

                        else if(contexto.equals(contexto_receta))
                        {
                            nameclas = 3;
                        }

                        else if(contexto.equals(contexto_favorito)){
                            nameclas = 4;
                        }


                        else {

                            nameclas =0;
                        }



                        Double te;



                        if(tiemp == null)
                        {
                            te = 0.0;
                        }
                        else  {

                            te = Double.parseDouble(tiemp);
                        }




                        bundle.putDouble("yttiempo", te);
                        bundle.putString("ytvideo", object.data);
                        bundle.putString("ytitulo", object.titulo);
                        bundle.putInt("context", nameclas);
                        bundle.putString("id", object.idlink );
                        bundle.putString("link", object.link);
                        bundle.putInt("id_tipo", object.id_tipo);



                        Intent intent = new Intent(mContext, details_video.class);

                        intent.putExtras(bundle);

                        Activity activity=(Activity)mContext;

                        mContext.startActivity(intent);
                        //activity.overridePendingTransition(R.anim.zoon_forward_in, R.anim.zoom_forward_out);



                    }
                });


                ((VideoTypeViewHolder)holder).Compartir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {


                            if (object.id_tipo == 1)
                            {
                                nombre_tipo = "receta";
                            }

                            else if(object.id_tipo == 2)
                            {
                                nombre_tipo = "ejercicio";
                            }



                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TITLE, object.titulo);
                            intent.putExtra(Intent.EXTRA_SUBJECT, object.titulo);
                            intent.putExtra(Intent.EXTRA_TEXT, object.titulo);
                            intent.putExtra(Intent.EXTRA_TEXT, "http://www.papeleselite.mx/"+nombre_tipo+"/"+object.link/*"https://www.youtube.com/watch?v="+object.data*/);
                         /*  String link = String.valueOf(intent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v="+object.data));
                            ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clipData = ClipData.newPlainText("link", link );

                            clipboardManager.setPrimaryClip(clipData);*/
                             intent.setType("text/plain");

                            mContext.startActivity(Intent.createChooser(intent, "compartir en"));


                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });


                // try {


                //    MediaController mediacontroller = new MediaController(this.mContext);
                //    mediacontroller.setAnchorView(((VideoTypeViewHolder) holder).video);
                    // Get the URL from String VideoURL
                    //Uri video = Uri.parse(VideoURL);
                    //   Uri videos = Uri.parse("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");

                  //  ((VideoTypeViewHolder) holder).video.setMediaController(mediacontroller);
                    //((VideoTypeViewHolder) holder).video.setVideoPath(object.data);//.setVideoURI(videos);


                // in pixels (left, top, right, bottom)
                    //((VideoTypeViewHolder) holder).video.setVideoPath();
                    //linearLayout.addView(video);


                    //videoview.setVideoURI(video);

               /* } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

                ((VideoTypeViewHolder) holder).video.requestFocus();
                ((VideoTypeViewHolder) holder).video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        //  pDialog.dismiss();
                        //video.start();
                        ((VideoTypeViewHolder) holder).video.start();
                    }
                });

*/
                break;
            }
        }

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // you can cast with float that I recommend.
            double sec = (double) msg.obj;
            tiemp = (String.valueOf(sec));
        }
    };


    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }
    public interface OnLoadMoreListener {
        void onLoadMore();
    }
    public void setLoaded() {
        loading = false;
    }


    public void setLoad(){
        loading = false;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    private class InsideWebViewClient extends WebViewClient {
        @Override
        // Force links to be opened inside WebView and not in Default Browser
        // Thanks http://stackoverflow.com/a/33681975/1815624
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}


