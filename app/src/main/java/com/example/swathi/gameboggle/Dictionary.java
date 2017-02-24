package com.example.swathi.gameboggle;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Dictionary{
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


    // Reads words from the 'dictionary.txt' file in assets, calls addWords() to add word to data structure.
    // Returns 0 for success, else -1. Log.e on error.
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


    // Adds a word to the Trie data structure
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



    // isWord will take in a string and return true if that string is a word in the dictioanry
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

    // isPrefix will return true if there are any words starting with the passed in string
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
}

