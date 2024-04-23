package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService{

    MultipleResponse<User> findAllUsers();
    SingleResponse<User> findUser(String email);

    SingleResponse<User> addUser(User user);

    SingleResponse<User> editUser(User user);

    SingleResponse<User> deleteUser(String email);
}
