package com.example.mobileapp_uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activty_List_Tes extends AppCompatActivity {

    SwipeRefreshLayout srl_main;
    private Context mContext;
    ProgressDialog progressDialog;
    ListView listView;
    private ArrayList<String> vNama_kendaraan;
    private ArrayList<String> vKode_kendaraan;
    private ArrayList<String> vGambar_kendaraan;
    AppCompatButton booking;
    public View view;

    ArrayList<String> array_nama_kendaraan, array_kode_kendaraan, array_gambar_kendaraan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tes);

        listView = findViewById(R.id.idLVPE);
        srl_main = findViewById(R.id.swipe_containerPE);
        progressDialog = new ProgressDialog(this);

        booking = (AppCompatButton) findViewById(R.id.id_button_malam);

        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });

        // Scheme colors for animation
        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );
        scrollRefresh();


    }
    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(booking);
            }
        },2000);
    }


    void initializeArray() {
        vNama_kendaraan = new ArrayList<String>();
        vKode_kendaraan = new ArrayList<String>();
        vGambar_kendaraan = new ArrayList<String>();

        vNama_kendaraan.clear();
        vKode_kendaraan.clear();
        vGambar_kendaraan.clear();
    }

    public View getData(View booking){
        initializeArray();
        AndroidNetworking.get("http://tekajeapunya.com/kelompok_8/API_Mitcsha/getDataKendaraan.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);

                                    vNama_kendaraan.add(jo.getString("nama_kendaraan"));
                                    vKode_kendaraan.add(jo.getString("kode_kendaraan"));
                                    vGambar_kendaraan.add(jo.getString("gambar_kendaraan"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_DataKendaraan adapter = new CLV_DataKendaraan(Activty_List_Tes.this,
                                        vNama_kendaraan,vKode_kendaraan,vGambar_kendaraan);
                                //Set adapter to list
                                listView.setAdapter(adapter);

                                booking.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View booking) {


                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(Activty_List_Tes.this, activity_info_detail2.class);
                                        i.putExtra("nama_kendaraan",vNama_kendaraan);
                                        i.putExtra("kode_kendaraan",vKode_kendaraan);
                                        i.putExtra("gambar_kendaraan",vGambar_kendaraan);
                                        startActivity(i);
                                    }
                                });


//edit and delete
                            }else{
                                Toast.makeText(Activty_List_Tes.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onError(ANError anError) {

                    }
                });
    return booking;

    }


}