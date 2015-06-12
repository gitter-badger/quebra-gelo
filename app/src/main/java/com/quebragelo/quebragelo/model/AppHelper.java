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
        createStatuses(db);
        createLocations(db);
        createTags(db);
        createPeople(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createPeople(SQLiteDatabase db){
        String sql = "CREATE TABLE " + Constraint.TABLE_PERSON + " (" +
                " _id INTEGER PRIMARY KEY," +
                Constraint.COLUMN_NAME + " TEXT," +
                Constraint.COLUMN_EMAIL + " TEXT," +
                Constraint.COLUMN_BIRTHDAY + " INTEGER," +
                Constraint.COLUMN_STATUS_ID + " INTEGER," +
                Constraint.COLUMN_BIO + " TEXT);";

        db.execSQL(sql);
    }

    private void createTags(SQLiteDatabase db){
        String sql = "CREATE TABLE " + Constraint.TABLE_TAG + " (" +
                " _id INTEGER PRIMARY KEY," +
                Constraint.COLUMN_PERSON_ID + " INTEGER," +
                Constraint.COLUMN_NAME + " TEXT," +
                Constraint.COLUMN_DESCRIPTION + " TEXT);";

        db.execSQL(sql);
    }

    private void createLocations(SQLiteDatabase db){
        String sql = "CREATE TABLE " + Constraint.TABLE_LOCATION + " (" +
                " _id INTEGER PRIMARY KEY," +
                Constraint.COLUMN_PERSON_ID + " INTEGER," +
                Constraint.COLUMN_LATITUDE + " INTEGER," +
                Constraint.COLUMN_LONGITUDE + " INTEGER);";

        db.execSQL(sql);
    }

    private void createStatuses(SQLiteDatabase db){
        String sql = "CREATE TABLE " + Constraint.TABLE_STATUS + " (" +
                " _id INTEGER PRIMARY KEY," +
                Constraint.COLUMN_PERSON_ID + " INTEGER," +
                Constraint.COLUMN_LAST_LOGGED + " INTEGER," +
                Constraint.COLUMN_LOGGED + " INTEGER);";

        db.execSQL(sql);
    }
}
