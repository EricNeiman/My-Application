// Eric Neiman CMSC 355 Assignment 4 Part 2 SQLite Database Helper
package com.example.ericn.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db; // declares database
    private static final int DATABASE_VERSION = 1; // database version
    private static final String DATABASE_NAME = "pairs.db"; // database name
    private static final String TABLE_NAME = "pairs"; // table name
    private static final String COLUMN_ID = "id"; // column id
    private static final String COLUMN_WORD = "word"; // column 0 words
    private static final String COLUMN_SYNONYM = "synonym"; // column 1 synonyms
    private static final String TABLE_CREATE = "create table " + TABLE_NAME +  " (id integer primary key not null , " +
            "word text not null , synonym text not null);"; // table creation string

    public DatabaseHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);} // database constructor

    public void insertPair(WordPair p) { // inserts word pair into database
        db = this.getWritableDatabase(); // accesses writable database
        ContentValues values = new ContentValues();
        String query = "select * from pairs"; // query for cursor
        Cursor cursor = db.rawQuery(query, null); // cursor for insertions
        values.put(COLUMN_ID, cursor.getCount()); // counts using the cursor and assigns the id
        values.put(COLUMN_WORD, p.getWord()); // puts word in column 1
        values.put(COLUMN_SYNONYM, p.getSynonym()); // puts synonym in column 2
        db.insert(TABLE_NAME, null, values);
        cursor.close(); // closes cursor
        db.close(); // closes database after insertion
    }

    public String searchWord(String wordIn) {
        db = this.getReadableDatabase(); // gets readable database
        Boolean synExists = false; // used for return message
        String a0, a1, b = "Word not found"; // declares a0, a1 and assigns b's default value
        String query = "select word, synonym from " + TABLE_NAME; // query for cursor
        Cursor cursor = db.rawQuery(query, null); // cursor for database
        if (cursor.moveToFirst()) { // checks if input is a word
            do {
                a0 = cursor.getString(0); // cursor checks word column
                a1 = cursor.getString(1); // cursor checks synonym column
                if (a0.equalsIgnoreCase(wordIn)) { // if wordIn is stored in word column
                    b = cursor.getString(1);
                    synExists = true;
                    break;
                } else if (a1.equalsIgnoreCase(wordIn)) { // if wordIn is stored in synonym column
                    b = cursor.getString(0);
                    synExists = true;
                    break;
                }
            } while (cursor.moveToNext()); // moves the cursor through the database
        }
        if (synExists == true) {b = b + " is a synonym for " + wordIn;} // if the synonym exists make b a full message
        cursor.close(); // closes cursor
        db.close(); // closes database after search
        return b; // returns either failure message or synonym for wordIn string
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int NewVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}