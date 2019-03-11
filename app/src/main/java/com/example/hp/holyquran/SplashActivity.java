package com.example.hp.holyquran;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.holyquran.Base.BaseActivity;

public class SplashActivity extends BaseActivity {
    private Handler waitHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        waitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ignored){
                    ignored.printStackTrace();
                }
            }
        },300);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        waitHandler.removeCallbacks(null);



    }
}
