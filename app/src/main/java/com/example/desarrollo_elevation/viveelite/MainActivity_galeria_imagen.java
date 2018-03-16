package com.example.desarrollo_elevation.viveelite;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.Adapter.Adapter_galeria;
import com.example.desarrollo_elevation.viveelite.Adapter.Adapter_grid_row_galeria;

import java.io.File;
import java.nio.BufferUnderflowException;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;
import static com.example.desarrollo_elevation.viveelite.R.drawable.abc_ab_share_pack_mtrl_alpha;
import static com.example.desarrollo_elevation.viveelite.R.drawable.item;
import static com.example.desarrollo_elevation.viveelite.R.drawable.tren;
import static com.example.desarrollo_elevation.viveelite.R.id.start;
//import static com.example.desarrollo_elevation.viveelite.R.id.textView;

public class MainActivity_galeria_imagen extends AppCompatActivity {

    int[] imagen = new int[]{R.mipmap.familia, R.drawable.familia3, R.drawable.familiahallowen,
    R.drawable.familia4, R.drawable.nino2, R.drawable.nino1, R.drawable.nina2, R.drawable.nino2, R.drawable.virola,
            R.drawable.familiahallowin2, R.mipmap.family, R.drawable.familia1,
            R.drawable.chicarojo, R.drawable.chicarojo2,
            R.mipmap.familia, R.mipmap.family, R.drawable.familia1,
            R.mipmap.familia, R.mipmap.family, R.drawable.familia1,
            R.mipmap.familia, R.mipmap.family, R.drawable.familia1,
            R.mipmap.familia, R.mipmap.family, R.drawable.familia1,};
   // Adapter_grid_row_galeria adapter;
   Adapter_galeria adapter;
    Toolbar toolbar;
    GridView lista;








    private static String APP_DIRECTORY = "Vive Elite/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private ImageView mSetImage;
    private Button mOptionButton;
    private RelativeLayout mRlView;

    private String mPath;

    private String[]FilePathString;
    private  File[] listFile;
    File file;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_galeria_imagen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar)findViewById(R.id.toolbargaleriadeimagen);





        toolbar.setTitle("Memorias");
        toolbar.setTitleTextColor(Color.WHITE);
        //toolbar.getBackground().setAlpha(00);
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);


        if(mayRequestStoragePermission()) {

            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
                        .show();
            } else {
                // Locate the image folder in your SD Card
                file = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "Vive_Elite");
                // Create a new folder if no folder named SDImageTutorial exist
                file.mkdirs();
            }


            if (file.isDirectory()) {
                listFile = file.listFiles();
                // Create a String array for FilePathStrings
                FilePathString = new String[listFile.length];
                // Create a String array for FileNameStrings


                for (int i = 0; i < listFile.length; i++) {
                    // Get the path of the image file
                    FilePathString[i] = listFile[i].getAbsolutePath();
                    // Get the name image file

                }
            }
        }
        else { return;}

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        adapter = new Adapter_galeria(MainActivity_galeria_imagen.this, FilePathString);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewergaleria);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       recyclerView.setHasFixedSize(true);

    recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(adapter);


       /* GridView lista = (GridView)findViewById(R.id.gridviewimagen);
        adapter = new Adapter_grid_row_galeria(MainActivity_galeria_imagen.this, FilePathString);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity_galeria_imagen.this, MainActivity_preview_image.class);

                Bundle bundle = new Bundle();
                String immg = FilePathString[i];
               bundle.putString("keyimagen",immg);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });*/

    }


    public void photocamara (MenuItem menuItem){
        if(mayRequestStoragePermission())
            openCamera();
        else
            return;


    }


    public  void photogalery(MenuItem menuItem)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);


    }

    private boolean mayRequestStoragePermission() {
        if(Build.VERSION.SDK_INT < M)
            return true;
        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;
       /* if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(mRlView, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });
        }*/else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }
        return false;
    }
    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();
        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();
        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpeg";

            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;

            File newFile = new File(mPath);



           // String se = textView.getText().toString();

         //   Intent e = new Intent(Ma

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
           // intent.putExtra(se, true);

         //   intent.getAction().toString().compareTo("key_image_code");
           // MEDIA_DIRECTORY.compareTo("App_elite");
            startActivityForResult(intent, PHOTO_CODE);


        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath = savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });



                    Bundle bundel = new Bundle();
                    bundel.putString("path_foto", mPath);

                    Intent intent = new Intent(MainActivity_galeria_imagen.this, MainActivity_Editarphoto.class );
                    intent.putExtras(bundel);

                    startActivity(intent);







                  /*  Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                    mSetImage.setImageBitmap(bitmap);*/
                    break;
                case SELECT_PICTURE:





                    Uri path = data.getData();

                   // Intent inte = new Intent(MainActivity_galeria_imagen.this, );
                    String uri = getPath(path);

                   //String ne = data.getDataString();
                    //String lod="este dice";

                    //og.d(lod, uri+" "+ path+" "+ ne);
                    Bundle bundeler = new Bundle();
                    bundeler.putString("path_getphoto", uri);

                    Intent intenter = new Intent(MainActivity_galeria_imagen.this, MainActivity_Editarphoto.class );
                    intenter.putExtras(bundeler);

                    startActivity(intenter);


                    //mSetImage.setImageURI(path);



                    break;

            }
        }
    }

    private String getPath(Uri uri) {
     //metodo que decifra
        String[] projection = { android.provider.MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity_galeria_imagen.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
              //  mOptionButton.setEnabled(true);
            }

            else{
                showExplanation();
            }
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_galeria_imagen.this);
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la camara de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();


            }
        });

        builder.show();
    }






    @Override
    public boolean onSupportNavigateUp() {

       // Bundle bundle = new Bundle();
       // bundle.putString("menu", "a");
        Intent intent = new Intent(MainActivity_galeria_imagen.this, Main_Activity_inicio.class);
      //  intent.putExtras(bundle);
        startActivity(intent);

       // onBackPressed();
        return false;
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

       if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
       {
           Intent intent = new Intent(MainActivity_galeria_imagen.this, Main_Activity_inicio.class);
           startActivity(intent);
       }


        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_galeria, menu);
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
}
