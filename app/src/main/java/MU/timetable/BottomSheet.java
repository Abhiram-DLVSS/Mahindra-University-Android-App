package MU.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.MU.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

import MU.MSAuth.Login;

public class BottomSheet extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        Button confirm_button = v.findViewById(R.id.confirm_sheet);
        Button cancel_button = v.findViewById(R.id.cancel_sheet);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                getContext().deleteDatabase("User");
                startActivity(new Intent(getContext(), Login.class));
                dismiss();
                CookieManager.getInstance().removeAllCookies(null);
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

}
