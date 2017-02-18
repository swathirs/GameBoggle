package com.example.swathi.gameboggle;

import android.os.Parcel;
import android.os.Parcelable;


public class TrieNode implements Parcelable {
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

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeInt(isWord ? 1 : 0);
        out.writeTypedArray(next, 0);
    }

    public static final Parcelable.Creator<TrieNode> CREATOR = new Parcelable.Creator<TrieNode>(){
        public TrieNode createFromParcel(Parcel in){
            return new TrieNode(in);
        }
        public TrieNode[] newArray(int size){
            return new TrieNode[size];
        }
    };

    private TrieNode(Parcel in){
        isWord = in.readInt() != 0;
        next = in.createTypedArray(TrieNode.CREATOR);
    }
}
