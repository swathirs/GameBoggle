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
import java.lang.Character;


public class Dictionary {
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
            InputStream in = context.getResources().openRawResource(context.getResources().getIdentifier("dictionary", "raw", context.getPackageName()));
            /*InputStream inputStream = context.getResources().openRawResource(R.raw.dictionary);
            //BufferedReader buffReader = new BufferedReader(new FileReader(dictionary));
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));*/


                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                while ((str = reader.readLine()) != null) {

                     this.addWord(str);


                }


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

    private class TrieNode
    {
        private TrieNode next[];
        private boolean isWord;
        //private char letter;

        TrieNode()
        {
            next = new TrieNode[26];
            for (int i = 0; i < 26; i++)
            {
                next[i] = null;
            }
            isWord = false;
            //letter = ' ';
        }

        TrieNode(boolean isWord)
        {
            next = new TrieNode[26];
            for (int i = 0; i < 26; i++)
            {
                next[i] = null;
            }
            this.isWord = isWord;
            //this.letter = letter;
        }

        public boolean addChild(char letter)
        {
            char lowercase = Character.toLowerCase(letter);
            if (this.next[lowercase] != null)
                return false;
            this.next[lowercase] = new TrieNode();
            return true;
        }

        public TrieNode traverseChar(char letter)
        {
            return this.next[Character.toLowerCase(letter) - 97];
        }

        public TrieNode traverseIndex(int index)
        {
            return this.next[index];
        }

        public boolean isWord()
        {
            return this.isWord;
        }

        public void markWord(boolean isWord)
        {
            this.isWord = isWord;
        }
    }


}
