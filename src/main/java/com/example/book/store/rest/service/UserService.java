package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;

import java.util.Map;

public interface UserService {

    MultipleResponse<User> findAllUsers();
    SingleResponse<User> findUser(String email);

    SingleResponse<User> addUser(User user);

    boolean deleteUser(User user);
}
