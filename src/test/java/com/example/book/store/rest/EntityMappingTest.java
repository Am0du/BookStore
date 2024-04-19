package com.example.book.store.rest;


import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.repository.AuthorityRepository;
import com.example.book.store.rest.repository.BookRepository;
import com.example.book.store.rest.repository.UserRepository;
import com.example.book.store.rest.service.AuthorityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EntityMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityServiceImpl authorityService;

    @Test
    void testEntityMappings() {
        // Create a user
        User user = new User();
        user.setFirstName("John");
        user.setMiddleName("johnson");
        user.setLastName("Doe");
        user.setEmail("john.di0e@example.com");
        user.setPassword("password");
        user.setActive(1);

        // Save the user to the database
        userRepository.save(user);

        // Create a book
        Book book = new Book("This is a book", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt " + "ut labore et dolore magna aliqua.", "genre", "www.google.com", user);
        book.setTitle("Sample Book");
        book.setDescription("Sample Description");
        book.setGenre("Sample Genre");
        book.setUser(user);

        // Save the book to the database
        bookRepository.save(book);

        // Create an authority
        Authority authority = new Authority();
        authority.setRole("ROLE_ADMIN");
        authority.setUser(user);

        // Save the authority to the database
        authorityRepository.save(authority);

        // Retrieve the user from the database and verify mappings
        User savedUser = userRepository.findById((int) user.getId()).orElse(null);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getBooks());
        assertNotNull(savedUser.getAuthority());

    }
}
