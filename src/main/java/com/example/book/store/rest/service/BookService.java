package com.example.book.store.rest.service;

import com.example.book.store.rest.dto.BookDTO;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;

import java.util.Optional;

public interface BookService {

    MultipleResponse<Book> findAllBooks();
    MultipleResponse<Book> findBookByGenre(String genre);
    SingleResponse<Book> addBook(Book book);

    SingleResponse<Book> findBookByTitle(String title);

    SingleResponse<Book> updateBook(BookDTO bookDTO, Book book);

    Book findBookById(long id);

    void deleteBook(Book book);


}
