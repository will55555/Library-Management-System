package org.WTT.service;

public class BookManagement {
    private long isbn;
    private String author;
    private String title;
    private int quantity;

    public BookManagement() {
    }

    public BookManagement(long isbn, String author, String title, int quantity) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.quantity = quantity;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}