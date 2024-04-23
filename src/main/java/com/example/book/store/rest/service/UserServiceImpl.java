package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.UserDataNotComplete;
import com.example.book.store.rest.exception.UserNotFound;
import com.example.book.store.rest.repository.UserRepository;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private MultipleResponse<User> multipleResponse;

    private SingleResponse<User> singleResponse;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(){}


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           MultipleResponse<User> multipleResponse,
                           SingleResponse<User> singleResponse,
                           PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.multipleResponse = multipleResponse;
        this.singleResponse = singleResponse;
        this.passwordEncoder = passwordEncoder;

    }

    private MultipleResponse<User> multipleResponse(List<User> user, boolean success){
        multipleResponse.setEntityList(user);
        multipleResponse.setSuccessfull(success);
        return multipleResponse;
    }

    private SingleResponse<User> singleResponse(User user, boolean success){
        singleResponse.setIssucessfull(success);
        singleResponse.setEntity(user);
        return singleResponse;
    }
    @Override
    public MultipleResponse<User> findAllUsers() {
        return multipleResponse(userRepository.findAll(), true);
    }

    @Override
    public SingleResponse<User> findUser(String email) {
        try {
            return singleResponse(userRepository.findByEmail(email), true);
        }catch (EntityNotFoundException exc){
            throw new UserNotFound("User with email address");
        }
    }

    @Override
    @Transactional
    public SingleResponse<User> addUser(User user) {
        try {
            User newUser = userRepository.save(user);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return singleResponse(newUser, true);
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
    public SingleResponse<User> editUser(User user) {
        try {
            User existingUser = userRepository.findByEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : existingUser.getFirstName());
            existingUser.setLastName(user.getLastName() != null ? user.getLastName() : existingUser.getLastName());
            existingUser.setMiddleName(user.getMiddleName() != null ? user.getMiddleName() : existingUser.getMiddleName());
            existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
            return  singleResponse(userRepository.save(existingUser), true);
        }catch (Exception exc){
            throw new UserNotFound("No user with " + user.getEmail() + "found");
        }

    }

    @Override
    @Transactional
    public SingleResponse<User> deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return singleResponse(user, true);

    }
}
