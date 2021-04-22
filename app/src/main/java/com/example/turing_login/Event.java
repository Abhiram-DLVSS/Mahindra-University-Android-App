package com.example.turing_login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
    private Button button;
    private WebView webView;
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
//        webView.getSettings().setDomStorageEnabled(true);
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
                    animation.setDuration(1000);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                } else if (dy < -10&&flag==0){
                    flag=1;
                    floatingmenu.setVisible(true);
                    final Animation animation = new TranslateAnimation(0,0,250,0);
                    animation.setDuration(1000);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                }
            }
        });

//      webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("testing", "shouldOverrideUrlLoading: ");
                if (url.equals("https://www.mahindraecolecentrale.edu.in/events")){
                    //notify the user that this url is blocked
                    Toast.makeText(Event.this, "Blocked", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
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
                view.loadUrl("javascript:disableSection('" + "b7LICyA-1618673933600" + "');");
                view.loadUrl("javascript:(function() { " +
                "document.getElementsById('b7LICyA-1618673933600')[0].style.display='none'; " +
                        "})()");
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