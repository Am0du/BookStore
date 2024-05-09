package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.Comment;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.SingleResponse;

public interface CommentService {

    SingleResponse<Comment> addComment(Comment comment);

    SingleResponse<Comment> editComment(Comment comment);
    void deleteComment(Comment comment);
}
