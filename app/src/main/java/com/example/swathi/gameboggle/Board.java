package com.example.swathi.gameboggle;

import java.util.ArrayList;

/**
 * Created by John on 2/9/2017.
 */

public class Board {
    private DiceController roller;
    private ArrayList<String> squares;

    public Board() {
        roller = new DiceController();
        squares = null;
    }

    public boolean genBoardArrangement() {
        squares = roller.genBoard();
        return true;
    }

    public ArrayList<String> getSquares() {
        return squares;
    }
}
