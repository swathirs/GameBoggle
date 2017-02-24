package com.example.swathi.gameboggle;

import android.os.Parcel;
import android.os.Parcelable;


public class TrieNode{
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
        if (this.next[lowercase - 97] != null)
            return false;
        this.next[lowercase - 97] = new TrieNode();
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
