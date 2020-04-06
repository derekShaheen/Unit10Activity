package com.shaheen.unit10activity;

/**
 * 'Comment' object
 * Stores information related to a comment
 */
public class Comment {
    private long id;
    private String comment;
    private String rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return comment + " - " + rating;
    }
}