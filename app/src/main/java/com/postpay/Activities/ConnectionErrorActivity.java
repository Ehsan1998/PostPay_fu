package com.postpay.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.postpay.Publics.Conection_Detector;
import com.postpay.R;

public class ConnectionErrorActivity extends AppCompatActivity {

    Conection_Detector conection_detector;
    WebView gif;
    Button tryagain;
    TextView openSetting;
    ImageView imageView;
    LinearLayout linearLayout;
    String file = "file:android_asset/noConnection.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_error);

        imageView = (ImageView) findViewById(R.id.imgView);
        tryagain = (Button) findViewById(R.id.TryAgain);
        openSetting = (TextView) findViewById(R.id.OpenSetting);
        linearLayout = (LinearLayout) findViewById(R.id.noConnectionLinear);
        gif = (WebView) findViewById(R.id.noConnectionGif);
        WebSettings webSettings = gif.getSettings();
        webSettings.setJavaScriptEnabled(true);
        gif.loadUrl(file);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.GONE);
            }
        },1000);

        openSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClicked();
            }
        });
    }

    private void onBtnClicked() {
        checkConnection();
    }

    public void checkConnection(){
        conection_detector = new Conection_Detector(this);
        conection_detector.execute();
    }
}
