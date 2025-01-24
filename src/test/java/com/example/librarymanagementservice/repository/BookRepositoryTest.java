package com.example.librarymanagementservice.repository;

import com.example.librarymanagementservice.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        // Clean up the collection before each test
        bookRepository.deleteAll()
                .then(bookRepository.save(new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", "2024-01-12", false)))
                .then(bookRepository.save(new Book("2", "To Kill a Mockingbird", "Harper Lee", "2024-01-14", false)))
                .block(); // Block here to ensure data setup completes before tests
    }

    @Test
    @DisplayName("Verify when book found in search result")
    void testFindByName_Found() {
        Mono<Book> bookMono = bookRepository.findByName("The Great Gatsby");

        StepVerifier.create(bookMono)
                .assertNext(book -> {
                    assertNotNull(book);
                    assertEquals("The Great Gatsby", book.getBookName());
                    assertEquals("F. Scott Fitzgerald", book.getAuthorName());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Verify when book not found in search result")
    void testFindByName_NotFound() {
        Mono<Book> bookMono = bookRepository.findByName("Nonexistent Book");

        StepVerifier.create(bookMono)
                .expectNextCount(0) // No book should be emitted
                .verifyComplete();
    }
}
