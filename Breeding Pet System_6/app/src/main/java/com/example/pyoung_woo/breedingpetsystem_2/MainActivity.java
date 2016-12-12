package com.example.pyoung_woo.breedingpetsystem_2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mLayout; //Page Background Setting
    private EditText userIdentity;
    private EditText userPassword;
    public int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen); //Page Full
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Page Fix
        setContentView(R.layout.activity_main); //Page XML
        mLayout = (RelativeLayout) findViewById(R.id.activity_main); //Page Background_1
        mLayout.setBackgroundColor(Color.rgb(255, 255, 255)); //Page Background_2
        userIdentity = (EditText) findViewById(R.id.identityEditText);
        userPassword = (EditText) findViewById(R.id.passwordEditText);
    }
    public void signUpOnClick(View v){
        Intent intent = new Intent(this,MainSignUp.class);
        startActivity(intent);
    }
    public void logInOnClick(View v){
        String userIdentity_c = userIdentity.getText().toString();
        String userPassword_c = userPassword.getText().toString();
        System.out.println(userIdentity_c);
        System.out.println(userPassword_c);
        if (userIdentity_c.equals("test")||userPassword_c.equals("1111")){
            Intent intent = new Intent(this,MainLogIn.class);
            Toast.makeText(this, "Log-In, Successfully.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Check Information Again, Please.", Toast.LENGTH_SHORT).show();
        }
    }
}
