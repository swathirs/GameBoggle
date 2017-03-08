package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HelpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
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


}
