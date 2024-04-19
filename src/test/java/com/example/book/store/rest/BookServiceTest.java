package com.example.book.store.rest;

import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.repository.BookRepository;
import com.example.book.store.rest.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    User user = new User("John", "Johnson", "Doe",
            "John.deo@example.com", "password", 1);

    Book book = new Book("This is a book",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt " +
                    "ut labore et dolore magna aliqua.", "genre", "www.google.com", user);

    @Test
    void findAllBooks(){
        when(bookRepository.findAll()).thenReturn(List.of(book));
        assertEquals(1, bookService.findAllBooks().getEntityList().size());

    }

    @Test
    void findBookByGenre(){
        when(bookRepository.findByGenre("genre")).thenReturn(book);
        assertEquals(1, bookService.findBookByGenre("genre").getEntityList().size());
    }

    @Test
    void addBook(){
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.addBook(book).getEntity());

    }

    @Test
    void findBookByTitle(){
        when(bookRepository.findByTitle("This is a book")).thenReturn(book);
        assertEquals(book, bookService.findBookByTitle("This is a book").getEntity());
    }

    @Test
    void updateBook(){
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.updateBook("Title is a book").getEntity());
    }

    @Test
    void deleteBook(){
        doNothing().when(bookRepository).delete(book);
        bookService.deleteBook(book);
        verify(bookRepository).delete(book);
    }

}
