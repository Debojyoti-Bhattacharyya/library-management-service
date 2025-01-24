package com.example.librarymanagementservice.controller;

import com.example.librarymanagementservice.model.Book;
import com.example.librarymanagementservice.model.BookListResponse;
import com.example.librarymanagementservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve a list of all books")
    void testRetrieveAllBooks() {
        // Arrange
        BookListResponse bookListResponse = new BookListResponse(List.of(
                new Book("1", "Book One", "Author One", "2024-01-12", false),
                new Book("2", "Book Two", "Author Two", "2024-01-12", true)
        ));
        when(bookService.getAllBooks()).thenReturn(Mono.just(bookListResponse));

        // Act
        Mono<BookListResponse> result = bookController.retrieveAllBooks();

        // Assert
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(2, response.getBookList().size()))
                .verifyComplete();
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    @DisplayName("Should retrieve a book by its ID")
    void testRetrieveBookById() {
        // Arrange
        Book book = new Book("1", "Book One", "Author One", "2024-01-12", false);
        when(bookService.getBookById("1")).thenReturn(Mono.just(book));

        // Act
        Mono<Book> result = bookController.retrieveBookById("1");

        // Assert
        StepVerifier.create(result)
                .assertNext(b -> assertEquals("1", b.getBookId()))
                .verifyComplete();
        verify(bookService, times(1)).getBookById("1");
    }

    @Test
    @DisplayName("Should retrieve a book by its name")
    void testRetrieveBookByName() {
        // Arrange
        Book book = new Book("1", "Book One", "Author One", "2024-01-12", false);
        when(bookService.getBookByName("Book One")).thenReturn(Mono.just(book));

        // Act
        Mono<Book> result = bookController.retrieveBookByName(book);

        // Assert
        StepVerifier.create(result)
                .assertNext(b -> assertEquals("Book One", b.getBookName()))
                .verifyComplete();
        verify(bookService, times(1)).getBookByName("Book One");
    }

    @Test
    @DisplayName("Should add a new book to the library")
    void testAddBookToLibrary() {
        // Arrange
        Book book = new Book(null, "Book One", "Author One", "2024-01-12", false);
        Book savedBook = new Book("1234", "Book One", "Author One", "2024-01-12", false);
        when(bookService.addBookToLibrary(book)).thenReturn(Mono.just(savedBook));

        // Act
        Mono<Book> result = bookController.addBookToLibrary(book);

        // Assert
        StepVerifier.create(result)
                .assertNext(b -> assertEquals("1234", b.getBookId()))
                .verifyComplete();
        verify(bookService, times(1)).addBookToLibrary(book);
    }

    @Test
    @DisplayName("Should update the status of a book")
    void testUpdateBookStatus() {
        // Arrange
        Book book = new Book("1", "Book One", "Author One", "2024-01-12", false);
        Book updatedBook = new Book("1", "Book One", "Author One", "2024-01-12", true);
        when(bookService.updateBookStatus("1")).thenReturn(Mono.just(updatedBook));

        // Act
        Mono<Book> result = bookController.updateBookStatus(book);

        // Assert
        StepVerifier.create(result)
                .assertNext(b -> assertTrue(b.isIssued()))
                .verifyComplete();
        verify(bookService, times(1)).updateBookStatus("1");
    }
}
