package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class HelpScreen extends AppCompatActivity {

    TextView single_help, multi_help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        single_help = (TextView) findViewById(R.id.tvSingleHelp);
        multi_help = (TextView) findViewById(R.id.tvMultiHelp);

        multi_help.setVisibility(View.INVISIBLE);   // hide multi

        // allow views to scroll
        multi_help.setMovementMethod(new ScrollingMovementMethod());
        single_help.setMovementMethod(new ScrollingMovementMethod());
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
                // do nothing
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioSingleHelp:
                if (checked)
                    single_help.setVisibility(View.VISIBLE);    // show single
                    multi_help.setVisibility(View.INVISIBLE);   // hide multi

                break;
            case R.id.radioMultiHelp:
                if (checked)
                    single_help.setVisibility(View.INVISIBLE);  // hide single
                    multi_help.setVisibility(View.VISIBLE);     // show multi

                break;

        }
    }


}
