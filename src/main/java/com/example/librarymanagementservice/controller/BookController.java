package com.example.librarymanagementservice.controller;

import com.example.librarymanagementservice.model.Book;
import com.example.librarymanagementservice.model.BookListResponse;
import com.example.librarymanagementservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/retrieveList")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BookListResponse> retrieveAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/retrieve/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> retrieveBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/retrieve/name")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> retrieveBookByName(@RequestBody Book book) {
        return bookService.getBookByName(book.getBookName());
    }

    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> addBookToLibrary(@RequestBody Book book) {
        return bookService.addBookToLibrary(book);
    }

    @PutMapping("/updateStatus")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> updateBookStatus(@RequestBody Book book) {
        return bookService.updateBookStatus(book.getBookId());
    }
}
