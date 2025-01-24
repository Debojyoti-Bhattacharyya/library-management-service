package com.example.librarymanagementservice.service;

import com.example.librarymanagementservice.exception.BookNotFoundException;
import com.example.librarymanagementservice.model.Book;
import com.example.librarymanagementservice.model.BookListResponse;
import com.example.librarymanagementservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return all books from the repository")
    void testGetAllBooks() {
        // Arrange
        List<Book> books = Arrays.asList(
                new Book("1", "Book One", "Author One", "2024-01-12", false),
                new Book("2", "Book Two", "Author Two", "2024-01-12", true)
        );
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(books));

        // Act
        Mono<BookListResponse> result = bookService.getAllBooks();

        // Assert
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(2, response.getBookList().size()))
                .verifyComplete();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return a book by its ID when found")
    void testGetBookById_Found() {
        // Arrange
        Book book = new Book("1", "Book One", "Author One", "2024-01-12", false);
        when(bookRepository.findById("1")).thenReturn(Mono.just(book));

        // Act
        Mono<Book> result = bookService.getBookById("1");

        // Assert
        StepVerifier.create(result)
                .expectNext(book)
                .verifyComplete();
        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    @DisplayName("Should throw BookNotFoundException when book by ID is not found")
    void testGetBookById_NotFound() {
        // Arrange
        when(bookRepository.findById("1")).thenReturn(Mono.empty());

        // Act
        Mono<Book> result = bookService.getBookById("1");

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof BookNotFoundException &&
                        throwable.getMessage().contains("1"))
                .verify();
        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    @DisplayName("Should return a book by its name")
    void testGetBookByName() {
        // Arrange
        Book book = new Book("1", "Book One", "Author One", "2024-01-12", false);
        when(bookRepository.findByName("Book One")).thenReturn(Mono.just(book));

        // Act
        Mono<Book> result = bookService.getBookByName("Book One");

        // Assert
        StepVerifier.create(result)
                .expectNext(book)
                .verifyComplete();
        verify(bookRepository, times(1)).findByName("Book One");
    }

    @Test
    @DisplayName("Should add a new book to the library")
    void testAddBookToLibrary() {
        // Arrange
        Book book = new Book(null, "Book One", "Author One", "2024-01-12", false);
        Book savedBook = new Book(UUID.randomUUID().toString(), "Book One", "Author One", "2024-01-12", false);
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(savedBook));

        // Act
        Mono<Book> result = bookService.addBookToLibrary(book);

        // Assert
        StepVerifier.create(result)
                .assertNext(b -> assertNotNull(b.getBookId()))
                .verifyComplete();
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("Should update the status of a book to issued")
    void testUpdateBookStatus() {
        // Arrange
        Book book = new Book("1", "Book One", "Author One", "2024-01-12", false);
        Book updatedBook = new Book("1", "Book One", "Author One", "2024-01-12", true);
        when(bookRepository.findById("1")).thenReturn(Mono.just(book));
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(updatedBook));

        // Act
        Mono<Book> result = bookService.updateBookStatus("1");

        // Assert
        StepVerifier.create(result)
                .assertNext(b -> assertTrue(b.isIssued()))
                .verifyComplete();
        verify(bookRepository, times(1)).findById("1");
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("Should throw BookNotFoundException when updating the status of a non-existent book")
    void testUpdateBookStatus_NotFound() {
        // Arrange
        when(bookRepository.findById("1")).thenReturn(Mono.empty());

        // Act
        Mono<Book> result = bookService.updateBookStatus("1");

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof BookNotFoundException &&
                        throwable.getMessage().contains("1"))
                .verify();
        verify(bookRepository, times(1)).findById("1");
        verify(bookRepository, never()).save(any(Book.class));
    }
}
