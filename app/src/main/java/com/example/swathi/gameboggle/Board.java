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

    private Context context;
    private Dictionary dictionary;
    private ValidWords valid;

    public Board() {
        roller = new DiceController();
        squares = null;
        valid = null;
    }
 
    public Board(Context context)
    {
        roller = new DiceController();
        squares = null;
        valid = null;//This will be created after the board is made
        this.context = context;
        dictionary = new Dictionary(this.context);
    }
   
    public boolean genBoardArrangement(int difficulty) {
        squares = roller.genBoard();
        valid = new ValidWords(squares, difficulty, dictionary);
        while (!valid.checkValidBoard())
        {
            valid = new ValidWords(squares, 1, dictionary);
        }

        return true;
    }

    public ArrayList<String> getSquares() {
        return squares;
    }

    public int checkWord(String aWord){
        return valid.checkWord(aWord);
    }
}
