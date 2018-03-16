package com.example.desarrollo_elevation.viveelite;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.R.attr.key;
import static com.example.desarrollo_elevation.viveelite.R.id.toolbar;

public class MainActivity_webviewQR extends AppCompatActivity {
    WebView web;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_webview_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = (Toolbar) findViewById(R.id.toolbarwebviewQR);
        toolbar.setTitle("PROMOCIONES");
        //toolbar.getBackground().setAlpha(00);
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);


        web = (WebView) findViewById(R.id.webviewqr);

        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setPluginState(WebSettings.PluginState.ON);
        web.getSettings().setBuiltInZoomControls(true);

        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        web.setWebViewClient(new WebViewClient() {
            // evita que los enlaces se abran fuera nuestra app en el navegador de android
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });


        Bundle bundle = getIntent().getExtras();

        String url = bundle.getString("QRKEY");


        web.loadUrl(url);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)

    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(MainActivity_webviewQR.this, MainActivity_QR.class);
            startActivity(intent);
            return  true;
        }


        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(MainActivity_webviewQR.this, MainActivity_QR.class);
        startActivity(intent);

        //onBackPressed();
        return false;
    }











}
