package com.example.book.store.rest;

import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.Comment;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.repository.CommentRepository;
import com.example.book.store.rest.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CommentServiceTest {

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    User user = new User("John", "Johnson", "Doe",
            "John.deo@example.com", "password", 1);

    Book book = new Book("This is a book",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt " +
                    "ut labore et dolore magna aliqua.", "genre", "www.google.com", user);


    Comment comment = new Comment("Awesome book", user, book);

    @Test
    void addComment(){
        when(commentRepository.save(comment)).thenReturn(comment);
        assertEquals(comment, commentService.addComment(comment).getEntity());

    }

    @Test
    void deleteComment(){
        doNothing().when(commentRepository).delete(comment);
        commentService.deleteComment(comment);
        verify(commentRepository).delete(comment);
    }





}
