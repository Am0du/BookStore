package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;

public interface BookService {

    MultipleResponse<Book> findAllBooks();
    MultipleResponse<Book> findBookByGenre(String genre);
    SingleResponse<Book> addBook(Book book);

    SingleResponse<Book> findBookByTitle(String title);

    SingleResponse<Book> updateBook(Book book);

    void deleteBook(Book book);


}
