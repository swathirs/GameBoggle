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

    /**
     * onBackPressed() -- do nothing,
     * disable to users ability to go back to previous game results or screens
     * */
    @Override
    public void onBackPressed() {
    }



    public void addListenerOnButton() {

        final Context context = this;

        singlePlayerButton = (Button) findViewById(R.id.buttonSinglePlayerId);
        multiPlayerButton = (Button) findViewById(R.id.buttonMultiPlayerModeID);

        helpButton = (Button) findViewById(R.id.buttonHelpId);
        highScoresButton = (Button) findViewById(R.id.buttonHighScoresId);

        singlePlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, SecondScreen.class);
                startActivity(intent);
            }

        });
        multiPlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, ChooseMultiplayerMode.class);
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

