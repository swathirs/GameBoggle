package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button singlePlayerButton, multiPlayerButton, helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        singlePlayerButton = (Button) findViewById(R.id.buttonSinglePlayerId);
        multiPlayerButton = (Button) findViewById(R.id.buttonMultiPlayerId);
        helpButton = (Button) findViewById(R.id.buttonHelpId);

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

                Intent intent = new Intent(context, MainActivity.class);
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
    }
}
