package com.example.librarymanagementservice.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListResponse {
    private final String requestId = UUID.randomUUID().toString();
    private List<Book> bookList;
}
