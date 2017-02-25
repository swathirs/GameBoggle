package com.example.swathi.gameboggle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by John on 2/10/2017.
 */


public class Board extends Activity {
    private DiceController roller;
    private ArrayList<String> squares;

    private Context context;
    private Dictionary dictionary;
    private ValidWords valid;

    private ArrayList<String> foundWordsList;
    private ArrayList<String> validWordsList;

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

    public boolean checkWord(String aWord){
        //return valid.checkWord(aWord);

        int result = valid.checkWord(aWord);
        if(result == -1){
            Toast.makeText(context.getApplicationContext(), "Word must be at least 3 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(result == 1){
            Toast.makeText(context.getApplicationContext(), "You've already submitted this word!", Toast.LENGTH_SHORT).show();
            return false;
        }
         if(result == 0){
            Toast.makeText(context.getApplicationContext(), "Word is not found in the dictionary!", Toast.LENGTH_SHORT).show();
           return false;
        }
        return true;
    }

    public ArrayList<String> foundWords(){
        foundWordsList = new ArrayList<String>();
        foundWordsList = valid.getFoundWords();
        return foundWordsList;

    }

    public ArrayList<String> validWords(){
        validWordsList = new ArrayList<String>();
        validWordsList = valid.getValidWords();
        return validWordsList;

    }


}
