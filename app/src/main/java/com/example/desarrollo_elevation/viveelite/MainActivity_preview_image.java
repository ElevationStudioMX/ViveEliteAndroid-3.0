package com.example.desarrollo_elevation.viveelite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.photogesturelibrary.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

import static android.R.attr.path;
import static com.example.desarrollo_elevation.viveelite.R.id.checkbox;
import static com.example.desarrollo_elevation.viveelite.R.id.toolbar;
import static com.example.desarrollo_elevation.viveelite.R.id.transition_current_scene;
//import static com.example.desarrollo_elevation.viveelite.R.id.webr;


public class MainActivity_preview_image extends AppCompatActivity {

    Toolbar toolbar;
    PhotoView photo;
    final Context context = this;
    File f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_preview_image);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = (Toolbar) findViewById(R.id.toolbarpreviewimagen);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);

        //toolbar.getBackground().setAlpha(00);
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);

        photo = (PhotoView) findViewById(R.id.idphotopreview);


        Bundle bundle = getIntent().getExtras();

       // String galery_path = bundle.getString("path_getphoto");

        String path = bundle.getString("saveimage");

        String galery_gridrow= bundle.getString("keyimagen");



/*if(!(galery_path== null)) {
    //Uri url = Uri.parse(galery_path);




     Picasso.with(this).load(new File(galery_path)).resize(1000,0).into(photo);
    //photo.setImageURI(url);
}*/

    if(!(path == null))
    {
       Picasso.with(this).load(new File(path)).resize(1000,0).into(photo);
     }

    else if(!(galery_gridrow == null))
{
    Picasso.with(this).load(new File(galery_gridrow)).resize(1000,0).into(photo);

}

        //photo.setImageResource(R.drawable.nina2);

    }



    public  void iconeditphoto(MenuItem menuItem)
    {




        Bundle bundle = getIntent().getExtras();

        //String galery_path = bundle.getString("path_getphoto");

        String path = bundle.getString("saveimage");

        String galery_gridrow= bundle.getString("keyimagen");


       /*if(!(galery_path== null)) {
            //Uri url = Uri.parse(galery_path);


            Bundle bolder = new Bundle();

            bolder.putString("getphoto_path", galery_path);

            Intent inte = new Intent(MainActivity_preview_image.this, MainActivity_Editarphoto.class);

            inte.putExtras(bolder);

            startActivity(inte);


          //  photo.setImageResource(R.drawable.marco2);
//            Picasso.with(this).load(new File(galery_path)).resize(1000,0).into(photo);
            //photo.setImageURI(url);
        }*/

         if(!(path == null))
        {

            Bundle bolder = new Bundle();
            bolder.putString("htap", path);


            Intent inter = new Intent(MainActivity_preview_image.this, MainActivity_Editarphoto.class);
            inter.putExtras(bolder);
            startActivity(inter);




  //          Picasso.with(this).load(new File(path)).resize(1000,0).into(photo);
        }

        else if(!(galery_gridrow == null))
        {
            //Picasso.with(this).load(new File(galery_gridrow)).resize(1000,0).into(photo);


            Bundle bolder = new Bundle();
            bolder.putString("gridrow_galery", galery_gridrow);
            Intent intere = new Intent(MainActivity_preview_image.this, MainActivity_Editarphoto.class);

            intere.putExtras(bolder);
            startActivity(intere);



        }





    }
    public void sharernetworkphoto(MenuItem menuItem)
    {
     comapartphoto();
    }


    public  void iconeliminarphoto(MenuItem menuItem)
    {

        Bundle bundle = getIntent().getExtras();

      //  String galery_path = bundle.getString("path_getphoto");

        //String path = bundle.getString("path_foto");

        String galery_gridrow= bundle.getString("keyimagen");


       /* if(!(galery_path== null)) {
            //Uri url = Uri.parse(galery_path);


             f = new File(galery_path);

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Borrar Fotografia");
            builder.setMessage("Deseas borrar la imagen aceptando se perdera la imagen para siempre");
            builder.setIcon(R.drawable.warning);

            builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                      f.delete();

                    Intent inte = new Intent(MainActivity_preview_image.this, MainActivity_galeria_imagen.class);
                    startActivity(inte);

                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

           dialogInterface.dismiss();

                }
            });

            builder.show();








            //  photo.setImageResource(R.drawable.marco2);
//            Picasso.with(this).load(new File(galery_path)).resize(1000,0).into(photo);
            //photo.setImageURI(url);
        }

        else if(!(path == null))
        {

             f = new File(path);

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Borrar Fotografia");
            builder.setMessage("Deseas borrar la imagen aceptando se perdera la imagen para siempre");
            builder.setIcon(R.drawable.warning);

            builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    f.delete();

                    Intent inte = new Intent(MainActivity_preview_image.this, MainActivity_galeria_imagen.class);
                    startActivity(inte);

                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                }
            });

            builder.show();






            //          Picasso.with(this).load(new File(path)).resize(1000,0).into(photo);
        }

        else */if(!(galery_gridrow == null))
        {
            f = new File(galery_gridrow);

          //  f.delete();



            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Borrar Fotografia");
            builder.setMessage("Deseas borrar la imagen aceptando se perdera la imagen para siempre");
            builder.setIcon(R.drawable.warning);

            builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    f.delete();

                    Intent inte = new Intent(MainActivity_preview_image.this, MainActivity_galeria_imagen.class);
                    startActivity(inte);

                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                }
            });
            builder.show();


         /*   Intent inte = new Intent(MainActivity_preview_image.this, MainActivity_galeria_imagen.class);
                startActivity(inte);*/





        }

    }




    private void comapartphoto(){


        photo.buildDrawingCache();
        Bitmap bitmap = photo.getDrawingCache();

        try {
            // creacion del archvo de bitmap que se va enviar a compartir
            File file = new File(photo.getContext().getCacheDir(), bitmap + ".png");
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);
            //convierte el bitma a un formato png
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            // se cierra el el archvio que esta entrado para luego realizar la conversion
            fOut.flush();
            fOut.close();

            file.setReadable(true, false);

            // se crea la accion para realizar el proceso de comaparti
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            // se selecciona el tipo de dato que se convertira
            intent.setType("image/png");
            // realiza el envio
            context.startActivity(Intent.createChooser(intent, "Compartir con"));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }




    @Override
    public boolean onSupportNavigateUp() {

        Intent intent = new Intent(MainActivity_preview_image.this, MainActivity_galeria_imagen.class );
        startActivity(intent);
        //onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.idcamara) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    if (keyCode == KeyEvent.KEYCODE_BACK  && event.getRepeatCount()== 0)
    {  Intent intent = new Intent(MainActivity_preview_image.this, MainActivity_galeria_imagen.class);
        startActivity(intent);

        return  true;

    }


        return super.onKeyDown(keyCode, event);



    }
}
