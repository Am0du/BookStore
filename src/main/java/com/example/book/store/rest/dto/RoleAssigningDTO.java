package com.example.book.store.rest.dto;

import com.example.book.store.rest.exception.InvalidRole;

public class RoleAssigningDTO {
    private String Email;
    private String prefix = "ROLE_";

    private String role;

    public RoleAssigningDTO(){}
    public RoleAssigningDTO(String email, String prefix, String role) {
        Email = email;
        this.prefix = prefix;
        this.role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        String newRole = role.toUpperCase();
        if(newRole.equals("ADMIN") || newRole.equals("MANAGER")){
            this.role = prefix.concat(newRole);
        }
        throw new InvalidRole("Role " + role + "Cannot be added, Ensure role is either 'ADMIN' or 'MANAGER'");
    }

    @Override
    public String toString() {
        return "RoleAssigningDTO{" +
                "Email='" + Email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
