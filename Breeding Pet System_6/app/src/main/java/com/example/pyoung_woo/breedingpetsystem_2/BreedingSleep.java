package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
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

public class BreedingSleep extends Activity {
    private RelativeLayout mLayout; //Page Background Setting
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.breeding_sleep); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.breeding_sleep); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2

        Button startSleepBtn = (Button) findViewById(R.id.startSleepBtn);
        final ImageView startSleepIV = (ImageView) findViewById(R.id.pet_sleeping);
        startSleepBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.sleep_anim
                );
                startSleepIV.startAnimation(anim);
            }
        });
    }
    public void doneSleepOnClick (View v){
        Intent intent = new Intent(this,MainBreeding.class);
        Toast.makeText(this, "Sleep Done!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
