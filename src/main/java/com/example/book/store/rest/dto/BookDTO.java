package com.example.book.store.rest.dto;

public class BookDTO {

    private long id;

    private String title;

    private String description;

    private String genre;

    private String purchase_link;

    public BookDTO(long id, String title, String description, String genre, String purchase_link) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.purchase_link = purchase_link;
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

    @Override
    public String toString() {
        return "bookDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", purchase_link='" + purchase_link + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
