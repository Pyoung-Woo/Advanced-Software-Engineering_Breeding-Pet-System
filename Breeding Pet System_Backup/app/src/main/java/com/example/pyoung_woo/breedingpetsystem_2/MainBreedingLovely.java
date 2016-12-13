package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Pyoung-Woo on 2016-12-13.
 */

public class MainBreedingLovely extends Activity {
    private RelativeLayout mLayout; //Page Background Setting
    private int love;
    private int status;
    private int health;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.breeding_main_lovely); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.breeding_main); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2
        /*
        TextView loveText = (TextView) findViewById(R.id.love_s);
        loveText.setText(""+love);
        loveText.setTextSize(20);
        TextView healthText = (TextView) findViewById(R.id.health_s);
        healthText.setText(""+health);
        healthText.setTextSize(20);
        TextView statusText = (TextView) findViewById(R.id.status_s);
        statusText.setText(""+status);
        statusText.setTextSize(20);
        */
    }

    public void disciplineOnClick(View v){
        status = status+1;
        Intent intent = new Intent(this,BreedingDiscipline.class);
        Toast.makeText(this, "Discipline", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void diseasepreventionOnClick(View v){
        health = health+1;
        Intent intent = new Intent(this,BreedingDiseasePrevention.class);
        Toast.makeText(this, "Disease Prevention", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void foodOnClick(View v){
        love = love+1;
        status = status+1;
        health = health+1;
        Intent intent = new Intent(this,BreedingFood.class);
        Toast.makeText(this, "Feed Food", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void healthOnClick(View v){
        health = health+1;
        status = status+1;
        Intent intent = new Intent(this,BreedingHealth.class);
        Toast.makeText(this, "Health", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void heartrateOnClick(View v){
        Intent intent = new Intent(this,BreedingHeartRate.class);
        Toast.makeText(this, "Heart Rate", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void minigameOnClick(View v){
        love = love +1;
        Intent intent = new Intent(this,BreedingMiniGame.class);
        Toast.makeText(this, "Mini Game", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        love = 0;
        status = 0;
        health = 0;
    }

    public void pacecounterOnClick(View v){
        Intent intent = new Intent(this,BreedingPaceCounter.class);
        Toast.makeText(this, "Pace Counter", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void playOnClick(View v){
        love = love+1;
        status = status+1;
        Intent intent = new Intent(this,BreedingPlay.class);
        Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void shoppingOnClick(View v){
        love = love+1;
        Intent intent = new Intent(this,BreedingShopping.class);
        Toast.makeText(this, "Shopping", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void sleepOnClick(View v){
        love = love+1;
        health = health+1;
        status = status+1;
        Intent intent = new Intent(this,BreedingSleep.class);
        Toast.makeText(this, "Sleep", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}
