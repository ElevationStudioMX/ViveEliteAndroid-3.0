package com.example.desarrollo_elevation.viveelite;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.desarrollo_elevation.viveelite.Adapter.Model_productos;
import java.util.ArrayList;

public class MainActivity_productos_elite extends AppCompatActivity {
    Toolbar toolbar;
    private  static  int  alturatotal;
    ImageView imgrollo;
    ImageView imgservilletas;
    ImageView imgtoalla;
    ImageView imgpanuelo;
    private  static ArrayList<Model_productos> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_productos_elite);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbarproductos);
        toolbar.setTitle("Productos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imgrollo = findViewById(R.id.idimgrollo);
        imgservilletas = findViewById(R.id.idimgservilletas);
        imgtoalla = findViewById(R.id.idimgtoallas);
        imgpanuelo = findViewById(R.id.idimgpanuelo);
        imgrollo.setImageResource(R.drawable.papel_higienico);
        imgservilletas.setImageResource(R.drawable.servilletas);
        imgtoalla.setImageResource(R.drawable.toallas_cocinas);
        imgpanuelo.setImageResource(R.drawable.faciales_y_panuelos);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        toolbar.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int  altura = toolbar.getMeasuredHeight();
        alturatotal = height-90;
        imgrollo.getLayoutParams().height = alturatotal/4;
        imgservilletas.getLayoutParams().height = alturatotal/4;
        imgtoalla.getLayoutParams().height = alturatotal/4;
        imgpanuelo.getLayoutParams().height = alturatotal/4;

        int rolloanchoimagen = imgrollo.getDrawable().getIntrinsicWidth();
        int rolloaltoimagen = imgrollo.getDrawable().getIntrinsicHeight();

        int servilletasanchoimagen = imgservilletas.getDrawable().getIntrinsicWidth();
        int servilletasaltoimagen = imgservilletas.getDrawable().getIntrinsicHeight();

        int toallaanchoimagen = imgtoalla.getDrawable().getIntrinsicWidth();
        int toallaaltoimagen = imgtoalla.getDrawable().getIntrinsicHeight();

        int panueloanchoimagen = imgpanuelo.getDrawable().getIntrinsicWidth();
        int panueloaltoimagen = imgpanuelo.getDrawable().getIntrinsicHeight();

        float razonrollo = (float)rolloanchoimagen/rolloaltoimagen;
        float razonservilleta = (float)servilletasanchoimagen/servilletasaltoimagen;
        float razontoalla = (float)toallaanchoimagen/toallaaltoimagen;
        float razonpanuelo = (float)panueloanchoimagen/panueloaltoimagen;
        float proporcionrollo = (float) alturatotal * razonrollo;
        float proporcionservilleta = (float) alturatotal * razonservilleta;
        float proporciontoalla = (float) alturatotal * razontoalla;
        float proporcionpanuelo= (float) alturatotal * razonpanuelo;
        imgrollo.getLayoutParams().width = (int)proporcionrollo;
        imgservilletas.getLayoutParams().width = (int)proporcionservilleta;
        imgtoalla.getLayoutParams().width = (int)proporciontoalla;
        imgpanuelo.getLayoutParams().width = (int)proporcionpanuelo;

        imgrollo.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("num", 1 );
            bundle.putString("titulo", "Papel Higiénico");
            Intent intent = new Intent(MainActivity_productos_elite.this, MainActivity_detalle_producto.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        imgservilletas.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("num", 2 );
            bundle.putString("titulo", "Servilletas");
            Intent intent = new Intent(MainActivity_productos_elite.this, MainActivity_detalle_producto.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        imgtoalla.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("num", 3 );
            bundle.putString("titulo", "Toallas de Cocina");
            Intent intent = new Intent(MainActivity_productos_elite.this, MainActivity_detalle_producto.class);
            intent.putExtras(bundle);
            startActivity(intent);

        });
        imgpanuelo.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("num", 4 );
            bundle.putString("titulo", "Faciales Y Pañuelos");
            Intent intent = new Intent(MainActivity_productos_elite.this, MainActivity_detalle_producto.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
    public int getAlturatotal(){
        return this.alturatotal;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(MainActivity_productos_elite.this, Main_Activity_inicio.class);
            startActivity(intent);
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(MainActivity_productos_elite.this, Main_Activity_inicio.class);
        startActivity(intent);
        return false;
    }
}
