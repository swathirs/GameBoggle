package com.example.swathi.gameboggle;

/**
 * Created by John on 2/10/2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Dictionary {
    private Context context;

    public Dictionary(Context context) {
        this.context = context;

        if(LoadWordsFile() == -1){
            System.out.println("Could not find file 'dictionary.txt'.");
            return;
        };

    }

    private int LoadWordsFile() {

        try {
            InputStream in = context.getResources().openRawResource(context.getResources().getIdentifier("dictionary", "raw", context.getPackageName()));
            /*InputStream inputStream = context.getResources().openRawResource(R.raw.dictionary);
            //BufferedReader buffReader = new BufferedReader(new FileReader(dictionary));
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));*/


                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);


            reader.close();

        } catch (IOException e) {
            Log.e("LoadWordsFile():", "Exception occurred while trying to open the dictionary file. \n");
            //e.printStackTrace();
            return -1;
        }
        return 0;
    }

}