package com.example.demo.Entity;


import java.util.List;

public class BookEntityBuilder {
    private Integer id;
    private String bookname;
    private String isbn;
    private String author;
    private List<UserEntity> users;

   BookEntityBuilder() {
    }

    public BookEntityBuilder id(final Integer id) {
        this.id = id;
        return this;
    }

    public BookEntityBuilder bookname(final String bookname) {
        this.bookname = bookname;
        return this;
    }

    public BookEntityBuilder isbn(final String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookEntityBuilder author(final String author) {
        this.author = author;
        return this;
    }

    public BookEntityBuilder users(final List<UserEntity> users) {
        this.users = users;
        return this;
    }

    public BookEntity build() {
        return new BookEntity(this.id, this.bookname, this.isbn, this.author, this.users);
    }

    public String toString() {
        return "BookEntity.BookEntityBuilder(id=" + this.id + ", bookname=" + this.bookname + ", isbn=" + this.isbn + ", author=" + this.author + ", users=" + this.users + ")";
    }
}
