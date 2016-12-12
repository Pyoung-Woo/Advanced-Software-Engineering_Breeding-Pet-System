package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Pyoung-Woo on 2016-12-08.
 */

public class BreedingHealth extends Activity{
    private RelativeLayout mLayout; //Page Background Setting
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.breeding_health); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.breeding_health); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2

        Button startHealthBtn = (Button) findViewById(R.id.startHealthBtn);
        final ImageView startHealthIV = (ImageView) findViewById(R.id.pet_sweat);
        startHealthBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.health_anim
                );
                startHealthIV.startAnimation(anim);
            }
        });
    }
    public void doneHealthOnClick (View v){
        Intent intent = new Intent(this,MainBreeding.class);
        Toast.makeText(this, "Health Done!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
