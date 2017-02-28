package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;


@RunWith(AndroidJUnit4.class)
public class Instrumented_ValidWordsTest {
    // ValidWords Class Fields:
    //    private int difficulty;
    //    private ArrayList<WordNode> validList[];
    //
    //    //These variables are used in finding the list of valid words
    //    private String tempBoard[][];
    //    private boolean visited[][];
    //    private int total;
    //    private int maxLength; //This variable is used to limit words to a specified length.  not meant for final version
    //
    //    //Dictionary
    //    private Dictionary dictionary;

    // Constructor input:   ArrayList<String> squares, int difficulty, Dictionary dictionary


    private Context appContext;
    private Dictionary aDictionary;
    private DiceController roller_good;
    private DiceController roller_bad;
    private ValidWords aValidWords;


    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();  // context
        aDictionary = new Dictionary(appContext);                 // a dictionary

        roller_good = new DiceController();  // dice controller get squares
        ArrayList<String> squares_good = roller_good.genBoard();  // squares

        ArrayList<String> squares_bad = create_bad_squares();


        // Create ValidWords Object:  squares, int difficulty, dictionary
        aValidWords = new ValidWords(squares_good, 1, aDictionary);
    }


    /**
     * create_bad_squares(): creates an array of squares with only one word,
     * this is an invalid boggle board.
     * Returns ArrayList
     */
    public ArrayList<String> create_bad_squares() {
        //roller_bad = new DiceController();  // dice controller for Bad_Board_Test

        ArrayList<String> temp = new ArrayList<String>();
        // add word "and"
        temp.add("a");
        temp.add("n");
        temp.add("d");
        // add "z" to rest of the board
        for(int i = 0; i < 9; i++) {
            temp.add("z");
        }

        System.out.print("\n\n bad squares: " + temp + "\n");

        return temp;
    }


    // tests for Valid Word?
    @Test
    public void ex_validWord() {

        //        assertFalse(aDictionary.isPrefix("zitheristsz"));
    }


} //end class


// Notes: TODO remove...

// private Looper mLooper = null;

// ... in @Bbefore
//         // initializeMessageLooper();  //start looper thread


//    /** Initializes the message looper receive the callback messages. */
//    private void initializeMessageLooper() {
//        // final ConditionVariable startDone = new ConditionVariable();
//
//        new Thread() {
//            @Override
//            public void run() {
//                // Set up a looper to be used
//                Looper.prepare();
//
//                // Save the looper so that we can terminate this thread
//                // after we are done with it.
//                mLooper = Looper.myLooper();
//
//                //mCamera = Camera.open();
//                //startDone.open();
//                Looper.loop();  // Blocks forever until Looper.quit() is called.
//            }
//        }.start();
//        if (!startDone.block(WAIT_FOR_COMMAND_TO_COMPLETE)) {
//            fail("initializeMessageLooper: start timeout");
//        }
//    }



    /*
    *  Terminates the message looper thread.
    */
//    @After
//    private void terminateMessageLooper() throws Exception {
//        mLooper.quit();
// Looper.quit() is asynchronous. The looper may still has some
// preview callbacks in the queue after quit is called. The preview
// callback still uses the camera object (setHasPreviewCallback).

// After camera is released, RuntimeException will be thrown from
// the method. So we need to join the looper thread here.
//        mLooper.getThread().join();
//mCamera.release();
//    }