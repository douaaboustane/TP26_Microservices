package com.microservices.lab.bookservice.dto;

public class BookBorrowResponse {
    private Long id;
    private String title;
    private String author;
    private Integer stock;
    private Double price;

    public BookBorrowResponse() {
    }

    public BookBorrowResponse(Long id, String title, String author, Integer stock, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

