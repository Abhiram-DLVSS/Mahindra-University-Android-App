package com.example.turing_login.timetable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.turing_login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonFragment extends Fragment {

    private static final String URL_DATA ="http://turing.infinityfreeapp.com/test.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Listitem_monfrag> listitem_monfrags;
    //to fetch data
    DatabaseReference reff;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonFragment.
     */
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
        recyclerView= view.findViewById(R.id.recyclerView_monFrag);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //loadRecyclerViewData();

        listitem_monfrags=new ArrayList<>();
        for(int i=0;i<2;i++){
            Listitem_monfrag listitem_monfrag=new Listitem_monfrag(
                    "heading"+(i+1),"testing"
            );
            listitem_monfrags.add(listitem_monfrag);
        }

        ReadHeader();
//        reff= FirebaseDatabase.getInstance().getReference().child("Monday").child("0");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String m1=snapshot.child("header1").getValue().toString();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Listitem_monfrag listitem_monfrag=new Listitem_monfrag(
//                "heading"+(1),"testing"
//        );
//        listitem_monfrags.add(listitem_monfrag);
//
//
//        adapter=new MonAdapter(listitem_monfrags,getContext());
//        recyclerView.setAdapter(adapter);
        return view;
    }
    private  void ReadHeader(){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Monday").child("0");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listitem_monfrags.clear();
                String m1=snapshot.child("header1").getValue().toString();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Listitem_monfrag listitem_monfrag=new Listitem_monfrag(
                m1+(1),"testing"
                );
                  //  Listitem_monfrag listitem_monfrag=snapshot1.getValue(Listitem_monfrag.class);

                    assert listitem_monfrag != null;

                    listitem_monfrags.add(listitem_monfrag);

                    adapter=new MonAdapter(listitem_monfrags,getContext());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadRecyclerViewData() {
//        ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Loading data....");
//        ProgressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray array=jsonObject.getJSONArray("Monday");
                    for(int i=0;i<array.length();i++){
                        JSONObject o=array.getJSONObject(i);
                        Listitem_monfrag item= new Listitem_monfrag(
                        o.getString("header"+i),o.getString("header"+i)
                        );

                        listitem_monfrags.add(item);
                    }
                    adapter= new MonAdapter(listitem_monfrags,getContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}