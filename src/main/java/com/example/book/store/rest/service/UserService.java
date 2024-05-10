package com.example.book.store.rest.service;

import com.example.book.store.rest.dto.UserDTO;
import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.response.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<UserResponse> findAllUsers();
    UserResponse findUser(String email);

    UserResponse addUser(User user);

    UserResponse editUser(UserDTO user);

    UserResponse addBookToUser(String userEmail, Book book);

    SingleResponse<User> deleteUser(String email);

    User getUser(String email);

    UserResponse addAuthorityToUser(String userEmail, Authority authority);
}
