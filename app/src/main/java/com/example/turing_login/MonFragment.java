package com.example.turing_login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<datamodel_mon> dataholder_mon;
    public MonFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MonFragment newInstance(String param1, String param2) {
        MonFragment fragment = new MonFragment();
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
        View view= inflater.inflate(R.layout.fragment_mon, container, false);
        recyclerView=view.findViewById(R.id.recycler_view_mon_frag);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder_mon=new ArrayList<>();
        datamodel_mon ob1=new datamodel_mon("My ","22");
        dataholder_mon.add(ob1);

        datamodel_mon ob2=new datamodel_mon("name","10");
        dataholder_mon.add(ob2);

        datamodel_mon ob3=new datamodel_mon("is","06");
        dataholder_mon.add(ob3);

        datamodel_mon ob4=new datamodel_mon("Abhiram","2002");
        dataholder_mon.add(ob4);

        datamodel_mon ob5=new datamodel_mon("DLVSS","2001");
        dataholder_mon.add(ob5);

        recyclerView.setAdapter(new adapter_mon(dataholder_mon));



        return view;
    }
}