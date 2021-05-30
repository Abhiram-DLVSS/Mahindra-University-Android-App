package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {
    public TextView textView,email,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));


        textView= findViewById(R.id.textView8);
        email=findViewById((R.id.emailid));
        info=findViewById((R.id.about_info));
        textView.setPaintFlags(textView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        String sourceString;
        sourceString= "<b>" + "Hi," + "</b> " +  "We are Second year Undergraduates from Mahindra University, We made this app because we felt that our friends and our colleagues deserve a one stop application for all the College related activities.";
//         sourceString = "<b>" + "Hi," + "</b> " +  "We are Second year Undergraduates from Mahindra University, We made this app because we felt that our friends and our colleagues deserve a one stop application for all the College related activities.";

        info.setText(Html.fromHtml(sourceString));

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:devturing21@gmail.com?subject=" + Uri.encode("Mahindra University App Feedback/ Bug Reports") + "&body=" + Uri.encode("~Write here~"));
                intent.setData(data);
                startActivity(intent);
            }
        });
    }

}