package com.example.book.store.rest.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "purchase_link")
    private String purchase_link;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy ="book")
    private List<Comment> comment;

    public Book(){}

    public Book(long id, String title, String description, String genre, String purchase_link, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.purchase_link = purchase_link;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPurchase_link() {
        return purchase_link;
    }

    public void setPurchase_link(String purchase_link) {
        this.purchase_link = purchase_link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", purchase_link='" + purchase_link + '\'' +
                ", user=" + user +
                '}';
    }
}
