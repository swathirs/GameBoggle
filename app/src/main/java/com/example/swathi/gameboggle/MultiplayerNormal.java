package com.example.swathi.gameboggle;

import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;



public class MultiplayerNormal extends AppCompatActivity {

    private Set<BluetoothDevice> Devices;
    public UUID myUUID = UUID.fromString("dfd0cd44-fec4-11e6-bc64-92361f002671");
    public ArrayList list = new ArrayList();
    public BluetoothSocket writerClientSocket = null;
    public BluetoothSocket writerServerSocket = null;
    public int mode = 0 ;
    public static String device = "";

    public final int SUCCESS_CONNECT = 0;
    private final int SERVER_SUCCESS = 2;
    private final int MESSAGE_READ = 1;

    private RelativeLayout connectLayer;
    private RelativeLayout touchview;

    ArrayList<String> letters;

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, sub, un;



    public BluetoothAdapter MBT = BluetoothAdapter.getDefaultAdapter();

    Board board = null;

    TextView textView;
    String currWord = "";
    TextView wordDisplay;
    ArrayList<Button> pressedButtons;
    public TextView roundScoreTextView;
    int roundScore = 0;


    private CountDownTimer countDownTimer;
    private final long startTime = 60 * 1000;
    private final long interval = 1 * 1000;

    public void setButtons(boolean [] list){
        findViewById(R.id.button1).setClickable(list[0]);
        findViewById(R.id.button2).setClickable(list[1]);
        findViewById(R.id.button3).setClickable(list[2]);
        findViewById(R.id.button4).setClickable(list[3]);
        findViewById(R.id.button5).setClickable(list[4]);
        findViewById(R.id.button6).setClickable(list[5]);
        findViewById(R.id.button7).setClickable(list[6]);
        findViewById(R.id.button8).setClickable(list[7]);
        findViewById(R.id.button9).setClickable(list[8]);
        findViewById(R.id.button10).setClickable(list[9]);
        findViewById(R.id.button11).setClickable(list[10]);
        findViewById(R.id.button12).setClickable(list[11]);
        findViewById(R.id.button13).setClickable(list[12]);
        findViewById(R.id.button14).setClickable(list[13]);
        findViewById(R.id.button15).setClickable(list[14]);
        findViewById(R.id.button16).setClickable(list[15]);
    }

    public void disablePressed(ArrayList<Button> pressedButtons){
        Iterator i = pressedButtons.iterator();
        Button b;
        while(i.hasNext()){
            b = (Button) i.next();
            b.setClickable(false);
        }
    }

    public void pressSubmit(View view){

        boolean dictReturn;

        dictReturn= board.checkWord(currWord);  // check if word is valid
        if(dictReturn){
            roundScore = roundScore + 1;  // increment score
            setScore(roundScore);         // set score
        }
        currWord = "";
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setMovementMethod(new ScrollingMovementMethod());
        wordDisplay.setText(currWord);
        wordDisplay.setVisibility(View.VISIBLE);
        boolean [] list =
                {true, true, true, true,
                        true, true, true, true,
                        true, true, true, true,
                        true, true, true, true};
        setButtons(list);
        pressedButtons = new ArrayList<>();
        sub.setClickable(false);
        un.setClickable(false);
    }

    public void pressUndo(View view){
        if(currWord.length() > 1) {
            int digit = currWord.length();
            currWord = currWord.substring(0, digit - 2);
            pressedButtons.remove(digit - 1);
            Button prev = pressedButtons.get(digit - 2);
            pressedButtons.remove(digit - 2);
            prev.performClick();
            un.setClickable(false);
        }
        else if(currWord.length() == 1){
            currWord = "";
            wordDisplay = (TextView) findViewById(R.id.Entry);
            wordDisplay.setMovementMethod(new ScrollingMovementMethod());
            wordDisplay.setText(currWord);
            wordDisplay.setVisibility(View.VISIBLE);
            boolean [] list =
                    {true, true, true, true,
                            true, true, true, true,
                            true, true, true, true,
                            true, true, true, true};
            setButtons(list);
            pressedButtons = new ArrayList<>();
            sub.setClickable(false);
            un.setClickable(false);
        }
    }

    public void press1(View view){
        currWord = currWord + letters.get(0);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list = {false, true, false, false,
                true, true, false, false,
                false, false, false, false,
                false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button1));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);

        Log.d("Buttons debug", "some string");
    }

    public void press2(View view){
        currWord = currWord + letters.get(1);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list = {true, false, true, false,
                true, true, true, false,
                false, false, false, false,
                false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button2));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press3(View view){
        currWord = currWord + letters.get(2);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, true, false, true,
                        false, true, true, true,
                        false, false, false, false,
                        false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button3));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press4(View view){
        currWord = currWord + letters.get(3);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, true, false,
                        false, false, true, true,
                        false, false, false, false,
                        false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button4));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press5(View view){
        currWord = currWord + letters.get(4);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {true, true, false, false,
                        false, true, false, false,
                        true, true, false, false,
                        false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button5));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press6(View view){
        currWord = currWord + letters.get(5);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {true, true, true, false,
                        true, false, true, false,
                        true, true, true, false,
                        false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button6));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press7(View view){
        currWord = currWord + letters.get(6);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, true, true, true,
                        false, true, false, true,
                        false, true, true, true,
                        false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button7));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press8(View view){
        currWord = currWord + letters.get(7);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, true, true,
                        false, false, true, false,
                        false, false, true, true,
                        false, false, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button8));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press9(View view){
        currWord = currWord + letters.get(8);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        true, true, false, false,
                        false, true, false, false,
                        true, true, false, false,};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button9));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press10(View view){
        currWord = currWord + letters.get(9);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        true, true, true, false,
                        true, false, true, false,
                        true, true, true, false,
                };
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button10));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press11(View view){
        currWord = currWord + letters.get(10);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        false, true, true, true,
                        false, true, false, true,
                        false, true, true, true};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button11));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press12(View view){
        currWord = currWord + letters.get(11);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        false, false, true, true,
                        false, false, true, false,
                        false, false, true, true};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button12));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press13(View view){
        currWord = currWord + letters.get(12);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        false, false, false, false,
                        true, true, false, false,
                        false, true, false, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button13));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press14(View view){
        currWord = currWord + letters.get(13);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        false, false, false, false,
                        true, true, true, false,
                        true, false, true, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button14));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press15(View view){
        currWord = currWord + letters.get(14);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        false, false, false, false,
                        false, true, true, true,
                        false, true, false, true};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button15));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }

    public void press16(View view){
        currWord = currWord + letters.get(15);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
        boolean [] list =
                {false, false, false, false,
                        false, false, false, false,
                        false, false, true, true,
                        false, false, true, false};
        setButtons(list);
        pressedButtons.add((Button) findViewById(R.id.button16));
        disablePressed(pressedButtons);
        sub.setClickable(true);
        un.setClickable(true);
    }




    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            switch(msg.what) {

                case SUCCESS_CONNECT:
                    String cans ="1";

                    Toast.makeText(getApplicationContext(), "Connection has been established!", Toast.LENGTH_LONG).show();

                    ConnectedThread sendAnswerstoClient = new ConnectedThread(writerClientSocket);
                    sendAnswerstoClient.write(cans.getBytes());

                    break;
                case MESSAGE_READ:
                    sendObject reader = (sendObject)msg.obj;
                    String recv = reader.sendBytes;
                    Log.d("Debug", recv);

                    if(recv.contains("1") == true){
                        cans ="2";
                        board = new Board(getApplicationContext());
                        board.genBoardArrangement(1);
                        AcceptThread temp;
                        connectLayer.setVisibility(View.INVISIBLE);


                        letters = board.getSquares();
                        b1.setText(letters.get(0));
                        b2.setText(letters.get(1));
                        b3.setText(letters.get(2));
                        b4.setText(letters.get(3));
                        b5.setText(letters.get(4));
                        b6.setText(letters.get(5));
                        b7.setText(letters.get(6));
                        b8.setText(letters.get(7));
                        b9.setText(letters.get(8));
                        b10.setText(letters.get(9));
                        b11.setText(letters.get(10));
                        b12.setText(letters.get(11));
                        b13.setText(letters.get(12));
                        b14.setText(letters.get(13));
                        b15.setText(letters.get(14));
                        b16.setText(letters.get(15));

                        countDownTimer.start();




                        touchview.setVisibility(View.VISIBLE);

                        ArrayList<String> squares = board.getSquares();

                        for(int i = 0; i < 16; i++){
                            cans = cans.concat(squares.get(i));

                        }
                        cans = cans.concat("2");
                        Log.d("Debug2", cans);
                        Toast.makeText(getApplicationContext(), cans, Toast.LENGTH_LONG).show();
                        sendAnswerstoClient = new ConnectedThread(writerServerSocket);
                        sendAnswerstoClient.write(cans.getBytes());
                    }
                    if(recv.contains("2") == true){
                        String curr =" ";
                        int i = 1;
                        String temp = "";
                        connectLayer.setVisibility(View.INVISIBLE);
                        touchview.setVisibility(View.VISIBLE);

                        while(!curr.contains("2")){
                            curr = String.valueOf(recv.charAt(i));
                            temp = temp.concat(curr);
                            i++;

                        }
                        board = new Board(getApplicationContext());
                        board.genBoardArrangement(temp);

                        letters = board.getSquares();
                        b1.setText(letters.get(0));
                        b2.setText(letters.get(1));
                        b3.setText(letters.get(2));
                        b4.setText(letters.get(3));
                        b5.setText(letters.get(4));
                        b6.setText(letters.get(5));
                        b7.setText(letters.get(6));
                        b8.setText(letters.get(7));
                        b9.setText(letters.get(8));
                        b10.setText(letters.get(9));
                        b11.setText(letters.get(10));
                        b12.setText(letters.get(11));
                        b13.setText(letters.get(12));
                        b14.setText(letters.get(13));
                        b15.setText(letters.get(14));
                        b16.setText(letters.get(15));

                        countDownTimer.start();

                        Log.d("Debug","In message_read");
                        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();

                    }

                    break;

            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_normal);
        device = "";
        final Context context = this;

        pressedButtons = new ArrayList<Button>();



        connectLayer = (RelativeLayout)findViewById(R.id.RL_Connect);
        touchview = (RelativeLayout) findViewById(R.id.game);

        connectLayer.setVisibility(View.VISIBLE);
        touchview.setVisibility(View.INVISIBLE);


        final Button Search = (Button) findViewById(R.id.search_button);
        final ListView BTdevices = (ListView) findViewById(R.id.PairedList);
        final Button Host = (Button) findViewById(R.id.HostBtn);
        final Button letsPlayBtn = (Button) findViewById(R.id.Play);

        textView = (TextView) findViewById(R.id.textView_Timer);
        countDownTimer = new MultiplayerNormal.CountDownTimerActivity(startTime, interval);
        textView.setText(textView.getText() + String.valueOf(startTime / 1000));
        textView.setVisibility(View.VISIBLE);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        b10 = (Button) findViewById(R.id.button10);
        b11 = (Button) findViewById(R.id.button11);
        b12 = (Button) findViewById(R.id.button12);
        b13 = (Button) findViewById(R.id.button13);
        b14 = (Button) findViewById(R.id.button14);
        b15 = (Button) findViewById(R.id.button15);
        b16 = (Button) findViewById(R.id.button16);
        sub = (Button) findViewById(R.id.submitButton);
        un = (Button) findViewById(R.id.undo);

        roundScoreTextView = (TextView)findViewById(R.id.tvRoundScoreID);
        roundScoreTextView.setText(Integer.toString(roundScore));


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////
                if(!MBT.isEnabled()){
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn,0);
                     Toast.makeText(getApplicationContext(), "Turning your phone's Bluetooth on...", Toast.LENGTH_SHORT).show();
                }
                /////////////
                mode =1;
                Devices = MBT.getBondedDevices();

                for(BluetoothDevice bt : Devices){
                    // if(bt.getName().equals("Galaxy")){
                    //   ConnectThread connect = new ConnectThread(bt);
                    // connect.start();
                    //}

                    list.add(bt.getName());}
                Toast.makeText(getApplicationContext(),"Showing Paired Devices to Connect to..", Toast.LENGTH_SHORT).show();
                final ArrayAdapter adapter = new ArrayAdapter(MultiplayerNormal.this,android.R.layout.simple_list_item_1, list);
                BTdevices.setAdapter(adapter);

            }
        });

        BTdevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                device = BTdevices.getItemAtPosition(position).toString();
                Devices = MBT.getBondedDevices();
                for(BluetoothDevice bt : Devices){
                    if(bt.getName().equals(device)){
                        ConnectThread connect = new ConnectThread(bt);
                        connect.start();
                    }
                }
            }
        });

        Host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////////////////
                if(!MBT.isEnabled()){
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn,0);
                     Toast.makeText(getApplicationContext(), "Turning your phone's Bluetooth on...", Toast.LENGTH_SHORT).show();
                }
                /////////////

                mode = 0;
                AcceptThread accept = new AcceptThread();
                accept.start();

            }
        });





    }

    private void sendClientToMain(BluetoothSocket mmSocket) {
        writerClientSocket = mmSocket;
    }

    private void sendServerToMain(BluetoothSocket socket) {
        writerServerSocket = socket;
    }


    public void manageConnectedSocket(final BluetoothSocket socket) {
        ConnectedThread boss = new ConnectedThread(socket);
        boss.start();

    }
    //Connecting as a server
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;
        BluetoothSocket socket = null;
        UUID myUUID = UUID.fromString("dfd0cd44-fec4-11e6-bc64-92361f002671");
        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = MBT.listenUsingRfcommWithServiceRecord("Multiplayer", myUUID);
                Log.d("Debug", "In AcceptThread Constructor...");
                Toast.makeText(getApplicationContext(), "SERVER IS UP AND RUNNING...", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                Log.e("TAG", "Socket's listen() method failed", e); }
            mmServerSocket = tmp;
        }

        public void run() {
            //BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket= mmServerSocket.accept();
                    Log.d("Multiplayer debug", "Server socket successfully created!");
                }
                catch (IOException e) {
                    Log.e("TAG", "Socket's accept() method failed", e);
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    MultiplayerNormal.this.runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(getApplication(), "Connection has been established",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    sendServerToMain(socket);
                    manageConnectedSocket(socket);
                   mHandler.obtainMessage(SERVER_SUCCESS,socket).sendToTarget();
                    try {
                        mmServerSocket.close();

                    } catch (IOException e
                            ) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    } // End of "Connecting as a server"

   // Connecting as a client
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MyUUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(myUUID);
            } catch (IOException e) {
                Log.e("In ConnectThread: ", "Socket's create() method failed", e);
            }
            mmSocket = tmp;
        }
       public void run() {
           // Cancel discovery because it will slow down the connection
           MBT.cancelDiscovery();

           try {
               // Connect the device through the socket. This will block
               // until it succeeds or throws an exception
               mmSocket.connect();

               // ClientSocket = mmSocket;

           } catch (IOException connectException) {
               // Unable to connect; close the socket and get out
               try {
                   mmSocket.close();
               } catch (IOException closeException) {
                   Log.e("ConnectThread's run():" , "Could not close the client socket", closeException);
               }
               return;
           }

           // The connection attempt succeeded. Perform work associated with
           // the connection in a separate thread.
           sendClientToMain(mmSocket);
           manageConnectedSocket(mmSocket);
           mHandler.obtainMessage(SUCCESS_CONNECT,mmSocket).sendToTarget();
       }


           /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

    //End of "Connecting as a client"



    public class sendObject{

        String sendBytes;
        BluetoothSocket sendSocket;
        sendObject(String byteSrc, BluetoothSocket skcSrc)
        {
            sendBytes= byteSrc;
            sendSocket = skcSrc;
        }

    }
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(this);
        return b.toByteArray();
    }

    class ConnectedThread extends Thread {//never stops....running in background at all times
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        public void run() {
            final byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    String bufferStr = new String(buffer);
                    // Send the obtained bytes to the UI activity
                    sendObject temp = new sendObject(bufferStr,mmSocket);
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, temp).sendToTarget();


                } catch (IOException e) {
                    break;
                }
            }
        }


        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] MESSAGE_WRITE) {
            try {
                mmOutStream.write(MESSAGE_WRITE);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= (x2-25) && x >= (x1+25) && y <= (y2-25) && y >= (y1+25));
    }


    @Override  //onResume from Activity class
    public void onResume() {
        super.onResume();

        touchview.setOnTouchListener(new View.OnTouchListener() {

            private boolean isInside = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();

                TextView t1 = (TextView) findViewById(R.id.textView22);
                t1.setText(String.valueOf(x));
                TextView t2 = (TextView) findViewById(R.id.textView23);
                t2.setText(String.valueOf(y));

                for (int i = 0; i < touchview.getChildCount(); i++) {
                    View current = touchview.getChildAt(i);
                    if (current instanceof Button) {
                        Button b = (Button) current;

                        if (!isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                b.getBottom())) {
                            //b.getBackground().setState(defaultStates);
                        }

                        if (isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                b.getBottom())) {
                            //b.performClick();
                            //b.getBackground().setState(STATE_PRESSED);
                            if(b.equals(b1) && b1.isClickable()) press1(touchview);
                            else if(b.equals(b2) && b2.isClickable()) press2(touchview);
                            else if(b.equals(b3) && b3.isClickable()) press3(touchview);
                            else if(b.equals(b4) && b4.isClickable()) press4(touchview);
                            else if(b.equals(b5) && b5.isClickable()) press5(touchview);
                            else if(b.equals(b6) && b6.isClickable()) press6(touchview);
                            else if(b.equals(b7) && b7.isClickable()) press7(touchview);
                            else if(b.equals(b8) && b8.isClickable()) press8(touchview);
                            else if(b.equals(b9) && b9.isClickable()) press9(touchview);
                            else if(b.equals(b10) && b10.isClickable()) press10(touchview);
                            else if(b.equals(b11) && b11.isClickable()) press11(touchview);
                            else if(b.equals(b12) && b12.isClickable()) press12(touchview);
                            else if(b.equals(b13) && b13.isClickable()) press13(touchview);
                            else if(b.equals(b14) && b14.isClickable()) press14(touchview);
                            else if(b.equals(b15) && b15.isClickable()) press15(touchview);
                            else if(b.equals(b16) && b16.isClickable()) press16(touchview);
                            else if(b.equals(sub) && sub.isClickable()) pressSubmit(touchview);
                            else if(b.equals(un) && un.isClickable()) pressUndo(touchview);
                        }

                    }
                }
                return true;
            }

        });



    }


    public class CountDownTimerActivity extends CountDownTimer {

        public CountDownTimerActivity(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            textView.setText("Time's up!");

           // delay for x milliseconds, i.e. 5000 = 5 sec
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText("" + millisUntilFinished/1000);
            if(currWord.length() > 0) un.setClickable(true);
        }
    }

    void setScore(int score)
    {
        TextView scoretxt = (TextView) findViewById(R.id.tvRoundScoreID);
        scoretxt.setText(Integer.toString(score));

    }

}

