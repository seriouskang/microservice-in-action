package com.example.book.application.service;

import com.example.book.application.port.in.MakeAvailableUsecase;
import com.example.book.application.port.out.BookOutputPort;
import com.example.book.domain.model.Book;
import com.example.book.framework.httpadapter.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MakeAvailableService implements MakeAvailableUsecase {
    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO available(Long id) {
        Book found = bookOutputPort.findById(id);
        found.makeAvailable();  // dirty check

        return BookOutputDTO.of(found);
    }
}
