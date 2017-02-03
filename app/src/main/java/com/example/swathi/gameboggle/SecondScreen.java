package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        addListenerOnButton();

        // Spinner element
        Spinner difficultyLevelSpinner = (Spinner) findViewById(R.id.spinnerDifficultyId);
        Spinner roundsSpinner = (Spinner) findViewById(R.id.spinnerRoundsId);

        // Spinner click listener
        difficultyLevelSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        roundsSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Easy");
        categories.add("Normal");
        categories.add("Difficult");

        List<String> roundsCategories = new ArrayList<String>();
        roundsCategories.add("1");
        roundsCategories.add("2");
        roundsCategories.add("3");
        roundsCategories.add("4");
        roundsCategories.add("5");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roundsCategories);

        // Drop down layout style - l
        // ist view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        difficultyLevelSpinner.setAdapter(dataAdapter1);
        roundsSpinner.setAdapter(dataAdapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void addListenerOnButton() {

        final Context context = this;

        submitButton = (Button) findViewById(R.id.buttonSubmitSecScreenId);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, ThirdScreen.class);
                startActivity(intent);
            }

        });
    }
}

