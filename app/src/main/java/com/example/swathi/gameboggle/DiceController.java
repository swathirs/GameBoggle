package com.example.swathi.gameboggle;

import java.util.ArrayList;

/**
 * Created by John on 2/10/2017.
 */


public class DiceController {
    private ArrayList<ArrayList<String>> dice;

    public DiceController(){
        dice = null;
    }

    public ArrayList<String> genBoard(){
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < 16; ++i) temp.add("A");
        return temp;
    }
}