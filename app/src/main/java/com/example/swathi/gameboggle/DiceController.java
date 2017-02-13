package com.example.swathi.gameboggle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by John on 2/10/2017.
 */


public class DiceController {
    private ArrayList<ArrayList<String>> dice;

    public DiceController(){
        dice = null;
    }

    public ArrayList<String> genBoard(){
        makeDice();
        Random rand = new Random();
        ArrayList<String> temp = new ArrayList<String>();
        int selection = 0;
        int face = 0;
        int total = 16;

        for(int i = 0; i < 16; ++i){
            selection = rand.nextInt(total);
            //for(int j = 0; j < 6; ++j) System.out.print(dice.get(selection).get(j)); System.out.print('\n'); //debugging
            face = rand.nextInt(6);
            temp.add(dice.get(selection).get(face));
            dice.remove(selection);
            --total;
        }
        return temp;
    }

    private ArrayList<String> makeDie(String a, String b, String c, String d, String e, String f){
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(a);
        temp.add(b);
        temp.add(c);
        temp.add(d);
        temp.add(e);
        temp.add(f);
        return temp;
    }

    private void makeDice(){
        dice = new ArrayList<ArrayList<String>>();
        dice.add(makeDie("i", "u", "n", "h", "m", "qu"));
        dice.add(makeDie("l", "n", "h", "n", "z", "r"));
        dice.add(makeDie("u", "m", "c", "o", "t", "i"));
        dice.add(makeDie("d", "r", "x", "i", "e", "l"));
        dice.add(makeDie("e", "i", "u", "n", "s", "e"));
        dice.add(makeDie("f", "s", "k", "f", "a", "p"));
        dice.add(makeDie("t", "o", "e", "s", "i", "s"));
        dice.add(makeDie("e", "y", "l", "d", "r", "v"));
        dice.add(makeDie("s", "c", "a", "o", "p", "h"));
        dice.add(makeDie("e", "w", "h", "g", "n", "e"));
        dice.add(makeDie("b", "o", "o", "j", "a", "b"));
        dice.add(makeDie("t", "w", "o", "o", "a", "t"));
        dice.add(makeDie("a", "g", "a", "e", "n", "e"));
        dice.add(makeDie("t", "s", "t", "i", "d", "y"));
        dice.add(makeDie("h", "e", "v", "w", "t", "r"));
        dice.add(makeDie("l", "t", "y", "e", "t", "r"));
    }
}