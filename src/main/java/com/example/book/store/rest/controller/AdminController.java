package com.example.book.store.rest.controller;

import com.example.book.store.rest.dto.RoleAssigningDTO;
import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.exception.UserNotFound;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.response.UserResponse;
import com.example.book.store.rest.security.JwtTokenProvider;
import com.example.book.store.rest.service.AuthorityService;
import com.example.book.store.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private final UserService userService;
    private final AuthorityService authorityService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AdminController(UserService userService, AuthorityService authorityService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/assign-role")
    public ResponseEntity<UserResponse> assignRole(@RequestBody RoleAssigningDTO roleAssigningDTO){
        Authority authority = new Authority();
        authority.setRole(roleAssigningDTO.getRole());
        return ResponseEntity.ok(userService.addAuthorityToUser(roleAssigningDTO.getEmail(), authority));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete-user-role")
    public ResponseEntity<?> deleteUserRole(@RequestBody RoleAssigningDTO roleAssigningDTO){
        UserResponse user = userService.findUser(roleAssigningDTO.getEmail());

        for(Authority authority : user.getAuthority()){
            if(authority.getRole().equals(roleAssigningDTO.getRole())) {
                authorityService.deleteAuthority(authority);
                return ResponseEntity.ok(roleAssigningDTO.getEmail() + " with role "
                        + roleAssigningDTO.getRole()
                        +" has been deleted");

            }
        }

        throw new UserNotFound(roleAssigningDTO.getEmail() + " does not have the role "+ roleAssigningDTO.getRole());

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete-user")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String headerValue){
        userService.deleteUser(jwtTokenProvider.getEmail(headerValue.substring(7)));
        return ResponseEntity.ok("User " + jwtTokenProvider.getEmail(headerValue.substring(7)) +" has been delete");
    }

}
