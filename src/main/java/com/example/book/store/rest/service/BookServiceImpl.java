package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.repository.BookRepository;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    private MultipleResponse<Book> multipleResponse;

    private SingleResponse<Book> singleResponse;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           MultipleResponse<Book> multipleResponse,
                           SingleResponse<Book> singleResponse){
        this.bookRepository = bookRepository;
        this.multipleResponse = multipleResponse;
        this.singleResponse = singleResponse;
    }


    private MultipleResponse<Book> multipleResponse(List<Book> books, boolean success){
        multipleResponse.setEntityList(books);
        multipleResponse.setSuccessfull(success);
        return multipleResponse;
    }

    private SingleResponse<Book> singleResponse(Book book, boolean success){
        singleResponse.setIssucessfull(success);
        singleResponse.setEntity(book);
        return singleResponse;
    }
    @Override
    public MultipleResponse<Book> findAllBooks() {
        return multipleResponse(bookRepository.findAll(), true);

    }

    @Override
    public MultipleResponse<Book> findBookByGenre(String genre) {
        return multipleResponse(bookRepository.findByGenre(genre), true);
    }

    @Override
    public SingleResponse<Book> addBook(Book book) {
        return singleResponse(bookRepository.save(book), true);
    }

    @Override
    public SingleResponse<Book> findBookByTitle(String title) {
        return singleResponse(bookRepository.findByTitle(title), true);
    }

    @Override
    public SingleResponse<Book> updateBook(Book book) {
        return singleResponse(bookRepository.save(book), true);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

}
