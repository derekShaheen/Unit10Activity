package com.shaheen.unit10activity;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class is used to manage the database connection, creation of comments, deleting, listing, and selecting comments
 */

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT,
            MySQLiteHelper.COLUMN_RATING};

    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * Creates a new comment
     * @param comment Specify a string to create a comment
     * @return
     */
    public Comment createComment(String comment, String rating) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        values.put(MySQLiteHelper.COLUMN_RATING, rating);

        /**
         * INSERT comments (comment)
         */
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        // Now we have the id of the new record
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        // Select that record
        cursor.moveToFirst();
        // Move it to the front
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    /**
     * Deletes a comment
     * @param comment Specify a comment object
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        /**
         * DELETE comment
         * WHERE _id = 'id'
         */
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * List all comments
     * @return List
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, null, null, null, null, null, null);
        // Create the cursor, move it to the first record
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) { // Then loop over that cursor for all records
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();// And list the comments
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        comment.setRating(cursor.getString( cursor.getColumnIndex( MySQLiteHelper.COLUMN_RATING ) ));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}