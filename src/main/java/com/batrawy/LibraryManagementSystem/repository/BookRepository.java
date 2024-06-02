package com.batrawy.LibraryManagementSystem.repository;

import com.batrawy.LibraryManagementSystem.model.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
