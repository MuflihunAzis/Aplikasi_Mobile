package com.example.mobileapp_uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class Sign_in extends AppCompatActivity {

    public static String my_shared_preferences;
    AppCompatButton id_button_login;
    TextView id_loginbackwelcome;
    TextInputEditText id_loginuser, id_loginpassword;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String username, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        id_button_login = findViewById(R.id.id_button_login);
        id_loginbackwelcome = findViewById(R.id.id_loginbackwelcome);
        id_loginuser = findViewById(R.id.id_loginuser);
        id_loginpassword = findViewById(R.id.id_loginpassword);

        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(getApplicationContext());

        id_loginbackwelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Splash_Screen.class);
                startActivity(intent);
            }
        });

        id_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(), Activity_Dashboard.class));
                progressDialog.setMessage("Login..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                username = id_loginuser.getText().toString();
                password = id_loginpassword.getText().toString();

                sessionManager.createSession(id_loginuser.getText().toString());

                validasiData();
            }
        });
    }

    void validasiData(){
        if(username.equals("") || password.equals("")){
            progressDialog.dismiss();
            Toast.makeText(Sign_in.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            cekLogin();
        }
    }

    void cekLogin(){
        AndroidNetworking.post("https://tekajeapunya.com/kelompok_8/API_Mitcsha/login.php")
                .addBodyParameter("Username",""+username)
                .addBodyParameter("Password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Cek Login")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("Cek Login",""+response);

                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            String nama = response.getString("Username");

//                            int id_user = response.getInt("id_user");
                            Toast.makeText(Sign_in.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                sessionManager.createSession(nama);
                                new AlertDialog.Builder(Sign_in.this)
                                        .setMessage("Berhasil Login")
                                        .setCancelable(false)
                                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(Sign_in.this, HomeActivity.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(Sign_in.this)
                                        .setMessage("Gagal Melakukan Login !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Intent intent = new Intent(Sign_in.this, Sign_in.class);
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
                        Log.d("ErrorLogin",""+anError.getErrorBody());
                    }
                });

    }
}
