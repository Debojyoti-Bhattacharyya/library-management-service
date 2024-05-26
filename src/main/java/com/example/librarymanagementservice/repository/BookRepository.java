package com.example.librarymanagementservice.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementservice.model.Book;

import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    @Query("{bookName: ?0}")
    Mono<Book> findByName(String bookName);
}
