package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondScreen extends AppCompatActivity {
    Button submitButton;
    private RadioGroup radioDifficultGroup;
    private RadioButton radioDifficultyModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        addListenerOnButton();

    }

    /** onCreateOptionsMenu: Creates the option menu at top of screen */
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** onOptionsItemSelected: Adds click event to option menu items */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context = this;
        Intent intent;
        switch(item.getItemId()) {
            case R.id.action_highScores:
                intent = new Intent(context, HighScores.class);
                startActivity(intent);
                break;
            case R.id.action_helpInstructions:
                intent = new Intent(context, HelpScreen.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    public void addListenerOnButton() {

        final int roundScore = 0;
        final Context context = this;

        radioDifficultGroup = (RadioGroup) findViewById(R.id.radioGroupDifficultyLevelID);

        submitButton = (Button) findViewById(R.id.buttonSubmitSecScreenId);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String selectedRadioValue = ((RadioButton)findViewById
                        (radioDifficultGroup.getCheckedRadioButtonId() )).getText().toString();

                Intent intent = new Intent(context, ThirdScreen.class);
                intent.putExtra("DifficultyStringValue", selectedRadioValue);
                intent.putExtra("RoundScore", roundScore );
                startActivity(intent);
            }

        });
    }
}

