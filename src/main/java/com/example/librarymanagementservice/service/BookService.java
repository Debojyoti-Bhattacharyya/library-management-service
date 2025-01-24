package com.example.librarymanagementservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementservice.exception.BookNotFoundException;
import com.example.librarymanagementservice.model.Book;
import com.example.librarymanagementservice.model.BookListResponse;
import com.example.librarymanagementservice.repository.BookRepository;

import reactor.core.publisher.Mono;

/**
 * @author Debojyoti
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Mono<BookListResponse> getAllBooks() {
        return bookRepository
                .findAll()
                .collectList()
                .map(BookListResponse::new);
    }

    public Mono<Book> getBookById(String id) {
        return bookRepository.findById(id).switchIfEmpty(Mono.error(new BookNotFoundException(id)));
    }

    public Mono<Book> getBookByName(String bookName) {
        return bookRepository.findByName(bookName);
    }

    public Mono<Book> addBookToLibrary(Book book) {
        book.setBookId(UUID.randomUUID().toString().split("-")[0]);
        return bookRepository.save(book);
    }

    public Mono<Book> updateBookStatus(String id) {
        return bookRepository.findById(id)
                .flatMap(item -> {
                    Mono.just(Book.builder()
                                    .bookId(id)
                                    .bookName(item.getBookName())
                                    .authorName(item.getAuthorName())
                                    .publishDate(item.getPublishDate())
                                    .isIssued(!item.isIssued())
                                    .build());
                    return bookRepository.save(item);
                })
                .switchIfEmpty(Mono.error(new BookNotFoundException(id)));
    }
}
