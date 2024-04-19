package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import jakarta.transaction.Transactional;

import java.util.ArrayList;

public interface AuthorityService {

    MultipleResponse<Authority> findAllAuthority();

    SingleResponse<Authority> setAuthority(Authority authority);

    void deleteAuthority(Authority authority);
}
