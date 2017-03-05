package com.example.swathi.gameboggle;

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
import android.util.Log;
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



    public BluetoothAdapter MBT = BluetoothAdapter.getDefaultAdapter();

    Board board = null;
    ThirdScreen thirdScreen = null;


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

                        while(!curr.contains("2")){
                            curr = String.valueOf(recv.charAt(i));
                            temp = temp.concat(curr);
                            i++;

                        }
                        board = new Board(getApplicationContext());
                        board.genBoardArrangement(temp);
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

        final RelativeLayout connectLayer = (RelativeLayout) findViewById(R.id.RL_Connect);
        connectLayer.setVisibility(View.VISIBLE);

        final Button Search = (Button) findViewById(R.id.search_button);
        final ListView BTdevices = (ListView) findViewById(R.id.PairedList);
        final Button Host = (Button) findViewById(R.id.HostBtn);
        final Button letsPlayBtn = (Button) findViewById(R.id.Play);



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

}

