package com.example.book.store.rest.entity;

import com.example.book.store.rest.repository.AuthorityRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private int active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> books;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authority;


    public User(){}

    public User(String firstName, String middle_name, String lastName,
                String email, String password, int active) {
        this.firstName = firstName;
        this.middleName = middle_name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        if(books == null) {
            books = new ArrayList<Book>();
        }
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Comment> getComments() {
        if(comments == null) {
            comments = new ArrayList<Comment>();
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Authority> getAuthority() {
        if(authority == null){
            authority = new ArrayList<Authority>();
        }
        return authority;
    }

    public void setAuthority(ArrayList<Authority> authority) {
        this.authority = authority;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middle_name='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", books=" + books +
                ", comments=" + comments +
                ", authority=" + authority  +
                '}';
    }
}
