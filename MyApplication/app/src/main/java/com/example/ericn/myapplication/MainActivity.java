// Eric Neiman CMSC 355 Assignment 4 Part 2 Welcome Screen
package com.example.ericn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this); // creates database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterValueClick(View eV) {
        if (eV.getId() == R.id.enterValues) {
            Intent enterValue = new Intent(MainActivity.this, EnterValues.class);
            startActivityForResult(enterValue, 1); // starts enter values activity expecting a result
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // catches return from EnterValues.class
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) { // checks if result is from EnterValues
            WordPair valuePair = new WordPair(data.getStringExtra("word1"), data.getStringExtra("word2")); // creates a word pair from two return strings
            helper.insertPair(valuePair); // inserts results into database
            Toast enterValuePop = Toast.makeText(MainActivity.this, valuePair.getSynonym() + " is a synonym for " + valuePair.getWord(), Toast.LENGTH_SHORT); // popup message for successful entrance of values
            enterValuePop.show(); // shows popup
        }
    }

    public void findSynonymClick(View fS) {
        if (fS.getId() == R.id.findSynonymButton) {
            String output = helper.searchWord(((EditText)findViewById(R.id.enterWord)).getText().toString()); // checks user input against the database
            Intent result = new Intent(MainActivity.this, FindSynonym.class);
            result.putExtra("word", output); // sends output to findSynonym
            startActivity(result); // starts results activity
        }
    }
}