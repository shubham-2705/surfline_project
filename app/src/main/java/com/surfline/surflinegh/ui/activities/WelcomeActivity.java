package com.surfline.surflinegh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.surfline.R;
import com.surfline.base.ui.activity.BaseActivity;
import com.surfline.surflinegh.constants.AppConstants;
import com.surfline.surflinegh.persistence.PrefrenceConstants;
import com.surfline.surflinegh.persistence.SurflineCacheManager;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        TextView txvSkip = (TextView) findViewById(R.id.txvSkip);
        final ImageView imvSplash = (ImageView) findViewById(R.id.imvSplash);
        final LinearLayout llyAfterSplash = (LinearLayout) findViewById(R.id.llyAfterSplash);
        llyAfterSplash.setVisibility(View.GONE);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,SignupActivity.class);
                startActivity(intent);

            }
        });
        txvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                            fadeOutAndHideView();
//                            txvTitle.startAnimation(new MyScaler(1.0f, 1.0f, 1.0f, 0.0f, 500, txvTitle, true));
                if(SurflineCacheManager.getInstance(WelcomeActivity.this).getBoolean(PrefrenceConstants.LOGGED_IN))
                {
                    Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    imvSplash.startAnimation(new MyScaler(1.0f, 1.0f, 1.2f, 1.0f, 1000, imvSplash, false));
                    llyAfterSplash.setVisibility(View.VISIBLE);
                }


            }
        }, AppConstants.SPLASH_TIMEOUT);

    }

    public class MyScaler extends ScaleAnimation {

        private View mView;
        private LinearLayout.LayoutParams mLayoutParams;
        private int mMarginBottomFromY, mMarginBottomToY;
        private boolean mVanishAfter = false;

        public MyScaler(float fromX, float toX, float fromY, float toY, int duration, View view,
                        boolean vanishAfter) {
            super(fromX, toX, fromY, toY);
            setDuration(duration);
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            int height = mView.getHeight() + mLayoutParams.topMargin;
            mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin - height;
            mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) - height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = mMarginBottomFromY
                        + (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime);
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin,
                        mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
            } else if (mVanishAfter) {
                mView.setVisibility(View.GONE);
            }
        }

    }
}
