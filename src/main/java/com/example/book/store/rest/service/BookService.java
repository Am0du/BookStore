package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;

public interface BookService {

    MultipleResponse<Book> findAllBooks();
    MultipleResponse<Book> findBookByGenre();
    SingleResponse<Book> addBook();

    SingleResponse<Book> findBookByTitle(String query);

    SingleResponse<Book> updateBook(String title);


}
