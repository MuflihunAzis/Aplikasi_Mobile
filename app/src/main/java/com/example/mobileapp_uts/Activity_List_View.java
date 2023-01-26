package com.example.mobileapp_uts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Activity_List_View extends AppCompatActivity {

    ArrayList<String> array_nama_kendaraan, array_kode_kendaraan, array_gambar_kendaraan;
    ProgressDialog progressDialog;
    SwipeRefreshLayout srl_main;
    private Context mContext;
    AppCompatButton id_button_malam;
    ListView listView;
    TextView status;
    String status_kendaraan;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public void ListView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KapanSajaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KapanSajaFragment newInstance(String param1, String param2) {
        KapanSajaFragment fragment = new KapanSajaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_kapan_saja, container, false);

        listView = findViewById(R.id.listView);
        status = findViewById(R.id.TVstatus);
        id_button_malam = findViewById(R.id.id_button_malam);
        progressDialog = new ProgressDialog(getApplicationContext());
        srl_main = findViewById(R.id.swipe_container);

        id_button_malam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_info_detail2.class);
                startActivity(intent);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                },2000);
            }
        });

        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });

        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        scrollRefresh();

        return rootview;


        //return null;
    }

    void initializeArray(){
        array_nama_kendaraan        = new ArrayList<String>();
        array_kode_kendaraan        = new ArrayList<String>();
        array_gambar_kendaraan      = new ArrayList<String>();

        array_nama_kendaraan.clear();
        array_kode_kendaraan.clear();
        array_gambar_kendaraan.clear();

        //status_kendaraan = status.getText().toString();
    }
    public  void getData(){
        initializeArray();
        AndroidNetworking.get("http://tekajeapunya.com/kelompok_8/API_Mitcsha/getDataKendaraan.php")
                //.addBodyParameter("status_kendaraan",""+status_kendaraan)
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Boolean status = response.getBoolean("status");
                            if (status) {
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon", "" + ja);
                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject jo = ja.getJSONObject(i);

                                    array_nama_kendaraan.add(jo.getString("nama_kendaraan"));
                                    array_kode_kendaraan.add(jo.getString("kode_kendaraan"));
                                    array_gambar_kendaraan.add(jo.getString("gambar_kendaraan"));
                                }
                                final CLV_DataKendaraan adapter = new CLV_DataKendaraan(Activity_List_View.this, array_nama_kendaraan, array_kode_kendaraan, array_gambar_kendaraan);
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik", "" + array_nama_kendaraan.get(position));
                                        Toast.makeText(Activity_List_View.this.getApplicationContext(), array_nama_kendaraan.get(position), Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(Activity_List_View.this.getApplicationContext(), activity_info_detail2.class);
                                        i.putExtra("nama_kendaraan", array_nama_kendaraan.get(position));
                                        i.putExtra("kode_kendaraan", array_kode_kendaraan.get(position));
                                        i.putExtra("gambar_kendaraan", array_gambar_kendaraan.get(position));
                                        startActivity(i);

                                    }
                                });
                            } else {
                                Toast.makeText(Activity_List_View.this.getApplicationContext(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void setContentView(int fragment_kapan_saja) {
    }

    public void scrollRefresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getData();
            }
        },1000);
    }

}