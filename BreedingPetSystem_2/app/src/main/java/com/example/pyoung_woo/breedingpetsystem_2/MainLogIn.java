package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pyoung_woo.breedingpetsystem_2.MainActivity;

/**
 * Created by Pyoung-Woo on 2016-12-07.
 */

public class MainLogIn extends Activity {
    private RelativeLayout mLayout; //Page Background Setting
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.login_main); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.logInMain); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2
        Button touchBtn = (Button) findViewById(R.id.touchBtn);
        final ImageView touchIV = (ImageView) findViewById(R.id.pet);
        //Toast.makeText(this, "Hi ♡", Toast.LENGTH_SHORT).show();
        touchBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.rotate_anim
                );
                touchIV.startAnimation(anim);
            }
        });
    }
    public void startOnClick(View v){
        Intent intent = new Intent(this,MainBreeding.class);
        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        }
}
