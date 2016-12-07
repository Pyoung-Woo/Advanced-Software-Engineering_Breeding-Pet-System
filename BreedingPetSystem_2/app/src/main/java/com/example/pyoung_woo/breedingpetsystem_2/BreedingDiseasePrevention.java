package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * Created by Pyoung-Woo on 2016-12-08.
 */

public class BreedingDiseasePrevention extends Activity {
    private RelativeLayout mLayout; //Page Background Setting
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.breeding_diseaseprevention); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.breeding_diseaseprevention); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2
    }
}
