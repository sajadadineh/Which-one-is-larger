package com.example.larger.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.larger.MyPreferenceManager;
import com.example.larger.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private final int ONE_NUMBER = 0;
    private final int TWO_NUMBER = 1;
    private final int EQUAL_BUTTON = 2;

    private boolean GameInProgress = false;

    private int CountDownInt = 3;
    private int NumberOneInt;
    private int NumberTwoInt;
    private int UserPointInt = 0;

    private TextView LargerText;
    private TextView WhichText;
    private TextView OneIs;
    private TextView AgainPlayGame;
    private TextView PointText;
    private TextView LevelText;
    private Button NumberOne;
    private Button NumberTwo;
    private Button EqualButton;
    private TextView CountDownNumber;
    private ConstraintLayout GameConstraint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FindViews();
        ConfigureLevel();
        StartCountDownTimer();

    }

    private void UpdateHighScore(){
        int previousHighScore = MyPreferenceManager.getInstance(GameActivity.this).getHighScore();
        if (previousHighScore < UserPointInt){
            MyPreferenceManager.getInstance(this).putHighScore(UserPointInt);
        }

    }

    private void StartGameAndFinish(){
        GameConstraint.setVisibility(View.VISIBLE);


        CountDownTimer countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long remainingTime) {

                LevelText.setText(getString(R.string.remaining_time , (int)(remainingTime/1000)));


            }

            @Override
            public void onFinish() {
                GameInProgress = false;
                LevelText.setText(getString(R.string.game_finished));
                UpdateHighScore();
                AgainPlayGame.setVisibility(View.VISIBLE);
                AgainPlayGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restartActivity();
                    }
                });

            }
        };
        countDownTimer.start();
        GameInProgress = true;
        GenerateOneLevel();
    }

    private void restartActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void StartCountDownTimer(){

        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(
                CountDownNumber,
                "scaleX",
                1f , 3f
        );
        scaleXAnimation.setDuration(1000);

        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(
                CountDownNumber,
                "scaleY",
                1f , 3f
        );
        scaleYAnimation.setDuration(1000);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(
                CountDownNumber,
                "alpha",
                1f , 0f
        );
        alphaAnimation.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimation,scaleYAnimation,alphaAnimation);

        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                if (CountDownInt == 0 ){
                    StartGameAndFinish();
                }else {
                    StartCountDownTimer();
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                CountDownNumber.setText(String.valueOf(CountDownInt));
                CountDownInt --;
            }

        });

        animatorSet.start();

    }

    private void ConfigureLevel(){

        PointText.setText(getString(R.string.user_point,0));

        NumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluateAndContinueGame(ONE_NUMBER);
            }
        });

        NumberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluateAndContinueGame(TWO_NUMBER);
            }
        });

        EqualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluateAndContinueGame(EQUAL_BUTTON);
            }
        });
    }

    private void EvaluateAndContinueGame(int whatPressed){

        if (!GameInProgress){
            return;
        }

        Evaluate(whatPressed);
        PointText.setText(getString(R.string.user_point,UserPointInt));
        GenerateOneLevel();
    }

    private void Evaluate(int whatPressed){

        if (whatPressed == ONE_NUMBER){

            if (NumberOneInt > NumberTwoInt){
                UserPointInt ++;
            }else {
                UserPointInt --;
            }

        }else if(whatPressed == TWO_NUMBER){

            if (NumberTwoInt > NumberOneInt){
                UserPointInt ++;
            }else {
                UserPointInt --;
            }

        }else if(whatPressed == EQUAL_BUTTON){

            if (NumberOneInt == NumberTwoInt){
                UserPointInt ++;
            }else {
                UserPointInt --;
            }
        }

    }

    private void GenerateOneLevel(){

        if (!GameInProgress){
            return;
        }
        NumberOneInt = GenerateInt();
        NumberTwoInt = GenerateInt();
        NumberOne.setText(String.valueOf(NumberOneInt));
        NumberTwo.setText(String.valueOf(NumberTwoInt));

    }

    private int GenerateInt(){
        Random random = new Random();

        int number = random.nextInt();
        if (number < 0){
            number = number* -1;
        }
        number = number%30;

        return number;

    }

    private void FindViews(){

        AgainPlayGame = findViewById(R.id.again_play_game);
        GameConstraint = findViewById(R.id.game_constrain);
        CountDownNumber = findViewById(R.id.count_down_number);
        LevelText = findViewById(R.id.level_text);
        PointText = findViewById(R.id.point_text);
        LargerText = findViewById(R.id.larger_text);
        WhichText = findViewById(R.id.which_text);
        OneIs = findViewById(R.id.one_is_text);
        NumberOne = findViewById(R.id.num_one);
        NumberTwo = findViewById(R.id.num_two);
        EqualButton = findViewById(R.id.equal_button);

    }

}
