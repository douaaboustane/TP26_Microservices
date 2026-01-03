package com.microservices.lab.bookservice.service;

import com.microservices.lab.bookservice.client.PricingClient;
import com.microservices.lab.bookservice.domain.Book;
import com.microservices.lab.bookservice.dto.BookBorrowResponse;
import com.microservices.lab.bookservice.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final PricingClient pricingClient;

    public BookService(BookRepository bookRepository, PricingClient pricingClient) {
        this.bookRepository = bookRepository;
        this.pricingClient = pricingClient;
    }

    @Transactional
    public BookBorrowResponse borrow(Long id) {
        logger.info("Borrowing book with id: {}", id);

        // Lock the row for update to handle concurrency
        Book book = bookRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        // Decrement stock (throws exception if stock <= 0)
        book.decrementStock();

        // Save the book
        book = bookRepository.save(book);

        // Call pricing service (with resilience patterns)
        Double price = pricingClient.getPrice(id);

        return new BookBorrowResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getStock(),
                price
        );
    }
}

