package com.batrawy.LibraryManagementSystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String title;
    private String author;
    private String isbn;

    /*public BookResponse(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }*/
}
