package com.batrawy.LibraryManagementSystem.controller;

import com.batrawy.LibraryManagementSystem.model.dto.BookRequest;
import com.batrawy.LibraryManagementSystem.model.dto.BookResponse;
import com.batrawy.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/books/")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest){
        return bookService.addBook(bookRequest);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest){
        return bookService.updateBook(id , bookRequest);
    }

    @GetMapping("/all")
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
}
