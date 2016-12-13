package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.samsung.android.sdk.accessory.example.helloaccessory.provider.ProviderService;

/**
 * Created by Pyoung-Woo on 2016-12-08.
 */

public class BreedingPaceCounter extends Activity{

    private RelativeLayout mLayout; //Page Background Setting
    public String temp[] = new String[3];
    public String temp2[] = new String[3];
    public String heart[] = new String[2];
    public String pedometer[] = new String[2];
    public String calorie[] = new String[2];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.breeding_pacecounter); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.breeding_pacecounter); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2

        TextView PacecounterView = (TextView)findViewById(R.id.breeding_pacecounter_text);
        TextView CalorieView = (TextView)findViewById(R.id.breeding_calorie_text);

        temp = ProviderService.message.split(",");

        for(int i = 0; i<3; i++){
            temp2[i] = temp[i].trim();
        }

        heart = temp2[0].split(" ");
        pedometer = temp2[1].split(" ");
        calorie = temp2[2].split(" ");

        PacecounterView.setText(pedometer[0]);
        CalorieView.setText(calorie[0]);
    }

}
