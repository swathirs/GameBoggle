package com.example.swathi.gameboggle;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * MultiplayerScore -- handles the scores in multiplayer game
 */

public class MultiplayerScore extends AppCompatActivity {

    // FIELDS:
    public TextView yourScoreTextView, theirScoreTextView;
    public TextView winLoseResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_score_screen);
    }

}
