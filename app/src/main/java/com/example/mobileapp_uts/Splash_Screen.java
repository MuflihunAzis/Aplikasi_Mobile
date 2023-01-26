package com.example.mobileapp_uts;

import static com.example.mobileapp_uts.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    TextView signup;
    AppCompatButton id_button_splashscreen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

       signup = (TextView) findViewById(R.id.id_spalsignup);
       id_button_splashscreen = findViewById(R.id.id_button_splashscreen);

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), Sign_up.class);
               startActivity(intent);
           }
       });

        id_button_splashscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sign_in.class);
                startActivity(intent);
            }
        });
    }
}