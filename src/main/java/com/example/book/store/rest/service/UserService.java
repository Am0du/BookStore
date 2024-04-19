package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;

import java.util.Map;

public interface UserService {

    MultipleResponse<User> findAllUsers();
    SingleResponse<User> findUser();

    SingleResponse<User> addUser();

    SingleResponse<User> updateUserPassword(User user, String password);

    SingleResponse<User> updateUserFirstName(User user, String name);

    SingleResponse<User> updateUserSecondName(User user, String name);

    SingleResponse<User> updateUserMiddleName(User user, String name);

    SingleResponse<User> updateUserEmail(User user, String email);

    void deleteUser(String email);
}
