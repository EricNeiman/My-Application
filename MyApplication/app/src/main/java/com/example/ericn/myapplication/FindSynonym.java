// Eric Neiman CMSC 355 Assignment 4 Part 2 Results Screen
package com.example.ericn.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FindSynonym extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_synonym); // find synonym xml file
        String word = getIntent().getStringExtra("word"); // gets word from MainActivity class
        TextView resultSyn = findViewById(R.id.resultTV);
        resultSyn.setText(word); // fills text view field
    }
}