package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;


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
    private ValidWords aValidBoard;
    private ValidWords invalidBoard;


    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();  // context
        aDictionary = new Dictionary(appContext);                 // a dictionary

        roller_good = new DiceController();  // dice controller get squares
        ArrayList<String> squares_good = roller_good.genBoard();  // squares


        // Create ValidWords Object:  squares, int difficulty, dictionary
        aValidBoard = new ValidWords(squares_good, 1, aDictionary);
    }


    /**
     * create_bad_squares(): a helper function that creates an array of squares
     * with only one valid word, this is an invalid boggle board.
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
        for (int i = 0; i < 13; i++) {
            temp.add("z");
        }

        System.out.print("\n\n bad squares: " + temp + "\n");

        return temp;
    }


    /**
     * Test checkValidBoard(): creates squares with just one valid word and checks that
     * checkValidBoard returns false
     */
    @Test
    public void checkValidBoard_wBadSquares_assertFalse() {
        ArrayList<String> squares_bad = create_bad_squares();  //create bad squares

        invalidBoard = new ValidWords(squares_bad, 1, aDictionary);
        assertFalse(invalidBoard.checkValidBoard());             // false with easy level

        invalidBoard = new ValidWords(squares_bad, 2, aDictionary);
        assertFalse(invalidBoard.checkValidBoard());             // false with normal level

        invalidBoard = new ValidWords(squares_bad, 3, aDictionary);
        assertFalse(invalidBoard.checkValidBoard());             // false with difficult level
    }

    /**
     * Test findAllWords(): creates squares with just one valid word and checks that
     * getValidWords() will return a list of size one.  i.e. FindAllWords should only find one word
     */
    @Test
    public void findAllWords_wBadSquares_assertSizeOfOne() {
        ArrayList<String> squares_bad = create_bad_squares();  // create bad squares
        invalidBoard = new ValidWords(squares_bad, 1, aDictionary);
        ArrayList<String> temp = invalidBoard.getValidWords();  // get list of valid words
        assertTrue(temp.size() == 1);  // verify size of list is 1
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