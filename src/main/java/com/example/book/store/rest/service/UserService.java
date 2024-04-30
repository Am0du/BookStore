package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService{

    MultipleResponse<User> findAllUsers();
    SingleResponse<User> findUser(String email);

    SingleResponse<User> addUser(User user);

    SingleResponse<User> editUser(User user);

    SingleResponse<User> addBookToUser(String userEmail, Book book);

    SingleResponse<User> deleteUser(String email);

    SingleResponse<User> addAuthorityToUser(String userEmail, Authority authority);
}
