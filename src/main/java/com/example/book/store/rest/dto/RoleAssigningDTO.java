package com.example.book.store.rest.dto;

import com.example.book.store.rest.exception.InvalidRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleAssigningDTO {
    private String email;
    private String prefix = "ROLE_";

    private String role;

    private static Logger LOGGER = LoggerFactory.getLogger(RoleAssigningDTO.class);
    public RoleAssigningDTO(){}
    public RoleAssigningDTO(String email, String prefix, String role) {
        this.email = email;
        this.prefix = prefix;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        String newRole = role.toUpperCase();
        if (newRole.equals("MANAGER") || newRole.equals("ADMIN")) {
            this.role = prefix.concat(newRole);
        } else {
            throw new InvalidRole("Role " + newRole + " Cannot be added, Ensure role is either 'ADMIN' or 'MANAGER'");

        }
    }
    @Override
    public String toString() {
        return "RoleAssigningDTO{" +
                "Email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
