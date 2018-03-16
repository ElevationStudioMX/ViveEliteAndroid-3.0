package com.example.desarrollo_elevation.viveelite;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.drive.Permission;
import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.M;
import static android.view.View.Z;

public class MainActivity_QR extends AppCompatActivity implements  ZXingScannerView.ResultHandler {

private final int MY_PERMISSIONS = 100;
private ZXingScannerView scannerView;
    private Button mOptionButton;
    private AbsoluteLayout mRlView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mRlView = (AbsoluteLayout)findViewById(R.id.activity_main_qr);



     /*   if(ContextCompat.checkSelfPermission(MainActivity_QR.this, CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity_QR.this, WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity_QR.this, CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(MainActivity_QR.this, WRITE_EXTERNAL_STORAGE))

            {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity_QR.this,
                        new String[]{CAMERA, WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }*/



        if(mayRequestStoragePermission()) {

            scannerView = new ZXingScannerView(this);

            setContentView(scannerView);
            scannerView.setResultHandler(this);

            scannerView.startCamera();
            // scannerView.bringToFront();

        }

        else {

            return;
           // return startActivity(intent);

        }



    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean mayRequestStoragePermission() {
       /* if(Build.VERSION.SDK_INT < M)
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
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        } */
       // @TargetApi(M)
        /*if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(mRlView, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });*/

        if(Build.VERSION.SDK_INT < M)
            return true;
        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))


        {
            return true;
        }

       /* else if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))) {
            Snackbar.make(mRlView, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });
        }*/
        else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        /*    Intent intent = new Intent(MainActivity_QR.this, MainActivity_QR.class);
            startActivity(intent);
*/
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(MainActivity_QR.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
  //              mOptionButton.setEnabled(true);
                scannerView = new ZXingScannerView(this);
                setContentView(scannerView);
                scannerView.setResultHandler(this);
                scannerView.startCamera();

            }

            else
            {
                showExplanation();
            }

        }



    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_QR.this);
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
    protected void onPause() {
        super.onPause();
       scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        Log.d("result", result.getText());

        String textQR = result.getText();

        if (!(result.getText() == null))
        {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);
        }
        Bundle bundle = new Bundle();
        bundle.putString("QRKEY", result.getText());


        Intent intent = new Intent(MainActivity_QR.this, MainActivity_webviewQR.class);

        intent.putExtras(bundle);



        startActivity(intent);







    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)

    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(MainActivity_QR.this, Main_Activity_inicio.class);
            startActivity(intent);
            return  true;
        }


        return super.onKeyDown(keyCode, event);
    }


}
