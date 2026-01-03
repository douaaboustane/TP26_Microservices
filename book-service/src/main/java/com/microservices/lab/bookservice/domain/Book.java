package com.microservices.lab.bookservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Integer stock;

    // Default constructor
    public Book() {
    }

    public Book(String title, String author, Integer stock) {
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public void decrementStock() {
        if (this.stock == null || this.stock <= 0) {
            throw new IllegalStateException("Cannot decrement stock: stock is " + this.stock);
        }
        this.stock--;
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
}

