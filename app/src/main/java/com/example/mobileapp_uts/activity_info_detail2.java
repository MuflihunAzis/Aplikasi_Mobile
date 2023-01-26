package com.example.mobileapp_uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;

public class activity_info_detail2 extends AppCompatActivity {
    TextView kode_truk;
    ImageView gambar_truk;
    AppCompatButton pesan;
    com.google.android.material.textfield.TextInputEditText ETnama, ETwaktu;

    public final String APP_TAG = "MyApp";
    Bitmap bitMap = null;
    public String photoFileName = "photo.jpg";
    File photoFile;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail2);
        kode_truk = findViewById(R.id.nama_truk);
        gambar_truk = findViewById(R.id.gambar_truk);
        pesan = findViewById(R.id.id_button_login);
        ETnama = findViewById(R.id.idNAMA);
        ETwaktu = findViewById(R.id.idWaktu);


        getDataIntent();
    }
    void getDataIntent(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            ETnama.setText(bundle.getString("nama_kendaraan"));
            kode_truk.setText(bundle.getString("kode_kendaraan"));
            Picasso.get().load("http://tekajeapunya.com/kelompok_8/image/" + bundle.getString("gambar_kendaraan")).into(gambar_truk);
        }else{
            ETnama.setText("");
            kode_truk.setText("");
        }
    }
}