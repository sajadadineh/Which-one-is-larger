package com.example.larger.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.larger.MyPreferenceManager;
import com.example.larger.R;


public class BestScoreFragment extends Fragment {

    private TextView HighScore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_best_score,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FindViews(view);


        HighScore.setText(getString(R.string.high_score)+ MyPreferenceManager.getInstance(getActivity()).getHighScore());


    }

    private void FindViews(View view){
        HighScore = (view).findViewById(R.id.high_score);
    }

}
