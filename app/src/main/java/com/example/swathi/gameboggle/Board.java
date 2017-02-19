package com.example.swathi.gameboggle;

import java.util.ArrayList;
import android.util.Log;

/**
 * Created by John on 2/10/2017.
 */


public class Board {
    private DiceController roller;
    private ArrayList<String> squares;
    private ValidWords valid;

    public Board() {
        roller = new DiceController();
        squares = null;
        valid = null;
    }

    public boolean genBoardArrangement(int difficulty) {
        squares = roller.genBoard();
        valid = new ValidWords(squares, difficulty);
        return true;
    }

    public ArrayList<String> getSquares() {
        return squares;
    }

    public int checkWord(String aWord){
        return valid.checkWord(aWord);
    }
}
