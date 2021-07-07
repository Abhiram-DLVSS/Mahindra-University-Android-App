package com.example.turing_login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.turing_login.timetable.TimeTable;
import com.google.firebase.auth.FirebaseAuth;

public class Event extends Intents {
    private int flag=1;
    private Button invisibleButton;
    private WebView webView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        ProgressDialog nDialog;
        nDialog = new ProgressDialog(com.example.turing_login.Event.this);
        nDialog.setMessage("Loading...");
        nDialog.setIndeterminate(false);
        nDialog.show();
        statusbar();
        ImageView imageView = findViewById(R.id.event_3dot);

        webView=findViewById(R.id.event_webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().getDomStorageEnabled();
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
        //Floating button disappears
        webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int dy=scrollY-oldScrollY;
//                Log.d("chk", "onScrollChange: "+(scrollY-oldScrollY));
                if (dy > 10&&flag==1 ){
                    flag=0;
                    final Animation animation = new TranslateAnimation(0,0,0,250);
                    animation.setDuration(500);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(flag==0)
                            floatingmenu.setVisibility(View.GONE);
                        }
                    }, 500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                } else if (dy < -10&&flag==0){
                    floatingmenu.setVisibility(View.VISIBLE);
                    flag=1;
                    floatingmenu.setVisible(true);
                    final Animation animation = new TranslateAnimation(0,0,250,0);
                    animation.setDuration(500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
//                Log.d("testing", "onPageFinished: ");
                nDialog.dismiss();
                view.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByTagName('header')[0];"
                        + "head.parentNode.removeChild(head);" +
                        "})()");
                view.loadUrl("javascript:(function() { " +
                        "var foot = document.getElementsByTagName('footer')[0];"
                        + "foot.parentNode.removeChild(foot);" +
                        "})()");
                view.loadUrl("javascript:(function() { " +
                        "var foot = document.getElementsByTagName('div')[0];"
                        + "foot.parentNode.removeChild(foot);" +
                        "})()");
                view.loadUrl("javascript:(function() { " +
                        "var div = document.getElementsByClassName('footer-bar-mobile')[0];"
                        + "div.parentNode.removeChild(div);" +
                        "})()");

                for (int i = 1; i <= 10; i++) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.loadUrl("javascript:(function() { " +
                                    "var divs = document.getElementsByTagName('iframe');"
                                    + "var div;"
                                    + "var i = divs.length;"
                                    + "while (i--) {"
                                    + "  div = divs[i];"
                                    + "  if (div.getAttribute('title') == 'chat widget') {"
                                    + "    div.parentNode.removeChild(div);"
                                    + "  }"
                                    + "}"
                                    + "})()");
                        }
                    }, i*1000);
                }
            }
        });

        webView.loadUrl("https://www.mahindraecolecentrale.edu.in/events");
        floatinginit();
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Event.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Event.this, imageView);
                popupMenu.getMenuInflater().inflate(R.menu.menu_in_event, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.refresh_in_event) {
                            openEvent();
                            finish();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
}