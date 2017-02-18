package com.example.swathi.gameboggle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;


public class ThirdScreen extends AppCompatActivity {
    // the third screen holds the boggle game board
    //MY CHANGES
    Board board = null;//new Board(getApplicationContext());
    //END MY CHANGES
    ArrayList<String> letters;
    Button currButton;


    // Fields specific for the shake detector feature
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        board = new Board(getApplicationContext());

        // fields used by SHAKE DETECTOR
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  //get sensor management service, cast as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //get accelerometer from the sensor manager

        // make new SHAKE DETECTOR object and initialize with an onShakeListener
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {

                //TODO   add code here...
                //TODO   generate the board on shake...
                board.genBoardArrangement();
                letters = board.getSquares();

                currButton = (Button)findViewById(R.id.button1);
                currButton.setText(letters.get(0));
                currButton = (Button)findViewById(R.id.button2);
                currButton.setText(letters.get(1));
                currButton = (Button)findViewById(R.id.button3);
                currButton.setText(letters.get(2));
                currButton = (Button)findViewById(R.id.button4);
                currButton.setText(letters.get(3));
                currButton = (Button)findViewById(R.id.button5);
                currButton.setText(letters.get(4));
                currButton = (Button)findViewById(R.id.button6);
                currButton.setText(letters.get(5));
                currButton = (Button)findViewById(R.id.button7);
                currButton.setText(letters.get(6));
                currButton = (Button)findViewById(R.id.button8);
                currButton.setText(letters.get(7));
                currButton = (Button)findViewById(R.id.button9);
                currButton.setText(letters.get(8));
                currButton = (Button)findViewById(R.id.button10);
                currButton.setText(letters.get(9));
                currButton = (Button)findViewById(R.id.button11);
                currButton.setText(letters.get(10));
                currButton = (Button)findViewById(R.id.button12);
                currButton.setText(letters.get(11));
                currButton = (Button)findViewById(R.id.button13);
                currButton.setText(letters.get(12));
                currButton = (Button)findViewById(R.id.button14);
                currButton.setText(letters.get(13));
                currButton = (Button)findViewById(R.id.button15);
                currButton.setText(letters.get(14));
                currButton = (Button)findViewById(R.id.button16);
                currButton.setText(letters.get(15));

                Log.d("ShakeDetector", "Shake Detected!");
            }
        });

    }


    @Override  //onResume from Activity class
    public void onResume() {
        super.onResume();

        // registers the SHAKE DETECTOR event with the System Sensor manager...
        //    ...need to register (onResume) and unregister (onPause) to stop listening to this system service

        // args: sensorEventListener, Sensor, Rate for how often to receive sensor - sensor_delay constant for UIs  )
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }


    @Override  //onPause from Activity class
    public void onPause() {
        super.onPause();
        // ...need unregister SHAKE DETECTOR to stop listening to System Sensor when app is paused
        mSensorManager.unregisterListener(mShakeDetector);

    }


}
