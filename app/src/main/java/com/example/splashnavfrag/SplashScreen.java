package com.example.splashnavfrag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    TextView splashAppName;
    TextView splashAppDesc;
    TextView splashAppSpac;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splashAppName = findViewById(R.id.splashAppName);
        splashAppDesc = findViewById(R.id.splashAppDesc);
        splashAppSpac = findViewById(R.id.splashAppSpac);

        // THE FOLLOWING ADJUSTS THE TEXT SIZES ACCORDING TO SCREEN SIZE FOR API 19
        float metricsWidth = getResources().getDisplayMetrics().widthPixels;
        float[] widthless = {200,300,400,500,600,700,800,900,
                1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,
                2100,2200,2300,2400,2500,2600,2700,2800,2900,3000,10000};
        float[] widthMore = {0,200,300,400,500,600,700,800,900,
                1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,
                2100,2200,2300,2400,2500,2600,2700,2800,2900,3000};

        // CHECK THE USER AGENT TO SEE IF DEVICE IS MOBILE OR TABLET
        String userAgent = new WebView(this).getSettings().getUserAgentString();
        float[] explaSize;

        // RESIZE THE COMPONENTS IF THE SCREEN IS A MOBILE
        if(userAgent.contains("Mobile")){
            explaSize = new float[]{
                    14, (float) 14.5, 15, (float) 15.5, 16, (float) 16.5, 17, (float) 17.5, 18, (float) 18.5,
                    19, (float) 19.5, 20, (float) 20.5, 21, (float) 21.5, 22, (float) 22.5, 23, (float) 23.5,
                    24, (float) 24.5, 25, (float) 25.5, 26, (float) 26.5, 27, (float) 27.5, 28, (float) 28.5};
            for (int mh = 0; mh < widthless.length; mh++){
                if (metricsWidth <= widthless[mh] && metricsWidth > widthMore[mh]){
                    splashAppName.setTextSize((float) (explaSize[mh] * 1.9));
                    splashAppDesc.setTextSize((float) (explaSize[mh] * 1.02));
                    splashAppSpac.setTextSize((float) (explaSize[mh] * 0.2));;
                }
            }
        } else {

            // RESIZE THE COMPONENTS IF THE SCREEN IS A TABLET
            explaSize = new float[]{
                    22, (float) 22.5, 23, (float) 23.5, 24, (float) 24.5, 25, (float) 25.5, 26, (float) 26.5,
                    27, (float) 27.5, 28, (float) 28.5, 29, (float) 29.5, 30, (float) 30.5, 31, (float) 31.5,
                    32, (float) 32.5, 33, (float) 33.5, 34, (float) 34.5, 35, (float) 35.5, 36, (float) 36.5};
            for (int mh = 0; mh < widthless.length; mh++){
                if (metricsWidth <= widthless[mh] && metricsWidth > widthMore[mh]){
                    splashAppName.setTextSize((float) (explaSize[mh] * 2.1));
                    splashAppDesc.setTextSize((float) (explaSize[mh] * 1.1));
                    splashAppSpac.setTextSize((float) (explaSize[mh] * 0.2));
                }
            }
        }

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        },3000);

    }
}

