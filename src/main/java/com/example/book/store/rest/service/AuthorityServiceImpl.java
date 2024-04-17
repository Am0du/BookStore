package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.UserDoesNotHaveAuthority;
import com.example.book.store.rest.repository.AuthorityRepository;
import com.example.book.store.rest.response.SingleResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private final AuthorityRepository authorityRepository;
    private final SingleResponse<Authority> singleResponse;

    private Authority authority;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository,
                                SingleResponse<Authority> singleResponse, Authority authority){
        this.singleResponse = singleResponse;
        this.authorityRepository = authorityRepository;
        this.authority = authority;
    }

    @Override
    public SingleResponse<Authority> findAllauthority() {

        Authority response = (Authority) authorityRepository.findAll();
        singleResponse.setEntity(response);
        singleResponse.setIssucessfull(true);
        return singleResponse;
    }

    @Override
    @Transactional
    public SingleResponse<Authority> setAuthority(String role, User user) {
        authority.setUser(user);
        authority.setRole(role);
        singleResponse.setEntity(authorityRepository.save(authority));
        singleResponse.setIssucessfull(true);
        return singleResponse;
    }

    @Override
    public void deleteAuthority(String role, User user) {
        for(Authority auth : user.getAuthority()){
            if(role.equals(auth.getRole())){
                authorityRepository.deleteById((int)auth.getId());
            }
         throw new UserDoesNotHaveAuthority("User " + user.getEmail() + " does not have role " + role);
        }
    }


}
