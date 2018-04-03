// Eric Neiman CMSC 355 Assignment 4 Part 2 WordPair Object
package com.example.ericn.myapplication;

public class WordPair {
    String word;
    String synonym;

    public WordPair(String w, String s) { // constructor declaring values
        this.word = w; // sets word to the first string
        this.synonym = s; // sets synonym to the second string
    }

    public String getWord() {return this.word;} // word getter
    public String getSynonym() {return this.synonym;} // synonym getter
}