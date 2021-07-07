package com.example.turing_login;

import android.app.ActivityManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.turing_login.timetable.TimeTable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

import static android.content.Context.ACTIVITY_SERVICE;

public class BottomSheet extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        Button confirm_button = v.findViewById(R.id.confirm_sheet);
        Button cancel_button = v.findViewById(R.id.cancel_sheet);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                FirebaseAuth.getInstance().signOut();
                getContext().deleteDatabase("User");
                startActivity(new Intent(getContext(), Login.class));
                dismiss();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        return v;
    }

}
