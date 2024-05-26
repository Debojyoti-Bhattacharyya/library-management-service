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
                .map(books -> new BookListResponse(books));
    }

    public Mono<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> getBookByName(String bookName) {
        return bookRepository.findByName(bookName);
    }

    public Mono<Book> addBookToLibrary(Book book) {
        book.setBookId(UUID.randomUUID().toString().split("-")[0]);
        return bookRepository.save(book);
    }

    public Mono<Book> updateBookStatus(String id) {
        Mono<Book> updatedBookMono = bookRepository.findById(id)
                .flatMap(item -> Mono.just(
                        Book
                                .builder()
                                .bookId(id)
                                .bookName(item.getBookName())
                                .authorName(item.getAuthorName())
                                .publishDate(item.getPublishDate())
                                .isIssued(!item.isIssued())
                                .build()))
                .switchIfEmpty(Mono.error(new BookNotFoundException(id)));
        return bookRepository.saveAll(updatedBookMono).next();
    }
}
