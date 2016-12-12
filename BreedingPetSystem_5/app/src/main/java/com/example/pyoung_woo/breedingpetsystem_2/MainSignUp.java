package com.example.pyoung_woo.breedingpetsystem_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;import com.example.pyoung_woo.breedingpetsystem_2.MainActivity;

/**
 * Created by Pyoung-Woo on 2016-12-07.
 */

public class MainSignUp extends Activity {
    private RelativeLayout mLayout; //Page Background Setting
    private EditText userIdentity;
    private EditText userPassword;
    private EditText userRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.signup_main); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.signUpMain); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2
        userIdentity = (EditText) findViewById(R.id.identityEditText);
        userPassword = (EditText) findViewById(R.id.passwordEditText);
        userRePassword = (EditText) findViewById(R.id.rePasswordEditText);
    }
    protected void signUpDoneOnClick(View v){
        String userPassword_c = userPassword.getText().toString();
        String userRePassword_c = userRePassword.getText().toString();
        //System.out.println(userPassword_c);
        //System.out.println(userRePassword_c);
        if (userPassword_c.equals(userRePassword_c)){
            Intent intent = new Intent(this,MainActivity.class);
            Toast.makeText(this, "Sign-Up, Successfully.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Check Password Again, Please.", Toast.LENGTH_SHORT).show();
        }
    }
    protected void cancelOnClick(View v){
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        }
}
