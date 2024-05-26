package com.example.librarymanagementservice.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String id) {
        super("Book with id : " + id + " not found!");
    }
}
