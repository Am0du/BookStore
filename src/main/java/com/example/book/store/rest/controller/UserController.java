package com.example.book.store.rest.controller;


import com.example.book.store.rest.dto.UserDTO;
import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.UserDoesNotHaveAuthority;
import com.example.book.store.rest.response.JwtAuthResponse;
import com.example.book.store.rest.response.MultipleResponse;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.response.UserResponse;
import com.example.book.store.rest.security.JwtTokenProvider;
import com.example.book.store.rest.security.SignIn;
import com.example.book.store.rest.security.SignUp;
import com.example.book.store.rest.service.AuthService;
import com.example.book.store.rest.service.AuthorityService;
import com.example.book.store.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    private final JwtAuthResponse jwtAuthResponse;
    private final JwtTokenProvider jwtTokenProvider;
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          AuthService authService,
                          JwtAuthResponse jwtAuthResponse,
                          JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtAuthResponse = jwtAuthResponse;


        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/users/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn){
        String token = authService.login(signIn);
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }


    @PostMapping("/users/signup")
    public ResponseEntity<?> signup(@RequestBody SignUp signup){

        User newUser = new User();

        newUser.setFirstName(signup.getFirstName());
        newUser.setMiddleName(signup.getMiddleName());
        newUser.setLastName(signup.getLastName());
        newUser.setPassword(signup.getPassword());
        newUser.setEmail(signup.getEmail());
        UserResponse user = userService.addUser(newUser);

        Authority authority = new Authority();
        authority.setRole("ROLE_USER");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addAuthorityToUser(user.getEmail(), authority));

    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/users")
    public List<UserResponse> getalluser(){
        return userService.findAllUsers();

    }

//    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/users/{email}")
    public ResponseEntity<UserResponse> user(@PathVariable String email){
        return  ResponseEntity.ok(userService.findUser(email));

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/users")
    public ResponseEntity<UserResponse> editUser(@RequestHeader("Authorization") String headerValue, @RequestBody UserDTO user){
        String email = jwtTokenProvider.getEmail(headerValue.substring(7));
        if (email.equals(user.getEmail()) || user.getEmail() == null) {
            user.setEmail(email);
            return ResponseEntity.ok(userService.editUser(user));
        }
        throw new UserDoesNotHaveAuthority("You can only change your detail");
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String headerValue){
        userService.deleteUser(jwtTokenProvider
                .getEmail(headerValue
                        .substring(7)));
        return ResponseEntity.ok("User has been deleted");
    }
}
