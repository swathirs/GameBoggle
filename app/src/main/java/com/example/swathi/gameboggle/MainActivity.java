package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Button singlePlayerButton, multiPlayerButton, helpButton, highScoresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        final int roundNumber = 1;
        final int roundScore = 0;

        final Context context = this;

        singlePlayerButton = (Button) findViewById(R.id.buttonSinglePlayerId);
        multiPlayerButton = (Button) findViewById(R.id.buttonMultiPlayerId);
        helpButton = (Button) findViewById(R.id.buttonHelpId);
        highScoresButton = (Button) findViewById(R.id.buttonHighScoresId);

        singlePlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, ThirdScreen.class);
                intent.putExtra("RoundNumber", roundNumber );
                intent.putExtra("RoundScore", roundScore );
                startActivity(intent);
            }

        });
        multiPlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("RoundNumber", roundNumber );
                intent.putExtra("RoundScore", roundScore );
                startActivity(intent);
            }

        });
        helpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, HelpScreen.class);
                startActivity(intent);
            }

        });
        highScoresButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, HighScores.class);
                startActivity(intent);
            }

        });

    }
}

/*
  Button current_button= (Button) v;
        //current_button.setText("Pressed!");
        int Round = 1;
        int Score = 0;
        switch(v.getId()){

            case R.id.button_single:
                Intent singleIntent = new Intent(v.getContext(), SinglePlayer.class);
                singleIntent.putExtra("Round",Round);
                singleIntent.putExtra("Score",Score);
                startActivity(singleIntent);
                break;
            case R.id.button_double:
                Intent doubleIntent = new Intent(v.getContext(), SetUpServerClient.class);
                doubleIntent.putExtra("Round",Round);
                doubleIntent.putExtra("Score",Score);
                doubleIntent.putExtra("Mode",BBDoubleBasicMode);
                startActivity(doubleIntent);
                break;
            case R.id.button_doubleCT:
                //NumPlayers = 2;
                Intent doubleCTIntent = new Intent(v.getContext(), SetUpServerClient.class);
                doubleCTIntent.putExtra("Round",Round);
                doubleCTIntent.putExtra("Score",Score);
                doubleCTIntent.putExtra("Mode",BBDoubleCutMode);
                startActivity(doubleCTIntent);
                break;
            default:

        }
*/
