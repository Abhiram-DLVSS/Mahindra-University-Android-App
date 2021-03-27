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
 * Use the {@link WedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WedFragment extends Fragment {

    private static final String URL_DATA ="http://turing.infinityfreeapp.com/test.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count,total;
    private List<Listitem_wedfrag> listitem_wedfrags;
    //to fetch data
    DatabaseReference reff;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WedFragment newInstance(String param1, String param2) {
        WedFragment fragment = new WedFragment();
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
        View view= inflater.inflate(R.layout.fragment_wed, container, false);
        recyclerView= view.findViewById(R.id.recyclerView_wedFrag);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //loadRecyclerViewData();

        listitem_wedfrags=new ArrayList<>();
//        for(int i=0;i<2;i++){
//            Listitem_wedfrag listitem_wedfrag=new Listitem_wedfrag(
//                    "heading"+(i+1),"testing"
//            );
//            listitem_wedfrags.add(listitem_wedfrag);
//        }

        ReadHeader();
//        reff= FirebaseDatabase.getInstance().getReference().child("Wedday").child("0");
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
//        Listitem_wedfrag listitem_wedfrag=new Listitem_wedfrag(
//                "heading"+(1),"testing"
//        );
//        listitem_wedfrags.add(listitem_wedfrag);
//
//
//        adapter=new WedAdapter(listitem_wedfrags,getContext());
//        recyclerView.setAdapter(adapter);
        return view;
    }
    private  void ReadHeader(){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("TimeTable").child("19").child("5").child("1").child("Wednesday");
        //   DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Wedday");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listitem_wedfrags.clear();
                total=(int) snapshot.getChildrenCount();
                for(count=0;count<total;count++){
                    String chil=""+count;
                    String m1=snapshot.child(chil).child("header").getValue().toString();
                    String m2=snapshot.child(chil).child("time").getValue().toString();
                    String m3=snapshot.child(chil).child("lecturer").getValue().toString();
                    Listitem_wedfrag listitem_wedfrag=new Listitem_wedfrag(m1,m2,m3);
                    //  Listitem_wedfrag listitem_wedfrag=snapshot1.getValue(Listitem_wedfrag.class);

                    assert listitem_wedfrag != null;
                    listitem_wedfrags.add(listitem_wedfrag);

                    adapter=new WedAdapter(listitem_wedfrags,getContext());
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
//                    JSONArray array=jsonObject.getJSONArray("Wedday");
//                    for(int i=0;i<array.length();i++){
//                        JSONObject o=array.getJSONObject(i);
//                        Listitem_wedfrag item= new Listitem_wedfrag(
//                        o.getString("header"+i),o.getString("header"+i)
//                        );
//
//                        listitem_wedfrags.add(item);
//                    }
//                    adapter= new WedAdapter(listitem_wedfrags,getContext());
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