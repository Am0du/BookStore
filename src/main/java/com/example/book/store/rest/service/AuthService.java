package com.example.book.store.rest.service;

import com.example.book.store.rest.security.SignIn;

public interface AuthService {

    String login(SignIn signin);

}