package com.example.user.webviewtest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtURL;
    Button btnMove,btnBack;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtURL=findViewById(R.id.edtURL);
        btnMove=findViewById(R.id.btnMove);
        btnBack=findViewById(R.id.btnBack);
        webView=findViewById(R.id.webView);
        webView.setWebViewClient(new myWebViewClient());
        WebSettings webSettings=webView.getSettings();
        webSettings.setBuiltInZoomControls(true);


        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://"+edtURL.getText().toString());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });

    }
    class myWebViewClient extends WebViewClient{


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}