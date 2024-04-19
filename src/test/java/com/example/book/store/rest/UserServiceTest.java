package com.example.book.store.rest;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.repository.UserRepository;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user = new User("John", "Johnson", "Doe",
            "John.deo@example.com", "password", 1);

    @Test
    void addUser(){
       when(userRepository.save(user)).thenReturn(user);
        SingleResponse<User> newUser = userService.addUser(user);

        assertEquals(user, newUser.getEntity());


    }

    @Test
    void updateUser(){
        user.setFirstName("Joe");
        when(userRepository.save(user)).thenReturn(user);
        SingleResponse<User> newUser = userService.addUser(user);
        assertEquals(user, newUser.getEntity());

    }

    @Test
    void deleteUser(){
        doNothing().when(userRepository).delete(user);
        userService.deleteUser(user);
        verify(userRepository).delete(user);

    }

    @Test
    void findUser(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        assertEquals(user, userService.findUser(user.getEmail()).getEntity());
    }

    @Test
    void findAllUser(){

        when(userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(1, userService.findAllUsers().getEntityList().size());


    }

}
