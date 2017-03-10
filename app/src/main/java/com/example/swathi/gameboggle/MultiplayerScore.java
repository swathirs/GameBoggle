package com.example.swathi.gameboggle;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * MultiplayerScore -- handles the scores in multiplayer game
 */

public class MultiplayerScore extends AppCompatActivity {

    // FIELDS:
    public TextView yourScoreTextView, theirScoreTextView, allWordsDispTextView;
    public TextView winLoseResultTextView;
    public ArrayList<String> allWordsArrayList;
    public int getWinVal, getScore, getRounds, getGameMode, getOpponentScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_score_screen);

        allWordsDispTextView = (TextView) findViewById(R.id.textViewAllPossibleWords);
        allWordsArrayList = new ArrayList<String>();
        allWordsArrayList = (ArrayList<String>) getIntent().getSerializableExtra("AllWords");
        allWordsDispTextView.setText(allWordsDispTextView.toString());
        allWordsDispTextView.setMovementMethod(new ScrollingMovementMethod());
        for (int j = 0; j < allWordsArrayList.size(); j++) {
            Log.d("ValidWords", allWordsArrayList.get(j));
        }

        /*
        *    allValidWordsListText = (TextView) findViewById(R.id.tvListOfPossibleWordsID);
        fetchValidWordsList = new ArrayList<String>();
        fetchValidWordsList = (ArrayList<String>) getIntent().getSerializableExtra("ValidWordsFromThirdScreen");
        allValidWordsListText.setText(fetchValidWordsList.toString());
        allValidWordsListText.setMovementMethod(new ScrollingMovementMethod());*/

        getWinVal = getIntent().getExtras().getInt("Win");
        Log.d("getWinVal", Integer.toString(getWinVal));

        getScore = getIntent().getExtras().getInt("Score");
        Log.d("getScore", Integer.toString(getScore));

        getRounds = getIntent().getExtras().getInt("Rounds");
        Log.d("getRounds", Integer.toString(getRounds));

        getGameMode = getIntent().getExtras().getInt("GameMode");
        Log.d("getGameMode", Integer.toString(getGameMode));

        getOpponentScore = getIntent().getExtras().getInt("OpponentScore");
        Log.d("getOpponentScore", Integer.toString(getOpponentScore));



    }

}
