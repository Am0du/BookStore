package com.example.book.store.rest;


import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.Errorhandler;
import com.example.book.store.rest.repository.AuthorityRepository;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.service.AuthorityService;
import com.example.book.store.rest.service.AuthorityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorityServiceTest {

    @Autowired
    private AuthorityService authorityService;

    @Autowired

    @MockBean
    private AuthorityRepository authorityRepository;

    @MockBean
    private Errorhandler errorhandler;


    @Test
    void getAllAuthority(){
        when(authorityRepository.findAll()).thenReturn(Stream.
                of(new Authority("ROLE_admin", new User("John", "Johnson", "Doe",
                        "John.deo@example.com", "password", 1))).collect(Collectors.toList()));
        assertEquals(1, authorityService.findAllAuthority().getEntityList().size());
    }

    @Test
    void setAuthority(){

        Authority expectedAuthority = new Authority("ROLE_admin", new User("John", "Johnson", "Doe",
                "John.deo@example.com", "password", 1));
        when(authorityRepository.save(expectedAuthority)).thenReturn(expectedAuthority);

        SingleResponse<Authority> resultAuthority =  authorityService.setAuthority(expectedAuthority);

        assertEquals(expectedAuthority, resultAuthority.getEntity());
    }

    @Test
    void deleteAuthority() {
        User user = new User("John", "Johnson", "Doe",
                "John.deo@example.com", "password", 1);

        Authority expectedAuthority = new Authority("ROLE_admin", user);


        doNothing().when(authorityRepository).deleteById((int) expectedAuthority.getId());
        authorityService.deleteAuthority(expectedAuthority);

        verify(authorityRepository).deleteById((int)expectedAuthority.getId());
    }
}
