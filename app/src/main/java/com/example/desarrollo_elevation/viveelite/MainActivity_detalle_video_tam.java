package com.example.desarrollo_elevation.viveelite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

//import static com.example.desarrollo_elevation.viveelite.R.id.textView;
import static com.example.desarrollo_elevation.viveelite.R.id.toolbar;
//import static com.example.desarrollo_elevation.viveelite.R.id.webr;

public class MainActivity_detalle_video_tam extends AppCompatActivity {
    private VideoEnabledWebView webView;
    private VideoEnabledWebChromeClient webChromeClient;
    TextView textView;//, textvideo;
    //ImageButton btnregreseo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_video_tam);
        //webView = (VideoEnabledWebView)findViewById(R.id.webViewer);


/*        final Toolbar toolbar = (Toolbar) findViewById(R.id.textViewtolbar);
        toolbar.setTitle(" videos de Ejercicios");
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/

        /*textView =(TextView) findViewById(R.id.webr);

        String d="que honda este es el ejercicio de hoy abdominales extremos" +
                "para calentar correr tres kilometros, tratar un kilometro" +
                "10 lagartijas de series de 3" +
                "30 abdominales de series de 3" +
                "15 sentadillas con series de 3" +
                "15 rotaciones de cuello series de 3 " +
                "movimientos de pierna, ejercicios de respiracion" +
                "movimientos de cadera y torzo" +
                "pero ultimo ejercicios de estiramiento para que se relajen y trabajen los musculos.";
        textView.setText(d);



        /*textView.getSettings().setJavaScriptEnabled(true);
        textView.getSettings().setAppCacheEnabled(false);
        textView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        String d="que honda este es el ejercicio de hoy abdominales extremos hasta el canzancio" +
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
                "                \"</html>\"";

        String datatos ="<html><body bgcolor=#ffefe text=#000>" +
                "<p style=\"text-align: justify;\"> "+d+" </p>"+
                " </body>" +
                "</html>";

        textView.loadData(datatos, "text/html", "UTF-16");*/

       //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbardetallevideower);
        //toolbar.setTitle("Video de ejercicio");
        //android:title="Recetas"
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
     //  setSupportActionBar(toolbar);
     //   getSupportActionBar().setTitle("video de ejercicio");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize the VideoEnabledWebChromeClient and set event handlers
      /*  View nonVideoLayout = findViewById(R.id.nonVideoLayouter); // Your own view, read class comments
        ViewGroup videoLayout = (ViewGroup)findViewById(R.id.videoLayouter); // Your own view, read class comments
        //noinspection all
        View loadingView = getLayoutInflater().inflate(R.layout.view_loading_video, null); // Your own view, read class comments
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                // Your code...
            }
        };
        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
        {
            @Override
            public void toggledFullscreen(boolean fullscreen)
            {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen)
                {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                      //  textView=(TextView)findViewById(R.id.textViewer);
                        //textView.setVisibility(View.INVISIBLE);

                        textView.setVisibility(View.INVISIBLE);
                        toolbar.setVisibility(View.INVISIBLE);
                    /*    textvideo=(TextView)findViewById(R.id.textViewer);
                        textvideo.setVisibility(View.INVISIBLE);

                        btnregreseo=(ImageButton)findViewById(R.id.imageregresar);
                        btnregreseo.setVisibility(View.INVISIBLE);
                     //   toolbar.setVisibility(View.INVISIBLE);*/
                /*    }
                }
                else
                {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                        textView.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.VISIBLE);
                       /* textView=(TextView)findViewById(R.id.textViewer);
                        textView.setVisibility(View.VISIBLE);

                       /* textvideo=(TextView)findViewById(R.id.textViewer);
                        textvideo.setVisibility(View.VISIBLE);

                        btnregreseo=(ImageButton)findViewById(R.id.imageregresar);
                        btnregreseo.setVisibility(View.VISIBLE);
                        //toolbar.setVisibility(View.VISIBLE);*/
                    }
                }

       /*     }
        });
        webView.setWebChromeClient(webChromeClient);
        // Call private class InsideWebViewClient
        webView.setWebViewClient(new MainActivity_detalle_video_tam.InsideWebViewClient());

        // Navigate anywhere you want, but consider that this classes have only been tested on YouTube's mobile site
        // webView.loadUrl("http://www.youtube.com/watch?v=jFYAp42rMEA");
/*    declaracion de la elemento de del webview*/
      /*  String html = "";
        html += "<html><body>";
        html += "<iframe width=\"100%\" height=\"100%\" src=\"http://www.youtube.com/embed/6J-cmRe-Kdg?rel=0&autohide=1&autoplay=1&theme=light&fs=0\\\"  allowfullscreen></iframe>";
        html += "</body></html>";

        webView.loadData(html, "text/html", null);

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
    public void onBackPressed()
    {
        // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
        if (!webChromeClient.onBackPressed())
        {
            if (webView.canGoBack())
            {
                webView.goBack();
            }
            else
            {
                // Standard back button implementation (for example this could close the app)
                super.onBackPressed();

            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return false;*/
   /* }
}*/