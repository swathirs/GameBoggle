package com.example.swathi.gameboggle;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by John on 2/10/2017.
 */

//This class will read in the high scores and allow a new highscore to be submitted
public class ScoreList {
    private Score scores[];
    private Context context;

    ScoreList(Context context)
    {
        scores = new Score[25];
        this.context = context;

        loadFromFile();
    }

    private int loadFromFile()
    {
        try {
            AssetManager am = context.getAssets();
            /*
            InputStream in = am.open("highscores.txt");
            InputStreamReader tmp = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(tmp);
            */
            Scanner reader = new Scanner(am.open("highscores.txt"));

            String name;
            int score;

            for (int i = 0; i < 25; i++)
            {
                scores[i] = new Score();
                name = reader.nextLine();
                scores[i].setName(name);
            }
            for (int i = 0; i < 25; i++)
            {
                score = reader.nextInt();
                scores[i].setScore(score);
            }

            reader.close();

        } catch (IOException e) {
            Log.e("LoadWordsFile():", "Exception occurred while trying to open the dictionary file. \n");
            return -1;
        }
        return 0;
    }

    private int saveToFile()
    {
        return 0;
    }

    public String[] getNames(int category)
    {
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
        return true;
    }

    public void addHighScore(int category, int score, String name)
    {

    }

    private class Score {
        String name;
        int score;

        Score()
        {
            name = "";
            score = 0;
        }

        String getName()
        {
            return this.name;
        }

        void setName(String name)
        {
            this.name = name;
        }

        int getScore()
        {
            return this.score;
        }

        void setScore(int score)
        {
            this.score = score;
        }
    }
}
