package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



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

    private ArrayList<String> validWordsList;
    private ArrayList<String> foundWordsList;
    private ArrayList<String> aSquares;




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
    @Test
    public void easy_genBoard() {
        aBoard.genBoardArrangement(easy);
        validWordsList = new ArrayList<String>();
        validWordsList = aBoard.validWords();

        // assert that the validWords is >= 2
        assertTrue( validWordsList.size() >= 2 );
    }


    /** normal_genBoard()
     * generates a board on easy setting and checks that the validWord
     * length is long enough: len >= 2 */
    @Test
    public void normal_genBoard() {

        aBoard.genBoardArrangement(normal);

        validWordsList = new ArrayList<String>();
        validWordsList = aBoard.validWords();

        // assert that the validWordsList is > 1
        assertTrue( validWordsList.size() >= 5 );
    }


    /** difficult_genBoard()
     * generates a board on easy setting and checks that the validWord
     * length is long enough: len >= 2 */
    @Test
    public void difficult_genBoard() {

        aBoard.genBoardArrangement(difficult);

        validWordsList = new ArrayList<String>();
        validWordsList = aBoard.validWords();

        // assert that the validWordsList is >= 7
        assertTrue( validWordsList.size() >= 7 );
    }



    // TESTS 2: Board getSquares()

    /** Checkes that Board getSquares() returns something which is not null after a
     * board has been generated */
    @Test
    public void getSquaresIsNotNull() {

        aBoard.genBoardArrangement(easy);
        aSquares = aBoard.getSquares();

        assertNotNull(aSquares);
    }



    // TESTS 3: Board checkWord()

//    @Test
//    public void one_checkWord() {
//    // aBoard = new Board(appContext);
//
//        aBoard.genBoardArrangement(normal);  // generate a board
//
//        // "a" should return false
//        assertFalse( aBoard.checkWord("a"));
//        // "at"  should return false
//        assertFalse( aBoard.checkWord("at"));
//        // "ate" should return true
//        assertTrue( aBoard.checkWord("ate"));
//    }


    /** validWords: check that validwords list returned from Board
     * is not null AND not empty */
    @Test
    public void validWordsIsNotNull() {
        aBoard.genBoardArrangement(easy);
        validWordsList = aBoard.validWords();

        assertNotNull(validWordsList);
        // valid words should return something
        assertTrue( validWordsList.size() >= 1 );
    }

    /** foundWords: check that foundWords list returned from Board
     * is not null  */
    @Test
    public void getFoundWordsIsNotNull() {
        aBoard.genBoardArrangement(easy);
        foundWordsList = aBoard.foundWords();

        assertNotNull(foundWordsList);
    }





} //end