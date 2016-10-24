package com.example.karthickramjee.androidlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    Button back,forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=(WebView)findViewById(R.id.webview);
        back=(Button)findViewById(R.id.back);
        forward=(Button)findViewById(R.id.forward);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
                String html="<html><body><a href=\"www.google.com\"/><ul><li>Four</li><li>Five</li><li>Six</li></ul></body></html>";
                String mime="text/html";
                String encoding="UTF-8";
                webView.loadDataWithBaseURL("",html,mime,encoding,"");
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goForward();
                String html="<html><body><a href=\"www.google.com\"/><ul><li>Seven</li><li>Eight</li><li>Nine</li></ul></body></html>";
                String mime="text/html";
                String encoding="UTF-8";
                webView.loadDataWithBaseURL("",html,mime,encoding,"");
            }
        });
        String html="<html><body><a href=\"www.google.com\"/><ul><li>one</li><li>two</li><li>three</li></ul></body></html>";
        String mime="text/html";
        String encoding="UTF-8";
        webView.loadDataWithBaseURL("",html,mime,encoding,"");
    }
}
