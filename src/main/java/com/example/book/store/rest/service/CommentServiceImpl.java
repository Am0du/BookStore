package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Comment;
import com.example.book.store.rest.repository.CommentRepository;
import com.example.book.store.rest.response.SingleResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private SingleResponse<Comment> singleResponse;

    private CommentRepository commentRepository;

    public CommentServiceImpl(SingleResponse<Comment> singleResponse, CommentRepository commentRepository){
        this.singleResponse = singleResponse;
        this.commentRepository = commentRepository;
    }


    @Override
    public SingleResponse<Comment> addComment(Comment comment) {
        singleResponse.setEntity(commentRepository.save(comment));
        singleResponse.setIssucessfull(true);
        return singleResponse;
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
