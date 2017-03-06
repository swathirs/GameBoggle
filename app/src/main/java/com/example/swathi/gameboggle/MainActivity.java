package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button singlePlayerButton, multiPlayerNormalButton, helpButton, highScoresButton, multiPlayerCutThroatButton;

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

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void addListenerOnButton() {

        final Context context = this;

        singlePlayerButton = (Button) findViewById(R.id.buttonSinglePlayerId);
        multiPlayerNormalButton = (Button) findViewById(R.id.buttonMultiPlayerNormalId);
        multiPlayerCutThroatButton = (Button) findViewById(R.id.buttonCutThroatMode);
        helpButton = (Button) findViewById(R.id.buttonHelpId);
        highScoresButton = (Button) findViewById(R.id.buttonHighScoresId);

        singlePlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, SecondScreen.class);
                startActivity(intent);
            }

        });
        multiPlayerNormalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MultiplayerNormal.class);
                startActivity(intent);
            }

        });
        multiPlayerCutThroatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MultiplayerNormal.class);
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

