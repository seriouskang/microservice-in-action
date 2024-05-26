package com.example.book.application.port.out;

import com.example.book.domain.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutputPort {
    Book findById(long id);
    Book save(Book book);
}
