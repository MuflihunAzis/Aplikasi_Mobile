package com.example.mobileapp_uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputEditText;
import com.androidnetworking.AndroidNetworking;

import org.json.JSONObject;

public class Sign_up extends AppCompatActivity {

    AppCompatButton id_button_signup;
    TextView id_signupbackwelcome;
    String username, email, password;
    TextInputEditText regisuser,regispassword,regisemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        id_button_signup = findViewById(R.id.id_button_signup);
        id_signupbackwelcome = findViewById(R.id.id_signupbackwelcome);
        regisuser= findViewById(R.id.id_regisuser);
        regispassword= findViewById(R.id.id_regispassword);
        regisemail= findViewById(R.id.id_regisemail);

        id_button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = regisuser.getText().toString();
                email = regisemail.getText().toString();
                password = regispassword.getText().toString();

                validasiData();

                // Toast.makeText(getApplicationContext(),"Berhasil Melakukan Register",Toast.LENGTH_LONG).show();
                // startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        id_signupbackwelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Splash_Screen.class);
                startActivity(intent);
            }
        });


    }

    void validasiData(){
        if(username.equals("") ||email.equals("") || password.equals("")){
            Toast.makeText(Sign_up.this, "Data Tidak Lengkap", Toast.LENGTH_SHORT).show();
        } else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tekajeapunya.com/kelompok_8/API_Mitcsha/register.php")
                .addBodyParameter("Username",""+username)
                .addBodyParameter("Email",""+email)
                .addBodyParameter("Password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("cekRegister",""+response);

                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            Toast.makeText(Sign_up.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(Sign_up.this)
                                        .setMessage("Berhasil Register")
                                        .setCancelable(false)
                                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(Sign_up.this, Sign_in.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(Sign_up.this)
                                        .setMessage("Gagal Melakukan Register !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(Sign_up.this, Sign_up.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }
}