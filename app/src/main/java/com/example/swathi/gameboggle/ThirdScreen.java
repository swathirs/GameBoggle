package com.example.swathi.gameboggle;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *  Third Screen holds the boggle game board and player game activity
 *
 */


public class ThirdScreen extends AppCompatActivity {

    // FIELDS:
    Board board = null; // new Board(getApplicationContext());
    ArrayList<String> letters;
    Button currButton;
    String currWord = "";
    TextView wordDisplay;

    public ArrayList<String> fetchFoundWords;
    public ArrayList<String> fetchValidWords;
    public ValidWords valid;

    int difficulty;
    int roundScore = 0;
    int roundNumber = 1;


    public TextView textView;
    public TextView roundScoreTextView;
    public ScoreList listOfHighScores; // ScoreList object, to check if player reaches a new high score

    private CountDownTimer countDownTimer;
    private final long startTime = 60 * 1000;
    private final long interval = 1 * 1000;

    // Fields specific for the shake detector feature
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


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

        dictReturn= board.checkWord(currWord);  // check if word is valid
        if(dictReturn){
                roundScore = roundScore + 1;  // increment score
                setScore(roundScore);         // set score
                checkHighScores(difficulty, roundScore);  // check if player reached a new high score, notify user if true
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


    /**
     * Below handles user selecting dice to select a word
     * */
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        letters = null;
        String difficultyString;
        //difficulty = 3; //TODO: get difficulty from screen 2



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        board = new Board(getApplicationContext());
        listOfHighScores = new ScoreList(getApplicationContext()); // ScoreList object, to check if player reaches a new high score


                // roundNumber = getIntent().getExtras().getInt("RoundNumber");
        roundScore= getIntent().getExtras().getInt("RoundScore"); //Obtained from SecondScreen
        difficultyString = getIntent().getExtras().getString("DifficultyStringValue");//Obtained from SecondScreen

       Log.d("DifficultyStringVal", difficultyString);

        switch (difficultyString)
        {
            case "Easy": difficulty = 1;
                break;
            case "Normal": difficulty = 2;
                break;
            case "Difficult": difficulty = 3;
                break;
            default: difficulty = 1;
                return;
        }
        Log.d("DifficultyIntVal:", String.valueOf(difficulty));

        // Timer
        textView = (TextView) findViewById(R.id.textView_Timer);
        countDownTimer = new CountDownTimerActivity(startTime, interval);
        textView.setText(textView.getText() + String.valueOf(startTime / 1000));
        textView.setVisibility(View.VISIBLE);

        // Score
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

    /**
     * checkHighScores():  - Checks the players score with the list of high scores.
     *                     - Notifies user if high new score is reached with a "Toast."
     * Input: int, players current game score
     * Output: none
     * */
    public void checkHighScores(int difficulty, int roundScore) {

        // Notify player if they've reached a new high score
        if(listOfHighScores.checkNewHighScore(difficulty, roundScore))
            Toast.makeText(getApplicationContext(), "New High Score!", Toast.LENGTH_SHORT).show();

    }

    public class CountDownTimerActivity extends CountDownTimer {

        public CountDownTimerActivity(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            textView.setText("Time's up!");
            textView.setTextColor(Color.RED);

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent i = new Intent(ThirdScreen.this, ScoreScreen.class);
                    i.putExtra("RoundScoreFromThirdScreen", roundScore);  // players score

                    fetchFoundWords = new ArrayList<String>();
                    fetchFoundWords = board.foundWords();
                    i.putExtra("FoundWordsFromThirdScreen", fetchFoundWords);

                    fetchValidWords = new ArrayList<String>();
                    fetchValidWords = board.validWords();
                    i.putExtra("ValidWordsFromThirdScreen", fetchValidWords);

                    i.putExtra("difficultyFromThirdScreen", difficulty);

                    for (int j =0; j < fetchFoundWords.size(); j++) {
                        Log.d("FoundWords", fetchFoundWords.get(j));
                    }
                    for (int j =0; j < fetchValidWords.size(); j++) {
                        Log.d("ValidWords", fetchValidWords.get(j));
                    }

                    startActivity(i);

                    finish();
                }
            }, 0);  // delay for x milliseconds, i.e. 5000 = 5 sec
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
