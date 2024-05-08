package com.example.book.store.rest.controller;

import com.example.book.store.rest.dto.BookDTO;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.exception.BookEditNotPermitted;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.response.UserResponse;
import com.example.book.store.rest.security.JwtTokenProvider;
import com.example.book.store.rest.service.BookService;
import com.example.book.store.rest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private UserService userService;
    private BookService bookService;

    private final JwtTokenProvider jwtTokenProvider;

    public BookController(UserService userService, BookService bookService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.bookService = bookService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("books")
    public ResponseEntity<MultipleResponse<Book>> allbooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/books/title/{bookTitle}")
    public ResponseEntity<SingleResponse<Book>> findBookWithTitle(@PathVariable String bookTitle) {
        return ResponseEntity.ok(bookService.findBookByTitle(bookTitle));
    }

    @GetMapping("/books/genre/{genre}")
    public ResponseEntity<MultipleResponse<Book>> findBooksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.findBookByGenre(genre));
    }

    @PostMapping("/books")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> addBook(@RequestBody Book book, @RequestHeader("Authorization") String headerValue) {
        book.setId(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService
                .addBookToUser(jwtTokenProvider.
                        getEmail(headerValue.substring(7)), book));
    }

    @PutMapping("/books")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SingleResponse<Book>> editBook(@RequestBody BookDTO bookDTO, @RequestHeader("Authorization") String headerValue) {
        UserResponse user = userService.findUser(jwtTokenProvider.getEmail(headerValue.substring(7)));
        for(Book book : user.getBooks()){
            if(book.getId() == bookDTO.getId())
                return ResponseEntity.ok(bookService.updateBook(bookDTO, book));
            }
        throw new BookEditNotPermitted("User "+ headerValue.substring(7) +
                "does not own the book " +
                bookDTO.getTitle() + " or book does not exist");
        }

    @DeleteMapping("/books")
    @PreAuthorize("hasRole('USER/{bookId}')")
    public ResponseEntity<?> deleteBook(@PathVariable long bookId, @RequestHeader("Authorization") String headerValue){
        UserResponse user = userService.findUser(jwtTokenProvider.getEmail(headerValue.substring(7)));
        for(Book book : user.getBooks()){
            if(book.getId() == bookId) {
                bookService.deleteBook(book);
                return ResponseEntity.ok("Book has been deleted");
            }
        }
        throw new BookEditNotPermitted("User "+ headerValue.substring(7) +
                "does not own the book or book does not exist");
    }


    }

