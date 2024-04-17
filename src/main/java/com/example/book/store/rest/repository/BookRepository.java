package com.example.book.store.rest.deo;

import com.example.book.store.rest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
