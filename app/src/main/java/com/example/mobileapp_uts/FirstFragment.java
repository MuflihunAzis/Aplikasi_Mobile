package com.example.mobileapp_uts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{

    AppCompatButton id_button_kapansaja, id_button_siang, id_button_malam;

    KapanSajaFragment kapanSajaFragment;
    SiangFragment siangFragment;
    MalamFragment malamFragment;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_first, container, false);

        //AppCompatButton appCompatButton = (AppCompatButton) rootview.findViewById(R.id.id_button_kapansaja);

        id_button_kapansaja = rootview.findViewById(R.id.id_button_kapansaja);

        id_button_siang = rootview.findViewById(R.id.id_button_siang);
        id_button_malam= rootview.findViewById(R.id.id_button_malam);

        id_button_siang.setOnClickListener(this);
        id_button_malam.setOnClickListener(this);


        id_button_kapansaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),Activty_List_Tes.class);
                startActivity(intent);
            }
        });


       return rootview;
    }



    void kapansaja() {
        kapanSajaFragment = new KapanSajaFragment();
        FragmentTransaction ft = getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.container, kapanSajaFragment);
        ft.commit();
    }
    void siang() {
        siangFragment = new SiangFragment();
        FragmentTransaction ft = getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.container, siangFragment);
        ft.commit();
    }
    void malam() {
        malamFragment = new MalamFragment();
        FragmentTransaction ft = getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.container, malamFragment);
        ft.commit();

    }







    @Override
    public void onClick(View view) {
        if (view == id_button_kapansaja) {
            kapansaja();
        }
        if (view == id_button_siang) {
            siang();
        }
        if (view == id_button_malam) {
            malam();
        }
    }
}