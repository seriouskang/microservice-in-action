package com.example.book.framework.jpaadapter;

import com.example.book.application.port.out.BookOutputPort;
import com.example.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookAdapter implements BookOutputPort {
    private final BookRepository bookRepository;

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
