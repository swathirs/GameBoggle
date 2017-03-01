package com.example.swathi.gameboggle;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreScreen extends AppCompatActivity {

    // FIELDS
    public ValidWords valid;
    public Board board;
    public TextView foundWordsText, allValidWordsListText;
    public ArrayList<String> fetchFoundWordsList, fetchValidWordsList;
    int roundScore = 0;
    int difficulty;

    //private String playerName = "Alice";
    public ScoreList listOfHighScores; // ScoreList object, to check if player reaches a new high score

    public TextView roundScoreDisp;
    Button nextGameButton, submitNameButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        difficulty = getIntent().getExtras().getInt("difficultyFromThirdScreen"); // Obtain difficulty setting from ThirdScreen

        roundScore = getIntent().getExtras().getInt("RoundScoreFromThirdScreen");//Obtained from ThirdScreen
        roundScoreDisp = (TextView) findViewById(R.id.tvScoreForThisRoundID);
        roundScoreDisp.setText(Integer.toString(roundScore));

        foundWordsText = (TextView) findViewById(R.id.textViewFoundWordsID);
        fetchFoundWordsList = new ArrayList<String>();
        fetchFoundWordsList = (ArrayList<String>) getIntent().getSerializableExtra("FoundWordsFromThirdScreen");
        foundWordsText.setText(fetchFoundWordsList.toString());

        allValidWordsListText = (TextView) findViewById(R.id.tvListOfPossibleWordsID);
        fetchValidWordsList = new ArrayList<String>();
        fetchValidWordsList = (ArrayList<String>) getIntent().getSerializableExtra("ValidWordsFromThirdScreen");
        allValidWordsListText.setText(fetchValidWordsList.toString());
        addListenerOnButton();
    }


    public void addListenerOnButton() {

        final Context context = this;

        nextGameButton = (Button) findViewById(R.id.btnNextRoundID);
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
                addPlayerToHighScores();
            }
        });
    }


    /**
     * addPlayerToHighScores():  Get's player's name from text boc and Adds the player's name and score to the high scores list
     * */
    private void addPlayerToHighScores() {
        EditText playerName;

        playerName = (EditText) findViewById(R.id.editText_Name);
        System.out.println("Players name: " + playerName + "  difficulty: " + difficulty + "  score: " + roundScore);

        //listOfHighScores.addHighScore(difficulty, roundScore, playerName.toString());
    }

    private void exit_application() {

        ActivityCompat.finishAffinity(this);
        System.exit(0);
    }

}



//        endGameButton = (Button) findViewById(R.id.btnSubmitNameID);
//        endGameButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                ScoreScreen.this.exit_application();
//            }
//        });



      /*  fetchFoundWords = new ArrayList<String>();
       fetchFoundWords = valid.getFoundWords();
        for (int i =0; i< fetchFoundWords.size(); i++) {
            Log.d("TAG", fetchFoundWords.get(i));


        fetchFoundWords = new ArrayList<String>();
        fetchFoundWords = board.foundWords();
        }*/