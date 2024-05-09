package com.example.book.store.rest.dto;

public class CommentDTO {

    private long bookId;

    private long commentId;

    private String comment;

    public CommentDTO(long bookId, long commentId, String comment) {
        this.bookId = bookId;
        this.commentId = commentId;
        this.comment = comment;
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

    @Override
    public String toString() {
        return "CommentDTO{" +
                "bookId=" + bookId +
                ", commentId=" + commentId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
