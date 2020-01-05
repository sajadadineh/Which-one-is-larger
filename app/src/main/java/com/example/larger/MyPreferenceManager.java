package com.example.larger;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {

    private static MyPreferenceManager instance = null ;

    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor ;

    public static MyPreferenceManager getInstance(Context context){
        if (instance == null){
            instance = new MyPreferenceManager(context);
        }
        return instance;
    }

    private MyPreferenceManager(Context context){
        sharedPreferences =context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public int getHighScore(){
        return sharedPreferences.getInt("high_score",0);
    }

    public void putHighScore(int highScore){
        editor.putInt("high_score",highScore);
        editor.apply();
    }

}



