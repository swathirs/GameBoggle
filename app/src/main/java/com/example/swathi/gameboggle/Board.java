package com.example.swathi.gameboggle;

//import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;



public class Board {
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

    /** constructor */
    public Board(Context context)
    {
        roller = new DiceController();
        squares = null;
        valid = null;  //This will be created after the board is made
        this.context = context;
        dictionary = new Dictionary(this.context);
    }


    /** genBoardArrangement()
     * input: int (representing the level of game difficulty)
     * */
    public boolean genBoardArrangement(int difficulty) {
        squares = roller.genBoard();
        valid = new ValidWords(squares, difficulty, dictionary);

        while (!valid.checkValidBoard())
        {
            valid = new ValidWords(squares, difficulty, dictionary);
        }

        return true;
    }
    public boolean genBoardArrangement(String tempSquares) {

        squares = new ArrayList<String>();

        String temp;

        for (int i = 0; i < tempSquares.length(); i++){
            temp = String.valueOf(tempSquares.charAt(i));
            if(temp.contains("q")){
                i++;
                temp = temp.concat("u");

            }
            squares.add(temp);
        }
        valid = new ValidWords(squares, 1, dictionary);
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
