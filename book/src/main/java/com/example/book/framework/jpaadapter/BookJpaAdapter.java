package com.example.book.framework.jpaadapter;

import com.example.book.application.port.out.BookOutputPort;
import com.example.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookJpaAdapter implements BookOutputPort {
    private final BookJpaRepository bookJpaRepository;

    @Override
    public Book findById(long id) {
        return bookJpaRepository.findById(id).get();
    }

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }
}
