package com.example.swathi.gameboggle;

import java.util.ArrayList;

/**
 * Created by John on 2/10/2017.
 */


public class Board {
    private DiceController roller;
    private ArrayList<String> squares;
    private ValidWords validList;

    public Board() {
        roller = new DiceController();
        squares = null;
    }

    public boolean genBoardArrangement() {
        squares = roller.genBoard();

        validList = new ValidWords(squares, 1);

        return true;
    }

    public ArrayList<String> getSquares() {
        return squares;
    }
}
