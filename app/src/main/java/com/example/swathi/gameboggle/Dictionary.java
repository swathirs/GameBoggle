package com.example.swathi.gameboggle;

/**
 * Created by John on 2/10/2017.
 */

import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.Character;
import android.util.Log;


public class Dictionary implements Parcelable{
    private Context context;
    private TrieNode root;

    public Dictionary(Context context) {
        this.context = context;

        root = new TrieNode(false);
        if(LoadWordsFile() == -1){
            System.out.println("Could not find file 'dictionary.txt'.");
            return;
        };

    }

    private int LoadWordsFile() {

        try {
            //InputStream in = context.getResources().openRawResource(context.getResources().getIdentifier("dictionary", "raw", context.getPackageName()));
            AssetManager am = context.getAssets();
            InputStream in = am.open("dictionary.txt");
            /*InputStream inputStream = context.getResources().openRawResource(R.raw.dictionary);
            //BufferedReader buffReader = new BufferedReader(new FileReader(dictionary));
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));*/

                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                while ((str = reader.readLine()) != null) {
                     this.addWord(str);
                }

            //am.close();
            reader.close();

        } catch (IOException e) {
            Log.e("LoadWordsFile():", "Exception occurred while trying to open the dictionary file. \n");
            //e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private void addWord(String word)
    {
        char letter = ' ';
        TrieNode currentNode = null;

        if (word.length() < 1)
            return;

        currentNode = root;
        for (int i = 0; i < word.length(); i++)
        {
            letter = word.charAt(i);
            if (currentNode.traverseChar(letter) == null)
                currentNode.addChild(letter);
            currentNode = currentNode.traverseChar(letter);
        }
        currentNode.markWord(true);
    }

    public boolean isWord(String word)
    {
        char letter = ' ';
        TrieNode currentNode = null;

        if (word.length() < 1)
            return false;

        currentNode = root;
        for (int i = 0; i < word.length(); i++)
        {
            letter = word.charAt(i);
            if (currentNode.traverseChar(letter) == null)
                return false;
            currentNode = currentNode.traverseChar(letter);
        }

        return currentNode.isWord();
    }

    public boolean isPrefix(String word)
    {
        char letter = ' ';
        TrieNode currentNode = null;

        if (word.length() < 1)
            return true;

        currentNode = root;
        for (int i = 0; i < word.length(); i++)
        {
            letter = word.charAt(i);
            if (currentNode.traverseChar(letter) == null)
                return false;
            currentNode = currentNode.traverseChar(letter);
        }

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeTypedObject(root, 1);
    }

    public static final Parcelable.Creator<Dictionary> CREATOR = new Parcelable.Creator<Dictionary>(){
        public Dictionary createFromParcel(Parcel in){
            return new Dictionary(in);
        }

        public Dictionary[] newArray(int size){
            return new Dictionary[size];
        }
    };

    private Dictionary(Parcel in){
        root = in.readTypedObject(TrieNode.CREATOR);
    }


}

