package com.example.mobileapp_uts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CLV_DataKendaraan extends ArrayAdapter<String>  {

    private  final Activity context;
    private ArrayList<String> vNama_kendaraan;
    private ArrayList<String> vKode_kendaraan;
    private ArrayList<String> vGambar_kendaraan;

    public CLV_DataKendaraan(Activity context, ArrayList<String> namaKendaraan, ArrayList<String> kodeKendaraan, ArrayList<String> gambarKendaraan) {
        super (context, R.layout.list_item, namaKendaraan);
        this.context = context;
        this.vNama_kendaraan = namaKendaraan;
        this.vKode_kendaraan = kodeKendaraan;
        this.vGambar_kendaraan = gambarKendaraan;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list_item, null, true);
        TextView nama_kendaraan = rowView.findViewById(R.id.nama_kendaraan);
        TextView kode_kendaraan = rowView.findViewById(R.id.kode_kendaraan);
        ImageView gambar_kendaraan = rowView.findViewById(R.id.gambar_kendaraan);

        nama_kendaraan.setText(vNama_kendaraan.get(position));
        kode_kendaraan.setText(vKode_kendaraan.get(position));
        if (vGambar_kendaraan.get(position).equals("")) {
            Picasso.get().load("https://tekajeapunya.com/kelompok_8/image/tesTruck.png").into(gambar_kendaraan);
        }else {
            Picasso.get().load("https://tekajepunya.com/kelompok_8/image/"+vGambar_kendaraan.get(position)).into(gambar_kendaraan);
        }
        return rowView;
    }
}
