package com.example.desarrollo_elevation.viveelite;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.Adapter.Adapter_ingredientes;
import com.example.desarrollo_elevation.viveelite.Adapter.Model_Ingredientes;
import com.example.desarrollo_elevation.viveelite.DB.DBHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.desarrollo_elevation.viveelite.R.mipmap.favoritos;

/**
 * Created by Desarrollo_Elevation on 21/02/17.
 */

public class details_video extends AppCompatActivity {
    private  static  String name_database_elite= "elite_v15";
    ImageButton favorito;
    Button Compartir;
    TextView tex;
    private Cursor cursor, cursorer;
    private DBHome dbHome1;
    private  SQLiteDatabase ds;
    private VideoEnabledWebView web;
    private VideoEnabledWebChromeClient webChromeClient;
    Toolbar toolbar;
    private static String video;
    private static String imagen;
    private static  int nota;
    private  static Cursor fila;
    private  static  Cursor filatiem;
    private static  String eleve_tiem;
    TextView textTiutulo;
    TextView textSubtitulo;
    TextView ingrediente;
    TextView preparacion;
    TextView duracion;
    TextView tiempo;
    Button videos;
    private static ArrayList<Model_Ingredientes> listado;
    private static String linkreceta;
    private static String linkejercicios;
    private static  String canidad_anidada;
    private static String unidad_anidada;
    private  static  String nombre_tipo;
    private  static  int id_ingrediente;
    private  static  int id_tipo_contenido;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_video);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        tex = (TextView) findViewById(R.id.textyt);
        textTiutulo = (TextView) findViewById(R.id.txttitulovideo);
        textSubtitulo = (TextView) findViewById(R.id.txtsutitulovideo);
        preparacion = (TextView) findViewById(R.id.textpreparacionyt);
        duracion = (TextView) findViewById(R.id.txtduracionesyt);
        tiempo = (TextView) findViewById(R.id.txtduraciontiempyt);
        videos = (Button) findViewById(R.id.btncontenidovideos);
        favorito = (ImageButton) findViewById(R.id.btnfavoritosvideo);
        Compartir = (Button) findViewById(R.id.btncompartirvideo);
        toolbar = (Toolbar) findViewById(R.id.textViewtolbaryt);
        toolbar.setTitleTextColor(Color.WHITE);
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        web = (VideoEnabledWebView) findViewById(R.id.weberyt);
        // scrollView=(ScrollView)findViewById(R.id.scrollViewyt);
        //      web =(WebView)findViewById(R.id.webi);
        web.getSettings().setJavaScriptEnabled(true);
        //  web.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36");
        web.getSettings().setPluginState(WebSettings.PluginState.ON);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setMediaPlaybackRequiresUserGesture(false);
        //webView.getSettings().setUseWideViewPort(true);
        web.getSettings().setAllowFileAccess(true);
        web.setWebChromeClient(new WebChromeClient());
        // web.getSettings().getPluginState();
        final View nonVideoLayout = findViewById(R.id.nonVideoLayoutyt); // Your own view, read class comments
        final ViewGroup videoLayout = (ViewGroup) findViewById(R.id.videoLayoutyt); // Your own view, read class comments
        View loadingView = getLayoutInflater().inflate(R.layout.view_loading_video, null); // Your own view, read class comments
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, web) {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                // Your code...
            }
        };
        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback() {
            @Override
            public void toggledFullscreen(boolean fullscreen) {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen) {
                    // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //nonVideoLayout.setBackgroundColor(Color.BLACK);
                    videoLayout.setBackgroundColor(Color.BLACK);
                    ///setMovementMethod(new ScrollingMovementMethod());

                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        //noinspection all
                        //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        toolbar.setVisibility(View.INVISIBLE);
                        // scrollView.setMotionEventSplittingEnabled(false);
                        //    webView.setScaleX(View.SCREEN_STATE_ON);
                        //  scrollView.setVisibility(View.INVISIBLE);
                        tex.setVisibility(View.INVISIBLE);
                        textSubtitulo.setVisibility(View.INVISIBLE);
                        textTiutulo.setVisibility(View.INVISIBLE);
                        favorito.setVisibility(View.INVISIBLE);
                        Compartir.setVisibility(View.INVISIBLE);
                        videos.setVisibility(View.INVISIBLE);
                    }
                } else {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //nonVideoLayout.setBackgroundColor(Color.BLACK);
                    videoLayout.setBackgroundColor(Color.WHITE);
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        toolbar.setVisibility(View.VISIBLE);
                        web.setVisibility(View.VISIBLE);
                        videoLayout.setVisibility(View.VISIBLE);
                        // scrollView.setVisibility(View.VISIBLE);
                        //  scrollView.setMotionEventSplittingEnabled(true);
                        tex.setVisibility(View.VISIBLE);
                        nonVideoLayout.setVisibility(View.VISIBLE);
                        Compartir.setVisibility(View.VISIBLE);
                        favorito.setVisibility(View.VISIBLE);
                        textTiutulo.setVisibility(View.VISIBLE);
                        textSubtitulo.setVisibility(View.VISIBLE);
                        videos.setVisibility(View.VISIBLE);
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
            }
        });
        web.setWebChromeClient(webChromeClient);
        web.setWebViewClient(new details_video.InsideWebViewClient());
        // web.setWebViewClient(new InsideWebViewClient());
        //);
    /*    String url = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "\n" +
                "\n" +
                "<body>\n" +
                "<div id=\"player\"></div>\n" +
                "</body>\n" +
                "</html>";

        String javascript = "var tag = document.createElement('script');\n" +
                "\n" +
                "tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                "var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
                "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                "\n" +
                "var player;\n" +
                "function onYouTubeIframeAPIReady() {\n" +
                "    player = new YT.Player('player', {\n" +
                "        height: '100%',\n" +
                "        width: '100%',\n" +
                "        videoId: 'MpD_Z2VPNzo',\n" +
                "        events: {\n" +
                "        'onReady': onReady\n" +
                "        },\n" +
                "        playerVars: {\n" +
                "        color: 'white',\n" +
                "        showinfo: 0,\n" +
                "        fs : 1,\n" +
                "         }\n" +
                "    });\n" +
                "}\n" +
                "function onReady() {\n" +
                "    player.addEventListener('onStateChange', function(e) {\n" +
                "        console.log('State is:', e.data);\n" +
                "        if(e.data == 2)\n" +
                "        {\n" +
                "        var d =\tparseInt(player.getCurrentTime());\n" +
                "           console.log('el tiempo de esta mamada es', d);\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    });\n" +
                "}";
                */
        //   web.loadData(javascript, "text/javascript", null);
        // web.loadData(url, "text/html", "UTF-16");
        Log.d("resultado  ", "" + tiempo);
       /* String detail ="Ejercicio de cardio" + "\n" +
                "conteido: correr cada 1 minuto despues de un movimiento"
                + "\n" + "salto de rana y de tijera"
                + "\n" + "30 abdominales series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 lagartijas series de 3";
               /* + "\n" + "y por ultimo ejercicios de respiracion para control de oxigeno."; */
        String font = "fonts/OpenSans-Light.ttf";
        String font1 = "fonts/OpenSans-Bold.ttf";
        String font2 = "fonts/OpenSans-Regular.ttf";
        String font3 = "fonts/Gotham-Medium.ttf";
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), font);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), font1);
        Typeface typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), font2);
        Typeface typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), font3);
        videos.getBackground().setAlpha(00);
        videos.setTypeface(typeface2);
        videos.setTextSize(22);
        textTiutulo.setTextSize(24);
        textTiutulo.setTypeface(typeface);
        tiempo.setTextSize(20);
        tiempo.setTypeface(face);
        textSubtitulo.setTextSize(22);
        textSubtitulo.setTypeface(typeface1);
        preparacion.setText("Preparación:");
        preparacion.setTextSize(22);
        preparacion.setTypeface(typeface1);
        duracion.setText("Duración: ");
        duracion.setTextSize(22);
        duracion.setTypeface(typeface1);
        tex.setTextSize(18);
        tex.setTypeface(face);
        Intent appLinkIntent = getIntent();
        final String appLinkAction = appLinkIntent.getAction();
        final Uri appLinkData = appLinkIntent.getData();
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {
            final String appp = appLinkData.getLastPathSegment();
            Log.v("estos datos a mostrar", appLinkData +" "+ appp);
            DBHome dbHome = new DBHome(this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            String sqltiem = "select id_tipo from contenido where permarlink='"+appp+"' ";
            filatiem = sqLiteDatabase.rawQuery(sqltiem, null);
            if (filatiem.moveToFirst()) {
                id_tipo_contenido = filatiem.getInt(0);
            }
            if(id_tipo_contenido == 1) {
                textSubtitulo.setText("Ingredientes:");
                linkreceta = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/receta-permalink/"+appp+"";
                new JsonTaskrecetaspermarlink().execute(linkreceta);
            } else if(id_tipo_contenido == 2) {
                Log.v("paso por aqui", "ejercicios");
                linkejercicios = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/ejercicio-permalink/"+appp+"";
                new JsonTaskejerciciospermarlink().execute(linkejercicios);
                videos.setText("E J E R C I C I O S");
                videos.setTextColor(Color.parseColor("#6dd8d8"));
                Drawable img = getApplicationContext().getResources().getDrawable(R.mipmap.imgejercicio);
                img.setBounds(0,0,60,60);
                videos.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                textSubtitulo.setTextSize(18);
                textSubtitulo.setTypeface(face);
                //textSubtitulo.setVisibility(View.VISIBLE);
            }
            /*String sqlid = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+appp+"";
            cursor = sqLiteDatabase.rawQuery(sqlid, null);
            if(cursor.moveToFirst())
            {
                String title = cursor.getString(0);
                String imagenes = cursor.getString(1);
                String prepa = cursor.getString(2);
                String dura = cursor.getString(3);

                text.setText(prepa+"\n" /*true);/*despliegue+"\n\n"+
                preparacion+"\n\n\n"+"Duración: "+duracion+"\n\n\n");*/
            //text.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            //text.setJustify(true);
               /* durateiemp.setText(dura+"\n\n");
                getSupportActionBar().setTitle(title);
                titulo.setText(title);
                Picasso.with(this).load(imagenes).resize(1000,0).into(imag);
            }
*/
/*
            String sqlingredientes ="select cantidad, ingrediente from ingredientes where ingredientes.id="+appp+"";
            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);
            listado = new ArrayList<>();
            while (cursor.moveToNext()) {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);
                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviweringredientes);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator()); */

            String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '" + appp + "'";
            cursor = sqLiteDatabase.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                int far = Integer.parseInt(cursor.getString(0));
                if (far == 0) {
                    favorito.setBackgroundResource(favoritos);
                } else if (far == 1) {
                    favorito.setBackgroundResource(R.mipmap.favoritos_check_red);
                }
            }

            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHome1 = new DBHome(details_video.this, name_database_elite, null, 1);
                    ds = dbHome1.getWritableDatabase();
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                    SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String tiempo = String.valueOf(tiem.format(sq));
                    String sqlite = "select favoritos from contenido where permarlink='" + appp/*titulo.getText().toString()*/ + "'";
                    ContentValues values = new ContentValues();
                    cursorer = ds.rawQuery(sqlite, null);
                    if (cursorer.moveToFirst()) {
                        int favor = Integer.parseInt(cursorer.getString(0));
                        Log.v("favorito", "" + favor);
                        if (favor == 0) {
                            values.put("favoritos", "1");
                            values.put("fecha_favoritos", tiempo);
                            ds.update("contenido", values, "permarlink='" + appp + "'", null);
                            favorito.setBackgroundResource(R.mipmap.favoritos_check_red);
                            ds.close();
                        } else if (favor == 1) {
                            values.put("favoritos", "0");
                            values.put("fecha_favoritos", tiempo);
                            ds.update("contenido", values, "permarlink='" + appp + "'", null);
                            favorito.setBackgroundResource(favoritos);
                            ds.close();
                        }
                    }
                }

            });
            Compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String direccion = String.valueOf(appLinkData);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, direccion);
                        intent.putExtra(Intent.EXTRA_SUBJECT, textTiutulo.getText().toString());
                        intent.putExtra(Intent.EXTRA_TITLE, textTiutulo.getText().toString());
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setType("text/plain");
                        startActivity(Intent.createChooser(intent, "Compartir con"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
          /*  Log.v("datos ", "" + appLinkData + " " + appp);
            if(appp.equals("agua-de-jamaica")) {
                titulo.setText(appp);
                Log.v("esto es" , "un rico y deliscios "+appp+" Pero se la bañan con los ingredientes hubieran hecho minimo meterno en el refri o comprar sobre sale barato");
            } else {
            }*/
        } else {
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("ytitulo");
            final String idlink = bundle.getString("id");
            final String link = bundle.getString("link");
            final int id_tipo = bundle.getInt("id_tipo");
            getSupportActionBar().setTitle(title);
            DBHome dbHome = new DBHome(this, name_database_elite, null, 1);
            final SQLiteDatabase database = dbHome.getWritableDatabase();
            int idobtenido = Integer.parseInt(idlink);
            String sqltiem = "select ELEV_TIMESTAMP from contenido where id=" + idobtenido + " and id_tipo=" + id_tipo + "";
            filatiem = database.rawQuery(sqltiem, null);

            if (filatiem.moveToFirst()) {
                eleve_tiem = filatiem.getString(0);
            }
            if (eleve_tiem == null) {
                eleve_tiem = "0000-00-00 00:00:00";
            }
            if (id_tipo == 1) {
                linkreceta = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/receta/" + idobtenido + "/" + eleve_tiem + "";
                textSubtitulo.setText("Ingredientes:");
                new JsonTaskrecetas().execute(linkreceta);
            } else if (id_tipo == 2) {
                Log.v("tiempo en ejercicio", eleve_tiem);
                Log.v("paso por aqui", "ejercicios");
                linkejercicios = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/ejercicio/" + idobtenido + "/" + eleve_tiem + "";
                new JsonTaskejercicios().execute(linkejercicios);
                textSubtitulo.setTextSize(18);
                textSubtitulo.setTypeface(face);
                videos.setText("E J E R C I C I O S");
                videos.setTextColor(Color.parseColor("#6dd8d8"));
                Drawable img = getApplicationContext().getResources().getDrawable(R.mipmap.imgejercicio);
                img.setBounds(0, 0, 60, 60);
                videos.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
            }
            String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = '" + idlink + "' and contenido.id_tipo=" + id_tipo + "";
            cursor = database.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                int favorit = Integer.parseInt(cursor.getString(0));
                if (favorit == 0) {
                    favorito.setBackgroundResource(favoritos);
                } else if (favorit == 1) {
                    favorito.setBackgroundResource(R.mipmap.favoritos_check_red);
                }
            }

            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHome1 = new DBHome(details_video.this, name_database_elite, null, 1);
                    ds = dbHome1.getWritableDatabase();
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                    SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String tiempo = String.valueOf(tiem.format(sq));
                    //String sql = "select contenido.favoritos from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.titulo = '"+titulo.getText().toString()+"'";
                    String sqlite = "select favoritos from contenido where id=" + idlink + " and id_tipo=" + id_tipo + "";
                    ContentValues values = new ContentValues();
                    cursorer = ds.rawQuery(sqlite, null);
                    if (cursorer.moveToFirst()) {
                        int favor = Integer.parseInt(cursorer.getString(0));
                        if (favor == 0) {
                            values.put("favoritos", "1");
                            values.put("fecha_favoritos", tiempo);
                            ds.update("contenido", values, "id=" + idlink + " and id_tipo=" + id_tipo + "", null);
                            favorito.setBackgroundResource(R.mipmap.favoritos_check_red);
                            //database.close();
                        } else if (favor == 1) {
                            values.put("favoritos", "0");
                            values.put("fecha_favoritos", tiempo);
                            ds.update("contenido", values, "id=" + idlink + " and id_tipo=" + id_tipo + "", null);
                            favorito.setBackgroundResource(favoritos);
                            //database.close();
                        }
                    }
                }
            });
            Compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (id_tipo == 1) {
                        nombre_tipo = "receta";
                    } else if (id_tipo == 2) {
                        nombre_tipo = "ejercicio";
                    }
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "http://www.papeleselite.mx/" + nombre_tipo + "/" + link/*http://www.papeleselite.mx/receta/pay-de-queso-con-fresas" /*"http://www.papeleselite.mx/receta/pay-de-queso-con-fresas/"*/);
                    intent.putExtra(Intent.EXTRA_TITLE, textTiutulo.getText().toString());
                    intent.putExtra(Intent.EXTRA_SUBJECT, textTiutulo.getText().toString());
                    // intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("http://www.papeleselite.mx/receta/pay-de-queso-con-fresas"));
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "compartir en"));
                }
            });
     /*   String html = "";   // frameborder=\"0\"
        html += "<html><body width=\"100%\" height=\"100%\" >";
        html += "<iframe width=\"100%\" height=\"100%\" scrolling=\"no\" transparency=\"transparency\"  src=\"http://www.youtube.com/embed/"+vid+"?rel=0&fs=1&autoplay=1&start="+tiempo+"\" frameborder=\"0\" allowfullscreen></iframe>";
        html += "</body></html>";
        String combinada ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n <style type=\"text/css\">\n" +
                "  html, body {\n" +
                "     height:100%;\n" +
                "     width:100%;\n" +
                "     margin: 0;\n" +
                "     padding: 0;\n" +
                "     background:[BG_COLOR];\n" +
                "     overflow:hidden;\n" +
                "     position:relative;\n" +
                "  }\n" +
                "</style>" +

                "<script type=\"text/javascript\">\n" +
                "\tvar tag = document.createElement('script');\n" +
                "\n" +
                "tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                "var firstScriptTag = document.getElementsByTagName('script')[0]; \n " +
                "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                "firstScriptTag.play();\n" +
                "var player;\n" +
                "function onYouTubeIframeAPIReady() {\n" +
                "    player = new YT.Player('player', {\n" +
                "        height: '100%',\n" +
                "        width: '100%',\n" +
                "        videoId: '"+vid+"',\n" +
                "        events: {\n" +
                "        'onReady': onReady\n" +
                "        },\n" +
                "        playerVars: {\n" +
                "        'color': 'white',\n" +
                "        'showinfo': 0,\n" +
                "        'iv_load_policy': 3,\n" +
                "        'rel': 0,\n" +
                "        'autoplay': 1,\n" +
                "        'start': "+tiempo+",\n" +
                "        'controls': 1,\n" +
                "        'fs' : 1\n" +
                "         }\n" +
                "    });\n" +
                "}\n" +
                "function onReady(event) {\n " +

                "        event.target.playVideo();\n" +

                "    player.addEventListener('onStateChange', function(e) {\n" +
                "        console.log('State is:', e.data);\n" +
                "        if(e.data == 2)\n" +
                "        {\n" +
                "        var d =\tparseInt(player.getCurrentTime());\n" +
                "           console.log('el tiempo de esta mamada es', d);\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    });" +
                "" +
                "" +
                "\n" +
                "}\n" +
                "</script>\n" +
                "<body>\n" +
                "<div id=\"player\"></div>\n" +
                "</body>\n" +
                "</html>";
        web.loadData(combinada, "text/html", "UTF-16");
///////web.loadUrl("http://www.youtube.com/embed/SoaCxtxDn_M?autoplay=1");
        /*
        *
        * "var iOS = /(iPad|iPhone|iPod|Android)/g.test(navigator.userAgent);\n" +
                "    if (!iOS) {\n" +
                "        event.target.playVideo();\n" +
                "    }" +
        * */
        }
    }

    public class JsonTaskrecetas extends AsyncTask<String, String, ArrayList<Model>> {
        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");
               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/
                DBHome db = new DBHome(details_video.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
                ArrayList<Model> list = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    video = "";
                   imagen = "";
                   nota = 0;
                    JSONObject ob = jsarreglo.getJSONObject(i);

                    int nota_id = ob.getInt("NOTA_ID");
                    String categoria = ob.getString("CATEGORIA");
                    String titulo = ob.getString("TITULO");
                    String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String preparacion = ob.getString("PREPARACION");
                    String duracion = ob.getString("DURACION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");
                    JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");
                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {
                        canidad_anidada = "";
                        unidad_anidada= "";
                        JSONObject object = jsonArrayingredientes.getJSONObject(l);
                        int id_ingrediente = object.getInt("INGREDIENTE_ID");
                        String cantidad = object.getString("CANTIDAD");
                        String unidad = object.getString("UNIDAD");
                        String ingrediente = object.getString("INGREDIENTE");

                        if(cantidad.equals("null")) {
                            canidad_anidada= "";
                        } else{
                            canidad_anidada = cantidad;
                        }
                        if (unidad.equals("null")) {
                            unidad_anidada ="";
                        } else {
                            unidad_anidada = unidad;
                        }
                        String query = "select count(*) from ingredientes where ingrediente_id="+id_ingrediente +" and id ="+nota_id+"";
                        Log.v("id nota", ""+nota_id);
                        fila = database.rawQuery(query, null);
                        if(fila.moveToFirst()) {
                            int contador = fila.getInt(0);

                            if(contador == 1) {
                                contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.update("ingredientes", contentingredientes, "ingrediente_id="+id_ingrediente +" and id ="+nota_id+"",null);
                            } else {
                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada +" " +unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.insert("ingredientes", null, contentingredientes);
                            }
                        }
                    }
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");
                    for (int j = 0; j < jsonArrayvideo.length(); j++) {
                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        video = object.getString("ELEV_YOUTUBE_CLAVE");
                        break;
                    }
                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {
                        JSONObject object = jsonArrayimagen.getJSONObject(x);
                        imagen = object.getString("NOMBRE");
                        break;
                    }
                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //if (tipo_Nota.equals("RECETA"))
                    // {
                    nota = 1;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }
                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";
                    fila = database.rawQuery(query, null);
                    if(fila.moveToFirst()) {
                        int contador = fila.getInt(0);
                        if(contador == 1) {
                        } else {*/
                            /*contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 1);
                            // }
                            //else  if (tipo_Nota.equals("EJERCICIO"))
                            //{
                            //  contentValues.put("id_tipo", 2);
                            //}
                            if (!video.equals("")) {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            } else if (!imagen.equals("")) {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/RECETA/"+imagen);
                            }*/
                    if (!video.equals("")) {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    } else if (!imagen.equals("")) {
                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/"+imagen);
                    }
                    contentValues.put("titulo", titulo);
                    contentValues.put("descripcion", descripcion_corta);
                    contentValues.put("categoria", categoria);
                    contentValues.put("influencer", influencer);
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/RECETA/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("preparacion", preparacion);
                    contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
                    /*
                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", "http://www.papeleselite.mx/RECETA/"+permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);*/
                           // database.insert("contenido", null, contentValues);
                     //   }
                   // }
                }
                return list;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(details_video.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("ytitulo");
            final int idlink = Integer.parseInt(bundle.getString("id"));
            final String link = bundle.getString("link");
            int tiempos = (int)bundle.getDouble("yttiempo");
            String query = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+idlink+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 2";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulo = fila.getString(0);
                String contenido = fila.getString(1);
                String preparaciones = fila.getString(2);
                String duraciones = fila.getString(3);
                textTiutulo.setText(titulo);
                tex.setText(preparaciones+"\n");
                tiempo.setVisibility(View.VISIBLE);
                preparacion.setVisibility(View.VISIBLE);
                duracion.setVisibility(View.VISIBLE);
                tex.setVisibility(View.VISIBLE);
                tiempo.setText(duraciones);

                String combinada ="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "</head>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n <style type=\"text/css\">\n" +
                        "  html, body {\n" +
                        "     height:100%;\n" +
                        "     width:100%;\n" +
                        "     margin: 0;\n" +
                        "     padding: 0;\n" +
                        "     background:[BG_COLOR];\n" +
                        "     overflow:hidden;\n" +
                        "     position:relative;\n" +
                        "  }\n" +
                        "</style>" +

                        "<script type=\"text/javascript\">\n" +
                        "\tvar tag = document.createElement('script');\n" +
                        "\n" +
                        "tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                        "var firstScriptTag = document.getElementsByTagName('script')[0]; \n " +
                        "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                        "firstScriptTag.play();\n" +
                        "var player;\n" +
                        "function onYouTubeIframeAPIReady() {\n" +
                        "    player = new YT.Player('player', {\n" +
                        "        height: '100%',\n" +
                        "        width: '100%',\n" +
                        "        videoId: '"+contenido+"',\n" +
                        "        events: {\n" +
                        "        'onReady': onReady\n" +
                        "        },\n" +
                        "        playerVars: {\n" +
                        "        'color': 'white',\n" +
                        "        'showinfo': 0,\n" +
                        "        'iv_load_policy': 3,\n" +
                        "        'rel': 0,\n" +
                        "        'autoplay': 1,\n" +
                        "        'start': "+tiempos+",\n" +
                        "        'controls': 1,\n" +
                        "        'fs' : 1\n" +
                        "         }\n" +
                        "    });\n" +
                        "}\n" +
                        "function onReady(event) {\n " +

                        "        event.target.playVideo();\n" +

                        "    player.addEventListener('onStateChange', function(e) {\n" +
                        "        console.log('State is:', e.data);\n" +
                        "        if(e.data == 2)\n" +
                        "        {\n" +
                        "        var d =\tparseInt(player.getCurrentTime());\n" +
                        "           console.log('el tiempo de esta mamada es', d);\n" +
                        "\n" +
                        "        }\n" +
                        "\n" +
                        "    });" +
                        "" +
                        "" +
                        "\n" +
                        "}\n" +
                        "</script>\n" +
                        "<body>\n" +
                        "<div id=\"player\"></div>\n" +
                        "</body>\n" +
                        "</html>";
                web.loadData(combinada, "text/html", "UTF-16");
            }
            String sqlingredientes ="select cantidad, ingrediente from ingredientes, contenido where contenido.id = ingredientes.id and contenido.id="+idlink+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 2 order by ingredientes.ingrediente_id ";
            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);
            listado = new ArrayList<>();
            while (cursor.moveToNext()) {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);
                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(details_video.this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, details_video.this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviwervideodetalle);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);

            //  MainActivity_prueba_json.MovieAdapter movieAdapter = new MainActivity_prueba_json.MovieAdapter(getApplicationContext(), R.layout.item_json, result);
            //   listView.setAdapter(movieAdapter);
            /*adapter_jason_contry adapter_json = new adapter_jason_contry(result, Main_jason_rest.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter_json);*/

            //textprueba.setText(result);
           /* DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            list = new ArrayList<>();
            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1 order by fecha_publicacion desc ";
            fila = sqLiteDatabase.rawQuery(query, null);
            list= new ArrayList<>();

            while (fila.moveToNext()) {
                int id_tipo_contenido = Integer.parseInt(fila.getString(0));
                String descrpition = fila.getString(1);
                String titulo = fila.getString(2);
                String contenido = fila.getString(3);
                int tipo = Integer.parseInt(fila.getString(4));
                int favor = Integer.parseInt(fila.getString(5));
                String id = fila.getString(6);
                String link = fila.getString(7);
                String preparacion = fila.getString(8);
                String duracion = fila.getString(9);
                String fecha =  fila.getString(10);

                if (tipo == 1) {
                    if(favor== 0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    } else if(favor ==1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                } else if(tipo == 2) {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    } else if (favor == 1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }
            }
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter); */
        }
    }

    public class JsonTaskejercicios extends AsyncTask<String, String, ArrayList<Model>> {
        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");
               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/
                DBHome db = new DBHome(details_video.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
                ArrayList<Model> list = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    video = "";
                    imagen = "";
                    nota = 0;
                    JSONObject ob = jsarreglo.getJSONObject(i);
                    int nota_id = ob.getInt("NOTA_ID");
                    String categoria = ob.getString("CATEGORIA");
                    String titulo = ob.getString("TITULO");
                    String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String descripcion_ejercicio =ob.getString("DESCRIPCION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");

                   /* JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");

                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {
                        canidad_anidada = "";
                        unidad_anidada= "";
                        JSONObject object = jsonArrayingredientes.getJSONObject(l);
                        int id_ingrediente = object.getInt("INGREDIENTE_ID");
                        String cantidad = object.getString("CANTIDAD");
                        String unidad = object.getString("UNIDAD");
                        String ingrediente = object.getString("INGREDIENTE");

                        if(cantidad.equals("null")) {
                            canidad_anidada= "";
                        } else {
                            canidad_anidada = cantidad;
                        }
                        if (unidad.equals("null")) {
                            unidad_anidada ="";
                        } else {
                            unidad_anidada = unidad;
                        }
                        String query = "select count(*) from ingredientes where ingrediente_id="+id_ingrediente +" and id ="+nota_id+"";
                        fila = database.rawQuery(query, null);

                        if(fila.moveToFirst()) {
                            int contador = fila.getInt(0);
                            if(contador == 1) {
                            } else {
                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.insert("ingredientes", null, contentingredientes);
                            }
                            // break;
                        }
                    }*/
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length(); j++) {
                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        video = object.getString("ELEV_YOUTUBE_CLAVE");
                        break;
                    }
                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {
                        JSONObject object = jsonArrayimagen.getJSONObject(x);
                        imagen = object.getString("NOMBRE");
                        break;
                    }
                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //if (tipo_Nota.equals("RECETA"))
                    // {
                    nota = 2;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }
                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";
                    //Log.v("tipo nota", ""+tipo_Nota);
                    fila = database.rawQuery(query, null);
                    if(fila.moveToFirst()) {
                        int contador = fila.getInt(0);
                        if(contador == 1) {
                        } else {
                        */
                            /*contentValues.put("id", nota_id);
                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 1);
                            // }
                            //else  if (tipo_Nota.equals("EJERCICIO"))
                            //{
                            //  contentValues.put("id_tipo", 2);
                            //}

                            if (!video.equals("")) {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            } else if (!imagen.equals("")) {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/RECETA/"+imagen);
                            } */
                    if (!video.equals("")) {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    } else if (!imagen.equals("")) {
                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/ejercicios/"+imagen);
                    }
                    contentValues.put("titulo", titulo);
                    contentValues.put("descripcion", descripcion_corta);
                    contentValues.put("categoria", categoria);
                    contentValues.put("influencer", influencer);
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/ejercicio/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("descripcion_ejercicio", descripcion_ejercicio);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
                    /*
                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", "http://www.papeleselite.mx/RECETA/"+permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);*/
                    // database.insert("contenido", null, contentValues);
                    //   }
                    // }
                }
                return list;

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(details_video.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("ytitulo");
            final int idlink = Integer.parseInt(bundle.getString("id"));
            final String link = bundle.getString("link");
            int tiempos = (int)bundle.getDouble("yttiempo");
            String query = "select contenido.titulo, contenido.contenido, contenido.descripcion_ejercicio from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id = "+idlink+" and contenido.id_tipo = 2 and contenido.id_tipo_contenido = 2";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulo = fila.getString(0);
                String contenido = fila.getString(1);
                String descripcion_ejercicio = fila.getString(2);
                textTiutulo.setText(titulo);
                textSubtitulo.setText(descripcion_ejercicio+"\n");
                tiempo.setText("");

                String combinada ="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "</head>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n <style type=\"text/css\">\n" +
                        "  html, body {\n" +
                        "     height:100%;\n" +
                        "     width:100%;\n" +
                        "     margin: 0;\n" +
                        "     padding: 0;\n" +
                        "     background:[BG_COLOR];\n" +
                        "     overflow:hidden;\n" +
                        "     position:relative;\n" +
                        "  }\n" +
                        "</style>" +

                        "<script type=\"text/javascript\">\n" +
                        "\tvar tag = document.createElement('script');\n" +
                        "\n" +
                        "tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                        "var firstScriptTag = document.getElementsByTagName('script')[0]; \n " +
                        "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                        "firstScriptTag.play();\n" +
                        "var player;\n" +
                        "function onYouTubeIframeAPIReady() {\n" +
                        "    player = new YT.Player('player', {\n" +
                        "        height: '100%',\n" +
                        "        width: '100%',\n" +
                        "        videoId: '"+contenido+"',\n" +
                        "        events: {\n" +
                        "        'onReady': onReady\n" +
                        "        },\n" +
                        "        playerVars: {\n" +
                        "        'color': 'white',\n" +
                        "        'showinfo': 0,\n" +
                        "        'iv_load_policy': 3,\n" +
                        "        'rel': 0,\n" +
                        "        'autoplay': 1,\n" +
                        "        'start': "+tiempos+",\n" +
                        "        'controls': 1,\n" +
                        "        'fs' : 1\n" +
                        "         }\n" +
                        "    });\n" +
                        "}\n" +
                        "function onReady(event) {\n " +

                        "        event.target.playVideo();\n" +

                        "    player.addEventListener('onStateChange', function(e) {\n" +
                        "        console.log('State is:', e.data);\n" +
                        "        if(e.data == 2)\n" +
                        "        {\n" +
                        "        var d =\tparseInt(player.getCurrentTime());\n" +
                        "           console.log('el tiempo de esta mamada es', d);\n" +
                        "\n" +
                        "        }\n" +
                        "\n" +
                        "    });" +
                        "" +
                        "" +
                        "\n" +
                        "}\n" +
                        "</script>\n" +
                        "<body>\n" +
                        "<div id=\"player\"></div>\n" +
                        "</body>\n" +
                        "</html>";

                web.loadData(combinada, "text/html", "UTF-16");
            }

           /* String sqlingredientes ="select cantidad, ingrediente from ingredientes where ingredientes.id="+idlink+"";
            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);
            listado = new ArrayList<>();
            while (cursor.moveToNext()) {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);
                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(details_video.this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, details_video.this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviwervideodetalle);
            recyclerView.setLayoutManager(linearLayoutManager);

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //  recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter); */
            //  MainActivity_prueba_json.MovieAdapter movieAdapter = new MainActivity_prueba_json.MovieAdapter(getApplicationContext(), R.layout.item_json, result);
            //   listView.setAdapter(movieAdapter);
            /*adapter_jason_contry adapter_json = new adapter_jason_contry(result, Main_jason_rest.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter_json);*/
            //textprueba.setText(result);
           /* DBHome dbHome = new DBHome(MainActivity_recetas.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();
            list = new ArrayList<>();
            String query = "select contenido.id_tipo_contenido, contenido.descripcion, contenido.titulo, contenido.contenido, contenido.id_tipo, contenido.favoritos, contenido.id, contenido.permarlink, contenido.preparacion, contenido.duracion, contenido.fecha from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id_tipo = 1 order by fecha_publicacion desc ";
            fila = sqLiteDatabase.rawQuery(query, null);
            list= new ArrayList<>();
            while (fila.moveToNext()) {
                int id_tipo_contenido = Integer.parseInt(fila.getString(0));
                String descrpition = fila.getString(1);
                String titulo = fila.getString(2);
                String contenido = fila.getString(3);
                int tipo = Integer.parseInt(fila.getString(4));
                int favor = Integer.parseInt(fila.getString(5));
                String id = fila.getString(6);
                String link = fila.getString(7);
                String preparacion = fila.getString(8);
                String duracion = fila.getString(9);
                String fecha =  fila.getString(10);
                if (tipo == 1) {
                    if(favor== 0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    } else if(favor ==1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgreceta, "#ff7cbd", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                } else if(tipo == 2) {
                    if(favor==0) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos, id, link, preparacion, duracion);
                        list.add(model);
                    } else if (favor == 1) {
                        Model model = new Model(id_tipo_contenido, descrpition, titulo, contenido, R.mipmap.imgejercicio, "#6dd8d8", R.mipmap.favoritos_check, id, link, preparacion, duracion);
                        list.add(model);
                    }
                }
            }
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, MainActivity_recetas.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_recetas.this, OrientationHelper.VERTICAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.layoutrecetas);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter); */
        }
    }

    public class JsonTaskrecetaspermarlink extends AsyncTask<String, String, ArrayList<Model>> {
        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);
                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");
               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/

                DBHome db = new DBHome(details_video.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
                ArrayList<Model> list = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    video = "";
                    imagen = "";
                    nota = 0;
                    JSONObject ob = jsarreglo.getJSONObject(i);
                    int nota_id = ob.getInt("NOTA_ID");
                    String categoria = ob.getString("CATEGORIA");
                    String titulo = ob.getString("TITULO");
                    String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String preparacion = ob.getString("PREPARACION");
                    String duracion = ob.getString("DURACION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");
                    JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");
                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {
                        canidad_anidada = "";
                        unidad_anidada= "";
                        JSONObject object = jsonArrayingredientes.getJSONObject(l);
                        int id_ingrediente = object.getInt("INGREDIENTE_ID");
                        String cantidad = object.getString("CANTIDAD");
                        String unidad = object.getString("UNIDAD");
                        String ingrediente = object.getString("INGREDIENTE");

                        if(cantidad.equals("null")) {
                            canidad_anidada= "";
                        } else {
                            canidad_anidada = cantidad;
                        }
                        if (unidad.equals("null")) {
                            unidad_anidada ="";
                        } else {
                            unidad_anidada = unidad;
                        }
                        String query = "select count(*) from ingredientes where ingrediente_id="+id_ingrediente +" and id ="+nota_id+"";
                        fila = database.rawQuery(query, null);

                        if(fila.moveToFirst()) {
                            int contador = fila.getInt(0);

                            if(contador == 1) {
                                contentingredientes.put("cantidad", canidad_anidada+" "+unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.update("ingredientes", contentingredientes, "ingrediente_id="+id_ingrediente +" and id ="+nota_id+"",null);
                            } else {
                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.insert("ingredientes", null, contentingredientes);
                            }
                        }
                    }
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length(); j++) {
                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        video = object.getString("ELEV_YOUTUBE_CLAVE");
                        break;
                    }
                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");
                    for (int x = 0; x < jsonArrayimagen.length(); x++) {
                        JSONObject object = jsonArrayimagen.getJSONObject(x);
                        imagen = object.getString("NOMBRE");
                        break;
                    }
                    Log.v("imagen", ""+imagen);
                    Log.v("video", ""+video);
                       /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //if (tipo_Nota.equals("RECETA"))
                    // {
                    nota = 1;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }
                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";
                    Log.v("id nota", ""+nota_id);
                    //Log.v("tipo nota", ""+tipo_Nota);
                    fila = database.rawQuery(query, null);
                    if(fila.moveToFirst()) {
                        int contador = fila.getInt(0);

                        if(contador == 1) {
                        } else { */
                            /*contentValues.put("id", nota_id);

                            //  if (tipo_Nota.equals("RECETA"))
                            // {
                            contentValues.put("id_tipo", 1);
                            // }
                            //else  if (tipo_Nota.equals("EJERCICIO"))
                            //{
                            //  contentValues.put("id_tipo", 2);
                            //}
                            if (!video.equals("")) {
                                contentValues.put("id_tipo_contenido", 2);
                                contentValues.put("contenido", video);
                            } else if (!imagen.equals("")) {
                                contentValues.put("id_tipo_contenido", 1);
                                contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/RECETA/"+imagen);
                            } */
                    if (!video.equals("")) {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    } else if (!imagen.equals("")) {
                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/recetas/"+imagen);

                    }
                    contentValues.put("titulo", titulo);
                    contentValues.put("descripcion", descripcion_corta);
                    contentValues.put("categoria", categoria);
                    contentValues.put("influencer", influencer);
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/RECETA/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("preparacion", preparacion);
                    contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
                    /*
                            contentValues.put("titulo", titulo);
                            contentValues.put("descripcion", descripcion_corta);
                            contentValues.put("categoria", categoria);
                            contentValues.put("influencer", influencer);
                            contentValues.put("permarlink", "http://www.papeleselite.mx/RECETA/"+permalink);
                            contentValues.put("fecha_publicacion", fecha_publicacion);
                            contentValues.put("fecha", tiempo);
                            contentValues.put("favoritos", 0);
                            contentValues.put("fecha_favoritos", tiempo);*/
                    // database.insert("contenido", null, contentValues);
                    //   }
                    // }
                }
                return list;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(details_video.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

           /* Bundle bundle = getIntent().getExtras();
            final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link"); */

            Intent appLinkIntent = getIntent();
            final String appLinkAction = appLinkIntent.getAction();
            final Uri appLinkData = appLinkIntent.getData();
            final String appp = appLinkData.getLastPathSegment();
            String query = "select contenido.titulo, contenido.contenido, contenido.preparacion, contenido.duracion, contenido.id from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '"+appp+"' and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 2";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String preparaciones = fila.getString(2);
                String duraciones = fila.getString(3);
                int id_contenido = fila.getInt(4);
                Log.v("preparacion comida", preparaciones);
                getSupportActionBar().setTitle(titulos);
                textTiutulo.setText(titulos);
                tex.setText(preparaciones+"\n");
                tiempo.setVisibility(View.VISIBLE);
                preparacion.setVisibility(View.VISIBLE);
                duracion.setVisibility(View.VISIBLE);
                tex.setVisibility(View.VISIBLE);
                tiempo.setText(duraciones);

                String combinada ="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "</head>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n <style type=\"text/css\">\n" +
                        "  html, body {\n" +
                        "     height:100%;\n" +
                        "     width:100%;\n" +
                        "     margin: 0;\n" +
                        "     padding: 0;\n" +
                        "     background:[BG_COLOR];\n" +
                        "     overflow:hidden;\n" +
                        "     position:relative;\n" +
                        "  }\n" +
                        "</style>" +

                        "<script type=\"text/javascript\">\n" +
                        "\tvar tag = document.createElement('script');\n" +
                        "\n" +
                        "tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                        "var firstScriptTag = document.getElementsByTagName('script')[0]; \n " +
                        "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                        "firstScriptTag.play();\n" +
                        "var player;\n" +
                        "function onYouTubeIframeAPIReady() {\n" +
                        "    player = new YT.Player('player', {\n" +
                        "        height: '100%',\n" +
                        "        width: '100%',\n" +
                        "        videoId: '"+contenido+"',\n" +
                        "        events: {\n" +
                        "        'onReady': onReady\n" +
                        "        },\n" +
                        "        playerVars: {\n" +
                        "        'color': 'white',\n" +
                        "        'showinfo': 0,\n" +
                        "        'iv_load_policy': 3,\n" +
                        "        'rel': 0,\n" +
                        "        'autoplay': 0,\n" +
                       // "        'start': "+tiempos+",\n" +
                        "        'controls': 1,\n" +
                        "        'fs' : 1\n" +
                        "         }\n" +
                        "    });\n" +
                        "}\n" +
                        "function onReady(event) {\n " +

                        "        event.target.playVideo();\n" +

                        "    player.addEventListener('onStateChange', function(e) {\n" +
                        "        console.log('State is:', e.data);\n" +
                        "        if(e.data == 2)\n" +
                        "        {\n" +
                        "        var d =\tparseInt(player.getCurrentTime());\n" +
                        "           console.log('el tiempo de esta mamada es', d);\n" +
                        "\n" +
                        "        }\n" +
                        "\n" +
                        "    });" +
                        "" +
                        "" +
                        "\n" +
                        "}\n" +
                        "</script>\n" +
                        "<body>\n" +
                        "<div id=\"player\"></div>\n" +
                        "</body>\n" +
                        "</html>";

                web.loadData(combinada, "text/html", "UTF-16");
               // titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                //Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);
                //text.setText(preparaciones+"\n"/*, true*/);
                /*durateiemp.setText(duraciones+"\n\n");

                prepa.setVisibility(View.VISIBLE);
                dura.setVisibility(View.VISIBLE);
                subtitulo.setVisibility(View.VISIBLE);
                durateiemp.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE); */
                // textTiutulo.setText(titulo);
                //tex.setText(preparaciones+"\n", true);
                /*tiempo.setVisibility(View.VISIBLE);
                preparacion.setVisibility(View.VISIBLE);
                duracion.setVisibility(View.VISIBLE);
                textSubtitulo.setVisibility(View.VISIBLE);
                tiempo.setText(duraciones); */
                id_ingrediente = id_contenido;
            }
            String sqlingredientes ="select cantidad, ingrediente from ingredientes, contenido where contenido.id = ingredientes.id and ingredientes.id="+id_ingrediente+" and contenido.id_tipo = 1 and contenido.id_tipo_contenido = 2  order by ingrediente_id";
            cursor = sqLiteDatabase.rawQuery(sqlingredientes, null);
            listado = new ArrayList<>();
            while (cursor.moveToNext()) {
                String cantidad= cursor.getString(0);
                String prepara = cursor.getString(1);
                Model_Ingredientes model = new Model_Ingredientes(1, cantidad, prepara);
                listado.add(model);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(details_video.this, OrientationHelper.VERTICAL, false);
            Adapter_ingredientes adapter = new Adapter_ingredientes(listado, details_video.this);
            RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recicleviwervideodetalle);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
        }
    }

    public class JsonTaskejerciciospermarlink extends AsyncTask<String, String, ArrayList<Model>> {
        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);
                //Log.v("json", parentjson.getString("RestResponse"));
                //Log.v("json", parentjson.getJSONObject("RestResponse").getString("result"));
                JSONArray jsarreglo = parentjson.getJSONArray("DATA");
                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);
                // JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");
               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/

                DBHome db = new DBHome(details_video.this, name_database_elite, null, 1);
                SQLiteDatabase database = db.getWritableDatabase();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String tiempo = String.valueOf(tiem.format(sq));
                ContentValues contentValues = new ContentValues();
                ContentValues contentingredientes = new ContentValues();
                ArrayList<Model> list = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    video = "";
                    imagen = "";
                    nota = 0;
                    JSONObject ob = jsarreglo.getJSONObject(i);

                    int nota_id = ob.getInt("NOTA_ID");
                    String categoria = ob.getString("CATEGORIA");
                    String titulo = ob.getString("TITULO");
                    String permalink = ob.getString("PERMALINK");
                    String influencer = ob.getString("INFLUENCER");
                    String descripcion_corta = ob.getString("DESCRIPCION_CORTA");
                    String descripcion_ejercicio =ob.getString("DESCRIPCION");
                    String fecha_publicacion = ob.getString("FECHA_PUBLICACION");
                    String ELEV_TIMESTAMP = ob.getString("ELEV_TIMESTAMP");

                   /* JSONArray jsonArrayingredientes = ob.getJSONArray("INGREDIENTES");

                    for (int l = 0; l < jsonArrayingredientes.length(); l++) {
                        canidad_anidada = "";
                        unidad_anidada= "";
                        JSONObject object = jsonArrayingredientes.getJSONObject(l);
                        int id_ingrediente = object.getInt("INGREDIENTE_ID");
                        String cantidad = object.getString("CANTIDAD");
                        String unidad = object.getString("UNIDAD");
                        String ingrediente = object.getString("INGREDIENTE");

                        if(cantidad.equals("null")) {
                            canidad_anidada= "";
                        } else {
                            canidad_anidada = cantidad;
                        }
                        if (unidad.equals("null")) {
                            unidad_anidada ="";
                        } else {
                            unidad_anidada = unidad;
                        }

                        String query = "select count(*) from ingredientes where ingrediente_id="+id_ingrediente +" and id ="+nota_id+"";
                        Log.v("id nota", ""+nota_id);
                        //Log.v("tipo nota", ""+tipo_Nota);
                        fila = database.rawQuery(query, null);

                        if(fila.moveToFirst()) {
                            int contador = fila.getInt(0);

                            if(contador == 1) {
                            } else {
                                contentingredientes.put("ingrediente_id", id_ingrediente);
                                contentingredientes.put("id", nota_id);
                                contentingredientes.put("cantidad", canidad_anidada + " " + unidad_anidada);
                                contentingredientes.put("ingrediente", ingrediente);
                                database.insert("ingredientes", null, contentingredientes);
                            }
                            // break;
                        }
                    }*/
                    JSONArray jsonArrayvideo = ob.getJSONArray("VIDEOS");

                    for (int j = 0; j < jsonArrayvideo.length(); j++) {
                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        video = object.getString("ELEV_YOUTUBE_CLAVE");
                        break;
                    }
                    JSONArray jsonArrayimagen = ob.getJSONArray("IMAGENES");

                    for (int x = 0; x < jsonArrayimagen.length(); x++) {
                        JSONObject object = jsonArrayimagen.getJSONObject(x);
                        imagen = object.getString("NOMBRE");
                        break;
                    }
                    Log.v("imagen", ""+imagen);
                    Log.v("video", ""+video);

                    /* String name = ob.getString("name");
                        String alpha2_code = ob.getString("alpha2_code");
                        String alpha3_code = ob.getString("alpha3_code");*/
                    //if (tipo_Nota.equals("RECETA"))
                    // {
                    nota = 2;
                    //}
                    // else  if (tipo_Nota.equals("EJERCICIO"))
                    //{
                    //    nota = 2;
                    // }



                /*    String query = "select count(*) from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.id ="+nota_id+" and contenido.id_tipo="+nota+"";

                    Log.v("id nota", ""+nota_id);
                    //Log.v("tipo nota", ""+tipo_Nota);
                    fila = database.rawQuery(query, null);

                    if(fila.moveToFirst())
                    {
                        int contador = fila.getInt(0);

                        Log.v("contador", ""+contador);

                        if(contador == 1)
                        {

                        }

                        else {

*/
                    if (!video.equals("")) {
                        contentValues.put("id_tipo_contenido", 2);
                        contentValues.put("contenido", video);
                    } else if (!imagen.equals("")) {
                        contentValues.put("id_tipo_contenido", 1);
                        contentValues.put("contenido", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/ejercicios/"+imagen);
                    }
                    contentValues.put("titulo", titulo);
                    contentValues.put("descripcion", descripcion_corta);
                    contentValues.put("categoria", categoria);
                    contentValues.put("influencer", influencer);
                    contentValues.put("permarlink", /*"http://www.papeleselite.mx/ejercicios/"+*/permalink);
                    contentValues.put("fecha_publicacion", fecha_publicacion);
                    contentValues.put("descripcion_ejercicio", descripcion_ejercicio);
                    //contentValues.put("duracion", duracion);
                    contentValues.put("ELEV_TIMESTAMP",  ELEV_TIMESTAMP);
                    database.update("contenido", contentValues, "id="+nota_id+" and id_tipo ="+nota+"", null);
                }
                return list;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);
            DBHome dbHome = new DBHome(details_video.this, name_database_elite, null, 1);
            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            //Bundle bundle = getIntent().getExtras();

           /* String title = bundle.getString("ytitulo");
            final int idlink = Integer.parseInt(bundle.getString("id"));
            final String link = bundle.getString("link");
            int tiempos = (int)bundle.getDouble("yttiempo"); */

          /*  final String titul = bundle.getString("titulo");
            final String imagen = bundle.getString("imagen");
            final int id = Integer.parseInt(bundle.getString("idlink"));
            final String link = bundle.getString("link"); */

            Intent appLinkIntent = getIntent();
            final String appLinkAction = appLinkIntent.getAction();
            final Uri appLinkData = appLinkIntent.getData();
            final String appp = appLinkData.getLastPathSegment();
            String query = "select contenido.titulo, contenido.contenido, contenido.descripcion_ejercicio from bandera, contenido, tipo_contenido where contenido.id_tipo = bandera.id_tipo and contenido.id_tipo_contenido = tipo_contenido.id_tipo_contenido and contenido.permarlink = '"+appp+"' and contenido.id_tipo = 2 and contenido.id_tipo_contenido = 2";
            fila = sqLiteDatabase.rawQuery(query, null);

            if (fila.moveToFirst()) {
                String titulos = fila.getString(0);
                String contenido = fila.getString(1);
                String descripcion_ejercicio = fila.getString(2);
                getSupportActionBar().setTitle(titulos);
                textTiutulo.setText(titulos);
                textSubtitulo.setText(descripcion_ejercicio+"\n"/*, true*/);
                tiempo.setText("");

                String combinada ="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "</head>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n <style type=\"text/css\">\n" +
                        "  html, body {\n" +
                        "     height:100%;\n" +
                        "     width:100%;\n" +
                        "     margin: 0;\n" +
                        "     padding: 0;\n" +
                        "     background:[BG_COLOR];\n" +
                        "     overflow:hidden;\n" +
                        "     position:relative;\n" +
                        "  }\n" +
                        "</style>" +

                        "<script type=\"text/javascript\">\n" +
                        "\tvar tag = document.createElement('script');\n" +
                        "\n" +
                        "tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                        "var firstScriptTag = document.getElementsByTagName('script')[0]; \n " +
                        "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                        "firstScriptTag.play();\n" +
                        "var player;\n" +
                        "function onYouTubeIframeAPIReady() {\n" +
                        "    player = new YT.Player('player', {\n" +
                        "        height: '100%',\n" +
                        "        width: '100%',\n" +
                        "        videoId: '"+contenido+"',\n" +
                        "        events: {\n" +
                        "        'onReady': onReady\n" +
                        "        },\n" +
                        "        playerVars: {\n" +
                        "        'color': 'white',\n" +
                        "        'showinfo': 0,\n" +
                        "        'iv_load_policy': 3,\n" +
                        "        'rel': 0,\n" +
                        "        'autoplay': 1,\n" +
                       // "        'start': "+tiempos+",\n" +
                        "        'controls': 1,\n" +
                        "        'fs' : 1\n" +
                        "         }\n" +
                        "    });\n" +
                        "}\n" +
                        "function onReady(event) {\n " +

                        "        event.target.playVideo();\n" +

                        "    player.addEventListener('onStateChange', function(e) {\n" +
                        "        console.log('State is:', e.data);\n" +
                        "        if(e.data == 2)\n" +
                        "        {\n" +
                        "        var d =\tparseInt(player.getCurrentTime());\n" +
                        "           console.log('el tiempo de esta mamada es', d);\n" +
                        "\n" +
                        "        }\n" +
                        "\n" +
                        "    });" +
                        "" +
                        "" +
                        "\n" +
                        "}\n" +
                        "</script>\n" +
                        "<body>\n" +
                        "<div id=\"player\"></div>\n" +
                        "</body>\n" +
                        "</html>";
                web.loadData(combinada, "text/html", "UTF-16");

                //titulo.setText(/*"Muffins de Plàtano con Crema de Cacahuate"*/titulos);
                //Picasso.with(MainActivity_contenidoreceta.this).load(contenido/*R.drawable.muffinsplatano*/).resize(1000, 0).into(imag);
                //subtitulo.setText(descripcion_ejercicio+"\n"/*, true*/);
                //durateiemp.setText(duraciones+"\n\n");
                // .setText("Preparación");
                //textTiutulo.setText(titulo);
                //text.setText(descripcion_ejercicio+"\n", true);
                //tiempo.setText("");
                //tex.setText("");
            }
        }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Bundle bundle = getIntent().getExtras();
            int ruta = bundle.getInt("context");
            if(ruta == 1) {
                Intent intent = new Intent(details_video.this, Main_Activity_inicio.class);
                startActivity(intent);
            } else if(ruta == 2) {
                Intent intent = new Intent(details_video.this, MainActivity_Ejercicios.class);
                startActivity(intent);
            } else  if(ruta == 3) {
                Intent intent = new Intent(details_video.this, MainActivity_recetas.class);
                startActivity(intent);
            } else if(ruta == 4) {
                Intent intent = new Intent(details_video.this, MainActivity_Favoritos.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(details_video.this, Main_Activity_inicio.class);
                startActivity(intent);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
         Bundle bundle = getIntent().getExtras();
         int ruta = bundle.getInt("context");
        if(ruta == 1) {
            Intent intent = new Intent(details_video.this, Main_Activity_inicio.class);
             startActivity(intent);
        } else if(ruta == 2){
            Intent intent = new Intent(details_video.this, MainActivity_Ejercicios.class);
            startActivity(intent);
        } else if(ruta == 3 ) {
            Intent intent = new  Intent(details_video.this, MainActivity_recetas.class);
            startActivity(intent);
        } else if(ruta == 4) {
            Intent intent = new Intent(details_video.this, MainActivity_Favoritos.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(details_video.this, Main_Activity_inicio.class);
            startActivity(intent);
        }
        return false;
    }

  /*  @Override
    public void onBackPressed()
    {
        // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
        if (!webChromeClient.onBackPressed())
        {
            if (web.canGoBack())
            {
                web.goBack();
            }
            else
            {
                // Standard back button implementation (for example this could close the app)
                super.onBackPressed();

            }
        }
    }*/
}
