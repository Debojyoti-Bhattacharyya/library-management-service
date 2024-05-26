package com.example.librarymanagementservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String bookId;
    private String bookName;
    private String authorName;
    private String publishDate;
    private boolean isIssued;
}
