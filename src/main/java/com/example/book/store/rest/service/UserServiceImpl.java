package com.example.book.store.rest.service;

import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.UserDataNotComplete;
import com.example.book.store.rest.exception.UserNotFound;
import com.example.book.store.rest.repository.UserRepository;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private MultipleResponse<User> multipleResponse;

    private SingleResponse<User> singleResponse;

    public UserServiceImpl(){}


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           MultipleResponse<User> multipleResponse,
                           SingleResponse<User> singleResponse){

        this.userRepository = userRepository;
        this.multipleResponse = multipleResponse;
        this.singleResponse = singleResponse;

    }
    @Override
    public MultipleResponse<User> findAllUsers() {

        multipleResponse.setEntityList(userRepository.findAll());
        multipleResponse.setSuccessfull(true);
        return multipleResponse;
    }

    @Override
    public SingleResponse<User> findUser(String email) {
        try {
            singleResponse.setEntity(userRepository.findByEmail(email));
            singleResponse.setIssucessfull(true);
            return singleResponse;
        }catch (EntityNotFoundException exc){
            throw new UserNotFound("User with email address");
        }
    }

    @Override
    @Transactional
    public SingleResponse<User> addUser(User user) {
        try {
            User newUser = userRepository.save(user);
            singleResponse.setEntity(newUser);
            singleResponse.setIssucessfull(true);
            return singleResponse;
        }catch (Exception  exc){
            throw new UserDataNotComplete(exc.getMessage());
        }
    }


    @Override
    @Transactional
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;

    }
}
