package com.example.librarymanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementservice.model.Book;
import com.example.librarymanagementservice.model.BookListResponse;
import com.example.librarymanagementservice.service.BookService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/retriveList")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BookListResponse> retriveAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/retrive/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> retriveBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/retrive/name")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> retriveBookByName(@RequestBody Book book) {
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
