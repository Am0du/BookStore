package com.example.book.store.rest.service;

import com.example.book.store.rest.dto.BookDTO;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.exception.BookAlreadyExist;
import com.example.book.store.rest.exception.BookDoesNotExist;
import com.example.book.store.rest.repository.BookRepository;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<Book> book = bookRepository.findByGenre(genre);
        if (!book.isEmpty())
            return multipleResponse(book, true);

        throw new BookDoesNotExist("Books with "+ genre + " does not exist");
    }

    @Override
    public SingleResponse<Book> addBook(Book book) {
        try{
            return singleResponse(bookRepository.save(book), true);
        }catch (DataIntegrityViolationException ex){
            throw new BookAlreadyExist("Book with title "+ book.getTitle() +" already exist.");
        }
    }

    @Override
    public SingleResponse<Book> findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book != null)
            return singleResponse(book, true);

        throw new BookDoesNotExist("Book " + title + " Does not Exist");
    }

    @Override
    public SingleResponse<Book> updateBook(BookDTO bookDTO, Book book) {
        book.setTitle(bookDTO.getTitle() != null ? bookDTO.getTitle() : book.getTitle());
        book.setDescription(bookDTO.getDescription() != null ? bookDTO.getDescription() : book.getDescription());
        book.setGenre(bookDTO.getGenre() != null ? bookDTO.getGenre() : book.getGenre());
        book.setPurchase_link(bookDTO.getPurchase_link() != null ? bookDTO.getPurchase_link() : book.getPurchase_link());
        return singleResponse(bookRepository.save(book), true);
    }

    @Override
    public Book findBookById(long id) {
        Book book = bookRepository.findById((int) id).orElse(null);
        if(book != null)
            return book;
        throw new BookDoesNotExist("Book with id " + id + " does not exist.");
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

}
