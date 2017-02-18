package com.example.swathi.gameboggle;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

/**
 * Created by John on 2/10/2017.
 */


public class Board {
    private DiceController roller;
    private ArrayList<String> squares;
    //Matt CHANGES
    private Context context;
    private ValidWords validWords;
    private Dictionary dictionary;
    //END Matt CHANGES

    public Board() {
        roller = new DiceController();
        squares = null;
    }

    //Matt CHANGES
    public Board(Context context)
    {
        roller = new DiceController();
        squares = null;
        validWords = null;//This will be created after the board is made
        this.context = context;
        dictionary = new Dictionary(this.context);
    }
    //END Matt CHANGES

    public boolean genBoardArrangement() {
        squares = roller.genBoard();

        //Matt CHANGES
        validWords = new ValidWords(squares, 1, dictionary);
        while (!validWords.checkValidBoard())
        {
            validWords = new ValidWords(squares, 1, dictionary);
        }
        //Uncomment this code to see the list of valid words on the board
        /*
        ArrayList<String> temp = null;
        temp = validWords.getValidWords();
        for (int i = 0; i < temp.size(); i++)
        {
            Log.d("wordList", temp.get(i));
        }
        //END Matt CHANGES
        */

        return true;
    }

    public ArrayList<String> getSquares() {
        return squares;
    }
}
