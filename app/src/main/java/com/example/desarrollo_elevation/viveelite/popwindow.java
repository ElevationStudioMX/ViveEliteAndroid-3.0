package com.example.desarrollo_elevation.viveelite;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Desarrollo_Elevation on 13/05/17.
 */

public class popwindow extends AppCompatActivity {
AbsoluteLayout absoluteLayout;
 ImageView imagepopview;
    TextView texthistorias;
    ImageButton btneliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popowindow);



        String fonter1 = "fonts/OpenSans-Regular.ttf";


        absoluteLayout =(AbsoluteLayout) findViewById(R.id.layoutpopview);
        //absoluteLayout.getBackground().setAlpha(00);

        imagepopview = (ImageView)findViewById(R.id.imagepopwindow);
 texthistorias = (TextView)findViewById(R.id.texthistoria);
btneliminar = (ImageButton)findViewById(R.id.imageButtoneliminar);

        btneliminar.getBackground().setAlpha(00);
        btneliminar.setColorFilter(Color.WHITE);
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), fonter1);

        texthistorias.setTypeface(typeface);

        texthistorias.setTextSize(10);
        texthistorias.setText("CONOCE LA HISTORIA");

        texthistorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=tg6aaaX-MNo&feature=youtu.be");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                finish();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width= displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;


        imagepopview.setImageResource(R.drawable.popup);//.resize(1000,0).into(imagepopview);

        int height_imagen = imagepopview.getDrawable().getIntrinsicHeight();
        int width_imgagen = imagepopview.getDrawable().getIntrinsicWidth();


        float razon = (float) height_imagen/width_imgagen;

         float altura_view = (float) ((width*.95)* (razon));

        getWindow().setLayout((int)(width*.95),(int)altura_view/*(int)(height*.8)*/);

        //Picasso.with(this).load(R.drawable.popup).resize(1000,0).into(imagepopview);


}

}
