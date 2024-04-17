package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.SingleResponse;

import java.util.ArrayList;

public interface AuthorityService {

    SingleResponse<Authority> findAllauthority();

    SingleResponse<Authority> setAuthority(String role, User user);

    void deleteAuthority(String role, User user);
}
