package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.UserDataNotComplete;
import com.example.book.store.rest.exception.UserNotFound;
import com.example.book.store.rest.repository.UserRepository;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private SingleResponse<User> singleResponse;

    private PasswordEncoder passwordEncoder;

    private UserResponse userResponse;

    public UserServiceImpl(){}


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           SingleResponse<User> singleResponse,
                           PasswordEncoder passwordEncoder, UserResponse userResponse){

        this.userRepository = userRepository;
        this.singleResponse = singleResponse;
        this.passwordEncoder = passwordEncoder;
        this.userResponse = userResponse;
    }

    private SingleResponse<User> singleResponse(User user, boolean success){
        singleResponse.setIssucessfull(success);
        singleResponse.setEntity(user);
        return singleResponse;
    }

    private UserResponse userResponse(User user){
        userResponse.setFirstName(user.getFirstName());
        userResponse.setMiddleName(user.getMiddleName());
        userResponse.setLastName(user.getLastName());
        userResponse.setAuthority(user.getAuthority());
        userResponse.setBooks(user.getBooks());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setComments(user.getComments());
        userResponse.setActive(user.getActive());
        return userResponse;
    }

    private List<UserResponse> userResponseMultipleResponse(List<User> users) {
        List <UserResponse> userResponses = new ArrayList<UserResponse>();
        for(User user: users) {
            userResponse.setFirstName(user.getFirstName());
            userResponse.setMiddleName(user.getMiddleName());
            userResponse.setLastName(user.getLastName());
            userResponse.setAuthority(user.getAuthority());
            userResponse.setBooks(user.getBooks());
            userResponse.setEmail(user.getEmail());
            userResponse.setId(user.getId());
            userResponse.setComments(user.getComments());
            userResponse.setActive(user.getActive());

            userResponses.add(userResponse);
        }

        return userResponses;
    }
    private String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userResponseMultipleResponse(userRepository.findAll());
    }

    @Override
    public UserResponse findUser(String email) {
        try {
            User user = userRepository.findByEmail(email);

            return userResponse(userRepository.findByEmail(email));
        }catch (EntityNotFoundException exc){
            throw new UserNotFound("User with email address");
        }
    }

    @Override
    @Transactional
    public UserResponse addUser(User user) {
        try {
            User newUser = userRepository.save(user);
            newUser.setPassword(encryptPassword(newUser.getPassword()));
            return userResponse(newUser);
        }catch (Exception  exc){
            throw new UserDataNotComplete(exc.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getAuthority()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getUserEmail())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse editUser(User user) {
        try {
            User existingUser = userRepository.findByEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : existingUser.getFirstName());
            existingUser.setLastName(user.getLastName() != null ? user.getLastName() : existingUser.getLastName());
            existingUser.setMiddleName(user.getMiddleName() != null ? user.getMiddleName() : existingUser.getMiddleName());
            existingUser.setPassword(user.getPassword() != null ? encryptPassword(user.getPassword()) : existingUser.getPassword());
            return  userResponse(userRepository.save(existingUser));
        }catch (Exception exc){
            throw new UserNotFound("No user with " + user.getEmail() + "found");
        }

    }

    @Override
    @Transactional
    public UserResponse addAuthorityToUser(String userEmail, Authority authority) {
        User user = userRepository.findByEmail(userEmail);
        authority.setUser(user);
        user.getAuthority().add(authority);
        userRepository.save(user);
        return userResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse addBookToUser(String userEmail, Book book) {
        User user = userRepository.findByEmail(userEmail);
        book.setUser(user);
        user.getBooks().add(book);
        userRepository.save(user);
        return userResponse(userRepository.save(user));
    }



    @Override
    @Transactional
    public SingleResponse<User> deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return singleResponse(user, true);

    }
}
