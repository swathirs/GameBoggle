package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class Instrumented_BoardTest {

    // Board Fields:
    //    private DiceController roller;
    //    private ArrayList<String> squares;
    //    private Context context;
    //    private Dictionary dictionary;
    //    private ValidWords valid;


    private Board aBoard;
    private Context appContext;


    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        aBoard = new Board(appContext);
    }

    // TESTS 1: genBoardArrangement
    // 1 = easy, 2 = normal, 3 = difficult

    @Test
    public void example() {


    }



    // TESTS 2: getSquares


    // TESTS 3: checkWord



}