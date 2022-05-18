package MU.timetable;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MU.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import MU.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String day;
    public int flag = 1;
    //to fetch data
    DatabaseReference reff;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count, total;
    private List<Listitem_tt> listitem;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag(String day) {
        // Required empty public constructor
        this.day = day;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag newInstance(String param1, String param2) {
        Frag fragment = new Frag(null);
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

        View view = inflater.inflate(R.layout.tt_fragment_box, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_boxFrag);
        recyclerView.setHasFixedSize(true);
        ReadHeader();
        return view;
    }

    private void ReadHeader() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listitem = new ArrayList<>();
        listitem.clear();

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getContext());
        Cursor data = mDatabaseHelper.getData();
        data.moveToNext();
        String rollnumber = data.getString(1);
        String year = rollnumber.substring(0, 2);
        String branch = rollnumber.substring(7, 8);
        String rno = rollnumber.substring(8, 10);
        int batch = -1;
        if (Integer.parseInt(branch) == 1) {
            if (Integer.parseInt(rno) <= 44)
                batch = 1;
            else
                batch = 3;//not there actually
        } else if (Integer.parseInt(branch) == 2) {
            if (Integer.parseInt(rno) <= 35)
                batch = 1;
            else
                batch = 2;
        } else if (Integer.parseInt(branch) == 3) {
            if (Integer.parseInt(rno) <= 35)
                batch = 1;
            else
                batch = 2;
        } else if (Integer.parseInt(branch) == 5) {
            if (Integer.parseInt(rno) <= 42)
                batch = 1;
            else
                batch = 2;
        }
        String batnum = "" + batch;


        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("TimeTable");
        reference1.keepSynced(true);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                total = (int) snapshot.child(year).child(branch).child(batnum).child(day).getChildrenCount();
                for (count = 0; count < total; count++) {
                    String chil = "" + count;
                    String m1 = snapshot.child(year).child(branch).child(batnum).child(day).child(chil).child("header").getValue().toString();
                    String m2 = snapshot.child(year).child(branch).child(batnum).child(day).child(chil).child("time").getValue().toString();
                    String m3 = snapshot.child(year).child(branch).child(batnum).child(day).child(chil).child("lecturer").getValue().toString();
                    String m4 = snapshot.child(year).child(branch).child(batnum).child(day).child(chil).child("link").getValue().toString();
                    String m5 = snapshot.child(year).child(branch).child(batnum).child(day).child(chil).child("loc").getValue().toString();
                    int k;
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
                    String currentDateTimeString = sdf.format(d);
                    int time = Integer.parseInt(currentDateTimeString);
                    Calendar c = Calendar.getInstance();
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    int cal = -1;
                    //To highlight the box color based on the time and day(The current class will be highlighted)
                    if (day.equals("Monday"))
                        cal = Calendar.MONDAY;
                    else if (day.equals("Tuesday"))
                        cal = Calendar.TUESDAY;
                    else if (day.equals("Wednesday"))
                        cal = Calendar.WEDNESDAY;
                    else if (day.equals("Thursday"))
                        cal = Calendar.THURSDAY;
                    else if (day.equals("Friday"))
                        cal = Calendar.FRIDAY;
                    else if (day.equals("Saturday"))
                        cal = Calendar.SATURDAY;
                    if (time >= Integer.parseInt(m2.substring(0, 2) + m2.substring(3, 5)) && time <= Integer.parseInt(m2.substring(8, 10) + m2.substring(11, 13)) && cal == dayOfWeek)
                        k = -7596779;
                    else
                        k = -1;//-16777216;
                    Listitem_tt listitem_frag = new Listitem_tt(m1, m2, m3, "" + k, m4, m5);
                    assert listitem != null;
                    listitem.add(listitem_frag);
                    adapter = new AdapterBox(listitem, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

