package com.example.mobileapp_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstTime) {

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, Activity_Slide.class);
                    startActivity(intent);
                    finish();
                } else {

//                    if (sessionManager.getSPSudahLogin()){
//                        startActivity(new Intent(MainActivity.this, KapanSajaFragment.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                        finish();
//                    }else {
//                        Intent aftersplash = new Intent(MainActivity.this, Splash_Screen.class);
//                        startActivity(aftersplash);
//                        finish();
//                    }
                    Intent aftersplash = new Intent(MainActivity.this, Splash_Screen.class);
                    startActivity(aftersplash);
                    finish();
                }

            }
        },SPLASH_SCREEN);


    }
}