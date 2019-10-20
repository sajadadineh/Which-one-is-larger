package com.example.larger.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.larger.view.fragment.BestScoreFragment;
import com.example.larger.R;

public class MainActivity extends AppCompatActivity {

    private TextView LargerText;
    private TextView WhichText;
    private TextView OneIs;
    private TextView HistoryText;
    private Button StartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViews();
        SetOnClick();
        HistoryFragment();

    }

    private void HistoryFragment(){

        HistoryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BestScoreFragment historyFragment = new BestScoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.history_fragment,historyFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void SetOnClick(){

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });

    }

    private void FindViews(){

        LargerText = findViewById(R.id.larger_text);
        WhichText = findViewById(R.id.which_text);
        OneIs = findViewById(R.id.one_is_text);
        HistoryText = findViewById(R.id.history_text);
        StartButton = findViewById(R.id.start_Button);

    }

}
