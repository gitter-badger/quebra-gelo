package com.quebragelo.quebragelo.model;

/**
 * Created by Bruno Casali on 18/05/15.
 */
public class Constraint {
    // Databases
    public static final String DATABASE_NAME = "ice_breaker_mobile";

    // Tables
    public static final String TABLE_STATUS = "statuses";
    public static final String TABLE_LOCATION = "locations";
    public static final String TABLE_TAG = "tags";
    public static final String TABLE_PERSON = "people";

    // Columns shared between tables
    public static final String COLUMN_PERSON_ID = "person_id";

    // Columns from TABLE_PERSON
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_STATUS_ID = "status_id";
    public static final String COLUMN_BIO = "bio";

    // Columns from TABLE_STATUS
    public static final String COLUMN_LOGGED = "logged";
    public static final String COLUMN_LAST_LOGGED = "last_logged";

    // Columns from TABLE_LOCATION
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    // Columns from TABLE_TAG
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_NAME = "name";
}
