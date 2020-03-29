package com.postpay.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.postpay.Publics.Conection_Detector;
import com.postpay.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class SplashScreenActivity extends AppCompatActivity {

    Animation topAnim;
    ImageView splashLogo;
    Context context;
    boolean internetConnection;
    Conection_Detector conection_detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.splashSpinKit);
        Sprite circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);

        splashLogo = (ImageView) findViewById(R.id.splashLogo);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        splashLogo.setAnimation(topAnim);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkConnection();
            }
        },2000);
    }

    /*public boolean isConnectingToInternet() {
        ConnectivityManager connectivity;
        connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url
                            .openConnection();
                    urlc.setConnectTimeout(5000);
                    urlc.connect();
                    Log.d("TAG222" , String.valueOf(urlc.getResponseCode()));
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return false;
    }*/
     public void checkConnection(){
         conection_detector = new Conection_Detector(this);
         conection_detector.execute();
     }
}
