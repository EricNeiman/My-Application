// Eric Neiman CMSC 355 Assignment 4 Part 2 EnterValues Screen
package com.example.ericn.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterValues extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_values); // enter values xml file

    }

    public void onSubmitClick(View s) {
        if (s.getId() == R.id.submitButton) {
            String word = ((EditText)findViewById(R.id.word1)).getText().toString(); // assigns word to the value of word1 casted to a string
            String synonym = ((EditText)findViewById(R.id.word2)).getText().toString(); // assigns synonym to the value of word2 casted to a string
            Intent returnIntent = new Intent();
            returnIntent.putExtra("word1", word); // sends word
            returnIntent.putExtra("word2", synonym); // sends synonym
            setResult(1, returnIntent);
            finish(); // closes activity on button click
        }
    }
}