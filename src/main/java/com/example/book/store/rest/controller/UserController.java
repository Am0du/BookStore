package com.example.book.store.rest.controller;


import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.security.SignIn;
import com.example.book.store.rest.security.SignUp;
import com.example.book.store.rest.service.AuthorityService;
import com.example.book.store.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private  final AuthorityService authorityService;

    private AuthenticationManager authenticationManager;


    @Autowired
    public UserController(UserService userService, AuthorityService authorityService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.authorityService = authorityService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/users/signin")
    public ResponseEntity<?> signIn(@RequestBody SignIn signIn){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signIn.getEmail(), signIn.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("User signed-in successfully!.");
    }

    @PostMapping("/users/signup")
    public ResponseEntity<?> signup(@RequestBody SignUp signup){

        User newUser = new User();

        newUser.setFirstName(signup.getFirstName());
        newUser.setMiddleName(signup.getMiddleName());
        newUser.setLastName(signup.getLastName());
        newUser.setPassword(signup.getPassword());
        newUser.setEmail(signup.getEmail());
        User user = userService.addUser(newUser).getEntity();

        Authority authority = new Authority();
        authority.setRole("ROLE_USER");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addAuthorityToUser(user.getEmail(), authority));

    }


    @GetMapping("/users")
    public MultipleResponse<User> getalluser(){
        return userService.findAllUsers();

    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody String email, String password){
//            userService.loadUserByUsername()
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("fdkbfdhbdf");
//    }

    @GetMapping("/users/{email}")
    public ResponseEntity<SingleResponse<User>> user(@PathVariable String email){
        return  ResponseEntity.ok(userService.findUser(email));

    }


    @PostMapping("/users")
    public ResponseEntity<SingleResponse<User>> adduser(@RequestBody User user) throws InterruptedException {
        user.setId(0);
       SingleResponse<User> newUser = userService.addUser(user);
       Authority authority = new Authority();
       authority.setRole("ROLE_Admin");


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addAuthorityToUser(user.getEmail(), authority));

    }

    @PutMapping("/users")
    public ResponseEntity<SingleResponse<User>> editUser(@RequestBody User user){
        return ResponseEntity.ok(userService.editUser(user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<SingleResponse<User>> deleteUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
