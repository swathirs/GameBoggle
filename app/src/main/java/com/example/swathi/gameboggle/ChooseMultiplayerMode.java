package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseMultiplayerMode extends AppCompatActivity {
    Button multiPlayerNormalButton,  multiPlayerCutThroatButton;

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
        multiPlayerNormalButton = (Button) findViewById(R.id.buttonNormalMultiplayer);
        multiPlayerCutThroatButton = (Button) findViewById(R.id.buttonCutthroatMultiplayer);



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

                Intent intent = new Intent(context, CutThroatMultiplayer.class);
                startActivity(intent);
            }

        });


    }


}
