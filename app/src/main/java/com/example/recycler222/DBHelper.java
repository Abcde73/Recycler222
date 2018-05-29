package com.example.recycler222;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "LoyaltyCards";

    public static final String TABLE_CONTACTS = "table_of_cards";
    public static final String KEY_ID = "_id";

    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_URL = "url";
    public static final String KEY_URL_BACK = "url_back";
    public static final String KEY_EXTRA = "extra_information";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /*
        db.execSQL("create table "+ DATABASE_NAME + "(" + KEY_ID +" INTEGER PRIMARY KEY, "+KEY_NAME +" text, "+KEY_URL +" text)");
        db.execSQL("create table "+ DATABASE_NAME + "(" + KEY_ID +" INTEGER PRIMARY KEY, "+KEY_NAME +" text, "+KEY_NUMBER +" text, "+ KEY_URL +" text,"+ KEY_URL_BACK +" text, "+ KEY_EXTRA +" text)");

*/
        /*
db.execSQL(" CREATE TABLE " + DATABASE_MARKSTABLE + " (" +
                    KEY_STUID + " TEXT PRIMARY KEY, " +
                    KEY_SUB1 + " TEXT NOT NULL, " +
                    KEY_SUB2 + " TEXT NOT NULL, " +
                    KEY_SUB3 + " TEXT NOT NULL, " +
                    KEY_MARKS1 + " INTEGER NOT NULL, " +
                    KEY_MARKS2 + " INTEGER NOT NULL, " +
                    KEY_MARKS3 + " INTEGER NOT NULL);"
            );

*/
        /*
        db.execSQL("create table "+ DATABASE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME +" text, " +
                KEY_NUMBER +" TEXT NOT NULL, " +
                KEY_URL +" text," +
                KEY_URL_BACK +" text, " +
                KEY_EXTRA +" text)");
*/
        db.execSQL(" CREATE TABLE " + DATABASE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT NOT NULL, " +
                KEY_NUMBER + " TEXT NOT NULL, " +
                KEY_URL + " TEXT NOT NULL, " +
                KEY_URL_BACK + " TEXT NOT NULL, " +
                KEY_EXTRA + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);
    }
}
