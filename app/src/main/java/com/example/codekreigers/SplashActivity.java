package com.example.codekreigers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity{

    private ImageView logo;
    private long splashTimeout = 4050;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);


        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(logo, "scaleX", 0.75f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(logo, "scaleY", 0.75f);
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(logo,"scaleX", 1f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(logo, "scaleY", 1f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(logo,"alpha",1f,0.45f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(logo,"alpha",0.45f,1f);
        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);
        scaleUpX.setDuration(1000);
        scaleUpY.setDuration(1000);
        fadeIn.setDuration(1000);
        fadeOut.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();
        AnimatorSet scaleUp = new AnimatorSet();
        AnimatorSet combinedPlay = new AnimatorSet();
        AnimatorSet combinedPlay1 = new AnimatorSet();
        AnimatorSet combinedPlay2 = new AnimatorSet();
        scaleDown.playTogether(scaleDownX,scaleDownY,fadeIn);
        scaleUp.playTogether(scaleUpX,scaleUpY,fadeOut);

        combinedPlay1.playSequentially(scaleDown,scaleUp);

        combinedPlay2.playSequentially(scaleDown,scaleUp);

        combinedPlay.playSequentially(combinedPlay1,combinedPlay2);


        combinedPlay.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
                boolean firstStart = prefs.getBoolean("firstStart",true);

                if(firstStart) {
                    Intent i = new Intent(SplashActivity.this, EnterName.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(SplashActivity.this, HomePage.class);
                    startActivity(i);
                }
                    finish();
            }

        },splashTimeout);
    }
}
