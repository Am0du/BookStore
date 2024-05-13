package com.example.book.store.rest.controller;

import com.example.book.store.rest.dto.CommentDTO;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.Comment;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.CommentNotFound;
import com.example.book.store.rest.response.UserResponse;
import com.example.book.store.rest.security.JwtTokenProvider;
import com.example.book.store.rest.service.BookService;
import com.example.book.store.rest.service.CommentService;
import com.example.book.store.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private BookService bookService;
    private CommentService commentService;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;
    private static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    public CommentController(BookService bookService, CommentService commentService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/comment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addComment(@RequestHeader("Authorization") String headerValue, @RequestBody CommentDTO commentDTO) {
        Book book = bookService.findBookById(commentDTO.getBookId());
        User user = userService.getUser(jwtTokenProvider.getEmail(headerValue.substring(7)));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(new Comment(commentDTO.getComment(), commentDTO.getTitle(), user, book)));
    }

    @PutMapping("/comment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editComment(@RequestHeader("Authorization") String headerValue, @RequestBody CommentDTO commentDTO) {
        User user = userService.getUser(jwtTokenProvider.getEmail(headerValue).substring(7));
        for (Comment userComment : user.getComments()) {
            if (userComment.getId() == commentDTO.getCommentId()) {
                userComment.setMessage(commentDTO.getComment());
                return ResponseEntity.ok(commentService.editComment(userComment));
            }
        }
        throw new CommentNotFound("User does not have comment with comment id " + commentDTO.getCommentId());
    }

    @DeleteMapping("/comment/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteComment(@RequestHeader("Authorization") String headerValue, @PathVariable long commentId){
        User user = userService.getUser(jwtTokenProvider.getEmail(headerValue).substring(7));
        for (Comment userComment : user.getComments()) {
            if (userComment.getId() == commentId) {
                commentService.deleteComment(userComment);
                return ResponseEntity.ok("Comment delete successfully");
            }
        }
        throw new CommentNotFound("User does not have comment with comment id " + commentId);
    }
}