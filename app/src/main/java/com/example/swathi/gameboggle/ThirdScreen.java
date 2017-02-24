package com.example.swathi.gameboggle;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import android.os.CountDownTimer;


public class ThirdScreen extends AppCompatActivity {
    // the third screen holds the boggle game board
    Board board = null;//new Board(getApplicationContext());
    ArrayList<String> letters;
    Button currButton;
    String currWord = "";
    TextView wordDisplay;

    public ArrayList<String> fetchFoundWords;
    public ValidWords valid;

    int difficulty;
    int roundScore = 0;
    int roundNumber = 1;


    public TextView textView;
    public TextView roundScoreTextView;


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

    public void pressSubmit(View view){

        boolean dictReturn;

        dictReturn= board.checkWord(currWord);  //TODO: find out why this line crashes
        if(dictReturn){


                roundScore = roundScore + 1;
                setScore(roundScore);
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
    }

    // Fields specific for the shake detector feature
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        letters = null;
        //difficulty = 3; //TODO: get difficulty from screen 2

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        board = new Board(getApplicationContext());

        roundNumber = getIntent().getExtras().getInt("RoundNumber");//Obtained from MainActivity
        roundScore= getIntent().getExtras().getInt("RoundScore");//Obtained from MainActivity
        difficulty= getIntent().getExtras().getInt("Difficulty");//Obtained from MainActivity

        textView = (TextView) findViewById(R.id.textView_Timer);
        countDownTimer = new CountDownTimerActivity(startTime, interval);
        textView.setText(textView.getText() + String.valueOf(startTime / 1000));
        textView.setVisibility(View.VISIBLE);

        roundScoreTextView = (TextView)findViewById(R.id.tvRoundScoreID);
        roundScoreTextView.setText(Integer.toString(roundScore));


        // fields used by SHAKE DETECTOR
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  //get sensor management service, cast as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //get accelerometer from the sensor manager

        // make new SHAKE DETECTOR object and initialize with an onShakeListener
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {

                //TODO   add code here...
                //TODO   generate the board on shake...
                countDownTimer.start();
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

    public class CountDownTimerActivity extends CountDownTimer {
        public CountDownTimerActivity(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            textView.setText("Time's up!");

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent i = new Intent(ThirdScreen.this, ScoreScreen.class);
                    i.putExtra("RoundScoreFromThirdScreen", roundScore);

                    startActivity(i);

                    finish();
                }
            }, 0);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText("" + millisUntilFinished/1000);
        }
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

    void setScore(int score)
    {
        TextView scoretxt = (TextView) findViewById(R.id.tvRoundScoreID);
        scoretxt.setText(Integer.toString(score));

    }





}
