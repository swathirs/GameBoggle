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

/**
 * Created by John on 2/10/2017.
 */

//This class will read in the high scores and allow a new highscore to be submitted
public class ScoreList {
    private Score[] scores;
    private Context context;
    private String filename;
    //private File file;

    ScoreList(Context context)
    {
        scores = new Score[25];
        this.context = context;
        filename = "highscores";
        /*
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }*/


        Log.d("ScoreList", "start initialization");
        for (int i = 0; i < 25; i++)
        {
            scores[i] = new Score();
        }

        loadFromFile();
    }

    private int loadFromFile()
    {
        Log.d("loadFromFile", "loading file");
        try {
            FileInputStream inputStream = context.openFileInput(filename);
            if (inputStream != null)
            {
                //read from the input steam into the data array
                Log.d("loadFromFile", "i'm in here?");
                //TESTING CHANGES
                /*
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader in = new BufferedReader(inputStreamReader);
                */
                Log.d("loadFromFile", "1");
                //String temp = "";
                for (int i = 0; i < 25; i++)
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
                    //Testing Changes
                    /*
                    temp = in.readLine();
                    scores[i].setName(temp);
                    */
                }
                Log.d("loadFromFile", "2");
                for (int i = 0; i < 25; i++)
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
                    //Testing changes
                    /*
                    temp = in.readLine();
                    if (temp != null)
                        scores[i].setScore(Integer.parseInt(temp));
                        */
                }
                inputStream.close();
                Log.d("loadFromFile", "3");
                //Log.d("loadFromFile", scores[0].getName());
                //If the file has been read in and something is null then that means there was no file
                if (scores[0].getName() == "")
                {
                    Log.d("loadFromFile", "found a null");
                    for (int i = 0; i < 25; i++) {
                        scores[i] = new Score();
                    }
                    saveToFile();
                }
                else
                    Log.d("loadFromFile", "not null? " + scores[0].getName());
            }
        } catch (Exception e) {
            Log.d("loadFromFile", "exception");
            //saveToFile();
            e.printStackTrace();
        }
        return 0;
    }

    private int saveToFile()
    {
        Log.d("saveToFile", "saving file");
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            //TESTING CHANGES
            /*
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter out = new BufferedWriter(outputStreamWriter);
            */
            for (int i = 0; i < 25; i++)
            {
                outputStream.write((scores[i].getName() + "\n").getBytes());
                //testing changes
                /*
                out.write(scores[i].getName());
                out.newLine();
                */
            }
            for (int i = 0; i < 25; i++)
            {
                String temp = Integer.toString(scores[i].getScore());
                outputStream.write((temp + "\n").getBytes());
                //outputStream.write(scores[i].getName().getBytes());
                //Testing Changes
                /*
                out.write(scores[i].getScore());
                out.newLine();
                */
            }
            //Testing changes
            /*
            out.close();
            outputStreamWriter.close();
            */
            outputStream.close();
        } catch (IOException e) {
            Log.e("LoadWordsFile():", "Exception occurred while trying to open the dictionary file. \n");
            return -1;
        }
        return 0;
    }

    public String[] getNames(int category)
    {
        Log.d("getNames", "getting names");
        int offset = category - 1;
        String names[] = new String[5];

        for (int i = 0; i < 5; i++)
        {
            names[i] = scores[(offset * 5) + i].getName();
        }

        return names;
    }

    public int[] getScores(int category)
    {
        Log.d("getScores", "getting scores");
        int offset = category - 1;
        int tempScores[] = new int[5];

        for (int i = 0; i < 5; i++)
        {
            tempScores[i] = scores[(offset * 5) + i].getScore();
        }

        return tempScores;
    }

    public boolean checkNewHighScore(int category, int score)
    {
        Log.d("checkNewHighScore", "checking if new high score");
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
        Log.d("addHighScore", "adding highscore");
        int offset = (category - 1) * 5;
        int[] tempScores = new int[5];
        String[] tempNames = new String[5];
        int start = -1;
        for (int i = 0; i < 5; i++)
        {
            if (start == -1)
            {
                if (scores[offset + i].getScore() < score)
                    start = i;
            }
            tempNames[i] = scores[i].getName();
            tempScores[i] = scores[i].getScore();
        }
        if (start != -1)
        {
            scores[start].setName(name);
            scores[start].setScore(score);
            for (int i = start + 1; i < 5; i++)
            {
                scores[i].setName(tempNames[i - 1]);
                scores[i].setScore(tempScores[i - 1]);
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

    public void testFunction()
    {
        loadFromFile();
    }
}
