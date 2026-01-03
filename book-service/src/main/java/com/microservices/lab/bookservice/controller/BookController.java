package com.microservices.lab.bookservice.controller;

import com.microservices.lab.bookservice.domain.Book;
import com.microservices.lab.bookservice.dto.BookBorrowResponse;
import com.microservices.lab.bookservice.repository.BookRepository;
import com.microservices.lab.bookservice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<BookBorrowResponse> borrowBook(@PathVariable Long id) {
        BookBorrowResponse response = bookService.borrow(id);
        return ResponseEntity.ok(response);
    }
}

