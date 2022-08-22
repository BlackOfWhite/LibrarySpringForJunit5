package com.officelibrary.library.domain;

public class BookDomain {

    private String title;
    private AuthorDomain author;

    public BookDomain(String title, AuthorDomain author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDomain getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDomain author) {
        this.author = author;
    }
}
