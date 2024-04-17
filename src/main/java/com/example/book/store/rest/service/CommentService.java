package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Comment;
import com.example.book.store.rest.response.SingleResponse;

public interface CommentService {

    SingleResponse<Comment> addComment(String title);

    void deleteComment(String title);
}
