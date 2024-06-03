package com.batrawy.LibraryManagementSystem.service.impl;

import com.batrawy.LibraryManagementSystem.model.dto.BookRequest;
import com.batrawy.LibraryManagementSystem.model.dto.BookResponse;
import com.batrawy.LibraryManagementSystem.model.entity.Book;
import com.batrawy.LibraryManagementSystem.repository.BookRepository;
import com.batrawy.LibraryManagementSystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public ResponseEntity<String> addBook(BookRequest bookRequest) {
        try {
            if(isValidBook(bookRequest)){
                Book book = createNewBook(bookRequest);
                bookRepository.save(book);
                return ResponseEntity.status(HttpStatus.OK).body("Book Added Successfully");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Book createNewBook(BookRequest bookRequest) {
        return new Book(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getIsbn());
    }

    private boolean isValidBook(BookRequest bookRequest) {
        return bookRequest.getTitle() != null && bookRequest.getAuthor() != null && bookRequest.getIsbn() != null;
    }

    @Override
    public ResponseEntity<String> deleteBook(Long id) {
        try {
            if(idExist(id)){
                bookRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Book deleted Successfully");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean idExist(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public ResponseEntity<String> updateBook(Long id, BookRequest bookRequest) {
        try {
            if(idExist(id)){
                if(isValidBook(bookRequest)){

                    Book book = getBook(id);
                    bookRepository.save(updateBookDetails(book, bookRequest));
                    return ResponseEntity.status(HttpStatus.OK).body("Book Updated Successfully");
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
                }
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Book updateBookDetails(Book book, BookRequest bookRequest) {
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        return book;
    }

    private Book getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return book;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<BookResponse> bookResponses = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {bookResponses.add(new BookResponse(book.getTitle(), book.getAuthor(), book.getIsbn()));});
        return bookResponses;
    }

    @Override
    public ResponseEntity<BookResponse> getBookById(Long id) {
        try {
            if(idExist(id)){
                Book book = getBook(id);
                return ResponseEntity.status(HttpStatus.OK).body(new BookResponse(book.getTitle(), book.getAuthor(), book.getIsbn()));
            }else{
                throw new RuntimeException("Something went wrong");            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
