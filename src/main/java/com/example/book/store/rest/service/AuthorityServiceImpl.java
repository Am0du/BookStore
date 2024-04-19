package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.repository.AuthorityRepository;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private final AuthorityRepository authorityRepository;
    private final SingleResponse<Authority> singleResponse;

    private final MultipleResponse<Authority> multipleResponse;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository,
                                SingleResponse<Authority> singleResponse, MultipleResponse<Authority> multipleResponse){
        this.singleResponse = singleResponse;
        this.authorityRepository = authorityRepository;
        this.multipleResponse = multipleResponse;

    }

    @Override
    public MultipleResponse<Authority> findAllAuthority() {

        List<Authority> response = authorityRepository.findAll();
        multipleResponse.setEntityList(response);
        multipleResponse.setSuccessfull(true);
        return multipleResponse;
    }

    @Override
    @Transactional
    public SingleResponse<Authority> setAuthority(Authority authority) {
        singleResponse.setEntity(authorityRepository.save(authority));
        singleResponse.setIssucessfull(true);
        return singleResponse;
    }

    @Override
    @Transactional
    public void deleteAuthority(Authority authority) {
        authorityRepository.deleteById((int)authority.getId());
        }
    }
