package com.example.turing_login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

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
        nDialog.setMessage("Loading the Website");
        nDialog.setIndeterminate(false);
        nDialog.show();
        statusbar();
        webView=findViewById(R.id.event_webview);

        webView.getSettings().setJavaScriptEnabled(true);
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
                Log.d("chk", "onScrollChange: "+(scrollY-oldScrollY));
                if (dy > 10&&flag==1 ){
                    flag=0;
                    final Animation animation = new TranslateAnimation(0,0,0,250);
                    animation.setDuration(500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                } else if (dy < -10&&flag==0){
                    flag=1;
                    floatingmenu.setVisible(true);
                    final Animation animation = new TranslateAnimation(0,0,250,0);
                    animation.setDuration(500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                }
            }
        });

//      webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("testing", "onPageFinished: ");
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

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_in_event,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        switch(id){
            case R.id.refresh_in_event: {
                finish();
                startActivity(getIntent());
            }
            break;
        }
        return true;
    }

}