package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by Pyoung-Woo on 2016-12-06.
 */

public class MainLoading extends Activity{
    private final int SPLASH_DISPLAY_LENGTH = 2056;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.loading_main); //Page XML

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainLoading.this,MainActivity.class);
                MainLoading.this.startActivity(mainIntent);
                MainLoading.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}