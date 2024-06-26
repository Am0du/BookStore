package com.example.book.store.rest.repository;

import com.example.book.store.rest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(String genre);
    Book findByTitle(String title);
}
