package com.example.book.store.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment(){}

    public Comment(String message, User user, Book book) {
        this.message = message;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public User getUser() {
//        return user;
//    }

    public String getUserFirstName() {
        return user.getFirstName();
    }

    public String getUserMiddleName() {
        return user.getMiddleName();
    }

    public String getUserLastName() {
        return user.getLastName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", user=" + user +
                ", book=" + book+
                '}';
    }
}
