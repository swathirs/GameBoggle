package com.example.swathi.gameboggle;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class BluetoothHostGame extends Activity {
    //bluetooth stuff
    public static int REQUEST_BLUETOOTH = 1;
    BluetoothAdapter BTAdapter;
    BluetoothConnectManager hostConnectManager;

    //gameplay stuff
    CountDownTimer gameTimer;
    boolean wordsGenerated = false;
    GridLayout letterGrid;
    Button btnBackToMenu;
    Button btnDone;
    Button clearButton;
    ListView mainListView;
    TextView playerScoreTextView;
    TextView player2ScoreTextView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList;
    ArrayList clientWordList;
    HashMap<Integer, Integer> scoreMap;
    Board board = null;
    TextView currentWordText;
    ArrayList<String> letters;
    TextView timerText;
    ArrayList<Integer> buttonsClicked;
    AlertDialog.Builder usernameBuilder;
    long timeBlinkInMilliSeconds = 60 * 1000;
    Integer currentScore;
    protected static Activity BluetoothHostGameActivity;
    Intent gameFinishIntent;
    boolean gameDone = false;
    String gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_host_game);
        BluetoothHostGameActivity = this;
        gameType = getIntent().getExtras().getString("gameType");


        initBluetooth();

    }


    private void initBluetooth(){
      //  currentWordText=(TextView)findViewById(R.id.txtCurrentWord);
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);

        }
        //start listening
        new AcceptThread().start();
    }




    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = BTAdapter.listenUsingRfcommWithServiceRecord("cs554team4", UUID.fromString("28901242-e667-40eb-bf4d-af5b6555e712"));
            } catch (IOException e) { System.out.println("listening error:"+e.toString());}
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {

                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Connection with other player successful", Toast.LENGTH_SHORT).show();
                            currentWordText.setText("");
                        }
                    });
                    BluetoothConnectManager bluetoothConnectManager = new BluetoothConnectManager(socket);
                    hostConnectManager=bluetoothConnectManager;

                    //send board
                    bluetoothConnectManager.sendObject(board);
                    bluetoothConnectManager.sendObject(gameType);

                   /* runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new BluetoothListenerTask().execute();
                        }
                    }); */
                   /*Generating board code goes here*/


                    gameTimer.start();

                    try {
                        mmServerSocket.close();
                    }catch (IOException e) {
                        break;
                    }
                }else{
                    currentWordText.setText("ERROR: Bluetooth server socket is null");
                }

            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }
}
