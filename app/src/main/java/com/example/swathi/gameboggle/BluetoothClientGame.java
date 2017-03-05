package com.example.swathi.gameboggle;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class BluetoothClientGame extends Activity {

    //bluetooth stuff
    public static int REQUEST_BLUETOOTH = 1;
    BluetoothAdapter BTAdapter;
    BluetoothConnectManager clientConnectManager;

    //gameplay stuff
    GridLayout letterGrid;
    Button btnBackToMenu;
    Button btnDone;
    ListView mainListView;
    TextView player1ScoreTextView;
    TextView player2ScoreTextView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList;
    ArrayList hostWordList;
    HashMap<Integer, Integer> scoreMap;
    Board board = null;
    TextView currentWordText;
    ArrayList<String> letters;
    TextView timerText;
    ArrayList<Integer> buttonsClicked;
    AlertDialog.Builder usernameBuilder;
    long timeBlinkInMilliSeconds = 60 * 1000;
    Integer currentScore=0;
    protected static Activity BluetoothClientGameActivity;
    Intent gameFinishIntent;
    boolean gameDone = false;
    String gameType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_client_game);
        BluetoothClientGameActivity = this;

        initBluetooth();

    }
    private void initBluetooth(){
        //  currentWordText=(TextView)findViewById(R.id.txtCurrentWord);
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);

        }

    }



}


