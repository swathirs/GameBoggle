package com.example.swathi.gameboggle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ThirdScreen extends AppCompatActivity {
    // the third screen holds the boggle game board
    Board board = new Board();
    ArrayList<String> letters;
    Button currButton;
    String currWord = "";
    TextView wordDisplay;
    int difficulty;

    public void pressSubmit(View view){
        //board.checkWord(currWord);  //TODO: find out why this line crashes
        currWord = "";
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press1(View view){
        currWord = currWord + letters.get(0);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press2(View view){
        currWord = currWord + letters.get(1);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press3(View view){
        currWord = currWord + letters.get(2);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press4(View view){
        currWord = currWord + letters.get(3);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press5(View view){
        currWord = currWord + letters.get(4);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press6(View view){
        currWord = currWord + letters.get(5);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press7(View view){
        currWord = currWord + letters.get(6);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press8(View view){
        currWord = currWord + letters.get(7);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press9(View view){
        currWord = currWord + letters.get(8);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press10(View view){
        currWord = currWord + letters.get(9);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press11(View view){
        currWord = currWord + letters.get(10);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press12(View view){
        currWord = currWord + letters.get(11);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press13(View view){
        currWord = currWord + letters.get(12);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press14(View view){
        currWord = currWord + letters.get(13);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press15(View view){
        currWord = currWord + letters.get(14);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    public void press16(View view){
        currWord = currWord + letters.get(15);
        wordDisplay = (TextView) findViewById(R.id.Entry);
        wordDisplay.setText(currWord);
    }

    // Fields specific for the shake detector feature
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        letters = null;
        difficulty = 3; //TODO: get difficulty from screen 2
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        // fields used by SHAKE DETECTOR
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  //get sensor management service, cast as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //get accelerometer from the sensor manager

        // make new SHAKE DETECTOR object and initialize with an onShakeListener
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {

                //TODO   add code here...
                //TODO   generate the board on shake...
                if(letters == null){
                    board.genBoardArrangement(difficulty);
                    letters = board.getSquares();

                    currButton = (Button) findViewById(R.id.button1);
                    currButton.setText(letters.get(0));
                    currButton = (Button) findViewById(R.id.button2);
                    currButton.setText(letters.get(1));
                    currButton = (Button) findViewById(R.id.button3);
                    currButton.setText(letters.get(2));
                    currButton = (Button) findViewById(R.id.button4);
                    currButton.setText(letters.get(3));
                    currButton = (Button) findViewById(R.id.button5);
                    currButton.setText(letters.get(4));
                    currButton = (Button) findViewById(R.id.button6);
                    currButton.setText(letters.get(5));
                    currButton = (Button) findViewById(R.id.button7);
                    currButton.setText(letters.get(6));
                    currButton = (Button) findViewById(R.id.button8);
                    currButton.setText(letters.get(7));
                    currButton = (Button) findViewById(R.id.button9);
                    currButton.setText(letters.get(8));
                    currButton = (Button) findViewById(R.id.button10);
                    currButton.setText(letters.get(9));
                    currButton = (Button) findViewById(R.id.button11);
                    currButton.setText(letters.get(10));
                    currButton = (Button) findViewById(R.id.button12);
                    currButton.setText(letters.get(11));
                    currButton = (Button) findViewById(R.id.button13);
                    currButton.setText(letters.get(12));
                    currButton = (Button) findViewById(R.id.button14);
                    currButton.setText(letters.get(13));
                    currButton = (Button) findViewById(R.id.button15);
                    currButton.setText(letters.get(14));
                    currButton = (Button) findViewById(R.id.button16);
                    currButton.setText(letters.get(15));
                }

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
