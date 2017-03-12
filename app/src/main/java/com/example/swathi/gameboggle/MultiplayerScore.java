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
    public TextView yourScoreTextView, theirScoreTextView;
    public TextView dispLoseTextView, dispWinTextView, dispDrawText;
    public TextView allWordsDispTextView;
    public TextView highscore_notification;
    public TextView winLoseResultTextView;

    public ArrayList<String> allWordsArrayList;

    public int getWinVal, getScore, getRounds, getGameMode, getOpponentScore;
    Button playAgainBtn, submitNameButton;

    public ScoreList listOfHighScores; // ScoreList object, to check if player reaches a new high score
    boolean hasHighScore = false;  // to indicate if player should be added to high scores list.
    EditText playerName;
    int difficulty;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_score_screen);

        // Possible words
        allWordsDispTextView = (TextView) findViewById(R.id.textViewAllPossibleWords);
        allWordsArrayList = new ArrayList<String>();
        allWordsArrayList = (ArrayList<String>) getIntent().getSerializableExtra("AllPossibleWords");
        allWordsDispTextView.setText(allWordsArrayList.toString());
        allWordsDispTextView.setMovementMethod(new ScrollingMovementMethod());
        for (int j = 0; j < allWordsArrayList.size(); j++) {
            Log.d("ValidWords", allWordsArrayList.get(j));
        }

        getWinVal = getIntent().getExtras().getInt("Win");
        Log.d("getWinVal", Integer.toString(getWinVal));

        // Player's Score
        yourScoreTextView = (TextView)findViewById(R.id.tvYourScore);
        getScore = getIntent().getExtras().getInt("Score");
        yourScoreTextView.setText(Integer.toString(getScore));
        Log.d("getScore", Integer.toString(getScore));

        getRounds = getIntent().getExtras().getInt("Rounds");
        Log.d("getRounds", Integer.toString(getRounds));

        getGameMode = getIntent().getExtras().getInt("GameMode");
        Log.d("getGameMode", Integer.toString(getGameMode));

        // Opponent's Score
        theirScoreTextView = (TextView)findViewById(R.id.tvOpponentsScore);
        getOpponentScore = getIntent().getExtras().getInt("OpponentScore");
        theirScoreTextView.setText(Integer.toString(getOpponentScore));
        Log.d("getOpponentScore", Integer.toString(getOpponentScore));

        // Display win/lose text
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

        // check is player's score qualifies for new high score
        difficulty = 4;  // default, multiplayer single round basic

        if (getGameMode == 1)
            difficulty = 6;  // multiplayer cut throat

        if (getRounds == 1) // is multiplayer multi-round basic
            difficulty = 5;

        hasHighScore = listOfHighScores.checkNewHighScore(difficulty, getScore);

        // false, hide high score notification message, input field and submit button
        if(!hasHighScore) {
            highscore_notification = (TextView) findViewById(R.id.textView_highScoreMsg);
            highscore_notification.setVisibility(View.INVISIBLE);

            playerName.setVisibility(View.INVISIBLE);

            submitNameButton = (Button) findViewById(R.id.btnSubmitNameID);
            submitNameButton.setVisibility(View.INVISIBLE);
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

        submitNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // add their name to high scores
                String name = playerName.getText().toString();
                listOfHighScores.addHighScore(difficulty, getScore, name);

                // send player to main screen
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
