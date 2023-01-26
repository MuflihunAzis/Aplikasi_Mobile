package com.example.mobileapp_uts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KapanSajaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KapanSajaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KapanSajaFragment() {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatButton id_tambah2;
        AppCompatButton id_tambah3;
        View rootview = inflater.inflate(R.layout.fragment_kapan_saja, container, false);

        AppCompatButton id_button_malam = rootview.findViewById(R.id.id_button_malam);
        id_tambah2 = rootview.findViewById(R.id.tambah2);
        id_tambah3 = rootview.findViewById(R.id.tambah3);
        id_button_malam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activty_List_Tes.class);
                startActivity(intent);
            }
        });

        id_tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), activity_info_detail2.class);
                startActivity(intent);
            }
        });
        id_tambah3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), activity_info_detail2.class);
                startActivity(intent);
            }
        });
        return rootview;
    }
}