package com.example.turing_login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SatFragment extends Fragment {

    private static final String URL_DATA ="http://turing.infinityfreeapp.com/test.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count,total;
    private List<Listitem_satfrag> listitem_satfrags;
    //to fetch data
    DatabaseReference reff;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SatFragment newInstance(String param1, String param2) {
        SatFragment fragment = new SatFragment();
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
        View view= inflater.inflate(R.layout.fragment_sat, container, false);
        recyclerView= view.findViewById(R.id.recyclerView_satFrag);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //loadRecyclerViewData();

        listitem_satfrags=new ArrayList<>();
//        for(int i=0;i<2;i++){
//            Listitem_satfrag listitem_satfrag=new Listitem_satfrag(
//                    "heading"+(i+1),"testing"
//            );
//            listitem_satfrags.add(listitem_satfrag);
//        }

        ReadHeader();
//        reff= FirebaseDatabase.getInstance().getReference().child("Satday").child("0");
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
//        Listitem_satfrag listitem_satfrag=new Listitem_satfrag(
//                "heading"+(1),"testing"
//        );
//        listitem_satfrags.add(listitem_satfrag);
//
//
//        adapter=new SatAdapter(listitem_satfrags,getContext());
//        recyclerView.setAdapter(adapter);
        return view;
    }
    private  void ReadHeader(){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();        //   DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Satday");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listitem_satfrags.clear();
                String rollnumber=snapshot.child("Users").child(currentuser).child("id").getValue().toString();
                String year=rollnumber.substring(0,2);
                String branch=rollnumber.substring(7,8);
                String rno=rollnumber.substring(8,10);
                String batch;
                if(Integer.parseInt(rno)<42)
                    batch="1";
                else
                    batch="2";

                total=(int) snapshot.child("TimeTable").child(year).child(branch).child("1").child("Saturday").getChildrenCount();
                for(count=0;count<total;count++){
                    String chil=""+count;
                    String m1=snapshot.child("TimeTable").child(year).child(branch).child(batch).child("Saturday").child(chil).child("header").getValue().toString();
                    String m2=snapshot.child("TimeTable").child(year).child(branch).child(batch).child("Saturday").child(chil).child("time").getValue().toString();
                    String m3=snapshot.child("TimeTable").child(year).child(branch).child(batch).child("Saturday").child(chil).child("lecturer").getValue().toString();
                    Listitem_satfrag listitem_satfrag=new Listitem_satfrag(m1,m2,m3);
                    //  Listitem_satfrag listitem_satfrag=snapshot1.getValue(Listitem_satfrag.class);

                    assert listitem_satfrag != null;
                    listitem_satfrags.add(listitem_satfrag);

                    adapter=new SatAdapter(listitem_satfrags,getContext());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //  private void loadRecyclerViewData() {
//        ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Loading data....");
//        ProgressDialog.show();

//        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//                try {
//                    JSONObject jsonObject=new JSONObject(s);
//                    JSONArray array=jsonObject.getJSONArray("Satday");
//                    for(int i=0;i<array.length();i++){
//                        JSONObject o=array.getJSONObject(i);
//                        Listitem_satfrag item= new Listitem_satfrag(
//                        o.getString("header"+i),o.getString("header"+i)
//                        );
//
//                        listitem_satfrags.add(item);
//                    }
//                    adapter= new SatAdapter(listitem_satfrags,getContext());
//                    recyclerView.setAdapter(adapter);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }
//        );
//        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);
//    }
}