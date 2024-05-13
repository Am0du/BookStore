package com.example.book.store.rest.dto;

public class CommentDTO {

    private long bookId;

    private long commentId;

    private String comment;

    private String title;

    public CommentDTO(long bookId, long commentId, String comment, String title) {
        this.bookId = bookId;
        this.commentId = commentId;
        this.comment = comment;
        this.title = title;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "bookId=" + bookId +
                ", commentId=" + commentId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
