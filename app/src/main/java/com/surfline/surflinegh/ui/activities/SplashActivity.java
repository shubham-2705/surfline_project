package com.surfline.surflinegh.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;

import com.surfline.R;
import com.surfline.base.ui.activity.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final long SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        navigateToNextScreen();
    }

    private void navigateToNextScreen() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                finish();
            }
        }, SPLASH_TIMER);
    }


}
