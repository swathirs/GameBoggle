package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.os.Looper; // LOOPER
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;


@RunWith(AndroidJUnit4.class)
public class Instrumented_ValidWordsTest {

    private Context appContext;

    private Dictionary aDictionary;
    private DiceController roller;
    private ValidWords aValidWords;

    private Looper mLooper = null;



    /** Initializes the message looper receive the callback messages. */
    private void initializeMessageLooper() {
        // final ConditionVariable startDone = new ConditionVariable();

        new Thread() {
            @Override
            public void run() {
                // Set up a looper to be used
                Looper.prepare();

                // Save the looper so that we can terminate this thread
                // after we are done with it.
                mLooper = Looper.myLooper();

                //mCamera = Camera.open();
                //startDone.open();

                Looper.loop();  // Blocks forever until Looper.quit() is called.

            }
        }.start();
//        if (!startDone.block(WAIT_FOR_COMMAND_TO_COMPLETE)) {
//            fail("initializeMessageLooper: start timeout");
//        }
    }


    //   // Initializes ValidWords for a specific board
    //public ValidWords(ArrayList<String> squares, int difficulty, Dictionary dictionary) {


    @Before
    public void setUp() throws Exception {
        // initializeMessageLooper();  //start looper thread

        appContext = InstrumentationRegistry.getTargetContext();  // context
        aDictionary = new Dictionary(appContext);                 // a dictionary

        roller = new DiceController();      // dice controller get squares
        ArrayList<String> squares = roller.genBoard();  // squares

        // Create ValidWords Object:  squares, int difficulty, dictionary
        aValidWords = new ValidWords(squares, 1, aDictionary);
    }



    /*
    *  Terminates the message looper thread.
    */
    @After
    private void terminateMessageLooper() throws Exception {
//        mLooper.quit();
        // Looper.quit() is asynchronous. The looper may still has some
        // preview callbacks in the queue after quit is called. The preview
        // callback still uses the camera object (setHasPreviewCallback).

        // After camera is released, RuntimeException will be thrown from
        // the method. So we need to join the looper thread here.
//        mLooper.getThread().join();
        //mCamera.release();
    }





    // tests for Valid Word?
    @Test
    public void ex_validWord() {

        //        assertFalse(aDictionary.isPrefix("zitheristsz"));
    }






} //end class
