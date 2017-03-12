package com.example.swathi.gameboggle;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * MultiplayerScore -- handles the scores in multiplayer game
 */

public class MultiplayerScore extends AppCompatActivity {

    // FIELDS:
    public TextView yourScoreTextView, theirScoreTextView, allWordsDispTextView, dispLoseTextView, dispWinTextView, dispDrawText;
    public TextView winLoseResultTextView;
    public ArrayList<String> allWordsArrayList;
    public int getWinVal, getScore, getRounds, getGameMode, getOpponentScore;
    Button playAgainBtn, submitNameButton;

    public ScoreList listOfHighScores; // ScoreList object, to check if player reaches a new high score
    boolean hasHighScore = false;  // to indicate if player should be added to high scores list.
    EditText playerName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_score_screen);

        allWordsDispTextView = (TextView) findViewById(R.id.textViewAllPossibleWords);
        allWordsArrayList = new ArrayList<String>();
        allWordsArrayList = (ArrayList<String>) getIntent().getSerializableExtra("AllPossibleWords");
        allWordsDispTextView.setText(allWordsArrayList.toString());
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

        yourScoreTextView = (TextView)findViewById(R.id.tvYourScore);
        getScore = getIntent().getExtras().getInt("Score");
        yourScoreTextView.setText(Integer.toString(getScore));
        Log.d("getScore", Integer.toString(getScore));

        getRounds = getIntent().getExtras().getInt("Rounds");
        Log.d("getRounds", Integer.toString(getRounds));

        getGameMode = getIntent().getExtras().getInt("GameMode");
        Log.d("getGameMode", Integer.toString(getGameMode));

        theirScoreTextView = (TextView)findViewById(R.id.tvOpponentsScore);
        getOpponentScore = getIntent().getExtras().getInt("OpponentScore");
        theirScoreTextView.setText(Integer.toString(getOpponentScore));
        Log.d("getOpponentScore", Integer.toString(getOpponentScore));

        dispLoseTextView = (TextView)findViewById(R.id.tvPlayerWinLose);
        dispWinTextView = (TextView)findViewById(R.id.textViewYouWin);
        dispDrawText = (TextView)findViewById(R.id.textViewMatchDraw);
        getWinVal = getIntent().getExtras().getInt("Win");
        if(getScore < getOpponentScore){
            dispLoseTextView.setVisibility(View.VISIBLE);
            dispWinTextView.setVisibility(View.INVISIBLE);
            dispDrawText.setVisibility(View.INVISIBLE);

        }
        else if (getScore > getOpponentScore){
            dispWinTextView.setVisibility(View.VISIBLE);
            dispLoseTextView.setVisibility(View.INVISIBLE);
            dispDrawText.setVisibility(View.INVISIBLE);
        }
        else{
            dispDrawText.setVisibility(View.VISIBLE);
            dispWinTextView.setVisibility(View.INVISIBLE);
            dispLoseTextView.setVisibility(View.INVISIBLE);
        }


        addListenerOnButton();

    }

    public void addListenerOnButton() {
        final Context context = this;
        playAgainBtn = (Button) findViewById(R.id.btnNextRoundID);
        submitNameButton = (Button) findViewById(R.id.btnSubmitNameID);

        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
