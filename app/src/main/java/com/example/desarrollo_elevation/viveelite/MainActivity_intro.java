package com.example.desarrollo_elevation.viveelite;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_intro extends AppCompatActivity {

    private  long tiempocarga = 1002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Intent inte = new Intent(MainActivity_intro.this, /*MainActivity_menutolbbed.class*/Main_Activity_inicio.class);

                startActivity(inte);

                MainActivity_intro.this.finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, tiempocarga);
    }
}
