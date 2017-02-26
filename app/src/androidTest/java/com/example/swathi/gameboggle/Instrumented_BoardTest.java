package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.ArrayList;


@RunWith(AndroidJUnit4.class)
public class Instrumented_BoardTest  {

    // Board Fields:
    //    private DiceController roller;
    //    private ArrayList<String> squares;
    //    private Context context;
    //    private Dictionary dictionary;
    //    private ValidWords valid;


    private Board aBoard;
    private Context appContext;
    private int easy = 1;
    private int normal = 2;
    private int difficult = 3;

    private ArrayList<String> validWords;



    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        aBoard = new Board(appContext);
    }

    // TESTS 1: Testing difficulty of a generated board
    // genBoardArrangement
    // 1 = easy; >= 2, 2 = normal: >= 5, 3 = difficult: >=7
    //  A valid grid of dice must contain at least two valid words in
    //      level easy, five valid words in level normal, and seven
    //      words in level difficult.


    /** easy_genBoard()
     * generates a board on easy setting and checks that the validWord
     * length is long enough: len >= 2 */
//    @Test
//    public void easy_genBoard() {
//
//        aBoard.genBoardArrangement(easy);
//        validWords = new ArrayList<String>();
//        validWords = aBoard.validWords();
//        // assert that the validWords is >= 2
//        assertTrue( validWords.size() >= 2 );
//    }


    /** normal_genBoard()
     * generates a board on easy setting and checks that the validWord
     * length is long enough: len >= 2 */
//    @Test
//    public void normal_genBoard() {
//
//        aBoard.genBoardArrangement(normal);
//
//        validWordsList = new ArrayList<String>();
//        validWordsList = aBoard.validWords();
//
//        // assert that the validWordsList is > 1
//        assertTrue( validWordsList.size() >= 5 );
//    }


    /** difficult_genBoard()
     * generates a board on easy setting and checks that the validWord
     * length is long enough: len >= 2 */
//    @Test
//    public void difficult_genBoard() {
//
//        aBoard.genBoardArrangement(difficult);
//
//        validWordsList = new ArrayList<String>();
//        validWordsList = aBoard.validWords();
//
//        // assert that the validWordsList is >= 7
//        assertTrue( validWordsList.size() >= 7 );
//    }




    // TESTS 2: getSquares


    // TESTS 3: checkWord



}