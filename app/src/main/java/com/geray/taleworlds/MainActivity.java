package com.geray.taleworlds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private RelativeLayout splash_layout;
    private static int PASS_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash_layout = findViewById(R.id.splashLayout);
        webView = findViewById(R.id.webView);
        //loads site in the app
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http://") || url.startsWith("https://")) && !url.startsWith("https://www.taleworlds.com/")) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });

        webView.loadUrl("https://www.taleworlds.com/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        initSplash();
    }


        private void initSplash(){

            Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);
            splash_layout.startAnimation(animation);

            //Passing
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.setVisibility(View.VISIBLE);
                    splash_layout.setVisibility(View.INVISIBLE);
                }
            },PASS_TIME);
        }

        //This method require to use back button if we want to go previous web page
        @Override
        public void onBackPressed() {
            if(webView.canGoBack()){
                webView.goBack();
            }
            else{
                super.onBackPressed();
            }
        }

    }
