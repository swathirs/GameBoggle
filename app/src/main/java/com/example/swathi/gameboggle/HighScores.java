package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class HighScores extends AppCompatActivity {
    RadioButton ModeSingle, ModeMulti, Level1, Level2, Level3;
    TextView Name1, Name2, Name3, Name4, Name5;
    TextView Score1, Score2, Score3, Score4, Score5;
    ScoreList list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        list = new ScoreList(getApplicationContext());

        ModeSingle = (RadioButton) findViewById(R.id.radioButton);
        ModeMulti = (RadioButton) findViewById(R.id.radioButton2);
        Level1 = (RadioButton) findViewById(R.id.radioButton3);
        Level2 = (RadioButton) findViewById(R.id.radioButton4);
        Level3 = (RadioButton) findViewById(R.id.radioButton5);

        Name1 = (TextView) findViewById(R.id.textView8);
        Name2 = (TextView) findViewById(R.id.textView11);
        Name3 = (TextView) findViewById(R.id.textView14);
        Name4 = (TextView) findViewById(R.id.textView17);
        Name5 = (TextView) findViewById(R.id.textView20);

        Score1 = (TextView) findViewById(R.id.textView7);
        Score2 = (TextView) findViewById(R.id.textView10);
        Score3 = (TextView) findViewById(R.id.textView13);
        Score4 = (TextView) findViewById(R.id.textView16);
        Score5 = (TextView) findViewById(R.id.textView19);

        ModeSingle.setOnClickListener(updateScores);
        ModeMulti.setOnClickListener(updateScores);
        Level1.setOnClickListener(updateScores);
        Level2.setOnClickListener(updateScores);
        Level3.setOnClickListener(updateScores);

        ModeSingle.setChecked(true);
        Level1.setChecked(true);
        changeScores();
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
                // do nothing
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

    //In this function changes the game types when the mode is switched and also unchecks other buttons when one is checked
    View.OnClickListener updateScores = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == ModeSingle)
            {
                ModeMulti.setChecked(false);
                Level1.setText("Easy");
                Level2.setText("Normal");
                Level3.setText("Difficult");
            }
            else if (view == ModeMulti)
            {
                ModeSingle.setChecked(false);
                Level1.setText("Single Round Basic");
                Level2.setText("Multi Round Basic");
                Level3.setText("Cut Throat");
            }
            else if (view == Level1)
            {
                Level2.setChecked(false);
                Level3.setChecked(false);
            }
            else if (view == Level2)
            {
                Level1.setChecked(false);
                Level3.setChecked(false);
            }
            else
            {
                Level1.setChecked(false);
                Level2.setChecked(false);
            }
            changeScores();
        }
    };

    //This function will look at the current state of what is pressed and put up the appropriate names and scores
    public void changeScores()
    {
        int category = 0;
        int scores[];
        String names[];
        if (ModeMulti.isChecked())
        {
            if (Level1.isChecked())
                category = 4;
            else if (Level2.isChecked())
                category = 5;
            else
                category = 6;
        }
        else
        {
            if (Level1.isChecked())
                category = 1;
            else if (Level2.isChecked())
                category = 2;
            else
                category = 3;
        }
        scores = list.getScores(category);
        names = list.getNames(category);

        Name1.setText(names[0]);
        Name2.setText(names[1]);
        Name3.setText(names[2]);
        Name4.setText(names[3]);
        Name5.setText(names[4]);
        Score1.setText(Integer.toString(scores[0]));
        Score2.setText(Integer.toString(scores[1]));
        Score3.setText(Integer.toString(scores[2]));
        Score4.setText(Integer.toString(scores[3]));
        Score5.setText(Integer.toString(scores[4]));
    }
}