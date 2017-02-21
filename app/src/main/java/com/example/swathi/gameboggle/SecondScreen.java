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

public class SecondScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button submitButton;
    private RadioButton radioDifficultyButton;
    private RadioGroup radioDifficultyLevelGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        Spinner roundsSpinner = (Spinner) findViewById(R.id.spinnerRoundsId);
        roundsSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        List<String> roundsCategories = new ArrayList<String>();
        roundsCategories.add("1");
        roundsCategories.add("2");
        roundsCategories.add("3");
        roundsCategories.add("4");
        roundsCategories.add("5");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roundsCategories);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        roundsSpinner.setAdapter(dataAdapter2);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final Context context = this;
        radioDifficultyLevelGroup = (RadioGroup) findViewById(R.id.radioDifficultyLevelID);
        // On selecting a spinner item
        final String item = parent.getItemAtPosition(position).toString();
        submitButton = (Button) findViewById(R.id.buttonSubmitSecScreenId);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                int selectedId = radioDifficultyLevelGroup.getCheckedRadioButtonId();
                radioDifficultyButton = (RadioButton) findViewById(selectedId);

                Intent intent = new Intent(context, ThirdScreen.class);
                intent.putExtra("noOfRounds",item);
                intent.putExtra("difficultyLevel", String.valueOf(selectedId));

                startActivity(intent);
            }

        });


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

