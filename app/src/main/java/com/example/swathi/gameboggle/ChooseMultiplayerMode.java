package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseMultiplayerMode extends AppCompatActivity {
    Button multiRoundBasic,  multiPlayerCutThroatButton, singleRoundBasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_multiplayer_mode);
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

        multiRoundBasic = (Button) findViewById(R.id.buttonMultiRoundBasic);
        multiPlayerCutThroatButton = (Button) findViewById(R.id.buttonCutthroatMultiplayer);
        singleRoundBasic = (Button) findViewById(R.id.buttonSingleRoundBasic);



        multiRoundBasic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int mode = 0;
                int rounds = 1;
                Intent intent = new Intent(context, MultiplayerNormal.class);
                intent.putExtra("ModeValue", mode);
                intent.putExtra("RoundValue", rounds);
                startActivity(intent);
            }

        });
        multiPlayerCutThroatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int mode = 1;
                int rounds = 0;
                Intent intent = new Intent(context, MultiplayerNormal.class);
                intent.putExtra("ModeValue", mode);
                intent.putExtra("RoundValue", rounds);
                startActivity(intent);
            }

        });

        singleRoundBasic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int mode = 0;
                int rounds = 0;
                Intent intent = new Intent(context, MultiplayerNormal.class);
                intent.putExtra("ModeValue", mode);
                intent.putExtra("RoundValue", rounds);
                startActivity(intent);
            }

        });


    }


}
