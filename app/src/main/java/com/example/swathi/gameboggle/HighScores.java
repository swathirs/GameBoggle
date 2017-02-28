package com.example.swathi.gameboggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by siggy on 2/6/17.
 */

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
        
    }

    public void updateScores(View view)
    {

    }

}