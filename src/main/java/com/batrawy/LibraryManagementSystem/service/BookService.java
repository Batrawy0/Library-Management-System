package com.batrawy.LibraryManagementSystem.service;

import com.batrawy.LibraryManagementSystem.model.dto.BookRequest;
import com.batrawy.LibraryManagementSystem.model.dto.BookResponse;
import com.batrawy.LibraryManagementSystem.model.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    public ResponseEntity<String> addBook(BookRequest bookRequest);

    public ResponseEntity<String> deleteBook(Long id);

    public ResponseEntity<String> updateBook(Long id, BookRequest bookRequest);

    public ResponseEntity<List<BookResponse>> getAllBooks();

    public ResponseEntity<?> getBookById(Long id);


}
