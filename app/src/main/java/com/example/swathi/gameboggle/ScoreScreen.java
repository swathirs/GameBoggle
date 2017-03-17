package com.example.swathi.gameboggle;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.swathi.gameboggle.R.id.btnNextRoundID;

public class ScoreScreen extends AppCompatActivity {

    // FIELDS
    public ValidWords valid;
    public Board board;
    public TextView foundWordsText, allValidWordsListText, highscore_notification;
    public ArrayList<String> fetchFoundWordsList, fetchValidWordsList;
    int roundScore = 0;
    int difficulty;

    public ScoreList listOfHighScores; // ScoreList object, to check if player reaches a new high score
    boolean hasHighScore = false;  // to indicate if player should be added to high scores list.
    EditText playerName;

    public TextView roundScoreDisp;
    Button nextGameButton, submitNameButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        difficulty = getIntent().getExtras().getInt("difficultyFromThirdScreen"); // Difficulty Level, obtained from ThirdScreen
        listOfHighScores = new ScoreList(getApplicationContext()); // ScoreList object, to check if player reaches a new high score


        // Score
        roundScore = getIntent().getExtras().getInt("RoundScoreFromThirdScreen"); // Score, obtained from ThirdScreen
        roundScoreDisp = (TextView) findViewById(R.id.tvScoreForThisRoundID);
        roundScoreDisp.setText(Integer.toString(roundScore));

        // Found Words
        foundWordsText = (TextView) findViewById(R.id.textViewFoundWordsID);
        fetchFoundWordsList = new ArrayList<String>();
        fetchFoundWordsList = (ArrayList<String>) getIntent().getSerializableExtra("FoundWordsFromThirdScreen");
        foundWordsText.setText(fetchFoundWordsList.toString());

        // Possible words
        allValidWordsListText = (TextView) findViewById(R.id.tvListOfPossibleWordsID);
        fetchValidWordsList = new ArrayList<String>();
        fetchValidWordsList = (ArrayList<String>) getIntent().getSerializableExtra("ValidWordsFromThirdScreen");
        allValidWordsListText.setText(fetchValidWordsList.toString());
        allValidWordsListText.setMovementMethod(new ScrollingMovementMethod());

        /*
        *  // Possible words
        allWordsDispTextView = (TextView) findViewById(R.id.textViewAllPossibleWords);
        allWordsArrayList = new ArrayList<String>();
        allWordsArrayList = (ArrayList<String>) getIntent().getSerializableExtra("AllPossibleWords");
        allWordsDispTextView.setText(allWordsArrayList.toString());
        allWordsDispTextView.setMovementMethod(new ScrollingMovementMethod());
        for (int j = 0; j < allWordsArrayList.size(); j++) {
            Log.d("ValidWords", allWordsArrayList.get(j));
        }*/

        // check is player's score qualifies for new high score
        System.out.println(" ** SCORE IS:   " + roundScore + " qualifies: " + listOfHighScores.checkNewHighScore(difficulty, roundScore));
        hasHighScore = listOfHighScores.checkNewHighScore(difficulty, roundScore);
        System.out.println(" ** HAS HIGH SCORE?  " + hasHighScore);


        // Show Text input, and submit button if player qualifies for new high score
        playerName = (EditText) findViewById(R.id.editText_Name);

        if(!hasHighScore) { // false, hide high score notification message, input field and submit button
            highscore_notification = (TextView) findViewById(R.id.textView_highScoreMsg);
            highscore_notification.setVisibility(View.INVISIBLE);

            playerName.setVisibility(View.INVISIBLE);

            submitNameButton = (Button) findViewById(R.id.btnSubmitNameID);
            submitNameButton.setVisibility(View.INVISIBLE);
        }
        else {  // player has high score, hide the next game button
            nextGameButton = (Button) findViewById(btnNextRoundID);
            nextGameButton.setVisibility(View.INVISIBLE);
        }

        // Button Listeners
        addListenerOnButton();
    }


    public void addListenerOnButton() {
        final Context context = this;
        nextGameButton = (Button) findViewById(btnNextRoundID);
        submitNameButton = (Button) findViewById(R.id.btnSubmitNameID);

        nextGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        submitNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                addPlayerToHighScores();  // add their name to high scores
                // send player to main screen
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    /**
     * addPlayerToHighScores():  Get's player's name from text box and Adds the player's name and score to the high scores list
     * */
    private void addPlayerToHighScores() {
        String name = playerName.getText().toString();
        listOfHighScores.addHighScore(difficulty, roundScore, name);
    }

    /**
     * onBackPressed() -- do nothing,
     * disable to users ability to go back to previous game results or screens
     * */
    @Override
    public void onBackPressed() {
    }

}