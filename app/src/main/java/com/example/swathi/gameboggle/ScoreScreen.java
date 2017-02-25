package com.example.swathi.gameboggle;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreScreen extends AppCompatActivity {

    public ValidWords valid;
    public Board board;
    public TextView foundWordsText, allValidWordsListText;
    public ArrayList<String> fetchFoundWordsList, fetchValidWordsList;
    int roundScore = 0;
    public TextView roundScoreDisp;
    Button nextGameButton, endGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        roundScore = getIntent().getExtras().getInt("RoundScoreFromThirdScreen");//Obtained from ThirdScreen
        roundScoreDisp = (TextView) findViewById(R.id.tvScoreForThisRoundID);
        roundScoreDisp.setText(Integer.toString(roundScore));

        foundWordsText = (TextView)findViewById(R.id.textViewFoundWordsID);
        fetchFoundWordsList = new ArrayList<String>();
        fetchFoundWordsList = (ArrayList<String>) getIntent().getSerializableExtra("FoundWordsFromThirdScreen");
        foundWordsText.setText(fetchFoundWordsList.toString());

        allValidWordsListText = (TextView)findViewById(R.id. tvListOfPossibleWordsID);
        fetchValidWordsList = new ArrayList<String>();
        fetchValidWordsList = (ArrayList<String>) getIntent().getSerializableExtra("ValidWordsFromThirdScreen");
         allValidWordsListText.setText(fetchValidWordsList.toString());

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        nextGameButton = (Button) findViewById(R.id.btnNextRoundID);
        endGameButton = (Button) findViewById(R.id.btnEndGameID);

       nextGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }

        });
        endGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ScoreScreen.this.exit_application();
            }

        });
    }
    private void exit_application() {

        ActivityCompat.finishAffinity(this);
        System.exit(0);
    }

}



      /*  fetchFoundWords = new ArrayList<String>();
       fetchFoundWords = valid.getFoundWords();
        for (int i =0; i< fetchFoundWords.size(); i++) {
            Log.d("TAG", fetchFoundWords.get(i));


        fetchFoundWords = new ArrayList<String>();
        fetchFoundWords = board.foundWords();
        }*/