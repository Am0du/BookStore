package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Comment;
import com.example.book.store.rest.exception.CommentNotFound;
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
    public SingleResponse<Comment> editComment(Comment comment) {
        Comment formerComment = commentRepository.findById((int)comment.getId()).orElse(null);

        if(formerComment != null){
            formerComment.setMessage(comment.getMessage() != null ? comment.getMessage() : formerComment.getMessage());
            singleResponse.setIssucessfull(true);
            singleResponse.setEntity(commentRepository.save(formerComment));
            return singleResponse;
        }

        throw new CommentNotFound("Comment with id " + comment.getId() + " does not exist");
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
