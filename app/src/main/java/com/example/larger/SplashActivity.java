package com.example.larger;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private TextView LargerText;
    private TextView WhichText;
    private TextView OneIs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FindViews();
        SplashScreen();
        Animation();

    }

    private void Animation(){

        ObjectAnimator TranslationXWhich = ObjectAnimator.ofFloat(
                WhichText,
                "TranslationX",
                -3000f,100f,-200f,0f
        );
        TranslationXWhich.setDuration(1500);


        ObjectAnimator TranslationXOneIs = ObjectAnimator.ofFloat(
                OneIs,
                "TranslationX",
                -3000f , 100f , -200 , 0f
        );
        TranslationXOneIs.setDuration(1800);


        ObjectAnimator TranslationXLarger = ObjectAnimator.ofFloat(
                LargerText,
                "TranslationX",
                -3000f , 100f , -200f , 0f
        );
        TranslationXLarger.setDuration(2500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(TranslationXWhich,TranslationXOneIs,TranslationXLarger);
        animatorSet.start();


    }

    private void SplashScreen(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        },3000);

    }

    private void FindViews(){

        LargerText = findViewById(R.id.larger_text);
        WhichText = findViewById(R.id.which_text);
        OneIs = findViewById(R.id.one_is_text);

    }

}
