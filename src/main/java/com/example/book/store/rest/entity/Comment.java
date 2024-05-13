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

    @Column(name = "title")
    private String title;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment(){}

    public Comment(String message,String title, User user, Book book) {
        this.message = message;
        this.title = title;
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

    public String getBookTitle() {
        return book.getTitle();
    }

    public long getBookId() {
        return book.getId();
    }

    public String getBookDescription() {
        return book.getDescription();
    }

    public String getBookPurchaseLink(){
        return book.getPurchase_link();
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
