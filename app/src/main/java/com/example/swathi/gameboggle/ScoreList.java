package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
READ FIRST
For the category parameter on various functions follow this guide:
1   =   singpleplayer easy
2   =   singleplayer normal
3   =   singleplayer difficult
4   =   multiplayer single round basic
5   =   multiplayer multi round basic
6   =   multiplayer cutthroat

Sample usage:
ScoreList list = new ScoreList(getApplicationContext());
//to see if a score of 20 would be a new highscore on singleplayer difficult
if (list.checkNewHighScores(3, 20)
    //this is a valid new high score, to submit it you would then do
    list.addHighScore(3, 20, name)
//to get the list of names and scores for multiplayer basic you would:
int scores[] = list.getScores(4)
String names[] = list.getNames(4)
//the resulting lists will always have 5 elements where [0] is the highest score, [4] is the lowest
//currently there is a bugged highscore file that i've left in so it will break this rule.
 */

//This class will read in the high scores and allow a new highscore to be submitted
public class ScoreList {
    private Score[] scores;
    private Context context;
    private String filename;

    //Initializes ScoreList with a context and tries to load from a file
    ScoreList(Context context)
    {
        scores = new Score[30];
        this.context = context;
        filename = "highscores";

        for (int i = 0; i < 30; i++)
        {
            scores[i] = new Score();
        }

        loadFromFile();
    }

    //opens up the input file to read from, if there is nothing to read then it creates the file and initializes it
    private int loadFromFile()
    {
        try {
            FileInputStream inputStream = context.openFileInput(filename);
            if (inputStream != null)
            {
                //This first loop gathers the names, and the second one gathers the scores
                for (int i = 0; i < 30; i++)
                {
                    int c;
                    String temp = "";
                    c = inputStream.read();
                    while (c  != -1 && c != 10)
                    {
                        temp = temp + Character.toString((char)c);
                        c = inputStream.read();
                    }
                    scores[i].setName(temp);
                }
                for (int i = 0; i < 30; i++)
                {
                    int c;
                    String temp = "";
                    c = inputStream.read();
                    while (c  != -1 && c != 10)
                    {
                        temp = temp + Character.toString((char)c);
                        c = inputStream.read();
                    }
                    if (temp != "")
                        scores[i].setScore(Integer.parseInt(temp));
                }
                inputStream.close();

                //if this if statement gets triggered that means there was no file previously, so this code initializes the file
                if (scores[0].getName() == "")
                {
                    for (int i = 0; i < 30; i++) {
                        scores[i] = new Score();
                    }
                    saveToFile();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //saves the file, should be called on any function that changes the contents of scores[]
    private int saveToFile()
    {
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            //first loop saves the names, second one saves the scores
            for (int i = 0; i < 30; i++)
            {
                outputStream.write((scores[i].getName() + "\n").getBytes());
            }
            for (int i = 0; i < 30; i++)
            {
                String temp = Integer.toString(scores[i].getScore());
                outputStream.write((temp + "\n").getBytes());
            }

            outputStream.close();
        } catch (IOException e) {
            return -1;
        }
        return 0;
    }

    //returns an array of names by category
    public String[] getNames(int category)
    {
        if (category < 1 || category > 6)
            return null;
        int offset = category - 1;
        String names[] = new String[5];

        for (int i = 0; i < 5; i++)
        {
            names[i] = scores[(offset * 5) + i].getName();
        }

        return names;
    }

    //returns an array of scores by category
    public int[] getScores(int category)
    {
        if (category < 1 || category > 6)
            return null;
        int offset = category - 1;
        int tempScores[] = new int[5];

        for (int i = 0; i < 5; i++)
        {
            tempScores[i] = scores[(offset * 5) + i].getScore();
        }

        return tempScores;
    }

    //returns true if the input score is a potential new high score
    public boolean checkNewHighScore(int category, int score)
    {
        if (category < 1 || category > 6)
            return false;
        int offset = (category - 1) * 5;
        for (int i = 0; i < 5; i++)
        {
            if (scores[offset + i].getScore() < score)
                return true;
        }
        return false;
    }

    //returns true if a new highscore is added
    public boolean addHighScore(int category, int score, String name)
    {
        if (category < 1 || category > 6)
            return false;
        int offset = (category - 1) * 5;

        //These arrays will hold the old highscores
        int[] tempScores = new int[5];
        String[] tempNames = new String[5];
        int start = -1;

        //traverse through scores until the one to replace is found
        for (int i = 0; i < 5; i++)
        {
            if (start == -1)
            {
                if (scores[offset + i].getScore() < score)
                    start = i;
            }
            tempNames[i] = scores[offset + i].getName();
            tempScores[i] = scores[offset + i].getScore();
        }

        //Do work here to insert the new score and shift the old scores down
        if (start != -1)
        {
            scores[offset + start].setName(name);
            scores[offset + start].setScore(score);
            for (int i = start + 1; i < 5; i++)
            {
                scores[offset + i].setName(tempNames[i - 1]);
                scores[offset + i].setScore(tempScores[i - 1]);
            }
            saveToFile();
            return true;
        }
        return false;
    }

    private class Score {
        private String name;
        private int score;

        public Score()
        {
            this.name = "empty";
            this.score = 0;
        }

        public String getName()
        {
            return this.name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getScore()
        {
            return this.score;
        }

        public void setScore(int score)
        {
            this.score = score;
        }
    }
}
