package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

