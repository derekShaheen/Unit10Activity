package com.shaheen.unit10activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Used to manage the SQLite database.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments"; // Name of the table
    public static final String COLUMN_ID = "_id";           // Primary key column
    public static final String COLUMN_COMMENT = "comment";  // Name of comment field

    private static final String DATABASE_NAME = "commments.db"; // Name of db file
    private static final int DATABASE_VERSION = 1;

    /**
     * Database creation sql statement
     * CREATE TABLE comments
     *              (_id integer primary key autoincrement,
     *              comment text not null)
     */
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    /**
     * Constructor
     * @param context   Specify the contect
     */
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Used to create the databased on first create
     * @param database  Specify the database
     *
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /**
     * Used to upgrade the data from an old version to a new version
     * @param db            Specify the database
     * @param oldVersion    Specify the old version
     * @param newVersion    Specify the new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
