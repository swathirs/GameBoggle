package com.example.swathi.gameboggle;

/**
 * Created by John on 2/10/2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dictionary extends AppCompatActivity {
    public ArrayList<String> dic = new ArrayList<String>();
    public  ArrayList<String> wordlist = new ArrayList<String>();
    public  static ArrayList<String> solution = new ArrayList<String>();
    public  static ArrayList<String> answer = new ArrayList<String>();

    public StringBuilder buf = new StringBuilder();
    public String[] dictWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        wordlist = new ArrayList<String>();
        solution = new ArrayList<String>();
        answer = new ArrayList<String>();

        InputStream in = getResources().openRawResource(getResources().getIdentifier("dictionary", "raw", getPackageName()));
        if (in != null) {
            InputStreamReader tmp = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(tmp);
            String str;

            try {
                while ((str = reader.readLine()) != null) {
                    buf.append(str.toUpperCase() + " ");
                    dic.add(str.toUpperCase());

                }
            } catch (IOException e) {

            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dictWords = buf.toString().split(" ");

        }

    }


}