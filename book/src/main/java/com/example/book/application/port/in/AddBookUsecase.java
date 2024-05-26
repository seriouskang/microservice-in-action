package com.example.book.application.port.in;

import com.example.book.framework.httpadapter.dto.BookInfoDTO;
import com.example.book.framework.httpadapter.dto.BookOutputDTO;

public interface AddBookUsecase {
    BookOutputDTO addBook(BookInfoDTO bookInfoDTO);
}
