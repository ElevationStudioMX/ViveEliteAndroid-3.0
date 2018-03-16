package com.example.desarrollo_elevation.viveelite;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.desarrollo_elevation.viveelite.spotify.CredentialsHandler;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.LoginActivity;

import java.util.concurrent.TimeUnit;

public class MainActivity_loginspotify extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @SuppressWarnings("SpellCheckingInspection")
    private static final String CLIENT_ID = "a2535973bdd84b22b6509ea3c2a56173";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String REDIRECT_URI = /*"elevation://callback";*/"testschema://callback";

    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loginspotify);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String token = CredentialsHandler.getToken(this);
        if (token == null) {
            setContentView(R.layout.activity_main_loginspotify);
            onLoginButtonClicked();

        } else {
            startMainActivity(token);
        }




    }

    private void onLoginButtonClicked() {



        final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(new String[]{"user-read-private", "playlist-read","user-read-birthdate", "user-read-email", "streaming"})
                //.//setCustomParam("", "")
                //.set

                .build();


        //final AuthenticationRequest request1 = new AuthenticationRequest.Builder()
//        request.getClientId();




        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        Log.d("reques", request.getClientId());
        Log.d("reques", request.getRedirectUri());
//        Log.d("reques", request.getState());
        Log.d("reques",String.valueOf(request.getScopes()));
        Log.d("reques", request.getResponseType());
        Log.d("reques", String.valueOf(request.toUri()));
        Log.d("reques", String.valueOf(request.describeContents()));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
          /*  final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                    .setScopes(new String[]{"playlist-read, user-read-private, user-read-birthdate, user-read-email"})
                    .build();*/
            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                   // logMessage("Got token: " + response.getAccessToken());
                    CredentialsHandler.setToken(this, response.getAccessToken(), response.getExpiresIn(), TimeUnit.SECONDS);
                    //CredentialsHandler.setToken();
                    startMainActivity(response.getAccessToken());
                  //  Log.d("mensaje de token", response.getAccessToken());
                    // Log.d("mensaje de token", response.);
                    //Log.d("mensaje de token", String.valueOf(response.getType()));
                    //Log.d("mensaje de token", String.valueOf(response.getExpiresIn()));
                    //Log.d("mensaje de token", String.valueOf(response.getCode()));


                    break;

                // Auth flow returned an error
                case ERROR:
                    logError("Auth error: " + response.getError());
                    Log.v("LINEA 108", "LINEA 108 pasa error 1");
                    onBackPressed();

                    break;

                // Most likely auth flow was cancelled
                default:
                    logError("Auth result: " + response.getType());
                    Log.v("LINEA 117", "LINEA 117 pasa error 2");
                    onBackPressed();
            }
        }
    }

    private void startMainActivity(String token) {
        Intent intent =  MainActivity_menu_musica.createIntent(this);/*MainActivity_pruebacombinacion.createIntent(this)*/
        intent.putExtra(MainActivity_menu_musica.EXTRA_TOKEN, token); /*MainActivity_pruebacombinacion.EXTRA_TOKEN, token*/
        startActivity(intent);
        finish();
    }

    private void logError(String msg) {
        Toast.makeText(this, "Error: " + msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg);
    }

    private void logMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
    }



}
