package com.example.book.application.service;

import com.example.book.application.port.in.AddBookUsecase;
import com.example.book.application.port.out.BookOutputPort;
import com.example.book.domain.model.Book;
import com.example.book.domain.model.vo.BookClassification;
import com.example.book.domain.model.vo.BookDesc;
import com.example.book.domain.model.vo.Location;
import com.example.book.domain.model.vo.Source;
import com.example.book.framework.httpadapter.dto.BookInfoDTO;
import com.example.book.framework.httpadapter.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddBookService implements AddBookUsecase {
    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO addBook(BookInfoDTO bookInfoDTO) {
        BookDesc desc = new BookDesc(bookInfoDTO.getDesc(), bookInfoDTO.getAuthor(), bookInfoDTO.getIsbn(),
                bookInfoDTO.getPublicationDate(), Source.valueOf(bookInfoDTO.getSource()));
        Book book = Book.enter(
                bookInfoDTO.getTitle(),
                desc,
                BookClassification.valueOf(bookInfoDTO.getClassification()),
                Location.valueOf(bookInfoDTO.getLocation())
        );

        Book saved = bookOutputPort.save(book);
        return BookOutputDTO.of(saved);
    }
}
