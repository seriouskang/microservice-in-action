package com.example.book.application.service;

import com.example.book.application.port.in.InquiryUsecase;
import com.example.book.application.port.out.BookOutputPort;
import com.example.book.domain.model.Book;
import com.example.book.framework.httpadapter.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService implements InquiryUsecase {
    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO bookInfo(long id) {
        Book found = bookOutputPort.findById(id);
        return BookOutputDTO.of(found);
    }
}
