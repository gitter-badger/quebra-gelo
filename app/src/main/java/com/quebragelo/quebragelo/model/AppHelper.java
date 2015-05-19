package com.quebragelo.quebragelo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno Casali on 24/04/2015.
 */
public class AppHelper extends SQLiteOpenHelper {
    public AppHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE statuses (" +
                      " _id INTEGER PRIMARY KEY, " +
                Constraint.COLUMN_PERSON_ID + " INTEGER, " +
                Constraint.COLUMN_LAST_LOGGED + " INTEGER, " +
                Constraint.COLUMN_LOGGED + " INTEGER);";



        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
