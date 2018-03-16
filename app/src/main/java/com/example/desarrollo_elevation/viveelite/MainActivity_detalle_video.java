package com.example.desarrollo_elevation.viveelite;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;

import static android.R.attr.y;
//import static com.example.desarrollo_elevation.viveelite.R.id.nonVideoLayout;
//import com.example.helperyoutube.YoutubePlayerView;

public class MainActivity_detalle_video extends AppCompatActivity {
//YoutubePlayerView video;

    //private VideoEnabledWebView web;
    //private VideoEnabledWebChromeClient webChromeClient;

    //  private VideoEnabledWebView webView;
    //private VideoEnabledWebChromeClient webChromeClient;
    //TextView te;
    Toolbar toolbar;
    TextView  text;
    ScrollView scrollView;
    private View mCustomView;
    private VideoEnabledWebView web;
    private VideoEnabledWebChromeClient webChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_video);
         toolbar = (Toolbar) findViewById(R.id.toolbardetallevideos);
        toolbar.setTitle("Detalle video");
        toolbar.setTitleTextColor(Color.WHITE);

        //android:title="Recetas"
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("video de ejercicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text = (TextView) findViewById(R.id.txtdetallevideoyt);
        scrollView =(ScrollView)findViewById(R.id.scrollViewyt);
        text.setText("Ejercicio de cardio" + "\n" +
                "conteido: correr cada 1 minuto despues de un movimiento"
                + "\n" + "salto de rana y de tijera"
                + "\n" + "30 abdominales series de 3"
                + "\n" + "10 sentadillas series de 3"
                + "\n" + "10 lagartijas series de 3"
                + "\n" + "y por ultimo ejercicios de respiracion para control de oxigeno."




        );


        web = (VideoEnabledWebView)findViewById(R.id.weberyoutube);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setPluginState(WebSettings.PluginState.ON);
        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setMediaPlaybackRequiresUserGesture(false);
        web.getSettings().setAllowFileAccess(true);




        final View nonVideoLayout = findViewById(R.id.nonVideoLayoutyoutube); // Your own view, read class comments
        final ViewGroup videoLayout = (ViewGroup)findViewById(R.id.videoLayoutyoutube); // Your own view, read class comments
        //noinspection all
        View loadingView = getLayoutInflater().inflate(R.layout.view_loading_video, null); // Your own view, read class comments
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, web) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                // Your code...
            }
        };
        webChromeClient.setOnToggledFullscreen(fullscreen -> {
            // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
            if (fullscreen)
            {


                //        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //nonVideoLayout.setBackgroundColor(Color.BLACK);
                videoLayout.setBackgroundColor(Color.BLACK);

                getWindow().setAttributes(attrs);
                if (android.os.Build.VERSION.SDK_INT >= 14)
                {
                    //noinspection all
                    //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);


                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

                    toolbar.setVisibility(View.INVISIBLE);

                    //    webView.setScaleX(View.SCREEN_STATE_ON);

                }
            }
            else
            {
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //nonVideoLayout.setBackgroundColor(Color.BLACK);
                videoLayout.setBackgroundColor(Color.WHITE);
                getWindow().setAttributes(attrs);
                if (android.os.Build.VERSION.SDK_INT >= 14)
                {
                    toolbar.setVisibility(View.VISIBLE);
                    web.setVisibility(View.VISIBLE);
                    videoLayout.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.VISIBLE);
                    nonVideoLayout.setVisibility(View.VISIBLE);



                    //noinspection all
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                }
            }

        });
        web.setWebChromeClient(webChromeClient);
        // Call private class InsideWebViewClient
        web.setWebViewClient(new MainActivity_detalle_video.InsideWebViewClient());

        // web.setWebViewClient(new InsideWebViewClient());
        //);



        Bundle bundle = getIntent().getExtras();

        int tiempo = (int)bundle.getDouble("yttiempo");


        Log.d("resultado  ", ""+tiempo   );


        String t = String.valueOf(tiempo);

        //tex.setText(t);

        String vid = bundle.getString("ytvideo");




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
                "        color: 'white',\n" +
                "        showinfo: 0,\n" +
                "        autoplay: 1,\n" +
                "        start: "+tiempo+",\n" +
                "        controls: 1,\n" +
                "        fs : 1\n" +
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
        *
        * */

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

      /*  video=(YoutubePlayerView)findViewById(R.id.detallevideo);

        video.setHorizontalScrollBarEnabled(false);
        video.getSettings().setTextZoom(100);
        video.getSettings().setJavaScriptEnabled(true);

        video.setWebViewClient(new WebViewClient());
        mChoromeClient = new FullscreenableChromeClient();
        video.setWebChromeClient(mChoromeClient);

        WebSettings settings = video.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);


        YTParams params = new YTParams();
        params.setControls(0);
        params.setfs(1);
        params.setmodestbranding(1);

        video.initialize("WCchr07kLPE", new YoutubePlayerView.YouTubeListener() {



            @Override
            public void onReady() {
                // when player is ready.
                JLog.i("onReady()");
            }

            @Override
            public void onStateChange(YoutubePlayerView.STATE state) {
                /**
                 * YoutubePlayerView.STATE
                 *
                 * UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, CUED, NONE
                 *


                JLog.i("onStateChange(" + state + ")");
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
                //handler.sendMessage(msg);
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






    }

    public class FullscreenableChromeClient extends WebChromeClient {
        private Activity mActivity = MainActivity_detalle_video.this;

        private WebChromeClient.CustomViewCallback mCustomViewCallback;

        private FrameLayout mFullscreenContainer;

        private final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        @Override
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }

            //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
            FrameLayout decor = (FrameLayout) mActivity.getWindow().getDecorView();
            mFullscreenContainer = new FullscreenHolder(mActivity);
            mFullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
            decor.addView(mFullscreenContainer, COVER_SCREEN_PARAMS);
            mCustomView = view;
            setFullscreen(true);
            mCustomViewCallback = callback;

            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            if (mCustomView == null) {
                return;
            }
            // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);//.SCREEN_ORIENTATION_PORTRAIT);
            setFullscreen(false);
            FrameLayout decor = (FrameLayout) mActivity.getWindow().getDecorView();
            decor.removeView(mFullscreenContainer);
            mFullscreenContainer = null;
            mCustomView = null;
            mCustomViewCallback.onCustomViewHidden();
        }

        private void setFullscreen(boolean enabled) {
            Window win = mActivity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
            if (enabled) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
                if (mCustomView != null) {
                    mCustomView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }
            }
            win.setAttributes(winParams);
        }
    }

    private static class FullscreenHolder extends FrameLayout {
        public FullscreenHolder(Context ctx) {
            super(ctx);
            //  setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }*/

  /*  @Override
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
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
